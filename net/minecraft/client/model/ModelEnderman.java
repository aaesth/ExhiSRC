// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.client.model;

import net.minecraft.entity.Entity;

public class ModelEnderman extends ModelBiped
{
    public boolean isCarrying;
    public boolean isAttacking;
    private static final String __OBFID = "CL_00000838";
    
    public ModelEnderman(final float p_i46305_1_) {
        super(0.0f, -14.0f, 64, 32);
        final float var2 = -14.0f;
        (this.bipedHeadwear = new ModelRenderer(this, 0, 16)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, p_i46305_1_ - 0.5f);
        this.bipedHeadwear.setRotationPoint(0.0f, 0.0f + var2, 0.0f);
        (this.bipedBody = new ModelRenderer(this, 32, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4, p_i46305_1_);
        this.bipedBody.setRotationPoint(0.0f, 0.0f + var2, 0.0f);
        (this.bipedRightArm = new ModelRenderer(this, 56, 0)).addBox(-1.0f, -2.0f, -1.0f, 2, 30, 2, p_i46305_1_);
        this.bipedRightArm.setRotationPoint(-3.0f, 2.0f + var2, 0.0f);
        this.bipedLeftArm = new ModelRenderer(this, 56, 0);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0f, -2.0f, -1.0f, 2, 30, 2, p_i46305_1_);
        this.bipedLeftArm.setRotationPoint(5.0f, 2.0f + var2, 0.0f);
        (this.bipedRightLeg = new ModelRenderer(this, 56, 0)).addBox(-1.0f, 0.0f, -1.0f, 2, 30, 2, p_i46305_1_);
        this.bipedRightLeg.setRotationPoint(-2.0f, 12.0f + var2, 0.0f);
        this.bipedLeftLeg = new ModelRenderer(this, 56, 0);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(-1.0f, 0.0f, -1.0f, 2, 30, 2, p_i46305_1_);
        this.bipedLeftLeg.setRotationPoint(2.0f, 12.0f + var2, 0.0f);
    }
    
    @Override
    public void setRotationAngles(final float p_78087_1_, final float p_78087_2_, final float p_78087_3_, final float p_78087_4_, final float p_78087_5_, final float p_78087_6_, final Entity p_78087_7_) {
        super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
        this.bipedHead.showModel = true;
        final float var8 = -14.0f;
        this.bipedBody.rotateAngleX = 0.0f;
        this.bipedBody.rotationPointY = var8;
        this.bipedBody.rotationPointZ = -0.0f;
        final ModelRenderer bipedRightLeg = this.bipedRightLeg;
        bipedRightLeg.rotateAngleX -= 0.0f;
        final ModelRenderer bipedLeftLeg = this.bipedLeftLeg;
        bipedLeftLeg.rotateAngleX -= 0.0f;
        this.bipedRightArm.rotateAngleX *= 0.5;
        this.bipedLeftArm.rotateAngleX *= 0.5;
        this.bipedRightLeg.rotateAngleX *= 0.5;
        this.bipedLeftLeg.rotateAngleX *= 0.5;
        final float var9 = 0.4f;
        if (this.bipedRightArm.rotateAngleX > var9) {
            this.bipedRightArm.rotateAngleX = var9;
        }
        if (this.bipedLeftArm.rotateAngleX > var9) {
            this.bipedLeftArm.rotateAngleX = var9;
        }
        if (this.bipedRightArm.rotateAngleX < -var9) {
            this.bipedRightArm.rotateAngleX = -var9;
        }
        if (this.bipedLeftArm.rotateAngleX < -var9) {
            this.bipedLeftArm.rotateAngleX = -var9;
        }
        if (this.bipedRightLeg.rotateAngleX > var9) {
            this.bipedRightLeg.rotateAngleX = var9;
        }
        if (this.bipedLeftLeg.rotateAngleX > var9) {
            this.bipedLeftLeg.rotateAngleX = var9;
        }
        if (this.bipedRightLeg.rotateAngleX < -var9) {
            this.bipedRightLeg.rotateAngleX = -var9;
        }
        if (this.bipedLeftLeg.rotateAngleX < -var9) {
            this.bipedLeftLeg.rotateAngleX = -var9;
        }
        if (this.isCarrying) {
            this.bipedRightArm.rotateAngleX = -0.5f;
            this.bipedLeftArm.rotateAngleX = -0.5f;
            this.bipedRightArm.rotateAngleZ = 0.05f;
            this.bipedLeftArm.rotateAngleZ = -0.05f;
        }
        this.bipedRightArm.rotationPointZ = 0.0f;
        this.bipedLeftArm.rotationPointZ = 0.0f;
        this.bipedRightLeg.rotationPointZ = 0.0f;
        this.bipedLeftLeg.rotationPointZ = 0.0f;
        this.bipedRightLeg.rotationPointY = 9.0f + var8;
        this.bipedLeftLeg.rotationPointY = 9.0f + var8;
        this.bipedHead.rotationPointZ = -0.0f;
        this.bipedHead.rotationPointY = var8 + 1.0f;
        this.bipedHeadwear.rotationPointX = this.bipedHead.rotationPointX;
        this.bipedHeadwear.rotationPointY = this.bipedHead.rotationPointY;
        this.bipedHeadwear.rotationPointZ = this.bipedHead.rotationPointZ;
        this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
        this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedHeadwear.rotateAngleZ = this.bipedHead.rotateAngleZ;
        if (this.isAttacking) {
            final float var10 = 1.0f;
            final ModelRenderer bipedHead = this.bipedHead;
            bipedHead.rotationPointY -= var10 * 5.0f;
        }
    }
}
