// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.client.renderer.entity.layers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.init.Blocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.client.renderer.entity.RenderIronGolem;

public class LayerIronGolemFlower implements LayerRenderer
{
    private final RenderIronGolem field_177154_a;
    private static final String __OBFID = "CL_00002408";
    
    public LayerIronGolemFlower(final RenderIronGolem p_i46107_1_) {
        this.field_177154_a = p_i46107_1_;
    }
    
    public void func_177153_a(final EntityIronGolem p_177153_1_, final float p_177153_2_, final float p_177153_3_, final float p_177153_4_, final float p_177153_5_, final float p_177153_6_, final float p_177153_7_, final float p_177153_8_) {
        if (p_177153_1_.getHoldRoseTick() != 0) {
            final BlockRendererDispatcher var9 = Minecraft.getMinecraft().getBlockRendererDispatcher();
            GlStateManager.enableRescaleNormal();
            GlStateManager.pushMatrix();
            GlStateManager.rotate(5.0f + 180.0f * ((ModelIronGolem)this.field_177154_a.getMainModel()).ironGolemRightArm.rotateAngleX / 3.1415927f, 1.0f, 0.0f, 0.0f);
            GlStateManager.rotate(90.0f, 1.0f, 0.0f, 0.0f);
            GlStateManager.translate(-0.9375f, -0.625f, -0.9375f);
            final float var10 = 0.5f;
            GlStateManager.scale(var10, -var10, var10);
            final int var11 = p_177153_1_.getBrightnessForRender(p_177153_4_);
            final int var12 = var11 % 65536;
            final int var13 = var11 / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var12 / 1.0f, var13 / 1.0f);
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            this.field_177154_a.bindTexture(TextureMap.locationBlocksTexture);
            var9.func_175016_a(Blocks.red_flower.getDefaultState(), 1.0f);
            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
        }
    }
    
    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
    
    @Override
    public void doRenderLayer(final EntityLivingBase p_177141_1_, final float p_177141_2_, final float p_177141_3_, final float p_177141_4_, final float p_177141_5_, final float p_177141_6_, final float p_177141_7_, final float p_177141_8_) {
        this.func_177153_a((EntityIronGolem)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
    }
}
