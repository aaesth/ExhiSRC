// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.entity.monster;

import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;

public class EntityBlaze extends EntityMob
{
    private float heightOffset;
    private int heightOffsetUpdateTime;
    private static final String __OBFID = "CL_00001682";
    
    public EntityBlaze(final World worldIn) {
        super(worldIn);
        this.heightOffset = 0.5f;
        this.isImmuneToFire = true;
        this.experienceValue = 10;
        this.tasks.addTask(4, new AIFireballAttack());
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }
    
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(48.0);
    }
    
    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)0));
    }
    
    @Override
    protected String getLivingSound() {
        return "mob.blaze.breathe";
    }
    
    @Override
    protected String getHurtSound() {
        return "mob.blaze.hit";
    }
    
    @Override
    protected String getDeathSound() {
        return "mob.blaze.death";
    }
    
    @Override
    public int getBrightnessForRender(final float p_70070_1_) {
        return 15728880;
    }
    
    @Override
    public float getBrightness(final float p_70013_1_) {
        return 1.0f;
    }
    
    @Override
    public void onLivingUpdate() {
        if (!this.onGround && this.motionY < 0.0) {
            this.motionY *= 0.6;
        }
        if (this.worldObj.isRemote) {
            if (this.rand.nextInt(24) == 0 && !this.isSlient()) {
                this.worldObj.playSound(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5, "fire.fire", 1.0f + this.rand.nextFloat(), this.rand.nextFloat() * 0.7f + 0.3f, false);
            }
            for (int var1 = 0; var1 < 2; ++var1) {
                this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5) * this.width, this.posY + this.rand.nextDouble() * this.height, this.posZ + (this.rand.nextDouble() - 0.5) * this.width, 0.0, 0.0, 0.0, new int[0]);
            }
        }
        super.onLivingUpdate();
    }
    
    @Override
    protected void updateAITasks() {
        if (this.isWet()) {
            this.attackEntityFrom(DamageSource.drown, 1.0f);
        }
        --this.heightOffsetUpdateTime;
        if (this.heightOffsetUpdateTime <= 0) {
            this.heightOffsetUpdateTime = 100;
            this.heightOffset = 0.5f + (float)this.rand.nextGaussian() * 3.0f;
        }
        final EntityLivingBase var1 = this.getAttackTarget();
        if (var1 != null && var1.posY + var1.getEyeHeight() > this.posY + this.getEyeHeight() + this.heightOffset) {
            this.motionY += (0.30000001192092896 - this.motionY) * 0.30000001192092896;
            this.isAirBorne = true;
        }
        super.updateAITasks();
    }
    
    @Override
    public void fall(final float distance, final float damageMultiplier) {
    }
    
    @Override
    protected Item getDropItem() {
        return Items.blaze_rod;
    }
    
    @Override
    public boolean isBurning() {
        return this.func_70845_n();
    }
    
    @Override
    protected void dropFewItems(final boolean p_70628_1_, final int p_70628_2_) {
        if (p_70628_1_) {
            for (int var3 = this.rand.nextInt(2 + p_70628_2_), var4 = 0; var4 < var3; ++var4) {
                this.dropItem(Items.blaze_rod, 1);
            }
        }
    }
    
    public boolean func_70845_n() {
        return (this.dataWatcher.getWatchableObjectByte(16) & 0x1) != 0x0;
    }
    
    public void func_70844_e(final boolean p_70844_1_) {
        byte var2 = this.dataWatcher.getWatchableObjectByte(16);
        if (p_70844_1_) {
            var2 |= 0x1;
        }
        else {
            var2 &= 0xFFFFFFFE;
        }
        this.dataWatcher.updateObject(16, var2);
    }
    
    @Override
    protected boolean isValidLightLevel() {
        return true;
    }
    
    class AIFireballAttack extends EntityAIBase
    {
        private EntityBlaze field_179469_a;
        private int field_179467_b;
        private int field_179468_c;
        private static final String __OBFID = "CL_00002225";
        
        public AIFireballAttack() {
            this.field_179469_a = EntityBlaze.this;
            this.setMutexBits(3);
        }
        
        @Override
        public boolean shouldExecute() {
            final EntityLivingBase var1 = this.field_179469_a.getAttackTarget();
            return var1 != null && var1.isEntityAlive();
        }
        
        @Override
        public void startExecuting() {
            this.field_179467_b = 0;
        }
        
        @Override
        public void resetTask() {
            this.field_179469_a.func_70844_e(false);
        }
        
        @Override
        public void updateTask() {
            --this.field_179468_c;
            final EntityLivingBase var1 = this.field_179469_a.getAttackTarget();
            final double var2 = this.field_179469_a.getDistanceSqToEntity(var1);
            if (var2 < 4.0) {
                if (this.field_179468_c <= 0) {
                    this.field_179468_c = 20;
                    this.field_179469_a.attackEntityAsMob(var1);
                }
                this.field_179469_a.getMoveHelper().setMoveTo(var1.posX, var1.posY, var1.posZ, 1.0);
            }
            else if (var2 < 256.0) {
                final double var3 = var1.posX - this.field_179469_a.posX;
                final double var4 = var1.getEntityBoundingBox().minY + var1.height / 2.0f - (this.field_179469_a.posY + this.field_179469_a.height / 2.0f);
                final double var5 = var1.posZ - this.field_179469_a.posZ;
                if (this.field_179468_c <= 0) {
                    ++this.field_179467_b;
                    if (this.field_179467_b == 1) {
                        this.field_179468_c = 60;
                        this.field_179469_a.func_70844_e(true);
                    }
                    else if (this.field_179467_b <= 4) {
                        this.field_179468_c = 6;
                    }
                    else {
                        this.field_179468_c = 100;
                        this.field_179467_b = 0;
                        this.field_179469_a.func_70844_e(false);
                    }
                    if (this.field_179467_b > 1) {
                        final float var6 = MathHelper.sqrt_float(MathHelper.sqrt_double(var2)) * 0.5f;
                        this.field_179469_a.worldObj.playAuxSFXAtEntity(null, 1009, new BlockPos((int)this.field_179469_a.posX, (int)this.field_179469_a.posY, (int)this.field_179469_a.posZ), 0);
                        for (int var7 = 0; var7 < 1; ++var7) {
                            final EntitySmallFireball var8 = new EntitySmallFireball(this.field_179469_a.worldObj, this.field_179469_a, var3 + this.field_179469_a.getRNG().nextGaussian() * var6, var4, var5 + this.field_179469_a.getRNG().nextGaussian() * var6);
                            var8.posY = this.field_179469_a.posY + this.field_179469_a.height / 2.0f + 0.5;
                            this.field_179469_a.worldObj.spawnEntityInWorld(var8);
                        }
                    }
                }
                this.field_179469_a.getLookHelper().setLookPositionWithEntity(var1, 10.0f, 10.0f);
            }
            else {
                this.field_179469_a.getNavigator().clearPathEntity();
                this.field_179469_a.getMoveHelper().setMoveTo(var1.posX, var1.posY, var1.posZ, 1.0);
            }
            super.updateTask();
        }
    }
}
