package teksturepako.saltwaterpatcher.mixins.late.bop;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.common.block.BlockBOPDecoration;
import biomesoplenty.common.block.BlockBOPFlower;
import biomesoplenty.common.block.BlockBOPGrass;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.common.Optional;
import org.spongepowered.asm.mixin.Mixin;
import vazkii.botania.api.item.IHornHarvestable;

@Optional.Interface(iface = "vazkii.botania.api.item.IHornHarvestable", modid = "botania")
@Mixin(BlockBOPFlower.class)
public abstract class MixinBlockFlower extends BlockBOPDecoration implements IShearable, IHornHarvestable {
    public IProperty variantProperty;

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
        IBlockState groundState = world.getBlockState(pos.down());
        Block groundBlock = groundState.getBlock();

        boolean onFertile = groundBlock == Blocks.DIRT ||
                groundBlock == Blocks.FARMLAND ||
                groundBlock == BOPBlocks.farmland_0 ||
                groundBlock == BOPBlocks.farmland_1 ||
                groundBlock == BOPBlocks.dirt ||
                groundBlock == Blocks.GRASS ||
                groundState.getMaterial() == Material.GRASS;

        boolean onDry = groundBlock == Blocks.HARDENED_CLAY || groundBlock == BOPBlocks.sand || groundBlock == Blocks.SAND || groundBlock == BOPBlocks.white_sand;
        boolean onNetherrack = groundBlock == Blocks.NETHERRACK;
        boolean onStone = groundBlock == Blocks.STONE;
        boolean onDriedSand = groundBlock == BOPBlocks.dried_sand;
        boolean onSpectralMoss = false;
        if (groundBlock instanceof BlockBOPGrass) {
            switch ((BlockBOPGrass.BOPGrassType) groundState.getValue(BlockBOPGrass.VARIANT)) {
                case SPECTRAL_MOSS:
                    onSpectralMoss = true;
                    break;
                case OVERGROWN_NETHERRACK:
                    onFertile = true;
                    onNetherrack = true;
                    break;
                case MYCELIAL_NETHERRACK:
                    onNetherrack = true;
                    break;
                case LOAMY:
                case SANDY:
                case SILTY:
                case ORIGIN:
                default:
                    onFertile = true;
            }
        }

        switch ((BOPFlowers) state.getValue(this.variantProperty)) {
            case BURNING_BLOSSOM:
                return onNetherrack;
            case ENDERLOTUS:
                return onSpectralMoss;
            case BROMELIAD:
                return onDry;
            case PINK_HIBISCUS:
            case LILY_OF_THE_VALLEY:
            case LAVENDER:
            case GOLDENROD:
            case GLOWFLOWER:
            case DEATHBLOOM:
            default:
                return onFertile;
            case WILTED_LILY:
                return onDriedSand;
            case MINERS_DELIGHT:
                return onStone;
        }
    }

}
