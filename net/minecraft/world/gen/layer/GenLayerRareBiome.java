// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;

public class GenLayerRareBiome extends GenLayer
{
    private static final String __OBFID = "CL_00000562";
    
    public GenLayerRareBiome(final long p_i45478_1_, final GenLayer p_i45478_3_) {
        super(p_i45478_1_);
        this.parent = p_i45478_3_;
    }
    
    @Override
    public int[] getInts(final int areaX, final int areaY, final int areaWidth, final int areaHeight) {
        final int[] var5 = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        final int[] var6 = IntCache.getIntCache(areaWidth * areaHeight);
        for (int var7 = 0; var7 < areaHeight; ++var7) {
            for (int var8 = 0; var8 < areaWidth; ++var8) {
                this.initChunkSeed(var8 + areaX, var7 + areaY);
                final int var9 = var5[var8 + 1 + (var7 + 1) * (areaWidth + 2)];
                if (this.nextInt(57) == 0) {
                    if (var9 == BiomeGenBase.plains.biomeID) {
                        var6[var8 + var7 * areaWidth] = BiomeGenBase.plains.biomeID + 128;
                    }
                    else {
                        var6[var8 + var7 * areaWidth] = var9;
                    }
                }
                else {
                    var6[var8 + var7 * areaWidth] = var9;
                }
            }
        }
        return var6;
    }
}
