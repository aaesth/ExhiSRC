// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.world.chunk.storage;

public class NibbleArrayReader
{
    public final byte[] data;
    private final int depthBits;
    private final int depthBitsPlusFour;
    private static final String __OBFID = "CL_00000376";
    
    public NibbleArrayReader(final byte[] dataIn, final int depthBitsIn) {
        this.data = dataIn;
        this.depthBits = depthBitsIn;
        this.depthBitsPlusFour = depthBitsIn + 4;
    }
    
    public int get(final int p_76686_1_, final int p_76686_2_, final int p_76686_3_) {
        final int var4 = p_76686_1_ << this.depthBitsPlusFour | p_76686_3_ << this.depthBits | p_76686_2_;
        final int var5 = var4 >> 1;
        final int var6 = var4 & 0x1;
        return (var6 == 0) ? (this.data[var5] & 0xF) : (this.data[var5] >> 4 & 0xF);
    }
}
