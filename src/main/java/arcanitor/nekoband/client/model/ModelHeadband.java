package arcanitor.nekoband.client.model;

import net.minecraft.client.model.ModelRenderer;

public class ModelHeadband extends ModelArmorBase {
    private final ModelRenderer headband;
    private final ModelRenderer earR;
    private final ModelRenderer earL;

    public ModelHeadband() {
        this.headband = new ModelRenderer(this,0,0);
        this.headband.setRotationPoint(0.0F,0.0F,0.0F);
        this.headband.addBox(0,0,0,1,1,1);

        this.earR = new ModelRenderer(this,0,0);

        this.earL = new ModelRenderer(this,0,0);
        this.earL.mirror = true;
    }
}
