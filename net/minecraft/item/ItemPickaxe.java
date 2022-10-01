// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.item;

import com.google.common.collect.Sets;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import java.util.Set;

public class ItemPickaxe extends ItemTool
{
    private static final Set effectiveBlocks;
    private static final String __OBFID = "CL_00000053";
    
    protected ItemPickaxe(final ToolMaterial p_i45347_1_) {
        super(2.0f, p_i45347_1_, ItemPickaxe.effectiveBlocks);
    }
    
    @Override
    public boolean canHarvestBlock(final Block blockIn) {
        return (blockIn == Blocks.obsidian) ? (this.toolMaterial.getHarvestLevel() == 3) : ((blockIn != Blocks.diamond_block && blockIn != Blocks.diamond_ore) ? ((blockIn != Blocks.emerald_ore && blockIn != Blocks.emerald_block) ? ((blockIn != Blocks.gold_block && blockIn != Blocks.gold_ore) ? ((blockIn != Blocks.iron_block && blockIn != Blocks.iron_ore) ? ((blockIn != Blocks.lapis_block && blockIn != Blocks.lapis_ore) ? ((blockIn != Blocks.redstone_ore && blockIn != Blocks.lit_redstone_ore) ? (blockIn.getMaterial() == Material.rock || blockIn.getMaterial() == Material.iron || blockIn.getMaterial() == Material.anvil) : (this.toolMaterial.getHarvestLevel() >= 2)) : (this.toolMaterial.getHarvestLevel() >= 1)) : (this.toolMaterial.getHarvestLevel() >= 1)) : (this.toolMaterial.getHarvestLevel() >= 2)) : (this.toolMaterial.getHarvestLevel() >= 2)) : (this.toolMaterial.getHarvestLevel() >= 2));
    }
    
    @Override
    public float getStrVsBlock(final ItemStack stack, final Block p_150893_2_) {
        return (p_150893_2_.getMaterial() != Material.iron && p_150893_2_.getMaterial() != Material.anvil && p_150893_2_.getMaterial() != Material.rock) ? super.getStrVsBlock(stack, p_150893_2_) : this.efficiencyOnProperMaterial;
    }
    
    static {
        effectiveBlocks = Sets.newHashSet((Object[])new Block[] { Blocks.activator_rail, Blocks.coal_ore, Blocks.cobblestone, Blocks.detector_rail, Blocks.diamond_block, Blocks.diamond_ore, Blocks.double_stone_slab, Blocks.golden_rail, Blocks.gold_block, Blocks.gold_ore, Blocks.ice, Blocks.iron_block, Blocks.iron_ore, Blocks.lapis_block, Blocks.lapis_ore, Blocks.lit_redstone_ore, Blocks.mossy_cobblestone, Blocks.netherrack, Blocks.packed_ice, Blocks.rail, Blocks.redstone_ore, Blocks.sandstone, Blocks.red_sandstone, Blocks.stone, Blocks.stone_slab });
    }
}
