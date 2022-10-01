// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.entity.ai;

import net.minecraft.world.IBlockAccess;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.entity.Entity;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;

public class EntityAIFollowOwner extends EntityAIBase
{
    private EntityTameable thePet;
    private EntityLivingBase theOwner;
    World theWorld;
    private double field_75336_f;
    private PathNavigate petPathfinder;
    private int field_75343_h;
    float maxDist;
    float minDist;
    private boolean field_75344_i;
    private static final String __OBFID = "CL_00001585";
    
    public EntityAIFollowOwner(final EntityTameable p_i1625_1_, final double p_i1625_2_, final float p_i1625_4_, final float p_i1625_5_) {
        this.thePet = p_i1625_1_;
        this.theWorld = p_i1625_1_.worldObj;
        this.field_75336_f = p_i1625_2_;
        this.petPathfinder = p_i1625_1_.getNavigator();
        this.minDist = p_i1625_4_;
        this.maxDist = p_i1625_5_;
        this.setMutexBits(3);
        if (!(p_i1625_1_.getNavigator() instanceof PathNavigateGround)) {
            throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
        }
    }
    
    @Override
    public boolean shouldExecute() {
        final EntityLivingBase var1 = this.thePet.func_180492_cm();
        if (var1 == null) {
            return false;
        }
        if (this.thePet.isSitting()) {
            return false;
        }
        if (this.thePet.getDistanceSqToEntity(var1) < this.minDist * this.minDist) {
            return false;
        }
        this.theOwner = var1;
        return true;
    }
    
    @Override
    public boolean continueExecuting() {
        return !this.petPathfinder.noPath() && this.thePet.getDistanceSqToEntity(this.theOwner) > this.maxDist * this.maxDist && !this.thePet.isSitting();
    }
    
    @Override
    public void startExecuting() {
        this.field_75343_h = 0;
        this.field_75344_i = ((PathNavigateGround)this.thePet.getNavigator()).func_179689_e();
        ((PathNavigateGround)this.thePet.getNavigator()).func_179690_a(false);
    }
    
    @Override
    public void resetTask() {
        this.theOwner = null;
        this.petPathfinder.clearPathEntity();
        ((PathNavigateGround)this.thePet.getNavigator()).func_179690_a(true);
    }
    
    @Override
    public void updateTask() {
        this.thePet.getLookHelper().setLookPositionWithEntity(this.theOwner, 10.0f, this.thePet.getVerticalFaceSpeed());
        if (!this.thePet.isSitting() && --this.field_75343_h <= 0) {
            this.field_75343_h = 10;
            if (!this.petPathfinder.tryMoveToEntityLiving(this.theOwner, this.field_75336_f) && !this.thePet.getLeashed() && this.thePet.getDistanceSqToEntity(this.theOwner) >= 144.0) {
                final int var1 = MathHelper.floor_double(this.theOwner.posX) - 2;
                final int var2 = MathHelper.floor_double(this.theOwner.posZ) - 2;
                final int var3 = MathHelper.floor_double(this.theOwner.getEntityBoundingBox().minY);
                for (int var4 = 0; var4 <= 4; ++var4) {
                    for (int var5 = 0; var5 <= 4; ++var5) {
                        if ((var4 < 1 || var5 < 1 || var4 > 3 || var5 > 3) && World.doesBlockHaveSolidTopSurface(this.theWorld, new BlockPos(var1 + var4, var3 - 1, var2 + var5)) && !this.theWorld.getBlockState(new BlockPos(var1 + var4, var3, var2 + var5)).getBlock().isFullCube() && !this.theWorld.getBlockState(new BlockPos(var1 + var4, var3 + 1, var2 + var5)).getBlock().isFullCube()) {
                            this.thePet.setLocationAndAngles(var1 + var4 + 0.5f, var3, var2 + var5 + 0.5f, this.thePet.rotationYaw, this.thePet.rotationPitch);
                            this.petPathfinder.clearPathEntity();
                            return;
                        }
                    }
                }
            }
        }
    }
}
