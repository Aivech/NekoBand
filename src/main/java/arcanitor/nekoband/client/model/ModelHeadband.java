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
        this.textureWidth = 32;

        //width: px from center to left, offx: move origin to left x px
        //height: px down from bottom of head
        //depth: px backwards from center
        this.headband = new ModelRenderer(this,0,-2);
        this.headband.setRotationPoint(0.0F,0.0F,0.0F);
        this.headband.addBox(-5,-1,-1,10,-8,2);

        this.earR = new ModelRenderer(this,0,0);
        this.earR.setRotationPoint(0.0F,0.0F,0.0F);
        this.earR.addBox(-6,-10,0,3,2,1);//ear back
        this.earR.addBox(-5,-11,0,1,1,1);//ear back top
        this.earR.addBox(-6,-8,-1,1,1,1);//ear rim bottom
        this.earR.addBox(-7,-10,-1,1,2,1);//ear rim outside
        this.earR.addBox(-6,-11,-1,1,1,1);//ear rim outside tip
        this.earR.addBox(-5,-12,-1,1,1,1);//ear rim tip
        this.earR.addBox(-4,-11,-1,1,1,1);//ear rim inside tip
        this.earR.addBox(-3,-10,-1,1,1,1);//ear rim inside

        this.earL = new ModelRenderer(this,0,0);
        this.earL.mirror = true;
        this.earR.setRotationPoint(0.0F,0.0F,0.0F);
        this.earR.addBox(6,-10,0,-3,2,1);//ear back
        this.earR.addBox(5,-11,0,-1,1,1);//ear back top
        this.earR.addBox(6,-8,-1,-1,1,1);//ear rim bottom
        this.earR.addBox(7,-10,-1,-1,2,1);//ear rim outside
        this.earR.addBox(6,-11,-1,-1,1,1);//ear rim outside tip
        this.earR.addBox(5,-12,-1,-1,1,1);//ear rim tip
        this.earR.addBox(4,-11,-1,-1,1,1);//ear rim inside tip
        this.earR.addBox(3,-10,-1,-1,1,1);//ear rim inside


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
