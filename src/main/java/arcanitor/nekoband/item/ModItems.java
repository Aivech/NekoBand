package arcanitor.nekoband.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {


    @GameRegistry.ObjectHolder("nekoband:headband")
    public static ItemHeadband itemHeadband;


    public static final CreativeTabs NEKOTAB = new CreativeTabs("NEKOTAB") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(itemHeadband);
        }
    };

    @SideOnly(Side.CLIENT)
    public static void initItemModels() {
        itemHeadband.initItemModel();
    }
}
