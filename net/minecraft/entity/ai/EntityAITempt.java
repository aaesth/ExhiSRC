// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.entity.ai;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.item.Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityCreature;

public class EntityAITempt extends EntityAIBase
{
    private EntityCreature temptedEntity;
    private double field_75282_b;
    private double targetX;
    private double targetY;
    private double targetZ;
    private double field_75278_f;
    private double field_75279_g;
    private EntityPlayer temptingPlayer;
    private int delayTemptCounter;
    private boolean isRunning;
    private Item field_151484_k;
    private boolean scaredByPlayerMovement;
    private boolean field_75286_m;
    private static final String __OBFID = "CL_00001616";
    
    public EntityAITempt(final EntityCreature p_i45316_1_, final double p_i45316_2_, final Item p_i45316_4_, final boolean p_i45316_5_) {
        this.temptedEntity = p_i45316_1_;
        this.field_75282_b = p_i45316_2_;
        this.field_151484_k = p_i45316_4_;
        this.scaredByPlayerMovement = p_i45316_5_;
        this.setMutexBits(3);
        if (!(p_i45316_1_.getNavigator() instanceof PathNavigateGround)) {
            throw new IllegalArgumentException("Unsupported mob type for TemptGoal");
        }
    }
    
    @Override
    public boolean shouldExecute() {
        if (this.delayTemptCounter > 0) {
            --this.delayTemptCounter;
            return false;
        }
        this.temptingPlayer = this.temptedEntity.worldObj.getClosestPlayerToEntity(this.temptedEntity, 10.0);
        if (this.temptingPlayer == null) {
            return false;
        }
        final ItemStack var1 = this.temptingPlayer.getCurrentEquippedItem();
        return var1 != null && var1.getItem() == this.field_151484_k;
    }
    
    @Override
    public boolean continueExecuting() {
        if (this.scaredByPlayerMovement) {
            if (this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 36.0) {
                if (this.temptingPlayer.getDistanceSq(this.targetX, this.targetY, this.targetZ) > 0.010000000000000002) {
                    return false;
                }
                if (Math.abs(this.temptingPlayer.rotationPitch - this.field_75278_f) > 5.0 || Math.abs(this.temptingPlayer.rotationYaw - this.field_75279_g) > 5.0) {
                    return false;
                }
            }
            else {
                this.targetX = this.temptingPlayer.posX;
                this.targetY = this.temptingPlayer.posY;
                this.targetZ = this.temptingPlayer.posZ;
            }
            this.field_75278_f = this.temptingPlayer.rotationPitch;
            this.field_75279_g = this.temptingPlayer.rotationYaw;
        }
        return this.shouldExecute();
    }
    
    @Override
    public void startExecuting() {
        this.targetX = this.temptingPlayer.posX;
        this.targetY = this.temptingPlayer.posY;
        this.targetZ = this.temptingPlayer.posZ;
        this.isRunning = true;
        this.field_75286_m = ((PathNavigateGround)this.temptedEntity.getNavigator()).func_179689_e();
        ((PathNavigateGround)this.temptedEntity.getNavigator()).func_179690_a(false);
    }
    
    @Override
    public void resetTask() {
        this.temptingPlayer = null;
        this.temptedEntity.getNavigator().clearPathEntity();
        this.delayTemptCounter = 100;
        this.isRunning = false;
        ((PathNavigateGround)this.temptedEntity.getNavigator()).func_179690_a(this.field_75286_m);
    }
    
    @Override
    public void updateTask() {
        this.temptedEntity.getLookHelper().setLookPositionWithEntity(this.temptingPlayer, 30.0f, this.temptedEntity.getVerticalFaceSpeed());
        if (this.temptedEntity.getDistanceSqToEntity(this.temptingPlayer) < 6.25) {
            this.temptedEntity.getNavigator().clearPathEntity();
        }
        else {
            this.temptedEntity.getNavigator().tryMoveToEntityLiving(this.temptingPlayer, this.field_75282_b);
        }
    }
    
    public boolean isRunning() {
        return this.isRunning;
    }
}
