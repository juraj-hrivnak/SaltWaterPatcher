package teksturepako.saltwaterpatcher.mixins.bop;

import biomesoplenty.common.block.BlockBOPDecoration;
import biomesoplenty.common.block.BlockBOPSeaweed;
import biomesoplenty.common.block.IBOPBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockBOPSeaweed.class)
public abstract class MixinBlockSeaweed extends BlockBOPDecoration implements IBOPBlock {
    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        entityIn.motionX = entityIn.motionX / 1.1;
        entityIn.motionY = entityIn.motionY / 1.1;
        entityIn.motionZ = entityIn.motionZ / 1.1;
    }
}