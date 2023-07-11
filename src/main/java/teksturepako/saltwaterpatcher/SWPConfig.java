package teksturepako.saltwaterpatcher;

import net.minecraftforge.common.config.Config;

@Config(modid = SWP.MOD_ID)
public class SWPConfig {
    @Config.Comment("Whether to load vanilla related mixins.")
    @Config.Name("Load vanilla mixins")
    public static boolean loadVanillaMixins = true;

    @Config.Comment("Whether to load mod related mixins.")
    @Config.Name("Load mod mixins")
    public static boolean loadModMixins = true;
}
