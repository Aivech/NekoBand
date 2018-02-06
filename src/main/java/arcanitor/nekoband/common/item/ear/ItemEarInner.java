package arcanitor.nekoband.common.item.ear;

import arcanitor.nekoband.common.item.ItemNekoband;
import net.minecraft.util.ResourceLocation;

public class ItemEarInner extends ItemNekoband implements IEar {
    public final ResourceLocation texture;
    private final boolean dyeable;

    public ItemEarInner(String name, ResourceLocation texture, boolean dyeable) {
        super(name);
        this.texture = texture;
        this.dyeable = dyeable;
    }

    public ItemEarInner(String name, ResourceLocation texture) {
        this(name, texture, false);
    }


    @Override
    public boolean isDyeable() {
        return dyeable;
    }
}
