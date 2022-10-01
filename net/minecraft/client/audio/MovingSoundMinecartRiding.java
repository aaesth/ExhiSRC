// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.client.audio;

import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;

public class MovingSoundMinecartRiding extends MovingSound
{
    private final EntityPlayer player;
    private final EntityMinecart minecart;
    private static final String __OBFID = "CL_00001119";
    
    public MovingSoundMinecartRiding(final EntityPlayer p_i45106_1_, final EntityMinecart minecart) {
        super(new ResourceLocation("minecraft:minecart.inside"));
        this.player = p_i45106_1_;
        this.minecart = minecart;
        this.attenuationType = ISound.AttenuationType.NONE;
        this.repeat = true;
        this.repeatDelay = 0;
    }
    
    @Override
    public void update() {
        if (!this.minecart.isDead && this.player.isRiding() && this.player.ridingEntity == this.minecart) {
            final float var1 = MathHelper.sqrt_double(this.minecart.motionX * this.minecart.motionX + this.minecart.motionZ * this.minecart.motionZ);
            if (var1 >= 0.01) {
                this.volume = 0.0f + MathHelper.clamp_float(var1, 0.0f, 1.0f) * 0.75f;
            }
            else {
                this.volume = 0.0f;
            }
        }
        else {
            this.donePlaying = true;
        }
    }
}
