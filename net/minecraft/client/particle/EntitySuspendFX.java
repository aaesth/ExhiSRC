// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.client.particle;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntitySuspendFX extends EntityFX
{
    private static final String __OBFID = "CL_00000928";
    
    protected EntitySuspendFX(final World worldIn, final double p_i1231_2_, final double p_i1231_4_, final double p_i1231_6_, final double p_i1231_8_, final double p_i1231_10_, final double p_i1231_12_) {
        super(worldIn, p_i1231_2_, p_i1231_4_ - 0.125, p_i1231_6_, p_i1231_8_, p_i1231_10_, p_i1231_12_);
        this.particleRed = 0.4f;
        this.particleGreen = 0.4f;
        this.particleBlue = 0.7f;
        this.setParticleTextureIndex(0);
        this.setSize(0.01f, 0.01f);
        this.particleScale *= this.rand.nextFloat() * 0.6f + 0.2f;
        this.motionX = p_i1231_8_ * 0.0;
        this.motionY = p_i1231_10_ * 0.0;
        this.motionZ = p_i1231_12_ * 0.0;
        this.particleMaxAge = (int)(16.0 / (Math.random() * 0.8 + 0.2));
    }
    
    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        if (this.worldObj.getBlockState(new BlockPos(this)).getBlock().getMaterial() != Material.water) {
            this.setDead();
        }
        if (this.particleMaxAge-- <= 0) {
            this.setDead();
        }
    }
    
    public static class Factory implements IParticleFactory
    {
        private static final String __OBFID = "CL_00002579";
        
        @Override
        public EntityFX func_178902_a(final int p_178902_1_, final World worldIn, final double p_178902_3_, final double p_178902_5_, final double p_178902_7_, final double p_178902_9_, final double p_178902_11_, final double p_178902_13_, final int... p_178902_15_) {
            return new EntitySuspendFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, p_178902_9_, p_178902_11_, p_178902_13_);
        }
    }
}
