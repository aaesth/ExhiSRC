// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.util;

import java.util.Iterator;

public class ChatComponentSelector extends ChatComponentStyle
{
    private final String field_179993_b;
    private static final String __OBFID = "CL_00002308";
    
    public ChatComponentSelector(final String p_i45996_1_) {
        this.field_179993_b = p_i45996_1_;
    }
    
    public String func_179992_g() {
        return this.field_179993_b;
    }
    
    @Override
    public String getUnformattedTextForChat() {
        return this.field_179993_b;
    }
    
    public ChatComponentSelector func_179991_h() {
        final ChatComponentSelector var1 = new ChatComponentSelector(this.field_179993_b);
        var1.setChatStyle(this.getChatStyle().createShallowCopy());
        for (final IChatComponent var3 : this.getSiblings()) {
            var1.appendSibling(var3.createCopy());
        }
        return var1;
    }
    
    @Override
    public boolean equals(final Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (!(p_equals_1_ instanceof ChatComponentSelector)) {
            return false;
        }
        final ChatComponentSelector var2 = (ChatComponentSelector)p_equals_1_;
        return this.field_179993_b.equals(var2.field_179993_b) && super.equals(p_equals_1_);
    }
    
    @Override
    public String toString() {
        return "SelectorComponent{pattern='" + this.field_179993_b + '\'' + ", siblings=" + this.siblings + ", style=" + this.getChatStyle() + '}';
    }
    
    @Override
    public IChatComponent createCopy() {
        return this.func_179991_h();
    }
}
