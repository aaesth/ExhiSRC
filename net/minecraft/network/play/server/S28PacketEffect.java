// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.network.play.server;

import net.minecraft.network.INetHandler;
import net.minecraft.network.play.INetHandlerPlayClient;
import java.io.IOException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.BlockPos;
import net.minecraft.network.Packet;

public class S28PacketEffect implements Packet
{
    private int soundType;
    private BlockPos field_179747_b;
    private int soundData;
    private boolean serverWide;
    private static final String __OBFID = "CL_00001307";
    
    public S28PacketEffect() {
    }
    
    public S28PacketEffect(final int p_i45978_1_, final BlockPos p_i45978_2_, final int p_i45978_3_, final boolean p_i45978_4_) {
        this.soundType = p_i45978_1_;
        this.field_179747_b = p_i45978_2_;
        this.soundData = p_i45978_3_;
        this.serverWide = p_i45978_4_;
    }
    
    @Override
    public void readPacketData(final PacketBuffer data) throws IOException {
        this.soundType = data.readInt();
        this.field_179747_b = data.readBlockPos();
        this.soundData = data.readInt();
        this.serverWide = data.readBoolean();
    }
    
    @Override
    public void writePacketData(final PacketBuffer data) throws IOException {
        data.writeInt(this.soundType);
        data.writeBlockPos(this.field_179747_b);
        data.writeInt(this.soundData);
        data.writeBoolean(this.serverWide);
    }
    
    public void func_180739_a(final INetHandlerPlayClient p_180739_1_) {
        p_180739_1_.handleEffect(this);
    }
    
    public boolean isSoundServerwide() {
        return this.serverWide;
    }
    
    public int getSoundType() {
        return this.soundType;
    }
    
    public int getSoundData() {
        return this.soundData;
    }
    
    public BlockPos func_179746_d() {
        return this.field_179747_b;
    }
    
    @Override
    public void processPacket(final INetHandler handler) {
        this.func_180739_a((INetHandlerPlayClient)handler);
    }
}
