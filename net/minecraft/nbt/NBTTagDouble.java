// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.nbt;

import net.minecraft.util.MathHelper;
import java.io.DataInput;
import java.io.IOException;
import java.io.DataOutput;

public class NBTTagDouble extends NBTPrimitive
{
    private double data;
    private static final String __OBFID = "CL_00001218";
    
    NBTTagDouble() {
    }
    
    public NBTTagDouble(final double data) {
        this.data = data;
    }
    
    @Override
    void write(final DataOutput output) throws IOException {
        output.writeDouble(this.data);
    }
    
    @Override
    void read(final DataInput input, final int depth, final NBTSizeTracker sizeTracker) throws IOException {
        sizeTracker.read(64L);
        this.data = input.readDouble();
    }
    
    @Override
    public byte getId() {
        return 6;
    }
    
    @Override
    public String toString() {
        return "" + this.data + "d";
    }
    
    @Override
    public NBTBase copy() {
        return new NBTTagDouble(this.data);
    }
    
    @Override
    public boolean equals(final Object p_equals_1_) {
        if (super.equals(p_equals_1_)) {
            final NBTTagDouble var2 = (NBTTagDouble)p_equals_1_;
            return this.data == var2.data;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        final long var1 = Double.doubleToLongBits(this.data);
        return super.hashCode() ^ (int)(var1 ^ var1 >>> 32);
    }
    
    @Override
    public long getLong() {
        return (long)Math.floor(this.data);
    }
    
    @Override
    public int getInt() {
        return MathHelper.floor_double(this.data);
    }
    
    @Override
    public short getShort() {
        return (short)(MathHelper.floor_double(this.data) & 0xFFFF);
    }
    
    @Override
    public byte getByte() {
        return (byte)(MathHelper.floor_double(this.data) & 0xFF);
    }
    
    @Override
    public double getDouble() {
        return this.data;
    }
    
    @Override
    public float getFloat() {
        return (float)this.data;
    }
}
