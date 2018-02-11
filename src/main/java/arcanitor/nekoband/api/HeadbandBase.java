package arcanitor.nekoband.api;

import arcanitor.nekoband.common.NekoBand;
import arcanitor.nekoband.common.item.Headband;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class HeadbandBase {
    /**
     * Add a helmet as a valid band base
     * Should be done immediately after registering the items
     * The itemstack must be of an ItemArmor for the head slot
     *
     * @see arcanitor.nekoband.common.item.ModItems#initItems(RegistryEvent.Register)
     *
     * @param helmet    The ItemStack to be added as a base
     * @param texture   Headband texture
     *
     * @return  true if the operation was successful
     */
    public static boolean addBase(ItemStack helmet, ResourceLocation texture) {
        if (helmet.getItem() instanceof ItemArmor && helmet.getItem().getEquipmentSlot(helmet) == EntityEquipmentSlot.HEAD) {
            Headband.addValidBase(new HeadbandBase(helmet,texture));
            return true;
        }
        NekoBand.log.error("Itemstack: "+helmet.toString()+" is not an ItemArmor helmet");
        return false;
    }



    private final ItemStack item;
    private final ResourceLocation texture;

    private HeadbandBase(ItemStack item, ResourceLocation texture) {
        this.item = item;
        this.texture = texture;
    }

    public ResourceLocation getTexture() { return this.texture; }
    public ItemStack getItemStack() { return this.item; }
}
