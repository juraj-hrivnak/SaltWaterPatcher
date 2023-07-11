package teksturepako.saltwaterpatcher;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

import static teksturepako.saltwaterpatcher.SWP.*;

public class SWPContainer extends DummyModContainer {
    public SWPContainer() {
        super(new ModMetadata());
        ModMetadata meta = this.getMetadata();
        meta.modId = MOD_ID + "core";
        meta.name = MOD_NAME + " Core";
        meta.description = "Core functionality of " + MOD_NAME;
        meta.version = VERSION;
        meta.authorList.add("TeksturePako");
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register(this);
        return true;
    }
}