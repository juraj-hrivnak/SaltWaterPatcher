package teksturepako.saltwaterpatcher.mixins.bop;

import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.block.BlockBOPDecoration;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockBOPCoral.class)
public abstract class MixinBlockCoral extends BlockBOPDecoration {
    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        entityIn.motionX = entityIn.motionX / 1.1;
        entityIn.motionY = entityIn.motionY / 1.1;
        entityIn.motionZ = entityIn.motionZ / 1.1;
    }
}