// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.entity.ai;

import net.minecraft.village.Village;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.village.VillageDoorInfo;
import net.minecraft.entity.EntityCreature;

public class EntityAIRestrictOpenDoor extends EntityAIBase
{
    private EntityCreature entityObj;
    private VillageDoorInfo frontDoor;
    private static final String __OBFID = "CL_00001610";
    
    public EntityAIRestrictOpenDoor(final EntityCreature p_i1651_1_) {
        this.entityObj = p_i1651_1_;
        if (!(p_i1651_1_.getNavigator() instanceof PathNavigateGround)) {
            throw new IllegalArgumentException("Unsupported mob type for RestrictOpenDoorGoal");
        }
    }
    
    @Override
    public boolean shouldExecute() {
        if (this.entityObj.worldObj.isDaytime()) {
            return false;
        }
        final BlockPos var1 = new BlockPos(this.entityObj);
        final Village var2 = this.entityObj.worldObj.getVillageCollection().func_176056_a(var1, 16);
        if (var2 == null) {
            return false;
        }
        this.frontDoor = var2.func_179865_b(var1);
        return this.frontDoor != null && this.frontDoor.func_179846_b(var1) < 2.25;
    }
    
    @Override
    public boolean continueExecuting() {
        return !this.entityObj.worldObj.isDaytime() && (!this.frontDoor.func_179851_i() && this.frontDoor.func_179850_c(new BlockPos(this.entityObj)));
    }
    
    @Override
    public void startExecuting() {
        ((PathNavigateGround)this.entityObj.getNavigator()).func_179688_b(false);
        ((PathNavigateGround)this.entityObj.getNavigator()).func_179691_c(false);
    }
    
    @Override
    public void resetTask() {
        ((PathNavigateGround)this.entityObj.getNavigator()).func_179688_b(true);
        ((PathNavigateGround)this.entityObj.getNavigator()).func_179691_c(true);
        this.frontDoor = null;
    }
    
    @Override
    public void updateTask() {
        this.frontDoor.incrementDoorOpeningRestrictionCounter();
    }
}
