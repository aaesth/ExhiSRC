// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.Container;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionHelper;
import java.util.List;
import net.minecraft.item.ItemPotion;
import net.minecraft.init.Items;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.BlockBrewingStand;
import java.util.Arrays;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.server.gui.IUpdatePlayerListBox;

public class TileEntityBrewingStand extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory
{
    private static final int[] inputSlots;
    private static final int[] outputSlots;
    private ItemStack[] brewingItemStacks;
    private int brewTime;
    private boolean[] filledSlots;
    private Item ingredientID;
    private String field_145942_n;
    private static final String __OBFID = "CL_00000345";
    
    public TileEntityBrewingStand() {
        this.brewingItemStacks = new ItemStack[4];
    }
    
    @Override
    public String getName() {
        return this.hasCustomName() ? this.field_145942_n : "container.brewing";
    }
    
    @Override
    public boolean hasCustomName() {
        return this.field_145942_n != null && this.field_145942_n.length() > 0;
    }
    
    public void func_145937_a(final String p_145937_1_) {
        this.field_145942_n = p_145937_1_;
    }
    
    @Override
    public int getSizeInventory() {
        return this.brewingItemStacks.length;
    }
    
    @Override
    public void update() {
        if (this.brewTime > 0) {
            --this.brewTime;
            if (this.brewTime == 0) {
                this.brewPotions();
                this.markDirty();
            }
            else if (!this.canBrew()) {
                this.brewTime = 0;
                this.markDirty();
            }
            else if (this.ingredientID != this.brewingItemStacks[3].getItem()) {
                this.brewTime = 0;
                this.markDirty();
            }
        }
        else if (this.canBrew()) {
            this.brewTime = 400;
            this.ingredientID = this.brewingItemStacks[3].getItem();
        }
        if (!this.worldObj.isRemote) {
            final boolean[] var1 = this.func_174902_m();
            if (!Arrays.equals(var1, this.filledSlots)) {
                this.filledSlots = var1;
                IBlockState var2 = this.worldObj.getBlockState(this.getPos());
                if (!(var2.getBlock() instanceof BlockBrewingStand)) {
                    return;
                }
                for (int var3 = 0; var3 < BlockBrewingStand.BOTTLE_PROPS.length; ++var3) {
                    var2 = var2.withProperty(BlockBrewingStand.BOTTLE_PROPS[var3], var1[var3]);
                }
                this.worldObj.setBlockState(this.pos, var2, 2);
            }
        }
    }
    
    private boolean canBrew() {
        if (this.brewingItemStacks[3] == null || this.brewingItemStacks[3].stackSize <= 0) {
            return false;
        }
        final ItemStack var1 = this.brewingItemStacks[3];
        if (!var1.getItem().isPotionIngredient(var1)) {
            return false;
        }
        boolean var2 = false;
        for (int var3 = 0; var3 < 3; ++var3) {
            if (this.brewingItemStacks[var3] != null && this.brewingItemStacks[var3].getItem() == Items.potionitem) {
                final int var4 = this.brewingItemStacks[var3].getMetadata();
                final int var5 = this.func_145936_c(var4, var1);
                if (!ItemPotion.isSplash(var4) && ItemPotion.isSplash(var5)) {
                    var2 = true;
                    break;
                }
                final List var6 = Items.potionitem.getEffects(var4);
                final List var7 = Items.potionitem.getEffects(var5);
                if ((var4 <= 0 || var6 != var7) && (var6 == null || (!var6.equals(var7) && var7 != null)) && var4 != var5) {
                    var2 = true;
                    break;
                }
            }
        }
        return var2;
    }
    
    private void brewPotions() {
        if (this.canBrew()) {
            final ItemStack var1 = this.brewingItemStacks[3];
            for (int var2 = 0; var2 < 3; ++var2) {
                if (this.brewingItemStacks[var2] != null && this.brewingItemStacks[var2].getItem() == Items.potionitem) {
                    final int var3 = this.brewingItemStacks[var2].getMetadata();
                    final int var4 = this.func_145936_c(var3, var1);
                    final List var5 = Items.potionitem.getEffects(var3);
                    final List var6 = Items.potionitem.getEffects(var4);
                    if ((var3 <= 0 || var5 != var6) && (var5 == null || (!var5.equals(var6) && var6 != null))) {
                        if (var3 != var4) {
                            this.brewingItemStacks[var2].setItemDamage(var4);
                        }
                    }
                    else if (!ItemPotion.isSplash(var3) && ItemPotion.isSplash(var4)) {
                        this.brewingItemStacks[var2].setItemDamage(var4);
                    }
                }
            }
            if (var1.getItem().hasContainerItem()) {
                this.brewingItemStacks[3] = new ItemStack(var1.getItem().getContainerItem());
            }
            else {
                final ItemStack itemStack = this.brewingItemStacks[3];
                --itemStack.stackSize;
                if (this.brewingItemStacks[3].stackSize <= 0) {
                    this.brewingItemStacks[3] = null;
                }
            }
        }
    }
    
    private int func_145936_c(final int p_145936_1_, final ItemStack p_145936_2_) {
        return (p_145936_2_ == null) ? p_145936_1_ : (p_145936_2_.getItem().isPotionIngredient(p_145936_2_) ? PotionHelper.applyIngredient(p_145936_1_, p_145936_2_.getItem().getPotionEffect(p_145936_2_)) : p_145936_1_);
    }
    
    @Override
    public void readFromNBT(final NBTTagCompound compound) {
        super.readFromNBT(compound);
        final NBTTagList var2 = compound.getTagList("Items", 10);
        this.brewingItemStacks = new ItemStack[this.getSizeInventory()];
        for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
            final NBTTagCompound var4 = var2.getCompoundTagAt(var3);
            final byte var5 = var4.getByte("Slot");
            if (var5 >= 0 && var5 < this.brewingItemStacks.length) {
                this.brewingItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
        this.brewTime = compound.getShort("BrewTime");
        if (compound.hasKey("CustomName", 8)) {
            this.field_145942_n = compound.getString("CustomName");
        }
    }
    
    @Override
    public void writeToNBT(final NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setShort("BrewTime", (short)this.brewTime);
        final NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.brewingItemStacks.length; ++var3) {
            if (this.brewingItemStacks[var3] != null) {
                final NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.brewingItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }
        compound.setTag("Items", var2);
        if (this.hasCustomName()) {
            compound.setString("CustomName", this.field_145942_n);
        }
    }
    
    @Override
    public ItemStack getStackInSlot(final int slotIn) {
        return (slotIn >= 0 && slotIn < this.brewingItemStacks.length) ? this.brewingItemStacks[slotIn] : null;
    }
    
    @Override
    public ItemStack decrStackSize(final int index, final int count) {
        if (index >= 0 && index < this.brewingItemStacks.length) {
            final ItemStack var3 = this.brewingItemStacks[index];
            this.brewingItemStacks[index] = null;
            return var3;
        }
        return null;
    }
    
    @Override
    public ItemStack getStackInSlotOnClosing(final int index) {
        if (index >= 0 && index < this.brewingItemStacks.length) {
            final ItemStack var2 = this.brewingItemStacks[index];
            this.brewingItemStacks[index] = null;
            return var2;
        }
        return null;
    }
    
    @Override
    public void setInventorySlotContents(final int index, final ItemStack stack) {
        if (index >= 0 && index < this.brewingItemStacks.length) {
            this.brewingItemStacks[index] = stack;
        }
    }
    
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }
    
    @Override
    public boolean isUseableByPlayer(final EntityPlayer playerIn) {
        return this.worldObj.getTileEntity(this.pos) == this && playerIn.getDistanceSq(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5) <= 64.0;
    }
    
    @Override
    public void openInventory(final EntityPlayer playerIn) {
    }
    
    @Override
    public void closeInventory(final EntityPlayer playerIn) {
    }
    
    @Override
    public boolean isItemValidForSlot(final int index, final ItemStack stack) {
        return (index == 3) ? stack.getItem().isPotionIngredient(stack) : (stack.getItem() == Items.potionitem || stack.getItem() == Items.glass_bottle);
    }
    
    public boolean[] func_174902_m() {
        final boolean[] var1 = new boolean[3];
        for (int var2 = 0; var2 < 3; ++var2) {
            if (this.brewingItemStacks[var2] != null) {
                var1[var2] = true;
            }
        }
        return var1;
    }
    
    @Override
    public int[] getSlotsForFace(final EnumFacing side) {
        return (side == EnumFacing.UP) ? TileEntityBrewingStand.inputSlots : TileEntityBrewingStand.outputSlots;
    }
    
    @Override
    public boolean canInsertItem(final int slotIn, final ItemStack itemStackIn, final EnumFacing direction) {
        return this.isItemValidForSlot(slotIn, itemStackIn);
    }
    
    @Override
    public boolean canExtractItem(final int slotId, final ItemStack stack, final EnumFacing direction) {
        return true;
    }
    
    @Override
    public String getGuiID() {
        return "minecraft:brewing_stand";
    }
    
    @Override
    public Container createContainer(final InventoryPlayer playerInventory, final EntityPlayer playerIn) {
        return new ContainerBrewingStand(playerInventory, this);
    }
    
    @Override
    public int getField(final int id) {
        switch (id) {
            case 0: {
                return this.brewTime;
            }
            default: {
                return 0;
            }
        }
    }
    
    @Override
    public void setField(final int id, final int value) {
        switch (id) {
            case 0: {
                this.brewTime = value;
                break;
            }
        }
    }
    
    @Override
    public int getFieldCount() {
        return 1;
    }
    
    @Override
    public void clearInventory() {
        for (int var1 = 0; var1 < this.brewingItemStacks.length; ++var1) {
            this.brewingItemStacks[var1] = null;
        }
    }
    
    static {
        inputSlots = new int[] { 3 };
        outputSlots = new int[] { 0, 1, 2 };
    }
}
