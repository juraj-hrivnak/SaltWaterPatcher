package teksturepako.saltwaterpatcher.mixins.late.bop;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.ISustainsPlantType;
import biomesoplenty.common.util.block.BlockQuery;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockQuery.BlockPosQuerySustainsPlantType.class)
public abstract class MixinBlockQuery implements IBlockPosQuery {
    private EnumPlantType plantType;

    public void BlockPosQuerySustainsPlantType(EnumPlantType plantType) {
        this.plantType = plantType;
    }

    @Override
    public boolean matches(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        if (block instanceof ISustainsPlantType) {
            return ((ISustainsPlantType) block).canSustainPlantType(world, pos, this.plantType);
        } else {
            switch (this.plantType) {
                case Desert:
                    return block == Blocks.SAND || block == Blocks.HARDENED_CLAY || block == Blocks.STAINED_HARDENED_CLAY || block == Blocks.DIRT || block == BOPBlocks.dirt || block == BOPBlocks.white_sand;
                case Nether:
                    return block == Blocks.SOUL_SAND || state == BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.OVERGROWN_NETHERRACK);
                case Crop:
                    return block == Blocks.FARMLAND || block == BOPBlocks.farmland_0 || block == BOPBlocks.farmland_1;
                case Cave:
                    return block.isSideSolid(state, world, pos, EnumFacing.UP);
                case Plains:
                    return state.getMaterial() == Material.GRASS || block == Blocks.GRASS || state == BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.LOAMY) || state == BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SILTY) || state == BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SANDY) || state == BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.DAISY) || block == Blocks.DIRT || block == BOPBlocks.dirt || block == Blocks.FARMLAND || block == BOPBlocks.farmland_0 || block == BOPBlocks.farmland_1 || block == Blocks.MYCELIUM;
                case Water:
                    return state.getMaterial() == Material.WATER && state.getValue(BlockLiquid.LEVEL) == 0;
                case Beach:
                    boolean isBeach = block == Blocks.GRASS || state == BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.LOAMY) || state == BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SILTY) || state == BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SANDY) || state == BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.DAISY) || block == Blocks.DIRT || block == BOPBlocks.dirt || block == BOPBlocks.white_sand || block == Blocks.SAND || block == Blocks.MYCELIUM;
                    boolean hasWater = world.getBlockState(pos.east()).getMaterial() == Material.WATER || world.getBlockState(pos.west()).getMaterial() == Material.WATER || world.getBlockState(pos.north()).getMaterial() == Material.WATER || world.getBlockState(pos.south()).getMaterial() == Material.WATER;
                    return isBeach && hasWater;
                default:
                    return false;
            }
        }
    }

}