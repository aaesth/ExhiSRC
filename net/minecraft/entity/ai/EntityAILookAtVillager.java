// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.monster.EntityIronGolem;

public class EntityAILookAtVillager extends EntityAIBase
{
    private EntityIronGolem theGolem;
    private EntityVillager theVillager;
    private int lookTime;
    private static final String __OBFID = "CL_00001602";
    
    public EntityAILookAtVillager(final EntityIronGolem p_i1643_1_) {
        this.theGolem = p_i1643_1_;
        this.setMutexBits(3);
    }
    
    @Override
    public boolean shouldExecute() {
        if (!this.theGolem.worldObj.isDaytime()) {
            return false;
        }
        if (this.theGolem.getRNG().nextInt(8000) != 0) {
            return false;
        }
        this.theVillager = (EntityVillager)this.theGolem.worldObj.findNearestEntityWithinAABB(EntityVillager.class, this.theGolem.getEntityBoundingBox().expand(6.0, 2.0, 6.0), this.theGolem);
        return this.theVillager != null;
    }
    
    @Override
    public boolean continueExecuting() {
        return this.lookTime > 0;
    }
    
    @Override
    public void startExecuting() {
        this.lookTime = 400;
        this.theGolem.setHoldingRose(true);
    }
    
    @Override
    public void resetTask() {
        this.theGolem.setHoldingRose(false);
        this.theVillager = null;
    }
    
    @Override
    public void updateTask() {
        this.theGolem.getLookHelper().setLookPositionWithEntity(this.theVillager, 30.0f, 30.0f);
        --this.lookTime;
    }
}
