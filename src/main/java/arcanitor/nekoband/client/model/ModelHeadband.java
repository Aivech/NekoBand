package arcanitor.nekoband.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.Nonnull;

public class ModelHeadband extends ModelArmorBase {
    private final ModelRenderer headband;
    private final ModelRenderer earR;
    private final ModelRenderer earL;

    public ModelHeadband() {
        this.textureHeight = 32;
        this.textureWidth = 64;

        float s = 0.01f;

        //width: px from center to left, offx: move origin to left x px
        //height: px down from bottom of head
        //depth: px backwards from center
        this.headband = new ModelRenderer(this,0,0);
        this.headband.setRotationPoint(0.0F,0.0F,0.0F);
        this.headband.addBox(-5,-9,-1,10,8,2,0.01f);

        this.earR = new ModelRenderer(this,0,0);
        this.earR.setRotationPoint(0.0F,0.0F,0.0F);
        this.earR.addBox(-6,-10,-0.01f,3,2,1,s);//ear back
        this.earR.addBox(-5,-11,-0.01f,1,1,1,s);//ear back top
        this.earR.addBox(-6,-8,-1.01f,1,1,1,s);//ear rim bottom
        this.earR.addBox(-7,-10,-1.01f,1,2,1,s);//ear rim outside
        this.earR.addBox(-6,-11,-1.01f,1,1,1,s);//ear rim outside tip
        this.earR.addBox(-5,-12,-1.01f,1,1,1,s);//ear rim tip
        this.earR.addBox(-4,-11,-1.01f,1,1,1,s);//ear rim inside tip
        this.earR.addBox(-3,-10,-1.01f,1,1,1,s);//ear rim inside

        this.earL = new ModelRenderer(this,0,0);
        this.earL.mirror = true;
        this.earL.setRotationPoint(0.0F,0.0F,0.0F);
        this.earL.addBox(3,-10,-0.01f,3,2,1,s);//ear back
        this.earL.addBox(4,-11,-0.01f,1,1,1,s);//ear back top
        this.earL.addBox(5,-8,-1.01f,1,1,1,s);//ear rim bottom
        this.earL.addBox(6,-10,-1.01f,1,2,1,s);//ear rim outside
        this.earL.addBox(5,-11,-1.01f,1,1,1,s);//ear rim outside tip
        this.earL.addBox(4,-12,-1.01f,1,1,1,s);//ear rim tip
        this.earL.addBox(3,-11,-1.01f,1,1,1,s);//ear rim inside tip
        this.earL.addBox(2,-10,-1.01f,1,1,1,s);//ear rim inside


        this.headband.addChild(this.earL);
        this.headband.addChild(this.earR);
    }

    @Override
    public void render(@Nonnull Entity entity,float limbSwing, float limbSwingAmt, float age, float netHeadYaw, float headPitch, float scale) {
        headband.showModel = true;
        bipedHeadwear.showModel = false;

        bipedHead = headband;

        super.render(entity,limbSwing,limbSwingAmt,age,netHeadYaw,headPitch,scale);
    }
}
