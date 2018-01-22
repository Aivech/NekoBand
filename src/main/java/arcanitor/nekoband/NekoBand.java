package arcanitor.nekoband;

import arcanitor.nekoband.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = NekoBand.MODID,name = NekoBand.NAME,version = NekoBand.VERSION,useMetadata = true)
public class NekoBand {
    public static final String MODID = "nekoband";
    public static final String NAME = "NekoBand";
    public static final String VERSION = "0.0.1";

    @Mod.Instance
    public static NekoBand instance;

    @SidedProxy(clientSide = "arcanitor.nekoband.proxy.ClientProxy",serverSide = "arcanitor.nekoband.proxy.ServerProxy")
    public static CommonProxy proxy;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        logger = e.getModLog();
        proxy.preInit(e);
    }
}
