// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.entity.monster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.Entity;
import com.google.common.base.Predicate;
import net.minecraft.entity.ai.EntityAICreeperSwell;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.world.World;

public class EntityCreeper extends EntityMob
{
    private int lastActiveTime;
    private int timeSinceIgnited;
    private int fuseTime;
    private int explosionRadius;
    private int field_175494_bm;
    private static final String __OBFID = "CL_00001684";
    
    public EntityCreeper(final World worldIn) {
        super(worldIn);
        this.fuseTime = 30;
        this.explosionRadius = 3;
        this.field_175494_bm = 0;
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAICreeperSwell(this));
        this.tasks.addTask(2, this.field_175455_a);
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, (Predicate)new Predicate() {
            private static final String __OBFID = "CL_00002224";
            
            public boolean func_179958_a(final Entity p_179958_1_) {
                return p_179958_1_ instanceof EntityOcelot;
            }
            
            public boolean apply(final Object p_apply_1_) {
                return this.func_179958_a((Entity)p_apply_1_);
            }
        }, 6.0f, 1.0, 1.2));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0, false));
        this.tasks.addTask(5, new EntityAIWander(this, 0.8));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
    }
    
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
    }
    
    @Override
    public int getMaxFallHeight() {
        return (this.getAttackTarget() == null) ? 3 : (3 + (int)(this.getHealth() - 1.0f));
    }
    
    @Override
    public void fall(final float distance, final float damageMultiplier) {
        super.fall(distance, damageMultiplier);
        this.timeSinceIgnited += (int)(distance * 1.5f);
        if (this.timeSinceIgnited > this.fuseTime - 5) {
            this.timeSinceIgnited = this.fuseTime - 5;
        }
    }
    
    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, (byte)(-1));
        this.dataWatcher.addObject(17, (byte)0);
        this.dataWatcher.addObject(18, (byte)0);
    }
    
    @Override
    public void writeEntityToNBT(final NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);
        if (this.dataWatcher.getWatchableObjectByte(17) == 1) {
            tagCompound.setBoolean("powered", true);
        }
        tagCompound.setShort("Fuse", (short)this.fuseTime);
        tagCompound.setByte("ExplosionRadius", (byte)this.explosionRadius);
        tagCompound.setBoolean("ignited", this.func_146078_ca());
    }
    
    @Override
    public void readEntityFromNBT(final NBTTagCompound tagCompund) {
        super.readEntityFromNBT(tagCompund);
        this.dataWatcher.updateObject(17, (byte)(byte)(tagCompund.getBoolean("powered") ? 1 : 0));
        if (tagCompund.hasKey("Fuse", 99)) {
            this.fuseTime = tagCompund.getShort("Fuse");
        }
        if (tagCompund.hasKey("ExplosionRadius", 99)) {
            this.explosionRadius = tagCompund.getByte("ExplosionRadius");
        }
        if (tagCompund.getBoolean("ignited")) {
            this.func_146079_cb();
        }
    }
    
    @Override
    public void onUpdate() {
        if (this.isEntityAlive()) {
            this.lastActiveTime = this.timeSinceIgnited;
            if (this.func_146078_ca()) {
                this.setCreeperState(1);
            }
            final int var1 = this.getCreeperState();
            if (var1 > 0 && this.timeSinceIgnited == 0) {
                this.playSound("creeper.primed", 1.0f, 0.5f);
            }
            this.timeSinceIgnited += var1;
            if (this.timeSinceIgnited < 0) {
                this.timeSinceIgnited = 0;
            }
            if (this.timeSinceIgnited >= this.fuseTime) {
                this.timeSinceIgnited = this.fuseTime;
                this.func_146077_cc();
            }
        }
        super.onUpdate();
    }
    
    @Override
    protected String getHurtSound() {
        return "mob.creeper.say";
    }
    
    @Override
    protected String getDeathSound() {
        return "mob.creeper.death";
    }
    
    @Override
    public void onDeath(final DamageSource cause) {
        super.onDeath(cause);
        if (cause.getEntity() instanceof EntitySkeleton) {
            final int var2 = Item.getIdFromItem(Items.record_13);
            final int var3 = Item.getIdFromItem(Items.record_wait);
            final int var4 = var2 + this.rand.nextInt(var3 - var2 + 1);
            this.dropItem(Item.getItemById(var4), 1);
        }
        else if (cause.getEntity() instanceof EntityCreeper && cause.getEntity() != this && ((EntityCreeper)cause.getEntity()).getPowered() && ((EntityCreeper)cause.getEntity()).isAIEnabled()) {
            ((EntityCreeper)cause.getEntity()).func_175493_co();
            this.entityDropItem(new ItemStack(Items.skull, 1, 4), 0.0f);
        }
    }
    
    @Override
    public boolean attackEntityAsMob(final Entity p_70652_1_) {
        return true;
    }
    
    public boolean getPowered() {
        return this.dataWatcher.getWatchableObjectByte(17) == 1;
    }
    
    public float getCreeperFlashIntensity(final float p_70831_1_) {
        return (this.lastActiveTime + (this.timeSinceIgnited - this.lastActiveTime) * p_70831_1_) / (this.fuseTime - 2);
    }
    
    @Override
    protected Item getDropItem() {
        return Items.gunpowder;
    }
    
    public int getCreeperState() {
        return this.dataWatcher.getWatchableObjectByte(16);
    }
    
    public void setCreeperState(final int p_70829_1_) {
        this.dataWatcher.updateObject(16, (byte)p_70829_1_);
    }
    
    @Override
    public void onStruckByLightning(final EntityLightningBolt lightningBolt) {
        super.onStruckByLightning(lightningBolt);
        this.dataWatcher.updateObject(17, (byte)1);
    }
    
    @Override
    protected boolean interact(final EntityPlayer p_70085_1_) {
        final ItemStack var2 = p_70085_1_.inventory.getCurrentItem();
        if (var2 != null && var2.getItem() == Items.flint_and_steel) {
            this.worldObj.playSoundEffect(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5, "fire.ignite", 1.0f, this.rand.nextFloat() * 0.4f + 0.8f);
            p_70085_1_.swingItem();
            if (!this.worldObj.isRemote) {
                this.func_146079_cb();
                var2.damageItem(1, p_70085_1_);
                return true;
            }
        }
        return super.interact(p_70085_1_);
    }
    
    private void func_146077_cc() {
        if (!this.worldObj.isRemote) {
            final boolean var1 = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
            final float var2 = this.getPowered() ? 2.0f : 1.0f;
            this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, this.explosionRadius * var2, var1);
            this.setDead();
        }
    }
    
    public boolean func_146078_ca() {
        return this.dataWatcher.getWatchableObjectByte(18) != 0;
    }
    
    public void func_146079_cb() {
        this.dataWatcher.updateObject(18, (byte)1);
    }
    
    public boolean isAIEnabled() {
        return this.field_175494_bm < 1 && this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot");
    }
    
    public void func_175493_co() {
        ++this.field_175494_bm;
    }
}
