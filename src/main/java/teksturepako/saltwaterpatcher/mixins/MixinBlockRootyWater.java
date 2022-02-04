package teksturepako.saltwaterpatcher.mixins;

import com.charles445.simpledifficulty.api.SDFluids;
import com.ferreusveritas.dynamictrees.blocks.BlockRooty;
import com.ferreusveritas.dynamictrees.blocks.BlockRootyWater;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockRootyWater.class)
public class MixinBlockRootyWater extends BlockRooty {

    public MixinBlockRootyWater(String name, Material material, boolean isTileEntity) {
        super(name, material, isTileEntity);
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        worldIn.setBlockState(pos, SDFluids.blockPurifiedWater.getDefaultState());
    }

    @Override
    public IBlockState getMimic(IBlockAccess access, BlockPos pos) {
        return SDFluids.blockPurifiedWater.getDefaultState();
    }

    @Override
    public IBlockState getDecayBlockState(IBlockAccess access, BlockPos pos) {
        return SDFluids.blockPurifiedWater.getDefaultState();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(World world, BlockPos pos, IBlockState state, Entity entity, Vec3d originalColor, float partialTicks) {
        return super.getFogColor(world, pos, SDFluids.blockPurifiedWater.getDefaultState(), entity, originalColor, partialTicks);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        int y = fromPos.getY() - pos.getY();
        if (y < 1) {
            IBlockState newState = worldIn.getBlockState(fromPos);
            if (this.canFlowInto(worldIn, fromPos, newState)) {
                newState.getBlock().dropBlockAsItem(worldIn, pos, newState, 0);
                worldIn.setBlockState(fromPos, SDFluids.blockPurifiedWater.getDefaultState().withProperty(BlockLiquid.LEVEL, 7), 2);
            }
        }
    }

    @Shadow
    private boolean canFlowInto(World worldIn, BlockPos pos, IBlockState state) {
        return false;
    }
}
