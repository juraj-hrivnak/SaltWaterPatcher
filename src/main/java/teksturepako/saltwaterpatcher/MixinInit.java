package teksturepako.saltwaterpatcher;

import org.spongepowered.asm.mixin.Mixins;
import zone.rong.mixinbooter.MixinLoader;

@MixinLoader
public class MixinInit {
    public MixinInit() {
        Mixins.addConfiguration("mixins.saltwaterpatcher.json");
    }
}
