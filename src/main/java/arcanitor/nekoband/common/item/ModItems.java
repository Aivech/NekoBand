package arcanitor.nekoband.common.item;

import arcanitor.nekoband.common.item.elemental.HeadbandEarth;
import arcanitor.nekoband.common.item.elemental.HeadbandFire;
import arcanitor.nekoband.common.item.elemental.HeadbandIce;
import arcanitor.nekoband.common.item.elemental.HeadbandLightning;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class ModItems {

    public static final TabNekoband NEKOTAB = new TabNekoband("NEKOTAB");
    /*public static final CreativeTabs NEKOTAB = new CreativeTabs("NEKOTAB") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(itemHeadband);
        }
    };*/

    public static ItemHeadband itemHeadband;

    public static HeadbandEarth headbandEarth;
    public static HeadbandLightning headbandLightning;
    public static HeadbandFire headbandFire;
    public static HeadbandIce headbandIce;

    @SubscribeEvent
    public static void initItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().registerAll(
                itemHeadband = new ItemHeadband("headband"),
                headbandEarth = new HeadbandEarth(),
                headbandLightning = new HeadbandLightning(),
                headbandFire = new HeadbandFire(),
                headbandIce = new HeadbandIce()
        );
    }

    @SideOnly(Side.CLIENT)
    public static void initItemModels() {
        itemHeadband.initItemModel();

        headbandLightning.initItemModel();
    }


}