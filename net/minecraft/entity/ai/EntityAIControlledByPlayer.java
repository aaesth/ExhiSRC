// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.entity.ai;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.entity.Entity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.pathfinder.WalkNodeProcessor;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLiving;

public class EntityAIControlledByPlayer extends EntityAIBase
{
    private final EntityLiving thisEntity;
    private final float maxSpeed;
    private float currentSpeed;
    private boolean speedBoosted;
    private int speedBoostTime;
    private int maxSpeedBoostTime;
    private static final String __OBFID = "CL_00001580";
    
    public EntityAIControlledByPlayer(final EntityLiving p_i1620_1_, final float p_i1620_2_) {
        this.thisEntity = p_i1620_1_;
        this.maxSpeed = p_i1620_2_;
        this.setMutexBits(7);
    }
    
    @Override
    public void startExecuting() {
        this.currentSpeed = 0.0f;
    }
    
    @Override
    public void resetTask() {
        this.speedBoosted = false;
        this.currentSpeed = 0.0f;
    }
    
    @Override
    public boolean shouldExecute() {
        return this.thisEntity.isEntityAlive() && this.thisEntity.riddenByEntity != null && this.thisEntity.riddenByEntity instanceof EntityPlayer && (this.speedBoosted || this.thisEntity.canBeSteered());
    }
    
    @Override
    public void updateTask() {
        final EntityPlayer var1 = (EntityPlayer)this.thisEntity.riddenByEntity;
        final EntityCreature var2 = (EntityCreature)this.thisEntity;
        float var3 = MathHelper.wrapAngleTo180_float(var1.rotationYaw - this.thisEntity.rotationYaw) * 0.5f;
        if (var3 > 5.0f) {
            var3 = 5.0f;
        }
        if (var3 < -5.0f) {
            var3 = -5.0f;
        }
        this.thisEntity.rotationYaw = MathHelper.wrapAngleTo180_float(this.thisEntity.rotationYaw + var3);
        if (this.currentSpeed < this.maxSpeed) {
            this.currentSpeed += (this.maxSpeed - this.currentSpeed) * 0.01f;
        }
        if (this.currentSpeed > this.maxSpeed) {
            this.currentSpeed = this.maxSpeed;
        }
        final int var4 = MathHelper.floor_double(this.thisEntity.posX);
        final int var5 = MathHelper.floor_double(this.thisEntity.posY);
        final int var6 = MathHelper.floor_double(this.thisEntity.posZ);
        float var7 = this.currentSpeed;
        if (this.speedBoosted) {
            if (this.speedBoostTime++ > this.maxSpeedBoostTime) {
                this.speedBoosted = false;
            }
            var7 += var7 * 1.15f * MathHelper.sin(this.speedBoostTime / this.maxSpeedBoostTime * 3.1415927f);
        }
        float var8 = 0.91f;
        if (this.thisEntity.onGround) {
            var8 = this.thisEntity.worldObj.getBlockState(new BlockPos(MathHelper.floor_float(var4), MathHelper.floor_float(var5) - 1, MathHelper.floor_float(var6))).getBlock().slipperiness * 0.91f;
        }
        final float var9 = 0.16277136f / (var8 * var8 * var8);
        final float var10 = MathHelper.sin(var2.rotationYaw * 3.1415927f / 180.0f);
        final float var11 = MathHelper.cos(var2.rotationYaw * 3.1415927f / 180.0f);
        final float var12 = var2.getAIMoveSpeed() * var9;
        float var13 = Math.max(var7, 1.0f);
        var13 = var12 / var13;
        final float var14 = var7 * var13;
        float var15 = -(var14 * var10);
        float var16 = var14 * var11;
        if (MathHelper.abs(var15) > MathHelper.abs(var16)) {
            if (var15 < 0.0f) {
                var15 -= this.thisEntity.width / 2.0f;
            }
            if (var15 > 0.0f) {
                var15 += this.thisEntity.width / 2.0f;
            }
            var16 = 0.0f;
        }
        else {
            var15 = 0.0f;
            if (var16 < 0.0f) {
                var16 -= this.thisEntity.width / 2.0f;
            }
            if (var16 > 0.0f) {
                var16 += this.thisEntity.width / 2.0f;
            }
        }
        final int var17 = MathHelper.floor_double(this.thisEntity.posX + var15);
        final int var18 = MathHelper.floor_double(this.thisEntity.posZ + var16);
        final int var19 = MathHelper.floor_float(this.thisEntity.width + 1.0f);
        final int var20 = MathHelper.floor_float(this.thisEntity.height + var1.height + 1.0f);
        final int var21 = MathHelper.floor_float(this.thisEntity.width + 1.0f);
        if (var4 != var17 || var6 != var18) {
            final Block var22 = this.thisEntity.worldObj.getBlockState(new BlockPos(var4, var5, var6)).getBlock();
            final boolean var23 = !this.isStairOrSlab(var22) && (var22.getMaterial() != Material.air || !this.isStairOrSlab(this.thisEntity.worldObj.getBlockState(new BlockPos(var4, var5 - 1, var6)).getBlock()));
            if (var23 && 0 == WalkNodeProcessor.func_176170_a(this.thisEntity.worldObj, this.thisEntity, var17, var5, var18, var19, var20, var21, false, false, true) && 1 == WalkNodeProcessor.func_176170_a(this.thisEntity.worldObj, this.thisEntity, var4, var5 + 1, var6, var19, var20, var21, false, false, true) && 1 == WalkNodeProcessor.func_176170_a(this.thisEntity.worldObj, this.thisEntity, var17, var5 + 1, var18, var19, var20, var21, false, false, true)) {
                var2.getJumpHelper().setJumping();
            }
        }
        if (!var1.capabilities.isCreativeMode && this.currentSpeed >= this.maxSpeed * 0.5f && this.thisEntity.getRNG().nextFloat() < 0.006f && !this.speedBoosted) {
            final ItemStack var24 = var1.getHeldItem();
            if (var24 != null && var24.getItem() == Items.carrot_on_a_stick) {
                var24.damageItem(1, var1);
                if (var24.stackSize == 0) {
                    final ItemStack var25 = new ItemStack(Items.fishing_rod);
                    var25.setTagCompound(var24.getTagCompound());
                    var1.inventory.mainInventory[var1.inventory.currentItem] = var25;
                }
            }
        }
        this.thisEntity.moveEntityWithHeading(0.0f, var7);
    }
    
    private boolean isStairOrSlab(final Block p_151498_1_) {
        return p_151498_1_ instanceof BlockStairs || p_151498_1_ instanceof BlockSlab;
    }
    
    public boolean isSpeedBoosted() {
        return this.speedBoosted;
    }
    
    public void boostSpeed() {
        this.speedBoosted = true;
        this.speedBoostTime = 0;
        this.maxSpeedBoostTime = this.thisEntity.getRNG().nextInt(841) + 140;
    }
    
    public boolean isControlledByPlayer() {
        return !this.isSpeedBoosted() && this.currentSpeed > this.maxSpeed * 0.3f;
    }
}
