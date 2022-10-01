// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.world;

import net.minecraft.util.MathHelper;

public class DifficultyInstance
{
    private final EnumDifficulty field_180172_a;
    private final float field_180171_b;
    private static final String __OBFID = "CL_00002261";
    
    public DifficultyInstance(final EnumDifficulty p_i45904_1_, final long p_i45904_2_, final long p_i45904_4_, final float p_i45904_6_) {
        this.field_180172_a = p_i45904_1_;
        this.field_180171_b = this.func_180169_a(p_i45904_1_, p_i45904_2_, p_i45904_4_, p_i45904_6_);
    }
    
    public float func_180168_b() {
        return this.field_180171_b;
    }
    
    public float func_180170_c() {
        return (this.field_180171_b < 2.0f) ? 0.0f : ((this.field_180171_b > 4.0f) ? 1.0f : ((this.field_180171_b - 2.0f) / 2.0f));
    }
    
    private float func_180169_a(final EnumDifficulty p_180169_1_, final long p_180169_2_, final long p_180169_4_, final float p_180169_6_) {
        if (p_180169_1_ == EnumDifficulty.PEACEFUL) {
            return 0.0f;
        }
        final boolean var7 = p_180169_1_ == EnumDifficulty.HARD;
        float var8 = 0.75f;
        final float var9 = MathHelper.clamp_float((p_180169_2_ - 72000.0f) / 1440000.0f, 0.0f, 1.0f) * 0.25f;
        var8 += var9;
        float var10 = 0.0f;
        var10 += MathHelper.clamp_float(p_180169_4_ / 3600000.0f, 0.0f, 1.0f) * (var7 ? 1.0f : 0.75f);
        var10 += MathHelper.clamp_float(p_180169_6_ * 0.25f, 0.0f, var9);
        if (p_180169_1_ == EnumDifficulty.EASY) {
            var10 *= 0.5f;
        }
        var8 += var10;
        return p_180169_1_.getDifficultyId() * var8;
    }
}
