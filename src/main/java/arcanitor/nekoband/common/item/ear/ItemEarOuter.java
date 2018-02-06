package arcanitor.nekoband.common.item.ear;

import arcanitor.nekoband.common.item.ItemNekoband;
import net.minecraft.util.ResourceLocation;

public class ItemEarOuter extends ItemNekoband {
    public final ResourceLocation texture;
    public ItemEarOuter(String name, ResourceLocation texture) {
        super(name);
        this.texture = texture;
    }
}
