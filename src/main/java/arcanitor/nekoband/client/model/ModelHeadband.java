package arcanitor.nekoband.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.Nonnull;

public class ModelHeadband extends ModelArmorBase {
    private final ModelRenderer headband;

    private final ModelRenderer earRBack;
    private final ModelRenderer earRBackTip;
    private final ModelRenderer earROutsideBottom;
    private final ModelRenderer earROutside;
    private final ModelRenderer earROutsideTip;
    private final ModelRenderer earRTip;
    private final ModelRenderer earRInsideTip;
    private final ModelRenderer earRInside;

    private final ModelRenderer earLBack;
    private final ModelRenderer earLBackTip;
    private final ModelRenderer earLOutsideBottom;
    private final ModelRenderer earLOutside;
    private final ModelRenderer earLOutsideTip;
    private final ModelRenderer earLTip;
    private final ModelRenderer earLInsideTip;
    private final ModelRenderer earLInside;

    public ModelHeadband() {
        this.textureHeight = 32;
        this.textureWidth = 64;

        float s = -0.1f;

        //PSA: DO NOT USE NEGATIVE VALUES FOR BOX DIMENSIONS. Negative offsets are OK, just not negative dimensions.
        //width: px from center to left, offx: move origin to left x px
        //height: px down from bottom of head
        //depth: px backwards from center
        this.headband = new ModelRenderer(this,0,0);
        this.headband.setRotationPoint(0.0F,0.0F,0.0F);
        this.headband.addBox(-5,-9,-1,10,8,2,0.01f);

        this.earRBack = new ModelRenderer(this,0,10);
        this.earRBack.setRotationPoint(0.0F,0.0F,0.0F);
        this.earRBack.addBox(-6,-10,-0.01f,3,2,1,s);
        this.earRBackTip = new ModelRenderer(this,0,10);
        this.earRBackTip.setRotationPoint(0.0F,0.0F,0.0F);
        this.earRBackTip.addBox(-5,-11,-0.01f,1,1,1,s);
        this.earROutsideBottom = new ModelRenderer(this,0,10);
        this.earROutsideBottom.setRotationPoint(0.0F,0.0F,0.0F);
        this.earROutsideBottom.addBox(-6,-8,-1.01f,1,1,1,s);//ear rim bottom
        this.earROutside = new ModelRenderer(this,0,10);
        this.earROutside.setRotationPoint(0.0F,0.0F,0.0F);
        this.earROutside.addBox(-7,-10,-1.01f,1,2,1,s);//ear rim outside
        this.earROutsideTip = new ModelRenderer(this,0,10);
        this.earROutsideTip.setRotationPoint(0.0F,0.0F,0.0F);
        this.earROutsideTip.addBox(-6,-11,-1.01f,1,1,1,s);//ear rim outside tip
        this.earRTip = new ModelRenderer(this,0,10);
        this.earRTip.setRotationPoint(0.0F,0.0F,0.0F);
        this.earRTip.addBox(-5,-12,-1.01f,1,1,1,s);//ear rim tip
        this.earRInsideTip = new ModelRenderer(this,0,10);
        this.earRInsideTip.setRotationPoint(0.0F,0.0F,0.0F);
        this.earRInsideTip.addBox(-4,-11,-1.01f,1,1,1,s);//ear rim inside tip
        this.earRInside = new ModelRenderer(this,0,10);
        this.earRInside.setRotationPoint(0.0F,0.0F,0.0F);
        this.earRInside.addBox(-3,-10,-1.01f,1,1,1,s);//ear rim inside

        this.earLBack = new ModelRenderer(this,0,10);
        this.earLBack.mirror = true;
        this.earLBack.setRotationPoint(0.0F,0.0F,0.0F);
        this.earLBack.addBox(3,-10,-0.01f,3,2,1,s);//ear back
        this.earLBackTip = new ModelRenderer(this,0,10);
        this.earLBackTip.mirror = true;
        this.earLBackTip.setRotationPoint(0.0F,0.0F,0.0F);
        this.earLBackTip.addBox(4,-11,-0.01f,1,1,1,s);//ear back top
        this.earLOutsideBottom = new ModelRenderer(this,0,10);
        this.earLOutsideBottom.mirror = true;
        this.earLOutsideBottom.setRotationPoint(0.0F,0.0F,0.0F);
        this.earLOutsideBottom.addBox(5,-8,-1.01f,1,1,1,s);//ear rim bottom
        this.earLOutside = new ModelRenderer(this,0,10);
        this.earLOutside.mirror = true;
        this.earLOutside.setRotationPoint(0.0F,0.0F,0.0F);
        this.earLOutside.addBox(6,-10,-1.01f,1,2,1,s);//ear rim outside
        this.earLOutsideTip = new ModelRenderer(this,0,10);
        this.earLOutsideTip.mirror = true;
        this.earLOutsideTip.setRotationPoint(0.0F,0.0F,0.0F);
        this.earLOutsideTip.addBox(5,-11,-1.01f,1,1,1,s);//ear rim outside tip
        this.earLTip = new ModelRenderer(this,0,10);
        this.earLTip.mirror = true;
        this.earLTip.setRotationPoint(0.0F,0.0F,0.0F);
        this.earLTip.addBox(4,-12,-1.01f,1,1,1,s);//ear rim tip
        this.earLInsideTip = new ModelRenderer(this,0,10);
        this.earLInsideTip.mirror = true;
        this.earLInsideTip.setRotationPoint(0.0F,0.0F,0.0F);
        this.earLInsideTip.addBox(3,-11,-1.01f,1,1,1,s);//ear rim inside tip
        this.earLInside = new ModelRenderer(this,0,10);
        this.earLInside.mirror = true;
        this.earLInside.setRotationPoint(0.0F,0.0F,0.0F);
        this.earLInside.addBox(2,-10,-1.01f,1,1,1,s);//ear rim inside


        headband.addChild(earRBack);
        headband.addChild(earLBack);

        earRBack.addChild(earRBackTip);
        earRBack.addChild(earROutsideBottom);
        earRBack.addChild(earROutside);
        earRBack.addChild(earROutsideTip);
        earRBack.addChild(earLTip);
        earRBack.addChild(earRInsideTip);
        earRBack.addChild(earLInside);

        earLBack.addChild(earLBackTip);
        earLBack.addChild(earLOutsideBottom);
        earLBack.addChild(earLOutside);
        earLBack.addChild(earLOutsideTip);
        earLBack.addChild(earLTip);
        earLBack.addChild(earLInsideTip);
        earLBack.addChild(earLInside);
    }

    @Override
    public void render(@Nonnull Entity entity,float limbSwing, float limbSwingAmt, float age, float netHeadYaw, float headPitch, float scale) {
        headband.showModel = true;
        bipedHeadwear.showModel = false;

        bipedHead = headband;

        super.render(entity,limbSwing,limbSwingAmt,age,netHeadYaw,headPitch,scale);
    }
}
