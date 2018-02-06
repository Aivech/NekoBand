package arcanitor.nekoband.api;

import arcanitor.nekoband.common.item.Headband;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class HeadbandBase {
    /**
     * Add an item as a valid band base
     * Should be done during FMLInitializationEvent
     *
     * @param item      The ItemStack to be added as a base
     * @param texture   Headband texture
     */
    public static void addBase(@Nonnull ItemStack item, ResourceLocation texture) {
        Headband.addValidBase(new HeadbandBase(item,texture));
    }



    private final ItemStack item;
    private final ResourceLocation texture;

    private HeadbandBase(ItemStack item, ResourceLocation texture) {
        this.item = item;
        this.texture = texture;
    }

    public ResourceLocation getTexture() { return this.texture; }
    public ItemStack getItem() { return this.item; }
}
