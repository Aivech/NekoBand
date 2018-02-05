package arcanitor.nekoband.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;

public class TabNekoband extends CreativeTabs {
    private NonNullList<ItemStack> displayItems = NonNullList.create();

    public TabNekoband(String label) {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModItems.itemHeadband);
    }

    @Override
    public void displayAllRelevantItems(NonNullList<ItemStack> items) {
        items.addAll(0,displayItems);
    }

    public void addToTab(@Nonnull ItemStack item) {
        displayItems.add(item);
    }

    public void addAllToTab(ItemStack... items) {
        for (ItemStack item : items) {
            if (item != null) displayItems.add(item);
        }
    }
}
