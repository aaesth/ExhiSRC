// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.client.renderer;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import java.util.Iterator;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.EnumWorldBlockLayer;

public class VboRenderList extends ChunkRenderContainer
{
    private static final String __OBFID = "CL_00002533";
    
    @Override
    public void func_178001_a(final EnumWorldBlockLayer p_178001_1_) {
        if (this.field_178007_b) {
            for (final RenderChunk var3 : this.field_178009_a) {
                final VertexBuffer var4 = var3.func_178565_b(p_178001_1_.ordinal());
                GlStateManager.pushMatrix();
                this.func_178003_a(var3);
                var3.func_178572_f();
                var4.func_177359_a();
                this.func_178010_a();
                var4.func_177358_a(7);
                GlStateManager.popMatrix();
            }
            OpenGlHelper.func_176072_g(OpenGlHelper.field_176089_P, 0);
            GlStateManager.func_179117_G();
            this.field_178009_a.clear();
        }
    }
    
    private void func_178010_a() {
        GL11.glVertexPointer(3, 5126, 28, 0L);
        GL11.glColorPointer(4, 5121, 28, 12L);
        GL11.glTexCoordPointer(2, 5126, 28, 16L);
        OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glTexCoordPointer(2, 5122, 28, 24L);
        OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
    }
}
