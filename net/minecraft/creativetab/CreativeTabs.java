// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.creativetab;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.enchantment.Enchantment;
import java.util.Iterator;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.EnumEnchantmentType;

public abstract class CreativeTabs
{
    public static final CreativeTabs[] creativeTabArray;
    public static final CreativeTabs tabBlock;
    public static final CreativeTabs tabDecorations;
    public static final CreativeTabs tabRedstone;
    public static final CreativeTabs tabTransport;
    public static final CreativeTabs tabMisc;
    public static final CreativeTabs tabAllSearch;
    public static final CreativeTabs tabFood;
    public static final CreativeTabs tabTools;
    public static final CreativeTabs tabCombat;
    public static final CreativeTabs tabBrewing;
    public static final CreativeTabs tabMaterials;
    public static final CreativeTabs tabInventory;
    private final int tabIndex;
    private final String tabLabel;
    private String theTexture;
    private boolean hasScrollbar;
    private boolean drawTitle;
    private EnumEnchantmentType[] enchantmentTypes;
    private ItemStack iconItemStack;
    private static final String __OBFID = "CL_00000005";
    
    public CreativeTabs(final int index, final String label) {
        this.theTexture = "items.png";
        this.hasScrollbar = true;
        this.drawTitle = true;
        this.tabIndex = index;
        this.tabLabel = label;
        CreativeTabs.creativeTabArray[index] = this;
    }
    
    public int getTabIndex() {
        return this.tabIndex;
    }
    
    public String getTabLabel() {
        return this.tabLabel;
    }
    
    public String getTranslatedTabLabel() {
        return "itemGroup." + this.getTabLabel();
    }
    
    public ItemStack getIconItemStack() {
        if (this.iconItemStack == null) {
            this.iconItemStack = new ItemStack(this.getTabIconItem(), 1, this.getIconItemDamage());
        }
        return this.iconItemStack;
    }
    
    public abstract Item getTabIconItem();
    
    public int getIconItemDamage() {
        return 0;
    }
    
    public String getBackgroundImageName() {
        return this.theTexture;
    }
    
    public CreativeTabs setBackgroundImageName(final String texture) {
        this.theTexture = texture;
        return this;
    }
    
    public boolean drawInForegroundOfTab() {
        return this.drawTitle;
    }
    
    public CreativeTabs setNoTitle() {
        this.drawTitle = false;
        return this;
    }
    
    public boolean shouldHidePlayerInventory() {
        return this.hasScrollbar;
    }
    
    public CreativeTabs setNoScrollbar() {
        this.hasScrollbar = false;
        return this;
    }
    
    public int getTabColumn() {
        return this.tabIndex % 6;
    }
    
    public boolean isTabInFirstRow() {
        return this.tabIndex < 6;
    }
    
    public EnumEnchantmentType[] getRelevantEnchantmentTypes() {
        return this.enchantmentTypes;
    }
    
    public CreativeTabs setRelevantEnchantmentTypes(final EnumEnchantmentType... types) {
        this.enchantmentTypes = types;
        return this;
    }
    
    public boolean hasRelevantEnchantmentType(final EnumEnchantmentType p_111226_1_) {
        if (this.enchantmentTypes == null) {
            return false;
        }
        for (final EnumEnchantmentType var5 : this.enchantmentTypes) {
            if (var5 == p_111226_1_) {
                return true;
            }
        }
        return false;
    }
    
    public void displayAllReleventItems(final List p_78018_1_) {
        for (final Item var3 : Item.itemRegistry) {
            if (var3 != null && var3.getCreativeTab() == this) {
                var3.getSubItems(var3, this, p_78018_1_);
            }
        }
        if (this.getRelevantEnchantmentTypes() != null) {
            this.addEnchantmentBooksToList(p_78018_1_, this.getRelevantEnchantmentTypes());
        }
    }
    
    public void addEnchantmentBooksToList(final List p_92116_1_, final EnumEnchantmentType... p_92116_2_) {
        for (final Enchantment var6 : Enchantment.enchantmentsList) {
            if (var6 != null && var6.type != null) {
                boolean var7 = false;
                for (int var8 = 0; var8 < p_92116_2_.length && !var7; ++var8) {
                    if (var6.type == p_92116_2_[var8]) {
                        var7 = true;
                    }
                }
                if (var7) {
                    p_92116_1_.add(Items.enchanted_book.getEnchantedItemStack(new EnchantmentData(var6, var6.getMaxLevel())));
                }
            }
        }
    }
    
    static {
        creativeTabArray = new CreativeTabs[12];
        tabBlock = new CreativeTabs(0, "buildingBlocks") {
            private static final String __OBFID = "CL_00000006";
            
            @Override
            public Item getTabIconItem() {
                return Item.getItemFromBlock(Blocks.brick_block);
            }
        };
        tabDecorations = new CreativeTabs(1, "decorations") {
            private static final String __OBFID = "CL_00000010";
            
            @Override
            public Item getTabIconItem() {
                return Item.getItemFromBlock(Blocks.double_plant);
            }
            
            @Override
            public int getIconItemDamage() {
                return BlockDoublePlant.EnumPlantType.PAEONIA.func_176936_a();
            }
        };
        tabRedstone = new CreativeTabs(2, "redstone") {
            private static final String __OBFID = "CL_00000011";
            
            @Override
            public Item getTabIconItem() {
                return Items.redstone;
            }
        };
        tabTransport = new CreativeTabs(3, "transportation") {
            private static final String __OBFID = "CL_00000012";
            
            @Override
            public Item getTabIconItem() {
                return Item.getItemFromBlock(Blocks.golden_rail);
            }
        };
        tabMisc = new CreativeTabs(4, "misc") {
            private static final String __OBFID = "CL_00000014";
            
            @Override
            public Item getTabIconItem() {
                return Items.lava_bucket;
            }
        }.setRelevantEnchantmentTypes(EnumEnchantmentType.ALL);
        tabAllSearch = new CreativeTabs(5, "search") {
            private static final String __OBFID = "CL_00000015";
            
            @Override
            public Item getTabIconItem() {
                return Items.compass;
            }
        }.setBackgroundImageName("item_search.png");
        tabFood = new CreativeTabs(6, "food") {
            private static final String __OBFID = "CL_00000016";
            
            @Override
            public Item getTabIconItem() {
                return Items.apple;
            }
        };
        tabTools = new CreativeTabs(7, "tools") {
            private static final String __OBFID = "CL_00000017";
            
            @Override
            public Item getTabIconItem() {
                return Items.iron_axe;
            }
        }.setRelevantEnchantmentTypes(EnumEnchantmentType.DIGGER, EnumEnchantmentType.FISHING_ROD, EnumEnchantmentType.BREAKABLE);
        tabCombat = new CreativeTabs(8, "combat") {
            private static final String __OBFID = "CL_00000018";
            
            @Override
            public Item getTabIconItem() {
                return Items.golden_sword;
            }
        }.setRelevantEnchantmentTypes(EnumEnchantmentType.ARMOR, EnumEnchantmentType.ARMOR_FEET, EnumEnchantmentType.ARMOR_HEAD, EnumEnchantmentType.ARMOR_LEGS, EnumEnchantmentType.ARMOR_TORSO, EnumEnchantmentType.BOW, EnumEnchantmentType.WEAPON);
        tabBrewing = new CreativeTabs(9, "brewing") {
            private static final String __OBFID = "CL_00000007";
            
            @Override
            public Item getTabIconItem() {
                return Items.potionitem;
            }
        };
        tabMaterials = new CreativeTabs(10, "materials") {
            private static final String __OBFID = "CL_00000008";
            
            @Override
            public Item getTabIconItem() {
                return Items.stick;
            }
        };
        tabInventory = new CreativeTabs(11, "inventory") {
            private static final String __OBFID = "CL_00000009";
            
            @Override
            public Item getTabIconItem() {
                return Item.getItemFromBlock(Blocks.chest);
            }
        }.setBackgroundImageName("inventory.png").setNoScrollbar().setNoTitle();
    }
}
