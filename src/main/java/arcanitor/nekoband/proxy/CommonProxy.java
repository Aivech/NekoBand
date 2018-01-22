package arcanitor.nekoband.proxy;

import arcanitor.nekoband.item.ItemHeadband;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {}

    @SubscribeEvent
    public static void initItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().register(new ItemHeadband("headband"));
    }

}
