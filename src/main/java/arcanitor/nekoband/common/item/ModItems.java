package arcanitor.nekoband.common.item;

import arcanitor.nekoband.api.HeadbandBase;
import arcanitor.nekoband.common.NekoBand;
import arcanitor.nekoband.common.item.ear.ItemEarInner;
import arcanitor.nekoband.common.item.ear.ItemEarOuter;
import arcanitor.nekoband.common.item.elemental.HeadbandEarth;
import arcanitor.nekoband.common.item.elemental.HeadbandFire;
import arcanitor.nekoband.common.item.elemental.HeadbandIce;
import arcanitor.nekoband.common.item.elemental.HeadbandLightning;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static arcanitor.nekoband.common.NekoBandKt.MODID;

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

    public static ItemEarInner earInnerDyeable;
    public static ItemEarOuter earOuterDyeable;

    @SubscribeEvent
    public static void initItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().registerAll(
                itemHeadband = new ItemHeadband("headband"),

                //headbandEarth = new HeadbandEarth(),
                //headbandLightning = new HeadbandLightning(),
                //headbandFire = new HeadbandFire(),
                //headbandIce = new HeadbandIce(),

                earOuterDyeable = new ItemEarOuter(
                        "ear_primary_dyeable",
                        new ResourceLocation(MODID,"item/ears/cat_ears_dyeable"),
                        true
                ),
                earInnerDyeable = new ItemEarInner(
                        "ear_secondary_dyeable",
                        new ResourceLocation(MODID,"item/ears/cat_ears_dyeable"),
                        true
                    )
        );

        HeadbandBase.addBase(
                new ItemStack(Items.LEATHER_HELMET),
                new ResourceLocation(MODID,"item/band/leather")
        );
        HeadbandBase.addBase(
                new ItemStack(Items.IRON_HELMET),
                new ResourceLocation(MODID,"item/band/iron")
        );
        HeadbandBase.addBase(
                new ItemStack(Items.GOLDEN_HELMET),
                new ResourceLocation(MODID,"item/band/gold")
        );
        HeadbandBase.addBase(
                new ItemStack(Items.DIAMOND_HELMET),
                new ResourceLocation(MODID,"item/band/diamond")
        );
    }

    @SideOnly(Side.CLIENT)
    public static void initItemModels() {
        itemHeadband.initItemModel();

        //headbandLightning.initItemModel();
    }


}