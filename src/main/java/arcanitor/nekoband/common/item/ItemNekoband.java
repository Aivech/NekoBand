package arcanitor.nekoband.common.item;

import arcanitor.nekoband.common.NekoBand;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import static arcanitor.nekoband.common.NekoBandKt.MODID;

public class ItemNekoband extends Item {
    public ItemNekoband(String name) {
        this.setRegistryName(new ResourceLocation(MODID,name));
        this.setUnlocalizedName(MODID+":"+name);
    }
}
