package teksturepako.saltwaterpatcher;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(
        modid = SWP.MOD_ID,
        name = SWP.MOD_NAME,
        version = SWP.VERSION,
        dependencies = SWP.DEPENDENCIES
)
public class SWP {

    public static final String MOD_ID = "saltwaterpatcher";
    public static final String MOD_NAME = "SaltWaterPatcher";
    public static final String VERSION = "1.5";
    public static final String DEPENDENCIES = "required-after:mixinbooter;"
            + "after:simpledifficulty;"
            + "after:biomesoplenty;"
            + "after:dynamictrees;"
            + "after:dynamictreesbop;";

    @Mod.Instance
    public static SWP INSTANCE;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    }

    @Mod.EventHandler
    public void onLoadComplete(FMLLoadCompleteEvent event) {
    }

}
