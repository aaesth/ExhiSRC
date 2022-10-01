// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.world.gen.feature;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.block.BlockDoublePlant;

public class WorldGenDoublePlant extends WorldGenerator
{
    private BlockDoublePlant.EnumPlantType field_150549_a;
    private static final String __OBFID = "CL_00000408";
    
    public void func_180710_a(final BlockDoublePlant.EnumPlantType p_180710_1_) {
        this.field_150549_a = p_180710_1_;
    }
    
    @Override
    public boolean generate(final World worldIn, final Random p_180709_2_, final BlockPos p_180709_3_) {
        boolean var4 = false;
        for (int var5 = 0; var5 < 64; ++var5) {
            final BlockPos var6 = p_180709_3_.add(p_180709_2_.nextInt(8) - p_180709_2_.nextInt(8), p_180709_2_.nextInt(4) - p_180709_2_.nextInt(4), p_180709_2_.nextInt(8) - p_180709_2_.nextInt(8));
            if (worldIn.isAirBlock(var6) && (!worldIn.provider.getHasNoSky() || var6.getY() < 254) && Blocks.double_plant.canPlaceBlockAt(worldIn, var6)) {
                Blocks.double_plant.func_176491_a(worldIn, var6, this.field_150549_a, 2);
                var4 = true;
            }
        }
        return var4;
    }
}
