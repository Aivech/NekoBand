package arcanitor.nekoband.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.Nonnull;

public class ModelHeadband extends ModelArmorBase {
    private final ModelRenderer headband;
    private final ModelRenderer earBack;
    private final ModelRenderer earBackTip;
    private final ModelRenderer earOutsideBottom;
    private final ModelRenderer earOutside;
    private final ModelRenderer earOutsideTip;
    private final ModelRenderer earTip;
    private final ModelRenderer earInsideTip;
    private final ModelRenderer earInside;

    public ModelHeadband() {
        this.textureHeight = 16;
        this.textureWidth = 32;

        //PSA: DO NOT USE NEGATIVE VALUES FOR BOX DIMENSIONS. Negative offsets are OK, just not negative dimensions.
        //width: px from center to left, offx: move origin to left x px
        //height: px down from bottom of head
        //depth: px backwards from center
        this.headband = new ModelRenderer(this,0,0);
        this.headband.setRotationPoint(0.0F,0.0F,0.0F);
        this.headband.addBox(-5,-9,-1,10,8,2,0);

        this.earBack = new ModelRenderer(this,0,10);
        this.earBack.setRotationPoint(0,0,0);
        this.earBack.addBox(-6,-10,-0.01f,3,2,1);
        this.earBack.addBox(3,-10,-0.01f,3,2,1,true);

        this.earBackTip = new ModelRenderer(this,0,13);
        this.earBackTip.setRotationPoint(0.0F,0.0F,0.0F);
        this.earBackTip.addBox(-5,-11,-0.01f,1,1,1);
        this.earBackTip.addBox(4,-11,-0.01f,1,1,1,true);

        this.earOutsideBottom = new ModelRenderer(this,12,10);
        this.earOutsideBottom.setRotationPoint(0.0F,0.0F,0.0F);
        this.earOutsideBottom.addBox(-6,-8,-1.01f,1,1,1);
        this.earOutsideBottom.addBox(5,-8,-1.01f,1,1,1,true);

        this.earOutside = new ModelRenderer(this,8,10);
        this.earOutside.setRotationPoint(0.0F,0.0F,0.0F);
        this.earOutside.addBox(-7,-10,-1.01f,1,2,1);
        this.earOutside.addBox(6,-10,-1.01f,1,2,1,true);

        this.earOutsideTip = new ModelRenderer(this,4,13);
        this.earOutsideTip.setRotationPoint(0.0F,0.0F,0.0F);
        this.earOutsideTip.addBox(-6,-11,-1.01f,1,1,1);
        this.earOutsideTip.addBox(5,-11,-1.01f,1,1,1,true);

        this.earTip = new ModelRenderer(this,8,13);
        this.earTip.setRotationPoint(0.0F,0.0F,0.0F);
        this.earTip.addBox(-5,-12,-1.01f,1,1,1);
        this.earTip.addBox(4,-12,-1.01f,1,1,1,true);

        this.earInsideTip = new ModelRenderer(this,12,13);
        this.earInsideTip.setRotationPoint(0.0F,0.0F,0.0F);
        this.earInsideTip.addBox(-4,-11,-1.01f,1,1,1);
        this.earInsideTip.addBox(3,-11,-1.01f,1,1,1,true);

        this.earInside = new ModelRenderer(this,16,10);
        this.earInside.setRotationPoint(0.0F,0.0F,0.0F);
        this.earInside.addBox(-3,-10,-1.01f,1,1,1);
        this.earInside.addBox(2,-10,-1.01f,1,1,1,true);

        headband.addChild(earBack);

        earBack.addChild(earBackTip);
        earBack.addChild(earOutsideBottom);
        earBack.addChild(earOutside);
        earBack.addChild(earOutsideTip);
        earBack.addChild(earTip);
        earBack.addChild(earInsideTip);
        earBack.addChild(earInside);
    }

    @Override
    public void render(@Nonnull Entity entity,float limbSwing, float limbSwingAmt, float age, float netHeadYaw, float headPitch, float scale) {
        headband.showModel = true;
        bipedHeadwear.showModel = false;

        bipedHead = headband;

        super.render(entity,limbSwing,limbSwingAmt,age,netHeadYaw,headPitch,scale);
    }
}
