package arcanitor.nekoband.item;

import arcanitor.nekoband.NekoBand;
import arcanitor.nekoband.item.elemental.HeadbandLightning;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {


    @GameRegistry.ObjectHolder(NekoBand.MODID+":headband")
    public static ItemHeadband itemHeadband;

    @GameRegistry.ObjectHolder(NekoBand.MODID+":headband_lightning")
    public static HeadbandLightning headbandLightning;


    public static final CreativeTabs NEKOTAB = new CreativeTabs("NEKOTAB") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(itemHeadband);
        }
    };

    @SideOnly(Side.CLIENT)
    public static void initItemModels() {
        headbandLightning.initItemModel();
        itemHeadband.initItemModel();
    }


}
