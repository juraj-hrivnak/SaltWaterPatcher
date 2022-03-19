package teksturepako.saltwaterpatcher.mixins.dt;

import com.charles445.simpledifficulty.api.SDFluids;
import com.ferreusveritas.dynamictrees.api.IPreGenFeature;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenMudHole;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.util.SafeChunkBounds;
import com.ferreusveritas.dynamictrees.worldgen.JoCode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FeatureGenMudHole.class)
public abstract class MixinFeatureGenMudHole implements IPreGenFeature {

    protected IBlockState mud;

    public void FeatureGenMudHole(IBlockState mud) {
        this.mud = mud;
    }

    @Override
    public BlockPos preGeneration(World world, BlockPos rootPos, Species species, int radius, EnumFacing facing, SafeChunkBounds safeBounds, JoCode joCode) {

        int seaLevel = world.getSeaLevel() - 1;

        IBlockState water = SDFluids.blockPurifiedWater.getDefaultState();

        if (rootPos.getY() == seaLevel) {
            for (int z = -2; z <= 2; z++) {
                for (int x = -2; x <= 2; x++) {
                    BlockPos offPos = rootPos.add(new Vec3i(x, 0, z));
                    if (safeBounds.inBounds(offPos, false)) {
                        int sqrDist = x * x + z * z;
                        if (sqrDist < (1.75f + world.rand.nextFloat())) {
                            world.setBlockState(offPos.down(2), mud);
                            world.setBlockState(offPos.down(1), mud);
                            if (sqrDist < (1.5f + world.rand.nextFloat())) {
                                world.setBlockState(offPos, water);
                            }
                        }
                    }
                }
            }
        }

        return rootPos;
    }
}