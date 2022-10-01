// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.entity.monster;

import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.WorldType;
import net.minecraft.util.BlockPos;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;
import net.minecraft.entity.EntityLiving;

public class EntitySlime extends EntityLiving implements IMob
{
    public float squishAmount;
    public float squishFactor;
    public float prevSquishFactor;
    private boolean field_175452_bi;
    private static final String __OBFID = "CL_00001698";
    
    public EntitySlime(final World worldIn) {
        super(worldIn);
        this.moveHelper = new SlimeMoveHelper();
        this.tasks.addTask(1, new AISlimeFloat());
        this.tasks.addTask(2, new AISlimeAttack());
        this.tasks.addTask(3, new AISlimeFaceRandom());
        this.tasks.addTask(5, new AISlimeHop());
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
        this.targetTasks.addTask(3, new EntityAIFindEntityNearest(this, EntityIronGolem.class));
    }
    
    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (byte)1);
    }
    
    protected void setSlimeSize(final int p_70799_1_) {
        this.dataWatcher.updateObject(16, (byte)p_70799_1_);
        this.setSize(0.51000005f * p_70799_1_, 0.51000005f * p_70799_1_);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(p_70799_1_ * p_70799_1_);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2f + 0.1f * p_70799_1_);
        this.setHealth(this.getMaxHealth());
        this.experienceValue = p_70799_1_;
    }
    
    public int getSlimeSize() {
        return this.dataWatcher.getWatchableObjectByte(16);
    }
    
    @Override
    public void writeEntityToNBT(final NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setInteger("Size", this.getSlimeSize() - 1);
        tagCompound.setBoolean("wasOnGround", this.field_175452_bi);
    }
    
    @Override
    public void readEntityFromNBT(final NBTTagCompound tagCompund) {
        super.readEntityFromNBT(tagCompund);
        int var2 = tagCompund.getInteger("Size");
        if (var2 < 0) {
            var2 = 0;
        }
        this.setSlimeSize(var2 + 1);
        this.field_175452_bi = tagCompund.getBoolean("wasOnGround");
    }
    
    protected EnumParticleTypes func_180487_n() {
        return EnumParticleTypes.SLIME;
    }
    
    protected String getJumpSound() {
        return "mob.slime." + ((this.getSlimeSize() > 1) ? "big" : "small");
    }
    
    @Override
    public void onUpdate() {
        if (!this.worldObj.isRemote && this.worldObj.getDifficulty() == EnumDifficulty.PEACEFUL && this.getSlimeSize() > 0) {
            this.isDead = true;
        }
        this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5f;
        this.prevSquishFactor = this.squishFactor;
        super.onUpdate();
        if (this.onGround && !this.field_175452_bi) {
            for (int var1 = this.getSlimeSize(), var2 = 0; var2 < var1 * 8; ++var2) {
                final float var3 = this.rand.nextFloat() * 3.1415927f * 2.0f;
                final float var4 = this.rand.nextFloat() * 0.5f + 0.5f;
                final float var5 = MathHelper.sin(var3) * var1 * 0.5f * var4;
                final float var6 = MathHelper.cos(var3) * var1 * 0.5f * var4;
                final World var7 = this.worldObj;
                final EnumParticleTypes var8 = this.func_180487_n();
                final double var9 = this.posX + var5;
                final double var10 = this.posZ + var6;
                var7.spawnParticle(var8, var9, this.getEntityBoundingBox().minY, var10, 0.0, 0.0, 0.0, new int[0]);
            }
            if (this.makesSoundOnLand()) {
                this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f) / 0.8f);
            }
            this.squishAmount = -0.5f;
        }
        else if (!this.onGround && this.field_175452_bi) {
            this.squishAmount = 1.0f;
        }
        this.field_175452_bi = this.onGround;
        this.alterSquishAmount();
    }
    
    protected void alterSquishAmount() {
        this.squishAmount *= 0.6f;
    }
    
    protected int getJumpDelay() {
        return this.rand.nextInt(20) + 10;
    }
    
    protected EntitySlime createInstance() {
        return new EntitySlime(this.worldObj);
    }
    
    @Override
    public void func_145781_i(final int p_145781_1_) {
        if (p_145781_1_ == 16) {
            final int var2 = this.getSlimeSize();
            this.setSize(0.51000005f * var2, 0.51000005f * var2);
            this.rotationYaw = this.rotationYawHead;
            this.renderYawOffset = this.rotationYawHead;
            if (this.isInWater() && this.rand.nextInt(20) == 0) {
                this.resetHeight();
            }
        }
        super.func_145781_i(p_145781_1_);
    }
    
    @Override
    public void setDead() {
        final int var1 = this.getSlimeSize();
        if (!this.worldObj.isRemote && var1 > 1 && this.getHealth() <= 0.0f) {
            for (int var2 = 2 + this.rand.nextInt(3), var3 = 0; var3 < var2; ++var3) {
                final float var4 = (var3 % 2 - 0.5f) * var1 / 4.0f;
                final float var5 = (var3 / 2 - 0.5f) * var1 / 4.0f;
                final EntitySlime var6 = this.createInstance();
                if (this.hasCustomName()) {
                    var6.setCustomNameTag(this.getCustomNameTag());
                }
                if (this.isNoDespawnRequired()) {
                    var6.enablePersistence();
                }
                var6.setSlimeSize(var1 / 2);
                var6.setLocationAndAngles(this.posX + var4, this.posY + 0.5, this.posZ + var5, this.rand.nextFloat() * 360.0f, 0.0f);
                this.worldObj.spawnEntityInWorld(var6);
            }
        }
        super.setDead();
    }
    
    @Override
    public void applyEntityCollision(final Entity entityIn) {
        super.applyEntityCollision(entityIn);
        if (entityIn instanceof EntityIronGolem && this.canDamagePlayer()) {
            this.func_175451_e((EntityLivingBase)entityIn);
        }
    }
    
    @Override
    public void onCollideWithPlayer(final EntityPlayer entityIn) {
        if (this.canDamagePlayer()) {
            this.func_175451_e(entityIn);
        }
    }
    
    protected void func_175451_e(final EntityLivingBase p_175451_1_) {
        final int var2 = this.getSlimeSize();
        if (this.canEntityBeSeen(p_175451_1_) && this.getDistanceSqToEntity(p_175451_1_) < 0.6 * var2 * 0.6 * var2 && p_175451_1_.attackEntityFrom(DamageSource.causeMobDamage(this), this.getAttackStrength())) {
            this.playSound("mob.attack", 1.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
            this.func_174815_a(this, p_175451_1_);
        }
    }
    
    @Override
    public float getEyeHeight() {
        return 0.625f * this.height;
    }
    
    protected boolean canDamagePlayer() {
        return this.getSlimeSize() > 1;
    }
    
    protected int getAttackStrength() {
        return this.getSlimeSize();
    }
    
    @Override
    protected String getHurtSound() {
        return "mob.slime." + ((this.getSlimeSize() > 1) ? "big" : "small");
    }
    
    @Override
    protected String getDeathSound() {
        return "mob.slime." + ((this.getSlimeSize() > 1) ? "big" : "small");
    }
    
    @Override
    protected Item getDropItem() {
        return (this.getSlimeSize() == 1) ? Items.slime_ball : null;
    }
    
    @Override
    public boolean getCanSpawnHere() {
        final Chunk var1 = this.worldObj.getChunkFromBlockCoords(new BlockPos(MathHelper.floor_double(this.posX), 0, MathHelper.floor_double(this.posZ)));
        if (this.worldObj.getWorldInfo().getTerrainType() == WorldType.FLAT && this.rand.nextInt(4) != 1) {
            return false;
        }
        if (this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL) {
            final BiomeGenBase var2 = this.worldObj.getBiomeGenForCoords(new BlockPos(MathHelper.floor_double(this.posX), 0, MathHelper.floor_double(this.posZ)));
            if (var2 == BiomeGenBase.swampland && this.posY > 50.0 && this.posY < 70.0 && this.rand.nextFloat() < 0.5f && this.rand.nextFloat() < this.worldObj.getCurrentMoonPhaseFactor() && this.worldObj.getLightFromNeighbors(new BlockPos(this)) <= this.rand.nextInt(8)) {
                return super.getCanSpawnHere();
            }
            if (this.rand.nextInt(10) == 0 && var1.getRandomWithSeed(987234911L).nextInt(10) == 0 && this.posY < 40.0) {
                return super.getCanSpawnHere();
            }
        }
        return false;
    }
    
    @Override
    protected float getSoundVolume() {
        return 0.4f * this.getSlimeSize();
    }
    
    @Override
    public int getVerticalFaceSpeed() {
        return 0;
    }
    
    protected boolean makesSoundOnJump() {
        return this.getSlimeSize() > 0;
    }
    
    protected boolean makesSoundOnLand() {
        return this.getSlimeSize() > 2;
    }
    
    @Override
    protected void jump() {
        this.motionY = 0.41999998688697815;
        this.isAirBorne = true;
    }
    
    @Override
    public IEntityLivingData func_180482_a(final DifficultyInstance p_180482_1_, final IEntityLivingData p_180482_2_) {
        int var3 = this.rand.nextInt(3);
        if (var3 < 2 && this.rand.nextFloat() < 0.5f * p_180482_1_.func_180170_c()) {
            ++var3;
        }
        final int var4 = 1 << var3;
        this.setSlimeSize(var4);
        return super.func_180482_a(p_180482_1_, p_180482_2_);
    }
    
    class AISlimeAttack extends EntityAIBase
    {
        private EntitySlime field_179466_a;
        private int field_179465_b;
        private static final String __OBFID = "CL_00002202";
        
        public AISlimeAttack() {
            this.field_179466_a = EntitySlime.this;
            this.setMutexBits(2);
        }
        
        @Override
        public boolean shouldExecute() {
            final EntityLivingBase var1 = this.field_179466_a.getAttackTarget();
            return var1 != null && var1.isEntityAlive();
        }
        
        @Override
        public void startExecuting() {
            this.field_179465_b = 300;
            super.startExecuting();
        }
        
        @Override
        public boolean continueExecuting() {
            final EntityLivingBase var1 = this.field_179466_a.getAttackTarget();
            boolean b;
            if (var1 == null) {
                b = false;
            }
            else if (!var1.isEntityAlive()) {
                b = false;
            }
            else {
                final int field_179465_b = this.field_179465_b - 1;
                this.field_179465_b = field_179465_b;
                b = (field_179465_b > 0);
            }
            return b;
        }
        
        @Override
        public void updateTask() {
            this.field_179466_a.faceEntity(this.field_179466_a.getAttackTarget(), 10.0f, 10.0f);
            ((SlimeMoveHelper)this.field_179466_a.getMoveHelper()).func_179920_a(this.field_179466_a.rotationYaw, this.field_179466_a.canDamagePlayer());
        }
    }
    
    class AISlimeFaceRandom extends EntityAIBase
    {
        private EntitySlime field_179461_a;
        private float field_179459_b;
        private int field_179460_c;
        private static final String __OBFID = "CL_00002198";
        
        public AISlimeFaceRandom() {
            this.field_179461_a = EntitySlime.this;
            this.setMutexBits(2);
        }
        
        @Override
        public boolean shouldExecute() {
            return this.field_179461_a.getAttackTarget() == null && (this.field_179461_a.onGround || this.field_179461_a.isInWater() || this.field_179461_a.func_180799_ab());
        }
        
        @Override
        public void updateTask() {
            final int field_179460_c = this.field_179460_c - 1;
            this.field_179460_c = field_179460_c;
            if (field_179460_c <= 0) {
                this.field_179460_c = 40 + this.field_179461_a.getRNG().nextInt(60);
                this.field_179459_b = this.field_179461_a.getRNG().nextInt(360);
            }
            ((SlimeMoveHelper)this.field_179461_a.getMoveHelper()).func_179920_a(this.field_179459_b, false);
        }
    }
    
    class AISlimeFloat extends EntityAIBase
    {
        private EntitySlime field_179457_a;
        private static final String __OBFID = "CL_00002201";
        
        public AISlimeFloat() {
            this.field_179457_a = EntitySlime.this;
            this.setMutexBits(5);
            ((PathNavigateGround)EntitySlime.this.getNavigator()).func_179693_d(true);
        }
        
        @Override
        public boolean shouldExecute() {
            return this.field_179457_a.isInWater() || this.field_179457_a.func_180799_ab();
        }
        
        @Override
        public void updateTask() {
            if (this.field_179457_a.getRNG().nextFloat() < 0.8f) {
                this.field_179457_a.getJumpHelper().setJumping();
            }
            ((SlimeMoveHelper)this.field_179457_a.getMoveHelper()).func_179921_a(1.2);
        }
    }
    
    class AISlimeHop extends EntityAIBase
    {
        private EntitySlime field_179458_a;
        private static final String __OBFID = "CL_00002200";
        
        public AISlimeHop() {
            this.field_179458_a = EntitySlime.this;
            this.setMutexBits(5);
        }
        
        @Override
        public boolean shouldExecute() {
            return true;
        }
        
        @Override
        public void updateTask() {
            ((SlimeMoveHelper)this.field_179458_a.getMoveHelper()).func_179921_a(1.0);
        }
    }
    
    class SlimeMoveHelper extends EntityMoveHelper
    {
        private float field_179922_g;
        private int field_179924_h;
        private EntitySlime field_179925_i;
        private boolean field_179923_j;
        private static final String __OBFID = "CL_00002199";
        
        public SlimeMoveHelper() {
            super(EntitySlime.this);
            this.field_179925_i = EntitySlime.this;
        }
        
        public void func_179920_a(final float p_179920_1_, final boolean p_179920_2_) {
            this.field_179922_g = p_179920_1_;
            this.field_179923_j = p_179920_2_;
        }
        
        public void func_179921_a(final double p_179921_1_) {
            this.speed = p_179921_1_;
            this.update = true;
        }
        
        @Override
        public void onUpdateMoveHelper() {
            this.entity.rotationYaw = this.limitAngle(this.entity.rotationYaw, this.field_179922_g, 30.0f);
            this.entity.rotationYawHead = this.entity.rotationYaw;
            this.entity.renderYawOffset = this.entity.rotationYaw;
            if (!this.update) {
                this.entity.setMoveForward(0.0f);
            }
            else {
                this.update = false;
                if (this.entity.onGround) {
                    this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));
                    if (this.field_179924_h-- <= 0) {
                        this.field_179924_h = this.field_179925_i.getJumpDelay();
                        if (this.field_179923_j) {
                            this.field_179924_h /= 3;
                        }
                        this.field_179925_i.getJumpHelper().setJumping();
                        if (this.field_179925_i.makesSoundOnJump()) {
                            this.field_179925_i.playSound(this.field_179925_i.getJumpSound(), this.field_179925_i.getSoundVolume(), ((this.field_179925_i.getRNG().nextFloat() - this.field_179925_i.getRNG().nextFloat()) * 0.2f + 1.0f) * 0.8f);
                        }
                    }
                    else {
                        final EntitySlime field_179925_i = this.field_179925_i;
                        final EntitySlime field_179925_i2 = this.field_179925_i;
                        final float n = 0.0f;
                        field_179925_i2.moveForward = n;
                        field_179925_i.moveStrafing = n;
                        this.entity.setAIMoveSpeed(0.0f);
                    }
                }
                else {
                    this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));
                }
            }
        }
    }
}
