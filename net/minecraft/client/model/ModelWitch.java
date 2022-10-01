// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.client.model;

import net.minecraft.util.MathHelper;
import net.minecraft.entity.Entity;

public class ModelWitch extends ModelVillager
{
    public boolean field_82900_g;
    private ModelRenderer field_82901_h;
    private ModelRenderer witchHat;
    private static final String __OBFID = "CL_00000866";
    
    public ModelWitch(final float p_i46361_1_) {
        super(p_i46361_1_, 0.0f, 64, 128);
        (this.field_82901_h = new ModelRenderer(this).setTextureSize(64, 128)).setRotationPoint(0.0f, -2.0f, 0.0f);
        this.field_82901_h.setTextureOffset(0, 0).addBox(0.0f, 3.0f, -6.75f, 1, 1, 1, -0.25f);
        this.villagerNose.addChild(this.field_82901_h);
        (this.witchHat = new ModelRenderer(this).setTextureSize(64, 128)).setRotationPoint(-5.0f, -10.03125f, -5.0f);
        this.witchHat.setTextureOffset(0, 64).addBox(0.0f, 0.0f, 0.0f, 10, 2, 10);
        this.villagerHead.addChild(this.witchHat);
        final ModelRenderer var2 = new ModelRenderer(this).setTextureSize(64, 128);
        var2.setRotationPoint(1.75f, -4.0f, 2.0f);
        var2.setTextureOffset(0, 76).addBox(0.0f, 0.0f, 0.0f, 7, 4, 7);
        var2.rotateAngleX = -0.05235988f;
        var2.rotateAngleZ = 0.02617994f;
        this.witchHat.addChild(var2);
        final ModelRenderer var3 = new ModelRenderer(this).setTextureSize(64, 128);
        var3.setRotationPoint(1.75f, -4.0f, 2.0f);
        var3.setTextureOffset(0, 87).addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        var3.rotateAngleX = -0.10471976f;
        var3.rotateAngleZ = 0.05235988f;
        var2.addChild(var3);
        final ModelRenderer var4 = new ModelRenderer(this).setTextureSize(64, 128);
        var4.setRotationPoint(1.75f, -2.0f, 2.0f);
        var4.setTextureOffset(0, 95).addBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.25f);
        var4.rotateAngleX = -0.20943952f;
        var4.rotateAngleZ = 0.10471976f;
        var3.addChild(var4);
    }
    
    @Override
    public void setRotationAngles(final float p_78087_1_, final float p_78087_2_, final float p_78087_3_, final float p_78087_4_, final float p_78087_5_, final float p_78087_6_, final Entity p_78087_7_) {
        super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
        final ModelRenderer villagerNose = this.villagerNose;
        final ModelRenderer villagerNose2 = this.villagerNose;
        final ModelRenderer villagerNose3 = this.villagerNose;
        final float offsetX = 0.0f;
        villagerNose3.offsetZ = offsetX;
        villagerNose2.offsetY = offsetX;
        villagerNose.offsetX = offsetX;
        final float var8 = 0.01f * (p_78087_7_.getEntityId() % 10);
        this.villagerNose.rotateAngleX = MathHelper.sin(p_78087_7_.ticksExisted * var8) * 4.5f * 3.1415927f / 180.0f;
        this.villagerNose.rotateAngleY = 0.0f;
        this.villagerNose.rotateAngleZ = MathHelper.cos(p_78087_7_.ticksExisted * var8) * 2.5f * 3.1415927f / 180.0f;
        if (this.field_82900_g) {
            this.villagerNose.rotateAngleX = -0.9f;
            this.villagerNose.offsetZ = -0.09375f;
            this.villagerNose.offsetY = 0.1875f;
        }
    }
}
