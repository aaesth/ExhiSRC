// 
// Decompiled by Procyon v0.5.30
// 

package exhibition.util.render;

import com.google.gson.annotations.Expose;

public class ColorContainer
{
    @Expose
    private int red;
    @Expose
    private int green;
    @Expose
    private int blue;
    @Expose
    private int alpha;
    
    public ColorContainer(final int r, final int g, final int b, final int a) {
        this.red = r;
        this.green = g;
        this.blue = b;
        this.alpha = a;
    }
    
    public ColorContainer(final int r, final int g, final int b) {
        this(r, g, b, 225);
    }
    
    public ColorContainer(final int b) {
        this(b, b, b);
    }
    
    public ColorContainer(final int b, final int a) {
        this(b, b, b, a);
    }
    
    public void setColor(final int r, final int g, final int b, final int a) {
        this.red = r;
        this.green = g;
        this.blue = b;
        this.alpha = a;
    }
    
    public void setRed(final int red) {
        this.setColor(this.red = red, this.green, this.blue, this.alpha);
    }
    
    public void setGreen(final int green) {
        this.green = green;
        this.setColor(this.red, green, this.blue, this.alpha);
    }
    
    public void setBlue(final int blue) {
        this.blue = blue;
        this.setColor(this.red, this.green, blue, this.alpha);
    }
    
    public void setAlpha(final int alpha) {
        this.alpha = alpha;
        this.setColor(this.red, this.green, this.blue, alpha);
    }
    
    public int getRed() {
        return this.red;
    }
    
    public int getGreen() {
        return this.green;
    }
    
    public int getBlue() {
        return this.blue;
    }
    
    public int getAlpha() {
        return this.alpha;
    }
    
    public int getIntCol() {
        return Colors.getColor(this.red, this.green, this.blue, this.alpha);
    }
}
