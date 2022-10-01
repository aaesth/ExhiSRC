// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.network.play.server;

import net.minecraft.network.INetHandler;
import net.minecraft.world.World;
import net.minecraft.network.play.INetHandlerPlayClient;
import java.io.IOException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;

public class S43PacketCamera implements Packet
{
    public int field_179781_a;
    private static final String __OBFID = "CL_00002289";
    
    public S43PacketCamera() {
    }
    
    public S43PacketCamera(final Entity p_i45960_1_) {
        this.field_179781_a = p_i45960_1_.getEntityId();
    }
    
    @Override
    public void readPacketData(final PacketBuffer data) throws IOException {
        this.field_179781_a = data.readVarIntFromBuffer();
    }
    
    @Override
    public void writePacketData(final PacketBuffer data) throws IOException {
        data.writeVarIntToBuffer(this.field_179781_a);
    }
    
    public void func_179779_a(final INetHandlerPlayClient p_179779_1_) {
        p_179779_1_.func_175094_a(this);
    }
    
    public Entity func_179780_a(final World worldIn) {
        return worldIn.getEntityByID(this.field_179781_a);
    }
    
    @Override
    public void processPacket(final INetHandler handler) {
        this.func_179779_a((INetHandlerPlayClient)handler);
    }
}
