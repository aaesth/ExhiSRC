// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.pathfinding;

import net.minecraft.util.MovingObjectPosition;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.pathfinder.NodeProcessor;
import net.minecraft.world.pathfinder.SwimNodeProcessor;
import net.minecraft.world.World;
import net.minecraft.entity.EntityLiving;

public class PathNavigateSwimmer extends PathNavigate
{
    private static final String __OBFID = "CL_00002244";
    
    public PathNavigateSwimmer(final EntityLiving p_i45873_1_, final World worldIn) {
        super(p_i45873_1_, worldIn);
    }
    
    @Override
    protected PathFinder func_179679_a() {
        return new PathFinder(new SwimNodeProcessor());
    }
    
    @Override
    protected boolean canNavigate() {
        return this.isInLiquid();
    }
    
    @Override
    protected Vec3 getEntityPosition() {
        return new Vec3(this.theEntity.posX, this.theEntity.posY + this.theEntity.height * 0.5, this.theEntity.posZ);
    }
    
    @Override
    protected void pathFollow() {
        final Vec3 var1 = this.getEntityPosition();
        final float var2 = this.theEntity.width * this.theEntity.width;
        final byte var3 = 6;
        if (var1.squareDistanceTo(this.currentPath.getVectorFromIndex(this.theEntity, this.currentPath.getCurrentPathIndex())) < var2) {
            this.currentPath.incrementPathIndex();
        }
        for (int var4 = Math.min(this.currentPath.getCurrentPathIndex() + var3, this.currentPath.getCurrentPathLength() - 1); var4 > this.currentPath.getCurrentPathIndex(); --var4) {
            final Vec3 var5 = this.currentPath.getVectorFromIndex(this.theEntity, var4);
            if (var5.squareDistanceTo(var1) <= 36.0 && this.isDirectPathBetweenPoints(var1, var5, 0, 0, 0)) {
                this.currentPath.setCurrentPathIndex(var4);
                break;
            }
        }
        this.func_179677_a(var1);
    }
    
    @Override
    protected void removeSunnyPath() {
        super.removeSunnyPath();
    }
    
    @Override
    protected boolean isDirectPathBetweenPoints(final Vec3 p_75493_1_, final Vec3 p_75493_2_, final int p_75493_3_, final int p_75493_4_, final int p_75493_5_) {
        final MovingObjectPosition var6 = this.worldObj.rayTraceBlocks(p_75493_1_, new Vec3(p_75493_2_.xCoord, p_75493_2_.yCoord + this.theEntity.height * 0.5, p_75493_2_.zCoord), false, true, false);
        return var6 == null || var6.typeOfHit == MovingObjectPosition.MovingObjectType.MISS;
    }
}
