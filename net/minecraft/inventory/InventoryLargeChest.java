// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.inventory;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.LockCode;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.ILockableContainer;

public class InventoryLargeChest implements ILockableContainer
{
    private String name;
    private ILockableContainer upperChest;
    private ILockableContainer lowerChest;
    private static final String __OBFID = "CL_00001507";
    
    public InventoryLargeChest(final String p_i45905_1_, ILockableContainer p_i45905_2_, ILockableContainer p_i45905_3_) {
        this.name = p_i45905_1_;
        if (p_i45905_2_ == null) {
            p_i45905_2_ = p_i45905_3_;
        }
        if (p_i45905_3_ == null) {
            p_i45905_3_ = p_i45905_2_;
        }
        this.upperChest = p_i45905_2_;
        this.lowerChest = p_i45905_3_;
        if (p_i45905_2_.isLocked()) {
            p_i45905_3_.setLockCode(p_i45905_2_.getLockCode());
        }
        else if (p_i45905_3_.isLocked()) {
            p_i45905_2_.setLockCode(p_i45905_3_.getLockCode());
        }
    }
    
    @Override
    public int getSizeInventory() {
        return this.upperChest.getSizeInventory() + this.lowerChest.getSizeInventory();
    }
    
    public boolean isPartOfLargeChest(final IInventory p_90010_1_) {
        return this.upperChest == p_90010_1_ || this.lowerChest == p_90010_1_;
    }
    
    @Override
    public String getName() {
        return this.upperChest.hasCustomName() ? this.upperChest.getName() : (this.lowerChest.hasCustomName() ? this.lowerChest.getName() : this.name);
    }
    
    @Override
    public boolean hasCustomName() {
        return this.upperChest.hasCustomName() || this.lowerChest.hasCustomName();
    }
    
    @Override
    public IChatComponent getDisplayName() {
        return this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName(), new Object[0]);
    }
    
    @Override
    public ItemStack getStackInSlot(final int slotIn) {
        return (slotIn >= this.upperChest.getSizeInventory()) ? this.lowerChest.getStackInSlot(slotIn - this.upperChest.getSizeInventory()) : this.upperChest.getStackInSlot(slotIn);
    }
    
    @Override
    public ItemStack decrStackSize(final int index, final int count) {
        return (index >= this.upperChest.getSizeInventory()) ? this.lowerChest.decrStackSize(index - this.upperChest.getSizeInventory(), count) : this.upperChest.decrStackSize(index, count);
    }
    
    @Override
    public ItemStack getStackInSlotOnClosing(final int index) {
        return (index >= this.upperChest.getSizeInventory()) ? this.lowerChest.getStackInSlotOnClosing(index - this.upperChest.getSizeInventory()) : this.upperChest.getStackInSlotOnClosing(index);
    }
    
    @Override
    public void setInventorySlotContents(final int index, final ItemStack stack) {
        if (index >= this.upperChest.getSizeInventory()) {
            this.lowerChest.setInventorySlotContents(index - this.upperChest.getSizeInventory(), stack);
        }
        else {
            this.upperChest.setInventorySlotContents(index, stack);
        }
    }
    
    @Override
    public int getInventoryStackLimit() {
        return this.upperChest.getInventoryStackLimit();
    }
    
    @Override
    public void markDirty() {
        this.upperChest.markDirty();
        this.lowerChest.markDirty();
    }
    
    @Override
    public boolean isUseableByPlayer(final EntityPlayer playerIn) {
        return this.upperChest.isUseableByPlayer(playerIn) && this.lowerChest.isUseableByPlayer(playerIn);
    }
    
    @Override
    public void openInventory(final EntityPlayer playerIn) {
        this.upperChest.openInventory(playerIn);
        this.lowerChest.openInventory(playerIn);
    }
    
    @Override
    public void closeInventory(final EntityPlayer playerIn) {
        this.upperChest.closeInventory(playerIn);
        this.lowerChest.closeInventory(playerIn);
    }
    
    @Override
    public boolean isItemValidForSlot(final int index, final ItemStack stack) {
        return true;
    }
    
    @Override
    public int getField(final int id) {
        return 0;
    }
    
    @Override
    public void setField(final int id, final int value) {
    }
    
    @Override
    public int getFieldCount() {
        return 0;
    }
    
    @Override
    public boolean isLocked() {
        return this.upperChest.isLocked() || this.lowerChest.isLocked();
    }
    
    @Override
    public void setLockCode(final LockCode code) {
        this.upperChest.setLockCode(code);
        this.lowerChest.setLockCode(code);
    }
    
    @Override
    public LockCode getLockCode() {
        return this.upperChest.getLockCode();
    }
    
    @Override
    public String getGuiID() {
        return this.upperChest.getGuiID();
    }
    
    @Override
    public Container createContainer(final InventoryPlayer playerInventory, final EntityPlayer playerIn) {
        return new ContainerChest(playerInventory, this, playerIn);
    }
    
    @Override
    public void clearInventory() {
        this.upperChest.clearInventory();
        this.lowerChest.clearInventory();
    }
}
