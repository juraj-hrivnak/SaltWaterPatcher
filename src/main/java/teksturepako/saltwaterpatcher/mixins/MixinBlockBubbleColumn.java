package teksturepako.saltwaterpatcher.mixins;

import com.fuzs.aquaacrobatics.block.BlockBubbleColumn;
import com.fuzs.aquaacrobatics.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockBubbleColumn.class)
public abstract class MixinBlockBubbleColumn extends BlockStaticLiquid {

    protected MixinBlockBubbleColumn(Material materialIn) {
        super(materialIn);
    }

    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!this.isValidPosition(worldIn, pos)) {
            worldIn.setBlockState(pos, REGISTRY.getObject(new ResourceLocation("simpledifficulty", "saltwater")).getDefaultState());
            return;
        }
        if (fromPos.up().equals(pos)) {
            worldIn.setBlockState(pos, CommonProxy.BUBBLE_COLUMN.getDefaultState().withProperty(DRAG, getDrag(worldIn, fromPos)), Constants.BlockFlags.SEND_TO_CLIENTS);
        } else if (fromPos.down().equals(pos) && worldIn.getBlockState(fromPos).getBlock() != this && canHoldBubbleColumn(worldIn, fromPos)) {
            worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
        }
        if(fromPos.getX() != pos.getX() || fromPos.getZ() != pos.getZ())
            super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
    }

    @Final
    @Shadow
    public static final PropertyBool DRAG = PropertyBool.create("drag"); /* true: upwards, false: downwards */

    @Shadow
    public abstract boolean isValidPosition(World worldIn, BlockPos pos);

    @Shadow
    public static boolean canHoldBubbleColumn(World world, BlockPos pos) {
        if(world.provider.doesWaterVaporize())
            return false;
        IBlockState self = world.getBlockState(pos);
        if(self.getMaterial() != Material.WATER)
            return false;
        if(!(self.getBlock() instanceof BlockLiquid))
            return false;
        return self.getValue(LEVEL) == 0;
    }

    @Shadow
    private static boolean getDrag(IBlockAccess p_203157_0_, BlockPos p_203157_1_) {
        IBlockState iblockstate = p_203157_0_.getBlockState(p_203157_1_);
        Block block = iblockstate.getBlock();
        if (block == CommonProxy.BUBBLE_COLUMN) {
            return iblockstate.getValue(DRAG);
        } else {
            return block == Blocks.SOUL_SAND;
        }
    }
}
