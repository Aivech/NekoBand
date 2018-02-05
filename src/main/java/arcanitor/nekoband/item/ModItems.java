package arcanitor.nekoband.item;

import arcanitor.nekoband.NekoBand;
import arcanitor.nekoband.item.elemental.HeadbandEarth;
import arcanitor.nekoband.item.elemental.HeadbandFire;
import arcanitor.nekoband.item.elemental.HeadbandIce;
import arcanitor.nekoband.item.elemental.HeadbandLightning;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public class ModItems {
    public static final TabNekoband NEKOTAB = new TabNekoband("NEKOTAB");

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