// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.entity.item;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.init.Blocks;
import net.minecraft.world.storage.MapData;
import net.minecraft.item.ItemMap;
import net.minecraft.init.Items;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.EntityHanging;

public class EntityItemFrame extends EntityHanging
{
    private float itemDropChance;
    private static final String __OBFID = "CL_00001547";
    
    public EntityItemFrame(final World worldIn) {
        super(worldIn);
        this.itemDropChance = 1.0f;
    }
    
    public EntityItemFrame(final World worldIn, final BlockPos p_i45852_2_, final EnumFacing p_i45852_3_) {
        super(worldIn, p_i45852_2_);
        this.itemDropChance = 1.0f;
        this.func_174859_a(p_i45852_3_);
    }
    
    @Override
    protected void entityInit() {
        this.getDataWatcher().addObjectByDataType(8, 5);
        this.getDataWatcher().addObject(9, (byte)0);
    }
    
    @Override
    public float getCollisionBorderSize() {
        return 0.0f;
    }
    
    @Override
    public boolean attackEntityFrom(final DamageSource source, final float amount) {
        if (this.func_180431_b(source)) {
            return false;
        }
        if (!source.isExplosion() && this.getDisplayedItem() != null) {
            if (!this.worldObj.isRemote) {
                this.func_146065_b(source.getEntity(), false);
                this.setDisplayedItem(null);
            }
            return true;
        }
        return super.attackEntityFrom(source, amount);
    }
    
    @Override
    public int getWidthPixels() {
        return 12;
    }
    
    @Override
    public int getHeightPixels() {
        return 12;
    }
    
    @Override
    public boolean isInRangeToRenderDist(final double distance) {
        double var3 = 16.0;
        var3 *= 64.0 * this.renderDistanceWeight;
        return distance < var3 * var3;
    }
    
    @Override
    public void onBroken(final Entity p_110128_1_) {
        this.func_146065_b(p_110128_1_, true);
    }
    
    public void func_146065_b(final Entity p_146065_1_, final boolean p_146065_2_) {
        if (this.worldObj.getGameRules().getGameRuleBooleanValue("doTileDrops")) {
            ItemStack var3 = this.getDisplayedItem();
            if (p_146065_1_ instanceof EntityPlayer) {
                final EntityPlayer var4 = (EntityPlayer)p_146065_1_;
                if (var4.capabilities.isCreativeMode) {
                    this.removeFrameFromMap(var3);
                    return;
                }
            }
            if (p_146065_2_) {
                this.entityDropItem(new ItemStack(Items.item_frame), 0.0f);
            }
            if (var3 != null && this.rand.nextFloat() < this.itemDropChance) {
                var3 = var3.copy();
                this.removeFrameFromMap(var3);
                this.entityDropItem(var3, 0.0f);
            }
        }
    }
    
    private void removeFrameFromMap(final ItemStack p_110131_1_) {
        if (p_110131_1_ != null) {
            if (p_110131_1_.getItem() == Items.filled_map) {
                final MapData var2 = ((ItemMap)p_110131_1_.getItem()).getMapData(p_110131_1_, this.worldObj);
                var2.playersVisibleOnMap.remove("frame-" + this.getEntityId());
            }
            p_110131_1_.setItemFrame(null);
        }
    }
    
    public ItemStack getDisplayedItem() {
        return this.getDataWatcher().getWatchableObjectItemStack(8);
    }
    
    public void setDisplayedItem(final ItemStack p_82334_1_) {
        this.func_174864_a(p_82334_1_, true);
    }
    
    private void func_174864_a(ItemStack p_174864_1_, final boolean p_174864_2_) {
        if (p_174864_1_ != null) {
            p_174864_1_ = p_174864_1_.copy();
            p_174864_1_.stackSize = 1;
            p_174864_1_.setItemFrame(this);
        }
        this.getDataWatcher().updateObject(8, p_174864_1_);
        this.getDataWatcher().setObjectWatched(8);
        if (p_174864_2_ && this.field_174861_a != null) {
            this.worldObj.updateComparatorOutputLevel(this.field_174861_a, Blocks.air);
        }
    }
    
    public int getRotation() {
        return this.getDataWatcher().getWatchableObjectByte(9);
    }
    
    public void setItemRotation(final int p_82336_1_) {
        this.func_174865_a(p_82336_1_, true);
    }
    
    private void func_174865_a(final int p_174865_1_, final boolean p_174865_2_) {
        this.getDataWatcher().updateObject(9, (byte)(p_174865_1_ % 8));
        if (p_174865_2_ && this.field_174861_a != null) {
            this.worldObj.updateComparatorOutputLevel(this.field_174861_a, Blocks.air);
        }
    }
    
    @Override
    public void writeEntityToNBT(final NBTTagCompound tagCompound) {
        if (this.getDisplayedItem() != null) {
            tagCompound.setTag("Item", this.getDisplayedItem().writeToNBT(new NBTTagCompound()));
            tagCompound.setByte("ItemRotation", (byte)this.getRotation());
            tagCompound.setFloat("ItemDropChance", this.itemDropChance);
        }
        super.writeEntityToNBT(tagCompound);
    }
    
    @Override
    public void readEntityFromNBT(final NBTTagCompound tagCompund) {
        final NBTTagCompound var2 = tagCompund.getCompoundTag("Item");
        if (var2 != null && !var2.hasNoTags()) {
            this.func_174864_a(ItemStack.loadItemStackFromNBT(var2), false);
            this.func_174865_a(tagCompund.getByte("ItemRotation"), false);
            if (tagCompund.hasKey("ItemDropChance", 99)) {
                this.itemDropChance = tagCompund.getFloat("ItemDropChance");
            }
            if (tagCompund.hasKey("Direction")) {
                this.func_174865_a(this.getRotation() * 2, false);
            }
        }
        super.readEntityFromNBT(tagCompund);
    }
    
    @Override
    public boolean interactFirst(final EntityPlayer playerIn) {
        if (this.getDisplayedItem() == null) {
            final ItemStack var2 = playerIn.getHeldItem();
            if (var2 != null && !this.worldObj.isRemote) {
                this.setDisplayedItem(var2);
                if (!playerIn.capabilities.isCreativeMode) {
                    final ItemStack itemStack = var2;
                    if (--itemStack.stackSize <= 0) {
                        playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, null);
                    }
                }
            }
        }
        else if (!this.worldObj.isRemote) {
            this.setItemRotation(this.getRotation() + 1);
        }
        return true;
    }
    
    public int func_174866_q() {
        return (this.getDisplayedItem() == null) ? 0 : (this.getRotation() % 8 + 1);
    }
}
