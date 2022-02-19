package teksturepako.saltwaterpatcher;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(
    modid = SaltWaterPatcher.MOD_ID,
    name = SaltWaterPatcher.MOD_NAME,
    version = SaltWaterPatcher.VERSION,
    dependencies = SaltWaterPatcher.DEPENDENCIES
)
public class SaltWaterPatcher {

    public static final String MOD_ID = "saltwaterpatcher";
    public static final String MOD_NAME = "SaltWaterPatcher";
    public static final String VERSION = "1.1";
    public static final String DEPENDENCIES = "after:biomesoplenty;after:simpledifficulty;after:dynamictreesbop;required-after:mixinbooter";

    @Mod.Instance
    public static SaltWaterPatcher INSTANCE;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {}

    @Mod.EventHandler
    public void onLoadComplete(FMLLoadCompleteEvent event) {}

}
