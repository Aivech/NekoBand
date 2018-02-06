package arcanitor.nekoband.common.item;

import arcanitor.nekoband.common.NekoBand;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemNekoband extends Item {
    public ItemNekoband(String name) {
        this.setRegistryName(new ResourceLocation(NekoBand.MODID,name));
        this.setUnlocalizedName(NekoBand.MODID+":"+name);
    }
}
