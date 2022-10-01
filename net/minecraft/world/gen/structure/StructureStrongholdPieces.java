// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.world.gen.structure;

import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.item.Item;
import net.minecraft.init.Items;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.util.Vec3i;
import net.minecraft.util.BlockPos;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import java.util.Random;
import java.util.Iterator;
import com.google.common.collect.Lists;
import java.util.List;

public class StructureStrongholdPieces
{
    private static final PieceWeight[] pieceWeightArray;
    private static List structurePieceList;
    private static Class strongComponentType;
    static int totalWeight;
    private static final Stones strongholdStones;
    private static final String __OBFID = "CL_00000483";
    
    public static void registerStrongholdPieces() {
        MapGenStructureIO.registerStructureComponent(ChestCorridor.class, "SHCC");
        MapGenStructureIO.registerStructureComponent(Corridor.class, "SHFC");
        MapGenStructureIO.registerStructureComponent(Crossing.class, "SH5C");
        MapGenStructureIO.registerStructureComponent(LeftTurn.class, "SHLT");
        MapGenStructureIO.registerStructureComponent(Library.class, "SHLi");
        MapGenStructureIO.registerStructureComponent(PortalRoom.class, "SHPR");
        MapGenStructureIO.registerStructureComponent(Prison.class, "SHPH");
        MapGenStructureIO.registerStructureComponent(RightTurn.class, "SHRT");
        MapGenStructureIO.registerStructureComponent(RoomCrossing.class, "SHRC");
        MapGenStructureIO.registerStructureComponent(Stairs.class, "SHSD");
        MapGenStructureIO.registerStructureComponent(Stairs2.class, "SHStart");
        MapGenStructureIO.registerStructureComponent(Straight.class, "SHS");
        MapGenStructureIO.registerStructureComponent(StairsStraight.class, "SHSSD");
    }
    
    public static void prepareStructurePieces() {
        StructureStrongholdPieces.structurePieceList = Lists.newArrayList();
        for (final PieceWeight var4 : StructureStrongholdPieces.pieceWeightArray) {
            var4.instancesSpawned = 0;
            StructureStrongholdPieces.structurePieceList.add(var4);
        }
        StructureStrongholdPieces.strongComponentType = null;
    }
    
    private static boolean canAddStructurePieces() {
        boolean var0 = false;
        StructureStrongholdPieces.totalWeight = 0;
        for (final PieceWeight var3 : StructureStrongholdPieces.structurePieceList) {
            if (var3.instancesLimit > 0 && var3.instancesSpawned < var3.instancesLimit) {
                var0 = true;
            }
            StructureStrongholdPieces.totalWeight += var3.pieceWeight;
        }
        return var0;
    }
    
    private static Stronghold func_175954_a(final Class p_175954_0_, final List p_175954_1_, final Random p_175954_2_, final int p_175954_3_, final int p_175954_4_, final int p_175954_5_, final EnumFacing p_175954_6_, final int p_175954_7_) {
        Object var8 = null;
        if (p_175954_0_ == Straight.class) {
            var8 = Straight.func_175862_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (p_175954_0_ == Prison.class) {
            var8 = Prison.func_175860_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (p_175954_0_ == LeftTurn.class) {
            var8 = LeftTurn.func_175867_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (p_175954_0_ == RightTurn.class) {
            var8 = LeftTurn.func_175867_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (p_175954_0_ == RoomCrossing.class) {
            var8 = RoomCrossing.func_175859_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (p_175954_0_ == StairsStraight.class) {
            var8 = StairsStraight.func_175861_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (p_175954_0_ == Stairs.class) {
            var8 = Stairs.func_175863_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (p_175954_0_ == Crossing.class) {
            var8 = Crossing.func_175866_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (p_175954_0_ == ChestCorridor.class) {
            var8 = ChestCorridor.func_175868_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (p_175954_0_ == Library.class) {
            var8 = Library.func_175864_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        else if (p_175954_0_ == PortalRoom.class) {
            var8 = PortalRoom.func_175865_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
        }
        return (Stronghold)var8;
    }
    
    private static Stronghold func_175955_b(final Stairs2 p_175955_0_, final List p_175955_1_, final Random p_175955_2_, final int p_175955_3_, final int p_175955_4_, final int p_175955_5_, final EnumFacing p_175955_6_, final int p_175955_7_) {
        if (!canAddStructurePieces()) {
            return null;
        }
        if (StructureStrongholdPieces.strongComponentType != null) {
            final Stronghold var8 = func_175954_a(StructureStrongholdPieces.strongComponentType, p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_, p_175955_7_);
            StructureStrongholdPieces.strongComponentType = null;
            if (var8 != null) {
                return var8;
            }
        }
        int var9 = 0;
        while (var9 < 5) {
            ++var9;
            int var10 = p_175955_2_.nextInt(StructureStrongholdPieces.totalWeight);
            for (final PieceWeight var12 : StructureStrongholdPieces.structurePieceList) {
                var10 -= var12.pieceWeight;
                if (var10 < 0) {
                    if (!var12.canSpawnMoreStructuresOfType(p_175955_7_)) {
                        break;
                    }
                    if (var12 == p_175955_0_.strongholdPieceWeight) {
                        break;
                    }
                    final Stronghold var13 = func_175954_a(var12.pieceClass, p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_, p_175955_7_);
                    if (var13 != null) {
                        final PieceWeight pieceWeight = var12;
                        ++pieceWeight.instancesSpawned;
                        p_175955_0_.strongholdPieceWeight = var12;
                        if (!var12.canSpawnMoreStructures()) {
                            StructureStrongholdPieces.structurePieceList.remove(var12);
                        }
                        return var13;
                    }
                    continue;
                }
            }
        }
        final StructureBoundingBox var14 = Corridor.func_175869_a(p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_);
        if (var14 != null && var14.minY > 1) {
            return new Corridor(p_175955_7_, p_175955_2_, var14, p_175955_6_);
        }
        return null;
    }
    
    private static StructureComponent func_175953_c(final Stairs2 p_175953_0_, final List p_175953_1_, final Random p_175953_2_, final int p_175953_3_, final int p_175953_4_, final int p_175953_5_, final EnumFacing p_175953_6_, final int p_175953_7_) {
        if (p_175953_7_ > 50) {
            return null;
        }
        if (Math.abs(p_175953_3_ - p_175953_0_.getBoundingBox().minX) <= 112 && Math.abs(p_175953_5_ - p_175953_0_.getBoundingBox().minZ) <= 112) {
            final Stronghold var8 = func_175955_b(p_175953_0_, p_175953_1_, p_175953_2_, p_175953_3_, p_175953_4_, p_175953_5_, p_175953_6_, p_175953_7_ + 1);
            if (var8 != null) {
                p_175953_1_.add(var8);
                p_175953_0_.field_75026_c.add(var8);
            }
            return var8;
        }
        return null;
    }
    
    static {
        pieceWeightArray = new PieceWeight[] { new PieceWeight(Straight.class, 40, 0), new PieceWeight(Prison.class, 5, 5), new PieceWeight(LeftTurn.class, 20, 0), new PieceWeight(RightTurn.class, 20, 0), new PieceWeight(RoomCrossing.class, 10, 6), new PieceWeight(StairsStraight.class, 5, 5), new PieceWeight(Stairs.class, 5, 5), new PieceWeight(Crossing.class, 5, 4), new PieceWeight(ChestCorridor.class, 5, 4), new PieceWeight(Library.class, 10, 2) {
                private static final String __OBFID = "CL_00000484";
                
                @Override
                public boolean canSpawnMoreStructuresOfType(final int p_75189_1_) {
                    return super.canSpawnMoreStructuresOfType(p_75189_1_) && p_75189_1_ > 4;
                }
            }, new PieceWeight(PortalRoom.class, 20, 1) {
                private static final String __OBFID = "CL_00000485";
                
                @Override
                public boolean canSpawnMoreStructuresOfType(final int p_75189_1_) {
                    return super.canSpawnMoreStructuresOfType(p_75189_1_) && p_75189_1_ > 5;
                }
            } };
        strongholdStones = new Stones(null);
    }
    
    public static class ChestCorridor extends Stronghold
    {
        private static final List strongholdChestContents;
        private boolean hasMadeChest;
        private static final String __OBFID = "CL_00000487";
        
        public ChestCorridor() {
        }
        
        public ChestCorridor(final int p_i45582_1_, final Random p_i45582_2_, final StructureBoundingBox p_i45582_3_, final EnumFacing p_i45582_4_) {
            super(p_i45582_1_);
            this.coordBaseMode = p_i45582_4_;
            this.field_143013_d = this.getRandomDoor(p_i45582_2_);
            this.boundingBox = p_i45582_3_;
        }
        
        @Override
        protected void writeStructureToNBT(final NBTTagCompound p_143012_1_) {
            super.writeStructureToNBT(p_143012_1_);
            p_143012_1_.setBoolean("Chest", this.hasMadeChest);
        }
        
        @Override
        protected void readStructureFromNBT(final NBTTagCompound p_143011_1_) {
            super.readStructureFromNBT(p_143011_1_);
            this.hasMadeChest = p_143011_1_.getBoolean("Chest");
        }
        
        @Override
        public void buildComponent(final StructureComponent p_74861_1_, final List p_74861_2_, final Random p_74861_3_) {
            this.getNextComponentNormal((Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
        }
        
        public static ChestCorridor func_175868_a(final List p_175868_0_, final Random p_175868_1_, final int p_175868_2_, final int p_175868_3_, final int p_175868_4_, final EnumFacing p_175868_5_, final int p_175868_6_) {
            final StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175868_2_, p_175868_3_, p_175868_4_, -1, -1, 0, 5, 5, 7, p_175868_5_);
            return (Stronghold.canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(p_175868_0_, var7) == null) ? new ChestCorridor(p_175868_6_, p_175868_1_, var7, p_175868_5_) : null;
        }
        
        @Override
        public boolean addComponentParts(final World worldIn, final Random p_74875_2_, final StructureBoundingBox p_74875_3_) {
            if (this.isLiquidInStructureBoundingBox(worldIn, p_74875_3_)) {
                return false;
            }
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 4, 4, 6, true, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.placeDoor(worldIn, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 1, 0);
            this.placeDoor(worldIn, p_74875_2_, p_74875_3_, Door.OPENING, 1, 1, 6);
            this.func_175804_a(worldIn, p_74875_3_, 3, 1, 2, 3, 1, 4, Blocks.stonebrick.getDefaultState(), Blocks.stonebrick.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SMOOTHBRICK.func_176624_a()), 3, 1, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SMOOTHBRICK.func_176624_a()), 3, 1, 5, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SMOOTHBRICK.func_176624_a()), 3, 2, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SMOOTHBRICK.func_176624_a()), 3, 2, 4, p_74875_3_);
            for (int var4 = 2; var4 <= 4; ++var4) {
                this.func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SMOOTHBRICK.func_176624_a()), 2, 1, var4, p_74875_3_);
            }
            if (!this.hasMadeChest && p_74875_3_.func_175898_b(new BlockPos(this.getXWithOffset(3, 3), this.getYWithOffset(2), this.getZWithOffset(3, 3)))) {
                this.hasMadeChest = true;
                this.func_180778_a(worldIn, p_74875_3_, p_74875_2_, 3, 2, 3, WeightedRandomChestContent.func_177629_a(ChestCorridor.strongholdChestContents, Items.enchanted_book.getRandomEnchantedBook(p_74875_2_)), 2 + p_74875_2_.nextInt(2));
            }
            return true;
        }
        
        static {
            strongholdChestContents = Lists.newArrayList((Object[])new WeightedRandomChestContent[] { new WeightedRandomChestContent(Items.ender_pearl, 0, 1, 1, 10), new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5), new WeightedRandomChestContent(Items.redstone, 0, 4, 9, 5), new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15), new WeightedRandomChestContent(Items.apple, 0, 1, 3, 15), new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_sword, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_chestplate, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_helmet, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_leggings, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_boots, 0, 1, 1, 5), new WeightedRandomChestContent(Items.golden_apple, 0, 1, 1, 1), new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 1), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1) });
        }
    }
    
    public static class Corridor extends Stronghold
    {
        private int field_74993_a;
        private static final String __OBFID = "CL_00000488";
        
        public Corridor() {
        }
        
        public Corridor(final int p_i45581_1_, final Random p_i45581_2_, final StructureBoundingBox p_i45581_3_, final EnumFacing p_i45581_4_) {
            super(p_i45581_1_);
            this.coordBaseMode = p_i45581_4_;
            this.boundingBox = p_i45581_3_;
            this.field_74993_a = ((p_i45581_4_ != EnumFacing.NORTH && p_i45581_4_ != EnumFacing.SOUTH) ? p_i45581_3_.getXSize() : p_i45581_3_.getZSize());
        }
        
        @Override
        protected void writeStructureToNBT(final NBTTagCompound p_143012_1_) {
            super.writeStructureToNBT(p_143012_1_);
            p_143012_1_.setInteger("Steps", this.field_74993_a);
        }
        
        @Override
        protected void readStructureFromNBT(final NBTTagCompound p_143011_1_) {
            super.readStructureFromNBT(p_143011_1_);
            this.field_74993_a = p_143011_1_.getInteger("Steps");
        }
        
        public static StructureBoundingBox func_175869_a(final List p_175869_0_, final Random p_175869_1_, final int p_175869_2_, final int p_175869_3_, final int p_175869_4_, final EnumFacing p_175869_5_) {
            final boolean var6 = true;
            StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, 4, p_175869_5_);
            final StructureComponent var8 = StructureComponent.findIntersecting(p_175869_0_, var7);
            if (var8 == null) {
                return null;
            }
            if (var8.getBoundingBox().minY == var7.minY) {
                for (int var9 = 3; var9 >= 1; --var9) {
                    var7 = StructureBoundingBox.func_175897_a(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, var9 - 1, p_175869_5_);
                    if (!var8.getBoundingBox().intersectsWith(var7)) {
                        return StructureBoundingBox.func_175897_a(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, var9, p_175869_5_);
                    }
                }
            }
            return null;
        }
        
        @Override
        public boolean addComponentParts(final World worldIn, final Random p_74875_2_, final StructureBoundingBox p_74875_3_) {
            if (this.isLiquidInStructureBoundingBox(worldIn, p_74875_3_)) {
                return false;
            }
            for (int var4 = 0; var4 < this.field_74993_a; ++var4) {
                this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 0, 0, var4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 1, 0, var4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 2, 0, var4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 3, 0, var4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 4, 0, var4, p_74875_3_);
                for (int var5 = 1; var5 <= 3; ++var5) {
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 0, var5, var4, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.air.getDefaultState(), 1, var5, var4, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.air.getDefaultState(), 2, var5, var4, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.air.getDefaultState(), 3, var5, var4, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 4, var5, var4, p_74875_3_);
                }
                this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 0, 4, var4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 1, 4, var4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 2, 4, var4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 3, 4, var4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 4, 4, var4, p_74875_3_);
            }
            return true;
        }
    }
    
    public static class Crossing extends Stronghold
    {
        private boolean field_74996_b;
        private boolean field_74997_c;
        private boolean field_74995_d;
        private boolean field_74999_h;
        private static final String __OBFID = "CL_00000489";
        
        public Crossing() {
        }
        
        public Crossing(final int p_i45580_1_, final Random p_i45580_2_, final StructureBoundingBox p_i45580_3_, final EnumFacing p_i45580_4_) {
            super(p_i45580_1_);
            this.coordBaseMode = p_i45580_4_;
            this.field_143013_d = this.getRandomDoor(p_i45580_2_);
            this.boundingBox = p_i45580_3_;
            this.field_74996_b = p_i45580_2_.nextBoolean();
            this.field_74997_c = p_i45580_2_.nextBoolean();
            this.field_74995_d = p_i45580_2_.nextBoolean();
            this.field_74999_h = (p_i45580_2_.nextInt(3) > 0);
        }
        
        @Override
        protected void writeStructureToNBT(final NBTTagCompound p_143012_1_) {
            super.writeStructureToNBT(p_143012_1_);
            p_143012_1_.setBoolean("leftLow", this.field_74996_b);
            p_143012_1_.setBoolean("leftHigh", this.field_74997_c);
            p_143012_1_.setBoolean("rightLow", this.field_74995_d);
            p_143012_1_.setBoolean("rightHigh", this.field_74999_h);
        }
        
        @Override
        protected void readStructureFromNBT(final NBTTagCompound p_143011_1_) {
            super.readStructureFromNBT(p_143011_1_);
            this.field_74996_b = p_143011_1_.getBoolean("leftLow");
            this.field_74997_c = p_143011_1_.getBoolean("leftHigh");
            this.field_74995_d = p_143011_1_.getBoolean("rightLow");
            this.field_74999_h = p_143011_1_.getBoolean("rightHigh");
        }
        
        @Override
        public void buildComponent(final StructureComponent p_74861_1_, final List p_74861_2_, final Random p_74861_3_) {
            int var4 = 3;
            int var5 = 5;
            if (this.coordBaseMode == EnumFacing.WEST || this.coordBaseMode == EnumFacing.NORTH) {
                var4 = 8 - var4;
                var5 = 8 - var5;
            }
            this.getNextComponentNormal((Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 5, 1);
            if (this.field_74996_b) {
                this.getNextComponentX((Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, var4, 1);
            }
            if (this.field_74997_c) {
                this.getNextComponentX((Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, var5, 7);
            }
            if (this.field_74995_d) {
                this.getNextComponentZ((Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, var4, 1);
            }
            if (this.field_74999_h) {
                this.getNextComponentZ((Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, var5, 7);
            }
        }
        
        public static Crossing func_175866_a(final List p_175866_0_, final Random p_175866_1_, final int p_175866_2_, final int p_175866_3_, final int p_175866_4_, final EnumFacing p_175866_5_, final int p_175866_6_) {
            final StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175866_2_, p_175866_3_, p_175866_4_, -4, -3, 0, 10, 9, 11, p_175866_5_);
            return (Stronghold.canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(p_175866_0_, var7) == null) ? new Crossing(p_175866_6_, p_175866_1_, var7, p_175866_5_) : null;
        }
        
        @Override
        public boolean addComponentParts(final World worldIn, final Random p_74875_2_, final StructureBoundingBox p_74875_3_) {
            if (this.isLiquidInStructureBoundingBox(worldIn, p_74875_3_)) {
                return false;
            }
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 9, 8, 10, true, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.placeDoor(worldIn, p_74875_2_, p_74875_3_, this.field_143013_d, 4, 3, 0);
            if (this.field_74996_b) {
                this.func_175804_a(worldIn, p_74875_3_, 0, 3, 1, 0, 5, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }
            if (this.field_74995_d) {
                this.func_175804_a(worldIn, p_74875_3_, 9, 3, 1, 9, 5, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }
            if (this.field_74997_c) {
                this.func_175804_a(worldIn, p_74875_3_, 0, 5, 7, 0, 7, 9, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }
            if (this.field_74999_h) {
                this.func_175804_a(worldIn, p_74875_3_, 9, 5, 7, 9, 7, 9, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }
            this.func_175804_a(worldIn, p_74875_3_, 5, 1, 10, 7, 3, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 1, 2, 1, 8, 2, 6, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 1, 5, 4, 4, 9, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 8, 1, 5, 8, 4, 9, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 1, 4, 7, 3, 4, 9, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 1, 3, 5, 3, 3, 6, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.func_175804_a(worldIn, p_74875_3_, 1, 3, 4, 3, 3, 4, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 1, 4, 6, 3, 4, 6, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 5, 1, 7, 7, 1, 8, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.func_175804_a(worldIn, p_74875_3_, 5, 1, 9, 7, 1, 9, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 5, 2, 7, 7, 2, 7, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 4, 5, 7, 4, 5, 9, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 8, 5, 7, 8, 5, 9, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 5, 5, 7, 7, 5, 9, Blocks.double_stone_slab.getDefaultState(), Blocks.double_stone_slab.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.torch.getDefaultState(), 6, 5, 6, p_74875_3_);
            return true;
        }
    }
    
    public static class LeftTurn extends Stronghold
    {
        private static final String __OBFID = "CL_00000490";
        
        public LeftTurn() {
        }
        
        public LeftTurn(final int p_i45579_1_, final Random p_i45579_2_, final StructureBoundingBox p_i45579_3_, final EnumFacing p_i45579_4_) {
            super(p_i45579_1_);
            this.coordBaseMode = p_i45579_4_;
            this.field_143013_d = this.getRandomDoor(p_i45579_2_);
            this.boundingBox = p_i45579_3_;
        }
        
        @Override
        public void buildComponent(final StructureComponent p_74861_1_, final List p_74861_2_, final Random p_74861_3_) {
            if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.EAST) {
                this.getNextComponentZ((Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
            }
            else {
                this.getNextComponentX((Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
            }
        }
        
        public static LeftTurn func_175867_a(final List p_175867_0_, final Random p_175867_1_, final int p_175867_2_, final int p_175867_3_, final int p_175867_4_, final EnumFacing p_175867_5_, final int p_175867_6_) {
            final StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175867_2_, p_175867_3_, p_175867_4_, -1, -1, 0, 5, 5, 5, p_175867_5_);
            return (Stronghold.canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(p_175867_0_, var7) == null) ? new LeftTurn(p_175867_6_, p_175867_1_, var7, p_175867_5_) : null;
        }
        
        @Override
        public boolean addComponentParts(final World worldIn, final Random p_74875_2_, final StructureBoundingBox p_74875_3_) {
            if (this.isLiquidInStructureBoundingBox(worldIn, p_74875_3_)) {
                return false;
            }
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 4, 4, 4, true, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.placeDoor(worldIn, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 1, 0);
            if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.EAST) {
                this.func_175804_a(worldIn, p_74875_3_, 4, 1, 1, 4, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }
            else {
                this.func_175804_a(worldIn, p_74875_3_, 0, 1, 1, 0, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }
            return true;
        }
    }
    
    public static class Library extends Stronghold
    {
        private static final List strongholdLibraryChestContents;
        private boolean isLargeRoom;
        private static final String __OBFID = "CL_00000491";
        
        public Library() {
        }
        
        public Library(final int p_i45578_1_, final Random p_i45578_2_, final StructureBoundingBox p_i45578_3_, final EnumFacing p_i45578_4_) {
            super(p_i45578_1_);
            this.coordBaseMode = p_i45578_4_;
            this.field_143013_d = this.getRandomDoor(p_i45578_2_);
            this.boundingBox = p_i45578_3_;
            this.isLargeRoom = (p_i45578_3_.getYSize() > 6);
        }
        
        @Override
        protected void writeStructureToNBT(final NBTTagCompound p_143012_1_) {
            super.writeStructureToNBT(p_143012_1_);
            p_143012_1_.setBoolean("Tall", this.isLargeRoom);
        }
        
        @Override
        protected void readStructureFromNBT(final NBTTagCompound p_143011_1_) {
            super.readStructureFromNBT(p_143011_1_);
            this.isLargeRoom = p_143011_1_.getBoolean("Tall");
        }
        
        public static Library func_175864_a(final List p_175864_0_, final Random p_175864_1_, final int p_175864_2_, final int p_175864_3_, final int p_175864_4_, final EnumFacing p_175864_5_, final int p_175864_6_) {
            StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175864_2_, p_175864_3_, p_175864_4_, -4, -1, 0, 14, 11, 15, p_175864_5_);
            if (!Stronghold.canStrongholdGoDeeper(var7) || StructureComponent.findIntersecting(p_175864_0_, var7) != null) {
                var7 = StructureBoundingBox.func_175897_a(p_175864_2_, p_175864_3_, p_175864_4_, -4, -1, 0, 14, 6, 15, p_175864_5_);
                if (!Stronghold.canStrongholdGoDeeper(var7) || StructureComponent.findIntersecting(p_175864_0_, var7) != null) {
                    return null;
                }
            }
            return new Library(p_175864_6_, p_175864_1_, var7, p_175864_5_);
        }
        
        @Override
        public boolean addComponentParts(final World worldIn, final Random p_74875_2_, final StructureBoundingBox p_74875_3_) {
            if (this.isLiquidInStructureBoundingBox(worldIn, p_74875_3_)) {
                return false;
            }
            byte var4 = 11;
            if (!this.isLargeRoom) {
                var4 = 6;
            }
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 13, var4 - 1, 14, true, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.placeDoor(worldIn, p_74875_2_, p_74875_3_, this.field_143013_d, 4, 1, 0);
            this.func_175805_a(worldIn, p_74875_3_, p_74875_2_, 0.07f, 2, 1, 1, 11, 4, 13, Blocks.web.getDefaultState(), Blocks.web.getDefaultState(), false);
            final boolean var5 = true;
            final boolean var6 = true;
            for (int var7 = 1; var7 <= 13; ++var7) {
                if ((var7 - 1) % 4 == 0) {
                    this.func_175804_a(worldIn, p_74875_3_, 1, 1, var7, 1, 4, var7, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
                    this.func_175804_a(worldIn, p_74875_3_, 12, 1, var7, 12, 4, var7, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
                    this.func_175811_a(worldIn, Blocks.torch.getDefaultState(), 2, 3, var7, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.torch.getDefaultState(), 11, 3, var7, p_74875_3_);
                    if (this.isLargeRoom) {
                        this.func_175804_a(worldIn, p_74875_3_, 1, 6, var7, 1, 9, var7, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
                        this.func_175804_a(worldIn, p_74875_3_, 12, 6, var7, 12, 9, var7, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
                    }
                }
                else {
                    this.func_175804_a(worldIn, p_74875_3_, 1, 1, var7, 1, 4, var7, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
                    this.func_175804_a(worldIn, p_74875_3_, 12, 1, var7, 12, 4, var7, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
                    if (this.isLargeRoom) {
                        this.func_175804_a(worldIn, p_74875_3_, 1, 6, var7, 1, 9, var7, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
                        this.func_175804_a(worldIn, p_74875_3_, 12, 6, var7, 12, 9, var7, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
                    }
                }
            }
            for (int var7 = 3; var7 < 12; var7 += 2) {
                this.func_175804_a(worldIn, p_74875_3_, 3, 1, var7, 4, 3, var7, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 6, 1, var7, 7, 3, var7, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 9, 1, var7, 10, 3, var7, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
            }
            if (this.isLargeRoom) {
                this.func_175804_a(worldIn, p_74875_3_, 1, 5, 1, 3, 5, 13, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 10, 5, 1, 12, 5, 13, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 5, 1, 9, 5, 2, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 5, 12, 9, 5, 13, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 9, 5, 11, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 8, 5, 11, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 9, 5, 10, p_74875_3_);
                this.func_175804_a(worldIn, p_74875_3_, 3, 6, 2, 3, 6, 12, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 10, 6, 2, 10, 6, 10, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 6, 2, 9, 6, 2, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 4, 6, 12, 8, 6, 12, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 9, 6, 11, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 8, 6, 11, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 9, 6, 10, p_74875_3_);
                final int var7 = this.getMetadataWithOffset(Blocks.ladder, 3);
                this.func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(var7), 10, 1, 13, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(var7), 10, 2, 13, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(var7), 10, 3, 13, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(var7), 10, 4, 13, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(var7), 10, 5, 13, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(var7), 10, 6, 13, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(var7), 10, 7, 13, p_74875_3_);
                final byte var8 = 7;
                final byte var9 = 7;
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8 - 1, 9, var9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8, 9, var9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8 - 1, 8, var9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8, 8, var9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8 - 1, 7, var9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8, 7, var9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8 - 2, 7, var9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8 + 1, 7, var9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8 - 1, 7, var9 - 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8 - 1, 7, var9 + 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8, 7, var9 - 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8, 7, var9 + 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.torch.getDefaultState(), var8 - 2, 8, var9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.torch.getDefaultState(), var8 + 1, 8, var9, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.torch.getDefaultState(), var8 - 1, 8, var9 - 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.torch.getDefaultState(), var8 - 1, 8, var9 + 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.torch.getDefaultState(), var8, 8, var9 - 1, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.torch.getDefaultState(), var8, 8, var9 + 1, p_74875_3_);
            }
            this.func_180778_a(worldIn, p_74875_3_, p_74875_2_, 3, 3, 5, WeightedRandomChestContent.func_177629_a(Library.strongholdLibraryChestContents, Items.enchanted_book.func_92112_a(p_74875_2_, 1, 5, 2)), 1 + p_74875_2_.nextInt(4));
            if (this.isLargeRoom) {
                this.func_175811_a(worldIn, Blocks.air.getDefaultState(), 12, 9, 1, p_74875_3_);
                this.func_180778_a(worldIn, p_74875_3_, p_74875_2_, 12, 8, 1, WeightedRandomChestContent.func_177629_a(Library.strongholdLibraryChestContents, Items.enchanted_book.func_92112_a(p_74875_2_, 1, 5, 2)), 1 + p_74875_2_.nextInt(4));
            }
            return true;
        }
        
        static {
            strongholdLibraryChestContents = Lists.newArrayList((Object[])new WeightedRandomChestContent[] { new WeightedRandomChestContent(Items.book, 0, 1, 3, 20), new WeightedRandomChestContent(Items.paper, 0, 2, 7, 20), new WeightedRandomChestContent(Items.map, 0, 1, 1, 1), new WeightedRandomChestContent(Items.compass, 0, 1, 1, 1) });
        }
    }
    
    static class PieceWeight
    {
        public Class pieceClass;
        public final int pieceWeight;
        public int instancesSpawned;
        public int instancesLimit;
        private static final String __OBFID = "CL_00000492";
        
        public PieceWeight(final Class p_i2076_1_, final int p_i2076_2_, final int p_i2076_3_) {
            this.pieceClass = p_i2076_1_;
            this.pieceWeight = p_i2076_2_;
            this.instancesLimit = p_i2076_3_;
        }
        
        public boolean canSpawnMoreStructuresOfType(final int p_75189_1_) {
            return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
        }
        
        public boolean canSpawnMoreStructures() {
            return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
        }
    }
    
    public static class PortalRoom extends Stronghold
    {
        private boolean hasSpawner;
        private static final String __OBFID = "CL_00000493";
        
        public PortalRoom() {
        }
        
        public PortalRoom(final int p_i45577_1_, final Random p_i45577_2_, final StructureBoundingBox p_i45577_3_, final EnumFacing p_i45577_4_) {
            super(p_i45577_1_);
            this.coordBaseMode = p_i45577_4_;
            this.boundingBox = p_i45577_3_;
        }
        
        @Override
        protected void writeStructureToNBT(final NBTTagCompound p_143012_1_) {
            super.writeStructureToNBT(p_143012_1_);
            p_143012_1_.setBoolean("Mob", this.hasSpawner);
        }
        
        @Override
        protected void readStructureFromNBT(final NBTTagCompound p_143011_1_) {
            super.readStructureFromNBT(p_143011_1_);
            this.hasSpawner = p_143011_1_.getBoolean("Mob");
        }
        
        @Override
        public void buildComponent(final StructureComponent p_74861_1_, final List p_74861_2_, final Random p_74861_3_) {
            if (p_74861_1_ != null) {
                ((Stairs2)p_74861_1_).strongholdPortalRoom = this;
            }
        }
        
        public static PortalRoom func_175865_a(final List p_175865_0_, final Random p_175865_1_, final int p_175865_2_, final int p_175865_3_, final int p_175865_4_, final EnumFacing p_175865_5_, final int p_175865_6_) {
            final StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175865_2_, p_175865_3_, p_175865_4_, -4, -1, 0, 11, 8, 16, p_175865_5_);
            return (Stronghold.canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(p_175865_0_, var7) == null) ? new PortalRoom(p_175865_6_, p_175865_1_, var7, p_175865_5_) : null;
        }
        
        @Override
        public boolean addComponentParts(final World worldIn, final Random p_74875_2_, final StructureBoundingBox p_74875_3_) {
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 10, 7, 15, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.placeDoor(worldIn, p_74875_2_, p_74875_3_, Door.GRATES, 4, 1, 0);
            final byte var4 = 6;
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 1, var4, 1, 1, var4, 14, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 9, var4, 1, 9, var4, 14, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 2, var4, 1, 8, var4, 2, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 2, var4, 14, 8, var4, 14, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 1, 1, 1, 2, 1, 4, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 8, 1, 1, 9, 1, 4, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 1, 1, 1, 3, Blocks.flowing_lava.getDefaultState(), Blocks.flowing_lava.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 9, 1, 1, 9, 1, 3, Blocks.flowing_lava.getDefaultState(), Blocks.flowing_lava.getDefaultState(), false);
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 3, 1, 8, 7, 1, 12, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.func_175804_a(worldIn, p_74875_3_, 4, 1, 9, 6, 1, 11, Blocks.flowing_lava.getDefaultState(), Blocks.flowing_lava.getDefaultState(), false);
            for (int var5 = 3; var5 < 14; var5 += 2) {
                this.func_175804_a(worldIn, p_74875_3_, 0, 3, var5, 0, 4, var5, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
                this.func_175804_a(worldIn, p_74875_3_, 10, 3, var5, 10, 4, var5, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
            }
            for (int var5 = 2; var5 < 9; var5 += 2) {
                this.func_175804_a(worldIn, p_74875_3_, var5, 3, 15, var5, 4, 15, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
            }
            int var5 = this.getMetadataWithOffset(Blocks.stone_brick_stairs, 3);
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 1, 5, 6, 1, 7, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 2, 6, 6, 2, 7, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 3, 7, 6, 3, 7, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            for (int var6 = 4; var6 <= 6; ++var6) {
                this.func_175811_a(worldIn, Blocks.stone_brick_stairs.getStateFromMeta(var5), var6, 1, 4, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.stone_brick_stairs.getStateFromMeta(var5), var6, 2, 5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.stone_brick_stairs.getStateFromMeta(var5), var6, 3, 6, p_74875_3_);
            }
            int var6 = EnumFacing.NORTH.getHorizontalIndex();
            int var7 = EnumFacing.SOUTH.getHorizontalIndex();
            int var8 = EnumFacing.EAST.getHorizontalIndex();
            int var9 = EnumFacing.WEST.getHorizontalIndex();
            if (this.coordBaseMode != null) {
                switch (StructureStrongholdPieces.SwitchEnumFacing.field_175951_b[this.coordBaseMode.ordinal()]) {
                    case 2: {
                        var6 = EnumFacing.SOUTH.getHorizontalIndex();
                        var7 = EnumFacing.NORTH.getHorizontalIndex();
                        break;
                    }
                    case 3: {
                        var6 = EnumFacing.WEST.getHorizontalIndex();
                        var7 = EnumFacing.EAST.getHorizontalIndex();
                        var8 = EnumFacing.SOUTH.getHorizontalIndex();
                        var9 = EnumFacing.NORTH.getHorizontalIndex();
                        break;
                    }
                    case 4: {
                        var6 = EnumFacing.EAST.getHorizontalIndex();
                        var7 = EnumFacing.WEST.getHorizontalIndex();
                        var8 = EnumFacing.SOUTH.getHorizontalIndex();
                        var9 = EnumFacing.NORTH.getHorizontalIndex();
                        break;
                    }
                }
            }
            this.func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var6).withProperty(BlockEndPortalFrame.field_176507_b, p_74875_2_.nextFloat() > 0.9f), 4, 3, 8, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var6).withProperty(BlockEndPortalFrame.field_176507_b, p_74875_2_.nextFloat() > 0.9f), 5, 3, 8, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var6).withProperty(BlockEndPortalFrame.field_176507_b, p_74875_2_.nextFloat() > 0.9f), 6, 3, 8, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var7).withProperty(BlockEndPortalFrame.field_176507_b, p_74875_2_.nextFloat() > 0.9f), 4, 3, 12, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var7).withProperty(BlockEndPortalFrame.field_176507_b, p_74875_2_.nextFloat() > 0.9f), 5, 3, 12, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var7).withProperty(BlockEndPortalFrame.field_176507_b, p_74875_2_.nextFloat() > 0.9f), 6, 3, 12, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var8).withProperty(BlockEndPortalFrame.field_176507_b, p_74875_2_.nextFloat() > 0.9f), 3, 3, 9, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var8).withProperty(BlockEndPortalFrame.field_176507_b, p_74875_2_.nextFloat() > 0.9f), 3, 3, 10, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var8).withProperty(BlockEndPortalFrame.field_176507_b, p_74875_2_.nextFloat() > 0.9f), 3, 3, 11, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var9).withProperty(BlockEndPortalFrame.field_176507_b, p_74875_2_.nextFloat() > 0.9f), 7, 3, 9, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var9).withProperty(BlockEndPortalFrame.field_176507_b, p_74875_2_.nextFloat() > 0.9f), 7, 3, 10, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var9).withProperty(BlockEndPortalFrame.field_176507_b, p_74875_2_.nextFloat() > 0.9f), 7, 3, 11, p_74875_3_);
            if (!this.hasSpawner) {
                final int var10 = this.getYWithOffset(3);
                final BlockPos var11 = new BlockPos(this.getXWithOffset(5, 6), var10, this.getZWithOffset(5, 6));
                if (p_74875_3_.func_175898_b(var11)) {
                    this.hasSpawner = true;
                    worldIn.setBlockState(var11, Blocks.mob_spawner.getDefaultState(), 2);
                    final TileEntity var12 = worldIn.getTileEntity(var11);
                    if (var12 instanceof TileEntityMobSpawner) {
                        ((TileEntityMobSpawner)var12).getSpawnerBaseLogic().setEntityName("Silverfish");
                    }
                }
            }
            return true;
        }
    }
    
    public static class Prison extends Stronghold
    {
        private static final String __OBFID = "CL_00000494";
        
        public Prison() {
        }
        
        public Prison(final int p_i45576_1_, final Random p_i45576_2_, final StructureBoundingBox p_i45576_3_, final EnumFacing p_i45576_4_) {
            super(p_i45576_1_);
            this.coordBaseMode = p_i45576_4_;
            this.field_143013_d = this.getRandomDoor(p_i45576_2_);
            this.boundingBox = p_i45576_3_;
        }
        
        @Override
        public void buildComponent(final StructureComponent p_74861_1_, final List p_74861_2_, final Random p_74861_3_) {
            this.getNextComponentNormal((Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
        }
        
        public static Prison func_175860_a(final List p_175860_0_, final Random p_175860_1_, final int p_175860_2_, final int p_175860_3_, final int p_175860_4_, final EnumFacing p_175860_5_, final int p_175860_6_) {
            final StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175860_2_, p_175860_3_, p_175860_4_, -1, -1, 0, 9, 5, 11, p_175860_5_);
            return (Stronghold.canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(p_175860_0_, var7) == null) ? new Prison(p_175860_6_, p_175860_1_, var7, p_175860_5_) : null;
        }
        
        @Override
        public boolean addComponentParts(final World worldIn, final Random p_74875_2_, final StructureBoundingBox p_74875_3_) {
            if (this.isLiquidInStructureBoundingBox(worldIn, p_74875_3_)) {
                return false;
            }
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 8, 4, 10, true, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.placeDoor(worldIn, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 1, 0);
            this.func_175804_a(worldIn, p_74875_3_, 1, 1, 10, 3, 3, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 1, 1, 4, 3, 1, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 1, 3, 4, 3, 3, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 1, 7, 4, 3, 7, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 1, 9, 4, 3, 9, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.func_175804_a(worldIn, p_74875_3_, 4, 1, 4, 4, 3, 6, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 5, 1, 5, 7, 3, 5, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
            this.func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), 4, 3, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), 4, 3, 8, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.iron_door.getStateFromMeta(this.getMetadataWithOffset(Blocks.iron_door, 3)), 4, 1, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.iron_door.getStateFromMeta(this.getMetadataWithOffset(Blocks.iron_door, 3) + 8), 4, 2, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.iron_door.getStateFromMeta(this.getMetadataWithOffset(Blocks.iron_door, 3)), 4, 1, 8, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.iron_door.getStateFromMeta(this.getMetadataWithOffset(Blocks.iron_door, 3) + 8), 4, 2, 8, p_74875_3_);
            return true;
        }
    }
    
    public static class RightTurn extends LeftTurn
    {
        private static final String __OBFID = "CL_00000495";
        
        @Override
        public void buildComponent(final StructureComponent p_74861_1_, final List p_74861_2_, final Random p_74861_3_) {
            if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.EAST) {
                this.getNextComponentX((Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
            }
            else {
                this.getNextComponentZ((Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
            }
        }
        
        @Override
        public boolean addComponentParts(final World worldIn, final Random p_74875_2_, final StructureBoundingBox p_74875_3_) {
            if (this.isLiquidInStructureBoundingBox(worldIn, p_74875_3_)) {
                return false;
            }
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 4, 4, 4, true, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.placeDoor(worldIn, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 1, 0);
            if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.EAST) {
                this.func_175804_a(worldIn, p_74875_3_, 0, 1, 1, 0, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }
            else {
                this.func_175804_a(worldIn, p_74875_3_, 4, 1, 1, 4, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }
            return true;
        }
    }
    
    public static class RoomCrossing extends Stronghold
    {
        private static final List strongholdRoomCrossingChestContents;
        protected int roomType;
        private static final String __OBFID = "CL_00000496";
        
        public RoomCrossing() {
        }
        
        public RoomCrossing(final int p_i45575_1_, final Random p_i45575_2_, final StructureBoundingBox p_i45575_3_, final EnumFacing p_i45575_4_) {
            super(p_i45575_1_);
            this.coordBaseMode = p_i45575_4_;
            this.field_143013_d = this.getRandomDoor(p_i45575_2_);
            this.boundingBox = p_i45575_3_;
            this.roomType = p_i45575_2_.nextInt(5);
        }
        
        @Override
        protected void writeStructureToNBT(final NBTTagCompound p_143012_1_) {
            super.writeStructureToNBT(p_143012_1_);
            p_143012_1_.setInteger("Type", this.roomType);
        }
        
        @Override
        protected void readStructureFromNBT(final NBTTagCompound p_143011_1_) {
            super.readStructureFromNBT(p_143011_1_);
            this.roomType = p_143011_1_.getInteger("Type");
        }
        
        @Override
        public void buildComponent(final StructureComponent p_74861_1_, final List p_74861_2_, final Random p_74861_3_) {
            this.getNextComponentNormal((Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 4, 1);
            this.getNextComponentX((Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 4);
            this.getNextComponentZ((Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 4);
        }
        
        public static RoomCrossing func_175859_a(final List p_175859_0_, final Random p_175859_1_, final int p_175859_2_, final int p_175859_3_, final int p_175859_4_, final EnumFacing p_175859_5_, final int p_175859_6_) {
            final StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175859_2_, p_175859_3_, p_175859_4_, -4, -1, 0, 11, 7, 11, p_175859_5_);
            return (Stronghold.canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(p_175859_0_, var7) == null) ? new RoomCrossing(p_175859_6_, p_175859_1_, var7, p_175859_5_) : null;
        }
        
        @Override
        public boolean addComponentParts(final World worldIn, final Random p_74875_2_, final StructureBoundingBox p_74875_3_) {
            if (this.isLiquidInStructureBoundingBox(worldIn, p_74875_3_)) {
                return false;
            }
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 10, 6, 10, true, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.placeDoor(worldIn, p_74875_2_, p_74875_3_, this.field_143013_d, 4, 1, 0);
            this.func_175804_a(worldIn, p_74875_3_, 4, 1, 10, 6, 3, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 0, 1, 4, 0, 3, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.func_175804_a(worldIn, p_74875_3_, 10, 1, 4, 10, 3, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            switch (this.roomType) {
                case 0: {
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 5, 1, 5, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 5, 2, 5, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 5, 3, 5, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.torch.getDefaultState(), 4, 3, 5, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.torch.getDefaultState(), 6, 3, 5, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.torch.getDefaultState(), 5, 3, 4, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.torch.getDefaultState(), 5, 3, 6, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.stone_slab.getDefaultState(), 4, 1, 4, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.stone_slab.getDefaultState(), 4, 1, 5, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.stone_slab.getDefaultState(), 4, 1, 6, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.stone_slab.getDefaultState(), 6, 1, 4, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.stone_slab.getDefaultState(), 6, 1, 5, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.stone_slab.getDefaultState(), 6, 1, 6, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.stone_slab.getDefaultState(), 5, 1, 4, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.stone_slab.getDefaultState(), 5, 1, 6, p_74875_3_);
                    break;
                }
                case 1: {
                    for (int var4 = 0; var4 < 5; ++var4) {
                        this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 3, 1, 3 + var4, p_74875_3_);
                        this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 7, 1, 3 + var4, p_74875_3_);
                        this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 3 + var4, 1, 3, p_74875_3_);
                        this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 3 + var4, 1, 7, p_74875_3_);
                    }
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 5, 1, 5, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 5, 2, 5, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 5, 3, 5, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.flowing_water.getDefaultState(), 5, 4, 5, p_74875_3_);
                    break;
                }
                case 2: {
                    for (int var4 = 1; var4 <= 9; ++var4) {
                        this.func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 1, 3, var4, p_74875_3_);
                        this.func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 9, 3, var4, p_74875_3_);
                    }
                    for (int var4 = 1; var4 <= 9; ++var4) {
                        this.func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), var4, 3, 1, p_74875_3_);
                        this.func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), var4, 3, 9, p_74875_3_);
                    }
                    this.func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 5, 1, 4, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 5, 1, 6, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 5, 3, 4, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 5, 3, 6, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 4, 1, 5, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 6, 1, 5, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 4, 3, 5, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 6, 3, 5, p_74875_3_);
                    for (int var4 = 1; var4 <= 3; ++var4) {
                        this.func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 4, var4, 4, p_74875_3_);
                        this.func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 6, var4, 4, p_74875_3_);
                        this.func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 4, var4, 6, p_74875_3_);
                        this.func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 6, var4, 6, p_74875_3_);
                    }
                    this.func_175811_a(worldIn, Blocks.torch.getDefaultState(), 5, 3, 5, p_74875_3_);
                    for (int var4 = 2; var4 <= 8; ++var4) {
                        this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 2, 3, var4, p_74875_3_);
                        this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 3, 3, var4, p_74875_3_);
                        if (var4 <= 3 || var4 >= 7) {
                            this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 4, 3, var4, p_74875_3_);
                            this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 5, 3, var4, p_74875_3_);
                            this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 6, 3, var4, p_74875_3_);
                        }
                        this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 7, 3, var4, p_74875_3_);
                        this.func_175811_a(worldIn, Blocks.planks.getDefaultState(), 8, 3, var4, p_74875_3_);
                    }
                    this.func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(this.getMetadataWithOffset(Blocks.ladder, EnumFacing.WEST.getIndex())), 9, 1, 3, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(this.getMetadataWithOffset(Blocks.ladder, EnumFacing.WEST.getIndex())), 9, 2, 3, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(this.getMetadataWithOffset(Blocks.ladder, EnumFacing.WEST.getIndex())), 9, 3, 3, p_74875_3_);
                    this.func_180778_a(worldIn, p_74875_3_, p_74875_2_, 3, 4, 8, WeightedRandomChestContent.func_177629_a(RoomCrossing.strongholdRoomCrossingChestContents, Items.enchanted_book.getRandomEnchantedBook(p_74875_2_)), 1 + p_74875_2_.nextInt(4));
                    break;
                }
            }
            return true;
        }
        
        static {
            strongholdRoomCrossingChestContents = Lists.newArrayList((Object[])new WeightedRandomChestContent[] { new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5), new WeightedRandomChestContent(Items.redstone, 0, 4, 9, 5), new WeightedRandomChestContent(Items.coal, 0, 3, 8, 10), new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15), new WeightedRandomChestContent(Items.apple, 0, 1, 3, 15), new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 1) });
        }
    }
    
    public static class Stairs extends Stronghold
    {
        private boolean field_75024_a;
        private static final String __OBFID = "CL_00000498";
        
        public Stairs() {
        }
        
        public Stairs(final int p_i2081_1_, final Random p_i2081_2_, final int p_i2081_3_, final int p_i2081_4_) {
            super(p_i2081_1_);
            this.field_75024_a = true;
            this.coordBaseMode = EnumFacing.Plane.HORIZONTAL.random(p_i2081_2_);
            this.field_143013_d = Door.OPENING;
            switch (StructureStrongholdPieces.SwitchEnumFacing.field_175951_b[this.coordBaseMode.ordinal()]) {
                case 1:
                case 2: {
                    this.boundingBox = new StructureBoundingBox(p_i2081_3_, 64, p_i2081_4_, p_i2081_3_ + 5 - 1, 74, p_i2081_4_ + 5 - 1);
                    break;
                }
                default: {
                    this.boundingBox = new StructureBoundingBox(p_i2081_3_, 64, p_i2081_4_, p_i2081_3_ + 5 - 1, 74, p_i2081_4_ + 5 - 1);
                    break;
                }
            }
        }
        
        public Stairs(final int p_i45574_1_, final Random p_i45574_2_, final StructureBoundingBox p_i45574_3_, final EnumFacing p_i45574_4_) {
            super(p_i45574_1_);
            this.field_75024_a = false;
            this.coordBaseMode = p_i45574_4_;
            this.field_143013_d = this.getRandomDoor(p_i45574_2_);
            this.boundingBox = p_i45574_3_;
        }
        
        @Override
        protected void writeStructureToNBT(final NBTTagCompound p_143012_1_) {
            super.writeStructureToNBT(p_143012_1_);
            p_143012_1_.setBoolean("Source", this.field_75024_a);
        }
        
        @Override
        protected void readStructureFromNBT(final NBTTagCompound p_143011_1_) {
            super.readStructureFromNBT(p_143011_1_);
            this.field_75024_a = p_143011_1_.getBoolean("Source");
        }
        
        @Override
        public void buildComponent(final StructureComponent p_74861_1_, final List p_74861_2_, final Random p_74861_3_) {
            if (this.field_75024_a) {
                StructureStrongholdPieces.strongComponentType = Crossing.class;
            }
            this.getNextComponentNormal((Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
        }
        
        public static Stairs func_175863_a(final List p_175863_0_, final Random p_175863_1_, final int p_175863_2_, final int p_175863_3_, final int p_175863_4_, final EnumFacing p_175863_5_, final int p_175863_6_) {
            final StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175863_2_, p_175863_3_, p_175863_4_, -1, -7, 0, 5, 11, 5, p_175863_5_);
            return (Stronghold.canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(p_175863_0_, var7) == null) ? new Stairs(p_175863_6_, p_175863_1_, var7, p_175863_5_) : null;
        }
        
        @Override
        public boolean addComponentParts(final World worldIn, final Random p_74875_2_, final StructureBoundingBox p_74875_3_) {
            if (this.isLiquidInStructureBoundingBox(worldIn, p_74875_3_)) {
                return false;
            }
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 4, 10, 4, true, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.placeDoor(worldIn, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 7, 0);
            this.placeDoor(worldIn, p_74875_2_, p_74875_3_, Door.OPENING, 1, 1, 4);
            this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 2, 6, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 1, 5, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.STONE.func_176624_a()), 1, 6, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 1, 5, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 1, 4, 3, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.STONE.func_176624_a()), 1, 5, 3, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 2, 4, 3, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 3, 3, 3, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.STONE.func_176624_a()), 3, 4, 3, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 3, 3, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 3, 2, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.STONE.func_176624_a()), 3, 3, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 2, 2, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 1, 1, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.STONE.func_176624_a()), 1, 2, 1, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 1, 1, 2, p_74875_3_);
            this.func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.STONE.func_176624_a()), 1, 1, 3, p_74875_3_);
            return true;
        }
    }
    
    public static class Stairs2 extends Stairs
    {
        public PieceWeight strongholdPieceWeight;
        public PortalRoom strongholdPortalRoom;
        public List field_75026_c;
        private static final String __OBFID = "CL_00000499";
        
        public Stairs2() {
            this.field_75026_c = Lists.newArrayList();
        }
        
        public Stairs2(final int p_i2083_1_, final Random p_i2083_2_, final int p_i2083_3_, final int p_i2083_4_) {
            super(0, p_i2083_2_, p_i2083_3_, p_i2083_4_);
            this.field_75026_c = Lists.newArrayList();
        }
        
        @Override
        public BlockPos func_180776_a() {
            return (this.strongholdPortalRoom != null) ? this.strongholdPortalRoom.func_180776_a() : super.func_180776_a();
        }
    }
    
    public static class StairsStraight extends Stronghold
    {
        private static final String __OBFID = "CL_00000501";
        
        public StairsStraight() {
        }
        
        public StairsStraight(final int p_i45572_1_, final Random p_i45572_2_, final StructureBoundingBox p_i45572_3_, final EnumFacing p_i45572_4_) {
            super(p_i45572_1_);
            this.coordBaseMode = p_i45572_4_;
            this.field_143013_d = this.getRandomDoor(p_i45572_2_);
            this.boundingBox = p_i45572_3_;
        }
        
        @Override
        public void buildComponent(final StructureComponent p_74861_1_, final List p_74861_2_, final Random p_74861_3_) {
            this.getNextComponentNormal((Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
        }
        
        public static StairsStraight func_175861_a(final List p_175861_0_, final Random p_175861_1_, final int p_175861_2_, final int p_175861_3_, final int p_175861_4_, final EnumFacing p_175861_5_, final int p_175861_6_) {
            final StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175861_2_, p_175861_3_, p_175861_4_, -1, -7, 0, 5, 11, 8, p_175861_5_);
            return (Stronghold.canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(p_175861_0_, var7) == null) ? new StairsStraight(p_175861_6_, p_175861_1_, var7, p_175861_5_) : null;
        }
        
        @Override
        public boolean addComponentParts(final World worldIn, final Random p_74875_2_, final StructureBoundingBox p_74875_3_) {
            if (this.isLiquidInStructureBoundingBox(worldIn, p_74875_3_)) {
                return false;
            }
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 4, 10, 7, true, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.placeDoor(worldIn, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 7, 0);
            this.placeDoor(worldIn, p_74875_2_, p_74875_3_, Door.OPENING, 1, 1, 7);
            final int var4 = this.getMetadataWithOffset(Blocks.stone_stairs, 2);
            for (int var5 = 0; var5 < 6; ++var5) {
                this.func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var4), 1, 6 - var5, 1 + var5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var4), 2, 6 - var5, 1 + var5, p_74875_3_);
                this.func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var4), 3, 6 - var5, 1 + var5, p_74875_3_);
                if (var5 < 5) {
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 1, 5 - var5, 1 + var5, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 2, 5 - var5, 1 + var5, p_74875_3_);
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 3, 5 - var5, 1 + var5, p_74875_3_);
                }
            }
            return true;
        }
    }
    
    static class Stones extends StructureComponent.BlockSelector
    {
        private static final String __OBFID = "CL_00000497";
        
        private Stones() {
        }
        
        @Override
        public void selectBlocks(final Random p_75062_1_, final int p_75062_2_, final int p_75062_3_, final int p_75062_4_, final boolean p_75062_5_) {
            if (p_75062_5_) {
                final float var6 = p_75062_1_.nextFloat();
                if (var6 < 0.2f) {
                    this.field_151562_a = Blocks.stonebrick.getStateFromMeta(BlockStoneBrick.CRACKED_META);
                }
                else if (var6 < 0.5f) {
                    this.field_151562_a = Blocks.stonebrick.getStateFromMeta(BlockStoneBrick.MOSSY_META);
                }
                else if (var6 < 0.55f) {
                    this.field_151562_a = Blocks.monster_egg.getStateFromMeta(BlockSilverfish.EnumType.STONEBRICK.func_176881_a());
                }
                else {
                    this.field_151562_a = Blocks.stonebrick.getDefaultState();
                }
            }
            else {
                this.field_151562_a = Blocks.air.getDefaultState();
            }
        }
        
        Stones(final Object p_i2080_1_) {
            this();
        }
    }
    
    public static class Straight extends Stronghold
    {
        private boolean expandsX;
        private boolean expandsZ;
        private static final String __OBFID = "CL_00000500";
        
        public Straight() {
        }
        
        public Straight(final int p_i45573_1_, final Random p_i45573_2_, final StructureBoundingBox p_i45573_3_, final EnumFacing p_i45573_4_) {
            super(p_i45573_1_);
            this.coordBaseMode = p_i45573_4_;
            this.field_143013_d = this.getRandomDoor(p_i45573_2_);
            this.boundingBox = p_i45573_3_;
            this.expandsX = (p_i45573_2_.nextInt(2) == 0);
            this.expandsZ = (p_i45573_2_.nextInt(2) == 0);
        }
        
        @Override
        protected void writeStructureToNBT(final NBTTagCompound p_143012_1_) {
            super.writeStructureToNBT(p_143012_1_);
            p_143012_1_.setBoolean("Left", this.expandsX);
            p_143012_1_.setBoolean("Right", this.expandsZ);
        }
        
        @Override
        protected void readStructureFromNBT(final NBTTagCompound p_143011_1_) {
            super.readStructureFromNBT(p_143011_1_);
            this.expandsX = p_143011_1_.getBoolean("Left");
            this.expandsZ = p_143011_1_.getBoolean("Right");
        }
        
        @Override
        public void buildComponent(final StructureComponent p_74861_1_, final List p_74861_2_, final Random p_74861_3_) {
            this.getNextComponentNormal((Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
            if (this.expandsX) {
                this.getNextComponentX((Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 2);
            }
            if (this.expandsZ) {
                this.getNextComponentZ((Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 2);
            }
        }
        
        public static Straight func_175862_a(final List p_175862_0_, final Random p_175862_1_, final int p_175862_2_, final int p_175862_3_, final int p_175862_4_, final EnumFacing p_175862_5_, final int p_175862_6_) {
            final StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175862_2_, p_175862_3_, p_175862_4_, -1, -1, 0, 5, 5, 7, p_175862_5_);
            return (Stronghold.canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(p_175862_0_, var7) == null) ? new Straight(p_175862_6_, p_175862_1_, var7, p_175862_5_) : null;
        }
        
        @Override
        public boolean addComponentParts(final World worldIn, final Random p_74875_2_, final StructureBoundingBox p_74875_3_) {
            if (this.isLiquidInStructureBoundingBox(worldIn, p_74875_3_)) {
                return false;
            }
            this.fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 4, 4, 6, true, p_74875_2_, StructureStrongholdPieces.strongholdStones);
            this.placeDoor(worldIn, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 1, 0);
            this.placeDoor(worldIn, p_74875_2_, p_74875_3_, Door.OPENING, 1, 1, 6);
            this.func_175809_a(worldIn, p_74875_3_, p_74875_2_, 0.1f, 1, 2, 1, Blocks.torch.getDefaultState());
            this.func_175809_a(worldIn, p_74875_3_, p_74875_2_, 0.1f, 3, 2, 1, Blocks.torch.getDefaultState());
            this.func_175809_a(worldIn, p_74875_3_, p_74875_2_, 0.1f, 1, 2, 5, Blocks.torch.getDefaultState());
            this.func_175809_a(worldIn, p_74875_3_, p_74875_2_, 0.1f, 3, 2, 5, Blocks.torch.getDefaultState());
            if (this.expandsX) {
                this.func_175804_a(worldIn, p_74875_3_, 0, 1, 2, 0, 3, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }
            if (this.expandsZ) {
                this.func_175804_a(worldIn, p_74875_3_, 4, 1, 2, 4, 3, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            }
            return true;
        }
    }
    
    abstract static class Stronghold extends StructureComponent
    {
        protected Door field_143013_d;
        private static final String __OBFID = "CL_00000503";
        
        public Stronghold() {
            this.field_143013_d = Door.OPENING;
        }
        
        protected Stronghold(final int p_i2087_1_) {
            super(p_i2087_1_);
            this.field_143013_d = Door.OPENING;
        }
        
        @Override
        protected void writeStructureToNBT(final NBTTagCompound p_143012_1_) {
            p_143012_1_.setString("EntryDoor", this.field_143013_d.name());
        }
        
        @Override
        protected void readStructureFromNBT(final NBTTagCompound p_143011_1_) {
            this.field_143013_d = Door.valueOf(p_143011_1_.getString("EntryDoor"));
        }
        
        protected void placeDoor(final World worldIn, final Random p_74990_2_, final StructureBoundingBox p_74990_3_, final Door p_74990_4_, final int p_74990_5_, final int p_74990_6_, final int p_74990_7_) {
            switch (StructureStrongholdPieces.SwitchEnumFacing.doorEnum[p_74990_4_.ordinal()]) {
                default: {
                    this.func_175804_a(worldIn, p_74990_3_, p_74990_5_, p_74990_6_, p_74990_7_, p_74990_5_ + 3 - 1, p_74990_6_ + 3 - 1, p_74990_7_, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
                    break;
                }
                case 2: {
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.oak_door.getDefaultState(), p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.oak_door.getStateFromMeta(8), p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                    break;
                }
                case 3: {
                    this.func_175811_a(worldIn, Blocks.air.getDefaultState(), p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.air.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_);
                    break;
                }
                case 4: {
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.iron_door.getDefaultState(), p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.iron_door.getStateFromMeta(8), p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.stone_button.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_button, 4)), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_ + 1, p_74990_3_);
                    this.func_175811_a(worldIn, Blocks.stone_button.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_button, 3)), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_ - 1, p_74990_3_);
                    break;
                }
            }
        }
        
        protected Door getRandomDoor(final Random p_74988_1_) {
            final int var2 = p_74988_1_.nextInt(5);
            switch (var2) {
                default: {
                    return Door.OPENING;
                }
                case 2: {
                    return Door.WOOD_DOOR;
                }
                case 3: {
                    return Door.GRATES;
                }
                case 4: {
                    return Door.IRON_DOOR;
                }
            }
        }
        
        protected StructureComponent getNextComponentNormal(final Stairs2 p_74986_1_, final List p_74986_2_, final Random p_74986_3_, final int p_74986_4_, final int p_74986_5_) {
            if (this.coordBaseMode != null) {
                switch (StructureStrongholdPieces.SwitchEnumFacing.field_175951_b[this.coordBaseMode.ordinal()]) {
                    case 1: {
                        return func_175953_c(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX + p_74986_4_, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ - 1, this.coordBaseMode, this.getComponentType());
                    }
                    case 2: {
                        return func_175953_c(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX + p_74986_4_, this.boundingBox.minY + p_74986_5_, this.boundingBox.maxZ + 1, this.coordBaseMode, this.getComponentType());
                    }
                    case 3: {
                        return func_175953_c(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ + p_74986_4_, this.coordBaseMode, this.getComponentType());
                    }
                    case 4: {
                        return func_175953_c(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ + p_74986_4_, this.coordBaseMode, this.getComponentType());
                    }
                }
            }
            return null;
        }
        
        protected StructureComponent getNextComponentX(final Stairs2 p_74989_1_, final List p_74989_2_, final Random p_74989_3_, final int p_74989_4_, final int p_74989_5_) {
            if (this.coordBaseMode != null) {
                switch (StructureStrongholdPieces.SwitchEnumFacing.field_175951_b[this.coordBaseMode.ordinal()]) {
                    case 1: {
                        return func_175953_c(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ + p_74989_5_, EnumFacing.WEST, this.getComponentType());
                    }
                    case 2: {
                        return func_175953_c(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ + p_74989_5_, EnumFacing.WEST, this.getComponentType());
                    }
                    case 3: {
                        return func_175953_c(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX + p_74989_5_, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                    }
                    case 4: {
                        return func_175953_c(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX + p_74989_5_, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
                    }
                }
            }
            return null;
        }
        
        protected StructureComponent getNextComponentZ(final Stairs2 p_74987_1_, final List p_74987_2_, final Random p_74987_3_, final int p_74987_4_, final int p_74987_5_) {
            if (this.coordBaseMode != null) {
                switch (StructureStrongholdPieces.SwitchEnumFacing.field_175951_b[this.coordBaseMode.ordinal()]) {
                    case 1: {
                        return func_175953_c(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74987_4_, this.boundingBox.minZ + p_74987_5_, EnumFacing.EAST, this.getComponentType());
                    }
                    case 2: {
                        return func_175953_c(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74987_4_, this.boundingBox.minZ + p_74987_5_, EnumFacing.EAST, this.getComponentType());
                    }
                    case 3: {
                        return func_175953_c(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.minX + p_74987_5_, this.boundingBox.minY + p_74987_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                    }
                    case 4: {
                        return func_175953_c(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.minX + p_74987_5_, this.boundingBox.minY + p_74987_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
                    }
                }
            }
            return null;
        }
        
        protected static boolean canStrongholdGoDeeper(final StructureBoundingBox p_74991_0_) {
            return p_74991_0_ != null && p_74991_0_.minY > 10;
        }
        
        public enum Door
        {
            OPENING("OPENING", 0), 
            WOOD_DOOR("WOOD_DOOR", 1), 
            GRATES("GRATES", 2), 
            IRON_DOOR("IRON_DOOR", 3);
            
            private static final Door[] $VALUES;
            private static final String __OBFID = "CL_00000504";
            
            private Door(final String p_i2086_1_, final int p_i2086_2_) {
            }
            
            static {
                $VALUES = new Door[] { Door.OPENING, Door.WOOD_DOOR, Door.GRATES, Door.IRON_DOOR };
            }
        }
    }
    
    static final class SwitchEnumFacing
    {
        static final int[] doorEnum;
        static final int[] field_175951_b;
        private static final String __OBFID = "CL_00001970";
        
        static {
            field_175951_b = new int[EnumFacing.values().length];
            try {
                SwitchEnumFacing.field_175951_b[EnumFacing.NORTH.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {}
            try {
                SwitchEnumFacing.field_175951_b[EnumFacing.SOUTH.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError2) {}
            try {
                SwitchEnumFacing.field_175951_b[EnumFacing.WEST.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError3) {}
            try {
                SwitchEnumFacing.field_175951_b[EnumFacing.EAST.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError4) {}
            doorEnum = new int[Stronghold.Door.values().length];
            try {
                SwitchEnumFacing.doorEnum[Stronghold.Door.OPENING.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError5) {}
            try {
                SwitchEnumFacing.doorEnum[Stronghold.Door.WOOD_DOOR.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError6) {}
            try {
                SwitchEnumFacing.doorEnum[Stronghold.Door.GRATES.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError7) {}
            try {
                SwitchEnumFacing.doorEnum[Stronghold.Door.IRON_DOOR.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError8) {}
        }
    }
}
