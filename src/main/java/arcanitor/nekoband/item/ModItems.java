package arcanitor.nekoband.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {


    @GameRegistry.ObjectHolder("nekoband:hairband")
    public static ItemHairband itemHairband;


    public static final CreativeTabs NEKOTAB = new CreativeTabs("NEKOTAB") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(itemHairband);
        }
    };
}
