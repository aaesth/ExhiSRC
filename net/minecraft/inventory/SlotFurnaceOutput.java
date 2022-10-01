// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.inventory;

import net.minecraft.stats.StatBase;
import net.minecraft.stats.AchievementList;
import net.minecraft.init.Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.util.MathHelper;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;

public class SlotFurnaceOutput extends Slot
{
    private EntityPlayer thePlayer;
    private int field_75228_b;
    private static final String __OBFID = "CL_00002183";
    
    public SlotFurnaceOutput(final EntityPlayer p_i45793_1_, final IInventory p_i45793_2_, final int p_i45793_3_, final int p_i45793_4_, final int p_i45793_5_) {
        super(p_i45793_2_, p_i45793_3_, p_i45793_4_, p_i45793_5_);
        this.thePlayer = p_i45793_1_;
    }
    
    @Override
    public boolean isItemValid(final ItemStack stack) {
        return false;
    }
    
    @Override
    public ItemStack decrStackSize(final int p_75209_1_) {
        if (this.getHasStack()) {
            this.field_75228_b += Math.min(p_75209_1_, this.getStack().stackSize);
        }
        return super.decrStackSize(p_75209_1_);
    }
    
    @Override
    public void onPickupFromSlot(final EntityPlayer playerIn, final ItemStack stack) {
        this.onCrafting(stack);
        super.onPickupFromSlot(playerIn, stack);
    }
    
    @Override
    protected void onCrafting(final ItemStack p_75210_1_, final int p_75210_2_) {
        this.field_75228_b += p_75210_2_;
        this.onCrafting(p_75210_1_);
    }
    
    @Override
    protected void onCrafting(final ItemStack p_75208_1_) {
        p_75208_1_.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.field_75228_b);
        if (!this.thePlayer.worldObj.isRemote) {
            int var2 = this.field_75228_b;
            final float var3 = FurnaceRecipes.instance().getSmeltingExperience(p_75208_1_);
            if (var3 == 0.0f) {
                var2 = 0;
            }
            else if (var3 < 1.0f) {
                int var4 = MathHelper.floor_float(var2 * var3);
                if (var4 < MathHelper.ceiling_float_int(var2 * var3) && Math.random() < var2 * var3 - var4) {
                    ++var4;
                }
                var2 = var4;
            }
            while (var2 > 0) {
                final int var4 = EntityXPOrb.getXPSplit(var2);
                var2 -= var4;
                this.thePlayer.worldObj.spawnEntityInWorld(new EntityXPOrb(this.thePlayer.worldObj, this.thePlayer.posX, this.thePlayer.posY + 0.5, this.thePlayer.posZ + 0.5, var4));
            }
        }
        this.field_75228_b = 0;
        if (p_75208_1_.getItem() == Items.iron_ingot) {
            this.thePlayer.triggerAchievement(AchievementList.acquireIron);
        }
        if (p_75208_1_.getItem() == Items.cooked_fish) {
            this.thePlayer.triggerAchievement(AchievementList.cookFish);
        }
    }
}
