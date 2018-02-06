package arcanitor.nekoband.common.item.ear;

import arcanitor.nekoband.common.item.Headband;
import arcanitor.nekoband.common.item.ItemNekoband;
import net.minecraft.util.ResourceLocation;

public class ItemEarOuter extends ItemNekoband implements IEar {
    public final ResourceLocation texture;
    private final boolean isDyeable;

    public ItemEarOuter(String name, ResourceLocation texture, boolean canDye) {
        super(name);
        this.texture = texture;
        Headband.addEarTexture(texture);
        this.isDyeable = canDye;
    }

    public ItemEarOuter(String name, ResourceLocation texture) {
        this(name,texture,false);
    }

    @Override
    public boolean isDyeable() { return isDyeable; }
}
