// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.client.stream;

import net.minecraft.entity.EntityLivingBase;

public class MetadataPlayerDeath extends Metadata
{
    private static final String __OBFID = "CL_00002376";
    
    public MetadataPlayerDeath(final EntityLivingBase p_i46066_1_, final EntityLivingBase p_i46066_2_) {
        super("player_death");
        if (p_i46066_1_ != null) {
            this.func_152808_a("player", p_i46066_1_.getName());
        }
        if (p_i46066_2_ != null) {
            this.func_152808_a("killer", p_i46066_2_.getName());
        }
    }
}
