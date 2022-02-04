package teksturepako.saltwaterpatcher.mixins;

import com.charles445.simpledifficulty.api.SDFluids;
import com.ferreusveritas.dynamictrees.ModBlocks;
import com.ferreusveritas.dynamictrees.systems.DirtHelper;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModBlocks.class)
public abstract class MixinModBlocks {

    // Add Simple Difficulty fluids as acceptable soils
    @Inject(method = "register(Lnet/minecraftforge/event/RegistryEvent$Register;)V", at = @At("TAIL"), remap = false)
    private static void register(RegistryEvent.Register<Block> event, CallbackInfo ci) {
        DirtHelper.registerSoil(SDFluids.blockPurifiedWater, "waterlike");
    }
}