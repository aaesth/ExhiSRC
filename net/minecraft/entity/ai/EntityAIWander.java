// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.entity.ai;

import net.minecraft.util.Vec3;
import net.minecraft.entity.EntityCreature;

public class EntityAIWander extends EntityAIBase
{
    private EntityCreature entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed;
    private int field_179481_f;
    private boolean field_179482_g;
    private static final String __OBFID = "CL_00001608";
    
    public EntityAIWander(final EntityCreature p_i1648_1_, final double p_i1648_2_) {
        this(p_i1648_1_, p_i1648_2_, 120);
    }
    
    public EntityAIWander(final EntityCreature p_i45887_1_, final double p_i45887_2_, final int p_i45887_4_) {
        this.entity = p_i45887_1_;
        this.speed = p_i45887_2_;
        this.field_179481_f = p_i45887_4_;
        this.setMutexBits(1);
    }
    
    @Override
    public boolean shouldExecute() {
        if (!this.field_179482_g) {
            if (this.entity.getAge() >= 100) {
                return false;
            }
            if (this.entity.getRNG().nextInt(this.field_179481_f) != 0) {
                return false;
            }
        }
        final Vec3 var1 = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);
        if (var1 == null) {
            return false;
        }
        this.xPosition = var1.xCoord;
        this.yPosition = var1.yCoord;
        this.zPosition = var1.zCoord;
        this.field_179482_g = false;
        return true;
    }
    
    @Override
    public boolean continueExecuting() {
        return !this.entity.getNavigator().noPath();
    }
    
    @Override
    public void startExecuting() {
        this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
    }
    
    public void func_179480_f() {
        this.field_179482_g = true;
    }
    
    public void func_179479_b(final int p_179479_1_) {
        this.field_179481_f = p_179479_1_;
    }
}
