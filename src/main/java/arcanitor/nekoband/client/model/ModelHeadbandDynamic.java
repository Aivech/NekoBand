package arcanitor.nekoband.client.model;

import arcanitor.nekoband.api.HeadbandBase;
import arcanitor.nekoband.common.NekoBand;
import arcanitor.nekoband.common.item.Headband;
import com.google.common.collect.ImmutableSet;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;

import java.util.Collection;
import java.util.function.Function;

public class ModelHeadbandDynamic implements IModel {
    public static final IModel MODEL = new ModelHeadbandDynamic();

    private static final ResourceLocation bandDefault = new ResourceLocation(NekoBand.MODID,"item/band/headband_dyeable");
    private static final ResourceLocation outerEarDefault = new ResourceLocation(NekoBand.MODID,"item/ears/cat_ears_dyeable");

    @Override
    public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        return null;
    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        ImmutableSet.Builder<ResourceLocation> builder = ImmutableSet.builder();

        //base textures - magic
        for (HeadbandBase base : Headband.bases.values()) builder.add(base.getTexture());

        //dyeable base
        builder.add(new ResourceLocation(NekoBand.MODID,"item/band/dyeable"));

        //ear textures - more magic
        builder.addAll(Headband.getEarTextures());

        //even more magic
        return builder.build();
    }
}
