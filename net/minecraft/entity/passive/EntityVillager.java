// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.entity.passive;

import net.minecraft.util.Tuple;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.util.MathHelper;
import net.minecraft.enchantment.Enchantment;
import java.util.Random;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.init.Items;
import java.util.Iterator;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.Potion;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.util.BlockPos;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHarvestFarmland;
import net.minecraft.entity.ai.EntityAIPlay;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIVillagerInteract;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.ai.EntityAIFollowGolem;
import net.minecraft.entity.ai.EntityAIVillagerMate;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAILookAtTradePlayer;
import net.minecraft.entity.ai.EntityAITradePlayer;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.Entity;
import com.google.common.base.Predicate;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.world.World;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.village.Village;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.EntityAgeable;

public class EntityVillager extends EntityAgeable implements INpc, IMerchant
{
    private int randomTickDivider;
    private boolean isMating;
    private boolean isPlaying;
    Village villageObj;
    private EntityPlayer buyingPlayer;
    private MerchantRecipeList buyingList;
    private int timeUntilReset;
    private boolean needsInitilization;
    private boolean field_175565_bs;
    private int wealth;
    private String lastBuyingPlayer;
    private int field_175563_bv;
    private int field_175562_bw;
    private boolean isLookingForHome;
    private boolean field_175564_by;
    private InventoryBasic field_175560_bz;
    private static final ITradeList[][][][] field_175561_bA;
    private static final String __OBFID = "CL_00001707";
    
    public EntityVillager(final World worldIn) {
        this(worldIn, 0);
    }
    
    public EntityVillager(final World worldIn, final int p_i1748_2_) {
        super(worldIn);
        this.field_175560_bz = new InventoryBasic("Items", false, 8);
        this.setProfession(p_i1748_2_);
        this.setSize(0.6f, 1.8f);
        ((PathNavigateGround)this.getNavigator()).func_179688_b(true);
        ((PathNavigateGround)this.getNavigator()).func_179690_a(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, (Predicate)new Predicate() {
            private static final String __OBFID = "CL_00002195";
            
            public boolean func_179530_a(final Entity p_179530_1_) {
                return p_179530_1_ instanceof EntityZombie;
            }
            
            public boolean apply(final Object p_apply_1_) {
                return this.func_179530_a((Entity)p_apply_1_);
            }
        }, 8.0f, 0.6, 0.6));
        this.tasks.addTask(1, new EntityAITradePlayer(this));
        this.tasks.addTask(1, new EntityAILookAtTradePlayer(this));
        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6));
        this.tasks.addTask(6, new EntityAIVillagerMate(this));
        this.tasks.addTask(7, new EntityAIFollowGolem(this));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0f, 1.0f));
        this.tasks.addTask(9, new EntityAIVillagerInteract(this));
        this.tasks.addTask(9, new EntityAIWander(this, 0.6));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0f));
        this.setCanPickUpLoot(true);
    }
    
    private void func_175552_ct() {
        if (!this.field_175564_by) {
            this.field_175564_by = true;
            if (this.isChild()) {
                this.tasks.addTask(8, new EntityAIPlay(this, 0.32));
            }
            else if (this.getProfession() == 0) {
                this.tasks.addTask(6, new EntityAIHarvestFarmland(this, 0.6));
            }
        }
    }
    
    @Override
    protected void func_175500_n() {
        if (this.getProfession() == 0) {
            this.tasks.addTask(8, new EntityAIHarvestFarmland(this, 0.6));
        }
        super.func_175500_n();
    }
    
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5);
    }
    
    @Override
    protected void updateAITasks() {
        final int randomTickDivider = this.randomTickDivider - 1;
        this.randomTickDivider = randomTickDivider;
        if (randomTickDivider <= 0) {
            final BlockPos var1 = new BlockPos(this);
            this.worldObj.getVillageCollection().func_176060_a(var1);
            this.randomTickDivider = 70 + this.rand.nextInt(50);
            this.villageObj = this.worldObj.getVillageCollection().func_176056_a(var1, 32);
            if (this.villageObj == null) {
                this.detachHome();
            }
            else {
                final BlockPos var2 = this.villageObj.func_180608_a();
                this.func_175449_a(var2, (int)(this.villageObj.getVillageRadius() * 1.0f));
                if (this.isLookingForHome) {
                    this.isLookingForHome = false;
                    this.villageObj.setDefaultPlayerReputation(5);
                }
            }
        }
        if (!this.isTrading() && this.timeUntilReset > 0) {
            --this.timeUntilReset;
            if (this.timeUntilReset <= 0) {
                if (this.needsInitilization) {
                    for (final MerchantRecipe var4 : this.buyingList) {
                        if (var4.isRecipeDisabled()) {
                            var4.func_82783_a(this.rand.nextInt(6) + this.rand.nextInt(6) + 2);
                        }
                    }
                    this.func_175554_cu();
                    this.needsInitilization = false;
                    if (this.villageObj != null && this.lastBuyingPlayer != null) {
                        this.worldObj.setEntityState(this, (byte)14);
                        this.villageObj.setReputationForPlayer(this.lastBuyingPlayer, 1);
                    }
                }
                this.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 0));
            }
        }
        super.updateAITasks();
    }
    
    @Override
    public boolean interact(final EntityPlayer p_70085_1_) {
        final ItemStack var2 = p_70085_1_.inventory.getCurrentItem();
        final boolean var3 = var2 != null && var2.getItem() == Items.spawn_egg;
        if (!var3 && this.isEntityAlive() && !this.isTrading() && !this.isChild()) {
            if (!this.worldObj.isRemote && (this.buyingList == null || this.buyingList.size() > 0)) {
                this.setCustomer(p_70085_1_);
                p_70085_1_.displayVillagerTradeGui(this);
            }
            p_70085_1_.triggerAchievement(StatList.timesTalkedToVillagerStat);
            return true;
        }
        return super.interact(p_70085_1_);
    }
    
    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, 0);
    }
    
    @Override
    public void writeEntityToNBT(final NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setInteger("Profession", this.getProfession());
        tagCompound.setInteger("Riches", this.wealth);
        tagCompound.setInteger("Career", this.field_175563_bv);
        tagCompound.setInteger("CareerLevel", this.field_175562_bw);
        tagCompound.setBoolean("Willing", this.field_175565_bs);
        if (this.buyingList != null) {
            tagCompound.setTag("Offers", this.buyingList.getRecipiesAsTags());
        }
        final NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.field_175560_bz.getSizeInventory(); ++var3) {
            final ItemStack var4 = this.field_175560_bz.getStackInSlot(var3);
            if (var4 != null) {
                var2.appendTag(var4.writeToNBT(new NBTTagCompound()));
            }
        }
        tagCompound.setTag("Inventory", var2);
    }
    
    @Override
    public void readEntityFromNBT(final NBTTagCompound tagCompund) {
        super.readEntityFromNBT(tagCompund);
        this.setProfession(tagCompund.getInteger("Profession"));
        this.wealth = tagCompund.getInteger("Riches");
        this.field_175563_bv = tagCompund.getInteger("Career");
        this.field_175562_bw = tagCompund.getInteger("CareerLevel");
        this.field_175565_bs = tagCompund.getBoolean("Willing");
        if (tagCompund.hasKey("Offers", 10)) {
            final NBTTagCompound var2 = tagCompund.getCompoundTag("Offers");
            this.buyingList = new MerchantRecipeList(var2);
        }
        final NBTTagList var3 = tagCompund.getTagList("Inventory", 10);
        for (int var4 = 0; var4 < var3.tagCount(); ++var4) {
            final ItemStack var5 = ItemStack.loadItemStackFromNBT(var3.getCompoundTagAt(var4));
            if (var5 != null) {
                this.field_175560_bz.func_174894_a(var5);
            }
        }
        this.setCanPickUpLoot(true);
        this.func_175552_ct();
    }
    
    @Override
    protected boolean canDespawn() {
        return false;
    }
    
    @Override
    protected String getLivingSound() {
        return this.isTrading() ? "mob.villager.haggle" : "mob.villager.idle";
    }
    
    @Override
    protected String getHurtSound() {
        return "mob.villager.hit";
    }
    
    @Override
    protected String getDeathSound() {
        return "mob.villager.death";
    }
    
    public void setProfession(final int p_70938_1_) {
        this.dataWatcher.updateObject(16, p_70938_1_);
    }
    
    public int getProfession() {
        return Math.max(this.dataWatcher.getWatchableObjectInt(16) % 5, 0);
    }
    
    public boolean isMating() {
        return this.isMating;
    }
    
    public void setMating(final boolean p_70947_1_) {
        this.isMating = p_70947_1_;
    }
    
    public void setPlaying(final boolean p_70939_1_) {
        this.isPlaying = p_70939_1_;
    }
    
    public boolean isPlaying() {
        return this.isPlaying;
    }
    
    @Override
    public void setRevengeTarget(final EntityLivingBase p_70604_1_) {
        super.setRevengeTarget(p_70604_1_);
        if (this.villageObj != null && p_70604_1_ != null) {
            this.villageObj.addOrRenewAgressor(p_70604_1_);
            if (p_70604_1_ instanceof EntityPlayer) {
                byte var2 = -1;
                if (this.isChild()) {
                    var2 = -3;
                }
                this.villageObj.setReputationForPlayer(p_70604_1_.getName(), var2);
                if (this.isEntityAlive()) {
                    this.worldObj.setEntityState(this, (byte)13);
                }
            }
        }
    }
    
    @Override
    public void onDeath(final DamageSource cause) {
        if (this.villageObj != null) {
            final Entity var2 = cause.getEntity();
            if (var2 != null) {
                if (var2 instanceof EntityPlayer) {
                    this.villageObj.setReputationForPlayer(var2.getName(), -2);
                }
                else if (var2 instanceof IMob) {
                    this.villageObj.endMatingSeason();
                }
            }
            else {
                final EntityPlayer var3 = this.worldObj.getClosestPlayerToEntity(this, 16.0);
                if (var3 != null) {
                    this.villageObj.endMatingSeason();
                }
            }
        }
        super.onDeath(cause);
    }
    
    @Override
    public void setCustomer(final EntityPlayer p_70932_1_) {
        this.buyingPlayer = p_70932_1_;
    }
    
    @Override
    public EntityPlayer getCustomer() {
        return this.buyingPlayer;
    }
    
    public boolean isTrading() {
        return this.buyingPlayer != null;
    }
    
    public boolean func_175550_n(final boolean p_175550_1_) {
        if (!this.field_175565_bs && p_175550_1_ && this.func_175553_cp()) {
            boolean var2 = false;
            for (int var3 = 0; var3 < this.field_175560_bz.getSizeInventory(); ++var3) {
                final ItemStack var4 = this.field_175560_bz.getStackInSlot(var3);
                if (var4 != null) {
                    if (var4.getItem() == Items.bread && var4.stackSize >= 3) {
                        var2 = true;
                        this.field_175560_bz.decrStackSize(var3, 3);
                    }
                    else if ((var4.getItem() == Items.potato || var4.getItem() == Items.carrot) && var4.stackSize >= 12) {
                        var2 = true;
                        this.field_175560_bz.decrStackSize(var3, 12);
                    }
                }
                if (var2) {
                    this.worldObj.setEntityState(this, (byte)18);
                    this.field_175565_bs = true;
                    break;
                }
            }
        }
        return this.field_175565_bs;
    }
    
    public void func_175549_o(final boolean p_175549_1_) {
        this.field_175565_bs = p_175549_1_;
    }
    
    @Override
    public void useRecipe(final MerchantRecipe p_70933_1_) {
        p_70933_1_.incrementToolUses();
        this.livingSoundTime = -this.getTalkInterval();
        this.playSound("mob.villager.yes", this.getSoundVolume(), this.getSoundPitch());
        int var2 = 3 + this.rand.nextInt(4);
        if (p_70933_1_.func_180321_e() == 1 || this.rand.nextInt(5) == 0) {
            this.timeUntilReset = 40;
            this.needsInitilization = true;
            this.field_175565_bs = true;
            if (this.buyingPlayer != null) {
                this.lastBuyingPlayer = this.buyingPlayer.getName();
            }
            else {
                this.lastBuyingPlayer = null;
            }
            var2 += 5;
        }
        if (p_70933_1_.getItemToBuy().getItem() == Items.emerald) {
            this.wealth += p_70933_1_.getItemToBuy().stackSize;
        }
        if (p_70933_1_.func_180322_j()) {
            this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY + 0.5, this.posZ, var2));
        }
    }
    
    @Override
    public void verifySellingItem(final ItemStack p_110297_1_) {
        if (!this.worldObj.isRemote && this.livingSoundTime > -this.getTalkInterval() + 20) {
            this.livingSoundTime = -this.getTalkInterval();
            if (p_110297_1_ != null) {
                this.playSound("mob.villager.yes", this.getSoundVolume(), this.getSoundPitch());
            }
            else {
                this.playSound("mob.villager.no", this.getSoundVolume(), this.getSoundPitch());
            }
        }
    }
    
    @Override
    public MerchantRecipeList getRecipes(final EntityPlayer p_70934_1_) {
        if (this.buyingList == null) {
            this.func_175554_cu();
        }
        return this.buyingList;
    }
    
    private void func_175554_cu() {
        final ITradeList[][][] var1 = EntityVillager.field_175561_bA[this.getProfession()];
        if (this.field_175563_bv != 0 && this.field_175562_bw != 0) {
            ++this.field_175562_bw;
        }
        else {
            this.field_175563_bv = this.rand.nextInt(var1.length) + 1;
            this.field_175562_bw = 1;
        }
        if (this.buyingList == null) {
            this.buyingList = new MerchantRecipeList();
        }
        final int var2 = this.field_175563_bv - 1;
        final int var3 = this.field_175562_bw - 1;
        final ITradeList[][] var4 = var1[var2];
        if (var3 < var4.length) {
            final ITradeList[] var6;
            final ITradeList[] var5 = var6 = var4[var3];
            for (int var7 = var5.length, var8 = 0; var8 < var7; ++var8) {
                final ITradeList var9 = var6[var8];
                var9.func_179401_a(this.buyingList, this.rand);
            }
        }
    }
    
    @Override
    public void setRecipes(final MerchantRecipeList p_70930_1_) {
    }
    
    @Override
    public IChatComponent getDisplayName() {
        final String var1 = this.getCustomNameTag();
        if (var1 != null && var1.length() > 0) {
            return new ChatComponentText(var1);
        }
        if (this.buyingList == null) {
            this.func_175554_cu();
        }
        String var2 = null;
        switch (this.getProfession()) {
            case 0: {
                if (this.field_175563_bv == 1) {
                    var2 = "farmer";
                    break;
                }
                if (this.field_175563_bv == 2) {
                    var2 = "fisherman";
                    break;
                }
                if (this.field_175563_bv == 3) {
                    var2 = "shepherd";
                    break;
                }
                if (this.field_175563_bv == 4) {
                    var2 = "fletcher";
                    break;
                }
                break;
            }
            case 1: {
                var2 = "librarian";
                break;
            }
            case 2: {
                var2 = "cleric";
                break;
            }
            case 3: {
                if (this.field_175563_bv == 1) {
                    var2 = "armor";
                    break;
                }
                if (this.field_175563_bv == 2) {
                    var2 = "weapon";
                    break;
                }
                if (this.field_175563_bv == 3) {
                    var2 = "tool";
                    break;
                }
                break;
            }
            case 4: {
                if (this.field_175563_bv == 1) {
                    var2 = "butcher";
                    break;
                }
                if (this.field_175563_bv == 2) {
                    var2 = "leather";
                    break;
                }
                break;
            }
        }
        if (var2 != null) {
            final ChatComponentTranslation var3 = new ChatComponentTranslation("entity.Villager." + var2, new Object[0]);
            var3.getChatStyle().setChatHoverEvent(this.func_174823_aP());
            var3.getChatStyle().setInsertion(this.getUniqueID().toString());
            return var3;
        }
        return super.getDisplayName();
    }
    
    @Override
    public float getEyeHeight() {
        float var1 = 1.62f;
        if (this.isChild()) {
            var1 -= 0.81;
        }
        return var1;
    }
    
    @Override
    public void handleHealthUpdate(final byte p_70103_1_) {
        if (p_70103_1_ == 12) {
            this.func_180489_a(EnumParticleTypes.HEART);
        }
        else if (p_70103_1_ == 13) {
            this.func_180489_a(EnumParticleTypes.VILLAGER_ANGRY);
        }
        else if (p_70103_1_ == 14) {
            this.func_180489_a(EnumParticleTypes.VILLAGER_HAPPY);
        }
        else {
            super.handleHealthUpdate(p_70103_1_);
        }
    }
    
    private void func_180489_a(final EnumParticleTypes p_180489_1_) {
        for (int var2 = 0; var2 < 5; ++var2) {
            final double var3 = this.rand.nextGaussian() * 0.02;
            final double var4 = this.rand.nextGaussian() * 0.02;
            final double var5 = this.rand.nextGaussian() * 0.02;
            this.worldObj.spawnParticle(p_180489_1_, this.posX + this.rand.nextFloat() * this.width * 2.0f - this.width, this.posY + 1.0 + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0f - this.width, var3, var4, var5, new int[0]);
        }
    }
    
    @Override
    public IEntityLivingData func_180482_a(final DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
        p_180482_2_ = super.func_180482_a(p_180482_1_, p_180482_2_);
        this.setProfession(this.worldObj.rand.nextInt(5));
        this.func_175552_ct();
        return p_180482_2_;
    }
    
    public void setLookingForHome() {
        this.isLookingForHome = true;
    }
    
    public EntityVillager func_180488_b(final EntityAgeable p_180488_1_) {
        final EntityVillager var2 = new EntityVillager(this.worldObj);
        var2.func_180482_a(this.worldObj.getDifficultyForLocation(new BlockPos(var2)), null);
        return var2;
    }
    
    @Override
    public boolean allowLeashing() {
        return false;
    }
    
    @Override
    public void onStruckByLightning(final EntityLightningBolt lightningBolt) {
        if (!this.worldObj.isRemote) {
            final EntityWitch var2 = new EntityWitch(this.worldObj);
            var2.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            var2.func_180482_a(this.worldObj.getDifficultyForLocation(new BlockPos(var2)), null);
            this.worldObj.spawnEntityInWorld(var2);
            this.setDead();
        }
    }
    
    public InventoryBasic func_175551_co() {
        return this.field_175560_bz;
    }
    
    @Override
    protected void func_175445_a(final EntityItem p_175445_1_) {
        final ItemStack var2 = p_175445_1_.getEntityItem();
        final Item var3 = var2.getItem();
        if (this.func_175558_a(var3)) {
            final ItemStack var4 = this.field_175560_bz.func_174894_a(var2);
            if (var4 == null) {
                p_175445_1_.setDead();
            }
            else {
                var2.stackSize = var4.stackSize;
            }
        }
    }
    
    private boolean func_175558_a(final Item p_175558_1_) {
        return p_175558_1_ == Items.bread || p_175558_1_ == Items.potato || p_175558_1_ == Items.carrot || p_175558_1_ == Items.wheat || p_175558_1_ == Items.wheat_seeds;
    }
    
    public boolean func_175553_cp() {
        return this.func_175559_s(1);
    }
    
    public boolean func_175555_cq() {
        return this.func_175559_s(2);
    }
    
    public boolean func_175557_cr() {
        final boolean var1 = this.getProfession() == 0;
        return var1 ? (!this.func_175559_s(5)) : (!this.func_175559_s(1));
    }
    
    private boolean func_175559_s(final int p_175559_1_) {
        final boolean var2 = this.getProfession() == 0;
        for (int var3 = 0; var3 < this.field_175560_bz.getSizeInventory(); ++var3) {
            final ItemStack var4 = this.field_175560_bz.getStackInSlot(var3);
            if (var4 != null) {
                if ((var4.getItem() == Items.bread && var4.stackSize >= 3 * p_175559_1_) || (var4.getItem() == Items.potato && var4.stackSize >= 12 * p_175559_1_) || (var4.getItem() == Items.carrot && var4.stackSize >= 12 * p_175559_1_)) {
                    return true;
                }
                if (var2 && var4.getItem() == Items.wheat && var4.stackSize >= 9 * p_175559_1_) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean func_175556_cs() {
        for (int var1 = 0; var1 < this.field_175560_bz.getSizeInventory(); ++var1) {
            final ItemStack var2 = this.field_175560_bz.getStackInSlot(var1);
            if (var2 != null && (var2.getItem() == Items.wheat_seeds || var2.getItem() == Items.potato || var2.getItem() == Items.carrot)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean func_174820_d(final int p_174820_1_, final ItemStack p_174820_2_) {
        if (super.func_174820_d(p_174820_1_, p_174820_2_)) {
            return true;
        }
        final int var3 = p_174820_1_ - 300;
        if (var3 >= 0 && var3 < this.field_175560_bz.getSizeInventory()) {
            this.field_175560_bz.setInventorySlotContents(var3, p_174820_2_);
            return true;
        }
        return false;
    }
    
    @Override
    public EntityAgeable createChild(final EntityAgeable p_90011_1_) {
        return this.func_180488_b(p_90011_1_);
    }
    
    static {
        field_175561_bA = new ITradeList[][][][] { { { { new EmeraldForItems(Items.wheat, new PriceInfo(18, 22)), new EmeraldForItems(Items.potato, new PriceInfo(15, 19)), new EmeraldForItems(Items.carrot, new PriceInfo(15, 19)), new ListItemForEmeralds(Items.bread, new PriceInfo(-4, -2)) }, { new EmeraldForItems(Item.getItemFromBlock(Blocks.pumpkin), new PriceInfo(8, 13)), new ListItemForEmeralds(Items.pumpkin_pie, new PriceInfo(-3, -2)) }, { new EmeraldForItems(Item.getItemFromBlock(Blocks.melon_block), new PriceInfo(7, 12)), new ListItemForEmeralds(Items.apple, new PriceInfo(-5, -7)) }, { new ListItemForEmeralds(Items.cookie, new PriceInfo(-6, -10)), new ListItemForEmeralds(Items.cake, new PriceInfo(1, 1)) } }, { { new EmeraldForItems(Items.string, new PriceInfo(15, 20)), new EmeraldForItems(Items.coal, new PriceInfo(16, 24)), new ItemAndEmeraldToItem(Items.fish, new PriceInfo(6, 6), Items.cooked_fish, new PriceInfo(6, 6)) }, { new ListEnchantedItemForEmeralds(Items.fishing_rod, new PriceInfo(7, 8)) } }, { { new EmeraldForItems(Item.getItemFromBlock(Blocks.wool), new PriceInfo(16, 22)), new ListItemForEmeralds(Items.shears, new PriceInfo(3, 4)) }, { new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 0), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 1), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 2), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 3), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 4), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 5), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 6), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 7), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 8), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 9), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 10), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 11), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 12), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 13), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 14), new PriceInfo(1, 2)), new ListItemForEmeralds(new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 15), new PriceInfo(1, 2)) } }, { { new EmeraldForItems(Items.string, new PriceInfo(15, 20)), new ListItemForEmeralds(Items.arrow, new PriceInfo(-12, -8)) }, { new ListItemForEmeralds(Items.bow, new PriceInfo(2, 3)), new ItemAndEmeraldToItem(Item.getItemFromBlock(Blocks.gravel), new PriceInfo(10, 10), Items.flint, new PriceInfo(6, 10)) } } }, { { { new EmeraldForItems(Items.paper, new PriceInfo(24, 36)), new ListEnchantedBookForEmeralds() }, { new EmeraldForItems(Items.book, new PriceInfo(8, 10)), new ListItemForEmeralds(Items.compass, new PriceInfo(10, 12)), new ListItemForEmeralds(Item.getItemFromBlock(Blocks.bookshelf), new PriceInfo(3, 4)) }, { new EmeraldForItems(Items.written_book, new PriceInfo(2, 2)), new ListItemForEmeralds(Items.clock, new PriceInfo(10, 12)), new ListItemForEmeralds(Item.getItemFromBlock(Blocks.glass), new PriceInfo(-5, -3)) }, { new ListEnchantedBookForEmeralds() }, { new ListEnchantedBookForEmeralds() }, { new ListItemForEmeralds(Items.name_tag, new PriceInfo(20, 22)) } } }, { { { new EmeraldForItems(Items.rotten_flesh, new PriceInfo(36, 40)), new EmeraldForItems(Items.gold_ingot, new PriceInfo(8, 10)) }, { new ListItemForEmeralds(Items.redstone, new PriceInfo(-4, -1)), new ListItemForEmeralds(new ItemStack(Items.dye, 1, EnumDyeColor.BLUE.getDyeColorDamage()), new PriceInfo(-2, -1)) }, { new ListItemForEmeralds(Items.ender_eye, new PriceInfo(7, 11)), new ListItemForEmeralds(Item.getItemFromBlock(Blocks.glowstone), new PriceInfo(-3, -1)) }, { new ListItemForEmeralds(Items.experience_bottle, new PriceInfo(3, 11)) } } }, { { { new EmeraldForItems(Items.coal, new PriceInfo(16, 24)), new ListItemForEmeralds(Items.iron_helmet, new PriceInfo(4, 6)) }, { new EmeraldForItems(Items.iron_ingot, new PriceInfo(7, 9)), new ListItemForEmeralds(Items.iron_chestplate, new PriceInfo(10, 14)) }, { new EmeraldForItems(Items.diamond, new PriceInfo(3, 4)), new ListEnchantedItemForEmeralds(Items.diamond_chestplate, new PriceInfo(16, 19)) }, { new ListItemForEmeralds(Items.chainmail_boots, new PriceInfo(5, 7)), new ListItemForEmeralds(Items.chainmail_leggings, new PriceInfo(9, 11)), new ListItemForEmeralds(Items.chainmail_helmet, new PriceInfo(5, 7)), new ListItemForEmeralds(Items.chainmail_chestplate, new PriceInfo(11, 15)) } }, { { new EmeraldForItems(Items.coal, new PriceInfo(16, 24)), new ListItemForEmeralds(Items.iron_axe, new PriceInfo(6, 8)) }, { new EmeraldForItems(Items.iron_ingot, new PriceInfo(7, 9)), new ListEnchantedItemForEmeralds(Items.iron_sword, new PriceInfo(9, 10)) }, { new EmeraldForItems(Items.diamond, new PriceInfo(3, 4)), new ListEnchantedItemForEmeralds(Items.diamond_sword, new PriceInfo(12, 15)), new ListEnchantedItemForEmeralds(Items.diamond_axe, new PriceInfo(9, 12)) } }, { { new EmeraldForItems(Items.coal, new PriceInfo(16, 24)), new ListEnchantedItemForEmeralds(Items.iron_shovel, new PriceInfo(5, 7)) }, { new EmeraldForItems(Items.iron_ingot, new PriceInfo(7, 9)), new ListEnchantedItemForEmeralds(Items.iron_pickaxe, new PriceInfo(9, 11)) }, { new EmeraldForItems(Items.diamond, new PriceInfo(3, 4)), new ListEnchantedItemForEmeralds(Items.diamond_pickaxe, new PriceInfo(12, 15)) } } }, { { { new EmeraldForItems(Items.porkchop, new PriceInfo(14, 18)), new EmeraldForItems(Items.chicken, new PriceInfo(14, 18)) }, { new EmeraldForItems(Items.coal, new PriceInfo(16, 24)), new ListItemForEmeralds(Items.cooked_porkchop, new PriceInfo(-7, -5)), new ListItemForEmeralds(Items.cooked_chicken, new PriceInfo(-8, -6)) } }, { { new EmeraldForItems(Items.leather, new PriceInfo(9, 12)), new ListItemForEmeralds(Items.leather_leggings, new PriceInfo(2, 4)) }, { new ListEnchantedItemForEmeralds(Items.leather_chestplate, new PriceInfo(7, 12)) }, { new ListItemForEmeralds(Items.saddle, new PriceInfo(8, 10)) } } } };
    }
    
    static class EmeraldForItems implements ITradeList
    {
        public Item field_179405_a;
        public PriceInfo field_179404_b;
        private static final String __OBFID = "CL_00002194";
        
        public EmeraldForItems(final Item p_i45815_1_, final PriceInfo p_i45815_2_) {
            this.field_179405_a = p_i45815_1_;
            this.field_179404_b = p_i45815_2_;
        }
        
        @Override
        public void func_179401_a(final MerchantRecipeList p_179401_1_, final Random p_179401_2_) {
            int var3 = 1;
            if (this.field_179404_b != null) {
                var3 = this.field_179404_b.func_179412_a(p_179401_2_);
            }
            p_179401_1_.add(new MerchantRecipe(new ItemStack(this.field_179405_a, var3, 0), Items.emerald));
        }
    }
    
    static class ItemAndEmeraldToItem implements ITradeList
    {
        public ItemStack field_179411_a;
        public PriceInfo field_179409_b;
        public ItemStack field_179410_c;
        public PriceInfo field_179408_d;
        private static final String __OBFID = "CL_00002191";
        
        public ItemAndEmeraldToItem(final Item p_i45813_1_, final PriceInfo p_i45813_2_, final Item p_i45813_3_, final PriceInfo p_i45813_4_) {
            this.field_179411_a = new ItemStack(p_i45813_1_);
            this.field_179409_b = p_i45813_2_;
            this.field_179410_c = new ItemStack(p_i45813_3_);
            this.field_179408_d = p_i45813_4_;
        }
        
        @Override
        public void func_179401_a(final MerchantRecipeList p_179401_1_, final Random p_179401_2_) {
            int var3 = 1;
            if (this.field_179409_b != null) {
                var3 = this.field_179409_b.func_179412_a(p_179401_2_);
            }
            int var4 = 1;
            if (this.field_179408_d != null) {
                var4 = this.field_179408_d.func_179412_a(p_179401_2_);
            }
            p_179401_1_.add(new MerchantRecipe(new ItemStack(this.field_179411_a.getItem(), var3, this.field_179411_a.getMetadata()), new ItemStack(Items.emerald), new ItemStack(this.field_179410_c.getItem(), var4, this.field_179410_c.getMetadata())));
        }
    }
    
    static class ListEnchantedBookForEmeralds implements ITradeList
    {
        private static final String __OBFID = "CL_00002193";
        
        @Override
        public void func_179401_a(final MerchantRecipeList p_179401_1_, final Random p_179401_2_) {
            final Enchantment var3 = Enchantment.enchantmentsList[p_179401_2_.nextInt(Enchantment.enchantmentsList.length)];
            final int var4 = MathHelper.getRandomIntegerInRange(p_179401_2_, var3.getMinLevel(), var3.getMaxLevel());
            final ItemStack var5 = Items.enchanted_book.getEnchantedItemStack(new EnchantmentData(var3, var4));
            int var6 = 2 + p_179401_2_.nextInt(5 + var4 * 10) + 3 * var4;
            if (var6 > 64) {
                var6 = 64;
            }
            p_179401_1_.add(new MerchantRecipe(new ItemStack(Items.book), new ItemStack(Items.emerald, var6), var5));
        }
    }
    
    static class ListEnchantedItemForEmeralds implements ITradeList
    {
        public ItemStack field_179407_a;
        public PriceInfo field_179406_b;
        private static final String __OBFID = "CL_00002192";
        
        public ListEnchantedItemForEmeralds(final Item p_i45814_1_, final PriceInfo p_i45814_2_) {
            this.field_179407_a = new ItemStack(p_i45814_1_);
            this.field_179406_b = p_i45814_2_;
        }
        
        @Override
        public void func_179401_a(final MerchantRecipeList p_179401_1_, final Random p_179401_2_) {
            int var3 = 1;
            if (this.field_179406_b != null) {
                var3 = this.field_179406_b.func_179412_a(p_179401_2_);
            }
            final ItemStack var4 = new ItemStack(Items.emerald, var3, 0);
            ItemStack var5 = new ItemStack(this.field_179407_a.getItem(), 1, this.field_179407_a.getMetadata());
            var5 = EnchantmentHelper.addRandomEnchantment(p_179401_2_, var5, 5 + p_179401_2_.nextInt(15));
            p_179401_1_.add(new MerchantRecipe(var4, var5));
        }
    }
    
    static class ListItemForEmeralds implements ITradeList
    {
        public ItemStack field_179403_a;
        public PriceInfo field_179402_b;
        private static final String __OBFID = "CL_00002190";
        
        public ListItemForEmeralds(final Item p_i45811_1_, final PriceInfo p_i45811_2_) {
            this.field_179403_a = new ItemStack(p_i45811_1_);
            this.field_179402_b = p_i45811_2_;
        }
        
        public ListItemForEmeralds(final ItemStack p_i45812_1_, final PriceInfo p_i45812_2_) {
            this.field_179403_a = p_i45812_1_;
            this.field_179402_b = p_i45812_2_;
        }
        
        @Override
        public void func_179401_a(final MerchantRecipeList p_179401_1_, final Random p_179401_2_) {
            int var3 = 1;
            if (this.field_179402_b != null) {
                var3 = this.field_179402_b.func_179412_a(p_179401_2_);
            }
            ItemStack var4;
            ItemStack var5;
            if (var3 < 0) {
                var4 = new ItemStack(Items.emerald, 1, 0);
                var5 = new ItemStack(this.field_179403_a.getItem(), -var3, this.field_179403_a.getMetadata());
            }
            else {
                var4 = new ItemStack(Items.emerald, var3, 0);
                var5 = new ItemStack(this.field_179403_a.getItem(), 1, this.field_179403_a.getMetadata());
            }
            p_179401_1_.add(new MerchantRecipe(var4, var5));
        }
    }
    
    static class PriceInfo extends Tuple
    {
        private static final String __OBFID = "CL_00002189";
        
        public PriceInfo(final int p_i45810_1_, final int p_i45810_2_) {
            super(p_i45810_1_, p_i45810_2_);
        }
        
        public int func_179412_a(final Random p_179412_1_) {
            return (int)(((int)this.getFirst() >= (int)this.getSecond()) ? this.getFirst() : ((int)this.getFirst() + p_179412_1_.nextInt((int)this.getSecond() - (int)this.getFirst() + 1)));
        }
    }
    
    interface ITradeList
    {
        void func_179401_a(final MerchantRecipeList p0, final Random p1);
    }
}
