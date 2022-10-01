// 
// Decompiled by Procyon v0.5.30
// 

package net.minecraft.world.storage;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;
import net.minecraft.nbt.NBTBase;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import net.minecraft.world.WorldSettings;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldType;
import net.minecraft.world.EnumDifficulty;

public class WorldInfo
{
    public static final EnumDifficulty DEFAULT_DIFFICULTY;
    private long randomSeed;
    private WorldType terrainType;
    private String generatorOptions;
    private int spawnX;
    private int spawnY;
    private int spawnZ;
    private long totalTime;
    private long worldTime;
    private long lastTimePlayed;
    private long sizeOnDisk;
    private NBTTagCompound playerTag;
    private int dimension;
    private String levelName;
    private int saveVersion;
    private int cleanWeatherTime;
    private boolean raining;
    private int rainTime;
    private boolean thundering;
    private int thunderTime;
    private WorldSettings.GameType theGameType;
    private boolean mapFeaturesEnabled;
    private boolean hardcore;
    private boolean allowCommands;
    private boolean initialized;
    private EnumDifficulty difficulty;
    private boolean difficultyLocked;
    private double borderCenterX;
    private double borderCenterZ;
    private double borderSize;
    private long borderSizeLerpTime;
    private double borderSizeLerpTarget;
    private double borderSafeZone;
    private double borderDamagePerBlock;
    private int borderWarningDistance;
    private int borderWarningTime;
    private GameRules theGameRules;
    private static final String __OBFID = "CL_00000587";
    
    protected WorldInfo() {
        this.terrainType = WorldType.DEFAULT;
        this.generatorOptions = "";
        this.borderCenterX = 0.0;
        this.borderCenterZ = 0.0;
        this.borderSize = 6.0E7;
        this.borderSizeLerpTime = 0L;
        this.borderSizeLerpTarget = 0.0;
        this.borderSafeZone = 5.0;
        this.borderDamagePerBlock = 0.2;
        this.borderWarningDistance = 5;
        this.borderWarningTime = 15;
        this.theGameRules = new GameRules();
    }
    
    public WorldInfo(final NBTTagCompound nbt) {
        this.terrainType = WorldType.DEFAULT;
        this.generatorOptions = "";
        this.borderCenterX = 0.0;
        this.borderCenterZ = 0.0;
        this.borderSize = 6.0E7;
        this.borderSizeLerpTime = 0L;
        this.borderSizeLerpTarget = 0.0;
        this.borderSafeZone = 5.0;
        this.borderDamagePerBlock = 0.2;
        this.borderWarningDistance = 5;
        this.borderWarningTime = 15;
        this.theGameRules = new GameRules();
        this.randomSeed = nbt.getLong("RandomSeed");
        if (nbt.hasKey("generatorName", 8)) {
            final String var2 = nbt.getString("generatorName");
            this.terrainType = WorldType.parseWorldType(var2);
            if (this.terrainType == null) {
                this.terrainType = WorldType.DEFAULT;
            }
            else if (this.terrainType.isVersioned()) {
                int var3 = 0;
                if (nbt.hasKey("generatorVersion", 99)) {
                    var3 = nbt.getInteger("generatorVersion");
                }
                this.terrainType = this.terrainType.getWorldTypeForGeneratorVersion(var3);
            }
            if (nbt.hasKey("generatorOptions", 8)) {
                this.generatorOptions = nbt.getString("generatorOptions");
            }
        }
        this.theGameType = WorldSettings.GameType.getByID(nbt.getInteger("GameType"));
        if (nbt.hasKey("MapFeatures", 99)) {
            this.mapFeaturesEnabled = nbt.getBoolean("MapFeatures");
        }
        else {
            this.mapFeaturesEnabled = true;
        }
        this.spawnX = nbt.getInteger("SpawnX");
        this.spawnY = nbt.getInteger("SpawnY");
        this.spawnZ = nbt.getInteger("SpawnZ");
        this.totalTime = nbt.getLong("Time");
        if (nbt.hasKey("DayTime", 99)) {
            this.worldTime = nbt.getLong("DayTime");
        }
        else {
            this.worldTime = this.totalTime;
        }
        this.lastTimePlayed = nbt.getLong("LastPlayed");
        this.sizeOnDisk = nbt.getLong("SizeOnDisk");
        this.levelName = nbt.getString("LevelName");
        this.saveVersion = nbt.getInteger("version");
        this.cleanWeatherTime = nbt.getInteger("clearWeatherTime");
        this.rainTime = nbt.getInteger("rainTime");
        this.raining = nbt.getBoolean("raining");
        this.thunderTime = nbt.getInteger("thunderTime");
        this.thundering = nbt.getBoolean("thundering");
        this.hardcore = nbt.getBoolean("hardcore");
        if (nbt.hasKey("initialized", 99)) {
            this.initialized = nbt.getBoolean("initialized");
        }
        else {
            this.initialized = true;
        }
        if (nbt.hasKey("allowCommands", 99)) {
            this.allowCommands = nbt.getBoolean("allowCommands");
        }
        else {
            this.allowCommands = (this.theGameType == WorldSettings.GameType.CREATIVE);
        }
        if (nbt.hasKey("Player", 10)) {
            this.playerTag = nbt.getCompoundTag("Player");
            this.dimension = this.playerTag.getInteger("Dimension");
        }
        if (nbt.hasKey("GameRules", 10)) {
            this.theGameRules.readGameRulesFromNBT(nbt.getCompoundTag("GameRules"));
        }
        if (nbt.hasKey("Difficulty", 99)) {
            this.difficulty = EnumDifficulty.getDifficultyEnum(nbt.getByte("Difficulty"));
        }
        if (nbt.hasKey("DifficultyLocked", 1)) {
            this.difficultyLocked = nbt.getBoolean("DifficultyLocked");
        }
        if (nbt.hasKey("BorderCenterX", 99)) {
            this.borderCenterX = nbt.getDouble("BorderCenterX");
        }
        if (nbt.hasKey("BorderCenterZ", 99)) {
            this.borderCenterZ = nbt.getDouble("BorderCenterZ");
        }
        if (nbt.hasKey("BorderSize", 99)) {
            this.borderSize = nbt.getDouble("BorderSize");
        }
        if (nbt.hasKey("BorderSizeLerpTime", 99)) {
            this.borderSizeLerpTime = nbt.getLong("BorderSizeLerpTime");
        }
        if (nbt.hasKey("BorderSizeLerpTarget", 99)) {
            this.borderSizeLerpTarget = nbt.getDouble("BorderSizeLerpTarget");
        }
        if (nbt.hasKey("BorderSafeZone", 99)) {
            this.borderSafeZone = nbt.getDouble("BorderSafeZone");
        }
        if (nbt.hasKey("BorderDamagePerBlock", 99)) {
            this.borderDamagePerBlock = nbt.getDouble("BorderDamagePerBlock");
        }
        if (nbt.hasKey("BorderWarningBlocks", 99)) {
            this.borderWarningDistance = nbt.getInteger("BorderWarningBlocks");
        }
        if (nbt.hasKey("BorderWarningTime", 99)) {
            this.borderWarningTime = nbt.getInteger("BorderWarningTime");
        }
    }
    
    public WorldInfo(final WorldSettings settings, final String name) {
        this.terrainType = WorldType.DEFAULT;
        this.generatorOptions = "";
        this.borderCenterX = 0.0;
        this.borderCenterZ = 0.0;
        this.borderSize = 6.0E7;
        this.borderSizeLerpTime = 0L;
        this.borderSizeLerpTarget = 0.0;
        this.borderSafeZone = 5.0;
        this.borderDamagePerBlock = 0.2;
        this.borderWarningDistance = 5;
        this.borderWarningTime = 15;
        this.theGameRules = new GameRules();
        this.populateFromWorldSettings(settings);
        this.levelName = name;
        this.difficulty = WorldInfo.DEFAULT_DIFFICULTY;
        this.initialized = false;
    }
    
    public void populateFromWorldSettings(final WorldSettings settings) {
        this.randomSeed = settings.getSeed();
        this.theGameType = settings.getGameType();
        this.mapFeaturesEnabled = settings.isMapFeaturesEnabled();
        this.hardcore = settings.getHardcoreEnabled();
        this.terrainType = settings.getTerrainType();
        this.generatorOptions = settings.getWorldName();
        this.allowCommands = settings.areCommandsAllowed();
    }
    
    public WorldInfo(final WorldInfo p_i2159_1_) {
        this.terrainType = WorldType.DEFAULT;
        this.generatorOptions = "";
        this.borderCenterX = 0.0;
        this.borderCenterZ = 0.0;
        this.borderSize = 6.0E7;
        this.borderSizeLerpTime = 0L;
        this.borderSizeLerpTarget = 0.0;
        this.borderSafeZone = 5.0;
        this.borderDamagePerBlock = 0.2;
        this.borderWarningDistance = 5;
        this.borderWarningTime = 15;
        this.theGameRules = new GameRules();
        this.randomSeed = p_i2159_1_.randomSeed;
        this.terrainType = p_i2159_1_.terrainType;
        this.generatorOptions = p_i2159_1_.generatorOptions;
        this.theGameType = p_i2159_1_.theGameType;
        this.mapFeaturesEnabled = p_i2159_1_.mapFeaturesEnabled;
        this.spawnX = p_i2159_1_.spawnX;
        this.spawnY = p_i2159_1_.spawnY;
        this.spawnZ = p_i2159_1_.spawnZ;
        this.totalTime = p_i2159_1_.totalTime;
        this.worldTime = p_i2159_1_.worldTime;
        this.lastTimePlayed = p_i2159_1_.lastTimePlayed;
        this.sizeOnDisk = p_i2159_1_.sizeOnDisk;
        this.playerTag = p_i2159_1_.playerTag;
        this.dimension = p_i2159_1_.dimension;
        this.levelName = p_i2159_1_.levelName;
        this.saveVersion = p_i2159_1_.saveVersion;
        this.rainTime = p_i2159_1_.rainTime;
        this.raining = p_i2159_1_.raining;
        this.thunderTime = p_i2159_1_.thunderTime;
        this.thundering = p_i2159_1_.thundering;
        this.hardcore = p_i2159_1_.hardcore;
        this.allowCommands = p_i2159_1_.allowCommands;
        this.initialized = p_i2159_1_.initialized;
        this.theGameRules = p_i2159_1_.theGameRules;
        this.difficulty = p_i2159_1_.difficulty;
        this.difficultyLocked = p_i2159_1_.difficultyLocked;
        this.borderCenterX = p_i2159_1_.borderCenterX;
        this.borderCenterZ = p_i2159_1_.borderCenterZ;
        this.borderSize = p_i2159_1_.borderSize;
        this.borderSizeLerpTime = p_i2159_1_.borderSizeLerpTime;
        this.borderSizeLerpTarget = p_i2159_1_.borderSizeLerpTarget;
        this.borderSafeZone = p_i2159_1_.borderSafeZone;
        this.borderDamagePerBlock = p_i2159_1_.borderDamagePerBlock;
        this.borderWarningTime = p_i2159_1_.borderWarningTime;
        this.borderWarningDistance = p_i2159_1_.borderWarningDistance;
    }
    
    public NBTTagCompound getNBTTagCompound() {
        final NBTTagCompound var1 = new NBTTagCompound();
        this.updateTagCompound(var1, this.playerTag);
        return var1;
    }
    
    public NBTTagCompound cloneNBTCompound(final NBTTagCompound nbt) {
        final NBTTagCompound var2 = new NBTTagCompound();
        this.updateTagCompound(var2, nbt);
        return var2;
    }
    
    private void updateTagCompound(final NBTTagCompound nbt, final NBTTagCompound playerNbt) {
        nbt.setLong("RandomSeed", this.randomSeed);
        nbt.setString("generatorName", this.terrainType.getWorldTypeName());
        nbt.setInteger("generatorVersion", this.terrainType.getGeneratorVersion());
        nbt.setString("generatorOptions", this.generatorOptions);
        nbt.setInteger("GameType", this.theGameType.getID());
        nbt.setBoolean("MapFeatures", this.mapFeaturesEnabled);
        nbt.setInteger("SpawnX", this.spawnX);
        nbt.setInteger("SpawnY", this.spawnY);
        nbt.setInteger("SpawnZ", this.spawnZ);
        nbt.setLong("Time", this.totalTime);
        nbt.setLong("DayTime", this.worldTime);
        nbt.setLong("SizeOnDisk", this.sizeOnDisk);
        nbt.setLong("LastPlayed", MinecraftServer.getCurrentTimeMillis());
        nbt.setString("LevelName", this.levelName);
        nbt.setInteger("version", this.saveVersion);
        nbt.setInteger("clearWeatherTime", this.cleanWeatherTime);
        nbt.setInteger("rainTime", this.rainTime);
        nbt.setBoolean("raining", this.raining);
        nbt.setInteger("thunderTime", this.thunderTime);
        nbt.setBoolean("thundering", this.thundering);
        nbt.setBoolean("hardcore", this.hardcore);
        nbt.setBoolean("allowCommands", this.allowCommands);
        nbt.setBoolean("initialized", this.initialized);
        nbt.setDouble("BorderCenterX", this.borderCenterX);
        nbt.setDouble("BorderCenterZ", this.borderCenterZ);
        nbt.setDouble("BorderSize", this.borderSize);
        nbt.setLong("BorderSizeLerpTime", this.borderSizeLerpTime);
        nbt.setDouble("BorderSafeZone", this.borderSafeZone);
        nbt.setDouble("BorderDamagePerBlock", this.borderDamagePerBlock);
        nbt.setDouble("BorderSizeLerpTarget", this.borderSizeLerpTarget);
        nbt.setDouble("BorderWarningBlocks", this.borderWarningDistance);
        nbt.setDouble("BorderWarningTime", this.borderWarningTime);
        if (this.difficulty != null) {
            nbt.setByte("Difficulty", (byte)this.difficulty.getDifficultyId());
        }
        nbt.setBoolean("DifficultyLocked", this.difficultyLocked);
        nbt.setTag("GameRules", this.theGameRules.writeGameRulesToNBT());
        if (playerNbt != null) {
            nbt.setTag("Player", playerNbt);
        }
    }
    
    public long getSeed() {
        return this.randomSeed;
    }
    
    public int getSpawnX() {
        return this.spawnX;
    }
    
    public int getSpawnY() {
        return this.spawnY;
    }
    
    public int getSpawnZ() {
        return this.spawnZ;
    }
    
    public long getWorldTotalTime() {
        return this.totalTime;
    }
    
    public long getWorldTime() {
        return this.worldTime;
    }
    
    public long getSizeOnDisk() {
        return this.sizeOnDisk;
    }
    
    public NBTTagCompound getPlayerNBTTagCompound() {
        return this.playerTag;
    }
    
    public void setSpawnX(final int p_76058_1_) {
        this.spawnX = p_76058_1_;
    }
    
    public void setSpawnY(final int p_76056_1_) {
        this.spawnY = p_76056_1_;
    }
    
    public void setSpawnZ(final int p_76087_1_) {
        this.spawnZ = p_76087_1_;
    }
    
    public void incrementTotalWorldTime(final long p_82572_1_) {
        this.totalTime = p_82572_1_;
    }
    
    public void setWorldTime(final long p_76068_1_) {
        this.worldTime = p_76068_1_;
    }
    
    public void setSpawn(final BlockPos spawnPoint) {
        this.spawnX = spawnPoint.getX();
        this.spawnY = spawnPoint.getY();
        this.spawnZ = spawnPoint.getZ();
    }
    
    public String getWorldName() {
        return this.levelName;
    }
    
    public void setWorldName(final String p_76062_1_) {
        this.levelName = p_76062_1_;
    }
    
    public int getSaveVersion() {
        return this.saveVersion;
    }
    
    public void setSaveVersion(final int p_76078_1_) {
        this.saveVersion = p_76078_1_;
    }
    
    public long getLastTimePlayed() {
        return this.lastTimePlayed;
    }
    
    public int func_176133_A() {
        return this.cleanWeatherTime;
    }
    
    public void func_176142_i(final int p_176142_1_) {
        this.cleanWeatherTime = p_176142_1_;
    }
    
    public boolean isThundering() {
        return this.thundering;
    }
    
    public void setThundering(final boolean p_76069_1_) {
        this.thundering = p_76069_1_;
    }
    
    public int getThunderTime() {
        return this.thunderTime;
    }
    
    public void setThunderTime(final int p_76090_1_) {
        this.thunderTime = p_76090_1_;
    }
    
    public boolean isRaining() {
        return this.raining;
    }
    
    public void setRaining(final boolean p_76084_1_) {
        this.raining = p_76084_1_;
    }
    
    public int getRainTime() {
        return this.rainTime;
    }
    
    public void setRainTime(final int p_76080_1_) {
        this.rainTime = p_76080_1_;
    }
    
    public WorldSettings.GameType getGameType() {
        return this.theGameType;
    }
    
    public boolean isMapFeaturesEnabled() {
        return this.mapFeaturesEnabled;
    }
    
    public void setMapFeaturesEnabled(final boolean enabled) {
        this.mapFeaturesEnabled = enabled;
    }
    
    public void setGameType(final WorldSettings.GameType type) {
        this.theGameType = type;
    }
    
    public boolean isHardcoreModeEnabled() {
        return this.hardcore;
    }
    
    public void setHardcore(final boolean hardcoreIn) {
        this.hardcore = hardcoreIn;
    }
    
    public WorldType getTerrainType() {
        return this.terrainType;
    }
    
    public void setTerrainType(final WorldType p_76085_1_) {
        this.terrainType = p_76085_1_;
    }
    
    public String getGeneratorOptions() {
        return this.generatorOptions;
    }
    
    public boolean areCommandsAllowed() {
        return this.allowCommands;
    }
    
    public void setAllowCommands(final boolean allow) {
        this.allowCommands = allow;
    }
    
    public boolean isInitialized() {
        return this.initialized;
    }
    
    public void setServerInitialized(final boolean initializedIn) {
        this.initialized = initializedIn;
    }
    
    public GameRules getGameRulesInstance() {
        return this.theGameRules;
    }
    
    public double func_176120_C() {
        return this.borderCenterX;
    }
    
    public double func_176126_D() {
        return this.borderCenterZ;
    }
    
    public double func_176137_E() {
        return this.borderSize;
    }
    
    public void func_176145_a(final double p_176145_1_) {
        this.borderSize = p_176145_1_;
    }
    
    public long func_176134_F() {
        return this.borderSizeLerpTime;
    }
    
    public void func_176135_e(final long p_176135_1_) {
        this.borderSizeLerpTime = p_176135_1_;
    }
    
    public double func_176132_G() {
        return this.borderSizeLerpTarget;
    }
    
    public void func_176118_b(final double p_176118_1_) {
        this.borderSizeLerpTarget = p_176118_1_;
    }
    
    public void func_176141_c(final double p_176141_1_) {
        this.borderCenterZ = p_176141_1_;
    }
    
    public void func_176124_d(final double p_176124_1_) {
        this.borderCenterX = p_176124_1_;
    }
    
    public double func_176138_H() {
        return this.borderSafeZone;
    }
    
    public void func_176129_e(final double p_176129_1_) {
        this.borderSafeZone = p_176129_1_;
    }
    
    public double func_176140_I() {
        return this.borderDamagePerBlock;
    }
    
    public void func_176125_f(final double p_176125_1_) {
        this.borderDamagePerBlock = p_176125_1_;
    }
    
    public int func_176131_J() {
        return this.borderWarningDistance;
    }
    
    public int func_176139_K() {
        return this.borderWarningTime;
    }
    
    public void func_176122_j(final int p_176122_1_) {
        this.borderWarningDistance = p_176122_1_;
    }
    
    public void func_176136_k(final int p_176136_1_) {
        this.borderWarningTime = p_176136_1_;
    }
    
    public EnumDifficulty getDifficulty() {
        return this.difficulty;
    }
    
    public void setDifficulty(final EnumDifficulty newDifficulty) {
        this.difficulty = newDifficulty;
    }
    
    public boolean isDifficultyLocked() {
        return this.difficultyLocked;
    }
    
    public void setDifficultyLocked(final boolean locked) {
        this.difficultyLocked = locked;
    }
    
    public void addToCrashReport(final CrashReportCategory category) {
        category.addCrashSectionCallable("Level seed", new Callable() {
            private static final String __OBFID = "CL_00000588";
            
            @Override
            public String call() {
                return String.valueOf(WorldInfo.this.getSeed());
            }
        });
        category.addCrashSectionCallable("Level generator", new Callable() {
            private static final String __OBFID = "CL_00000589";
            
            @Override
            public String call() {
                return String.format("ID %02d - %s, ver %d. Features enabled: %b", WorldInfo.this.terrainType.getWorldTypeID(), WorldInfo.this.terrainType.getWorldTypeName(), WorldInfo.this.terrainType.getGeneratorVersion(), WorldInfo.this.mapFeaturesEnabled);
            }
        });
        category.addCrashSectionCallable("Level generator options", new Callable() {
            private static final String __OBFID = "CL_00000590";
            
            @Override
            public String call() {
                return WorldInfo.this.generatorOptions;
            }
        });
        category.addCrashSectionCallable("Level spawn location", new Callable() {
            private static final String __OBFID = "CL_00000591";
            
            @Override
            public String call() {
                return CrashReportCategory.getCoordinateInfo(WorldInfo.this.spawnX, WorldInfo.this.spawnY, WorldInfo.this.spawnZ);
            }
        });
        category.addCrashSectionCallable("Level time", new Callable() {
            private static final String __OBFID = "CL_00000592";
            
            @Override
            public String call() {
                return String.format("%d game time, %d day time", WorldInfo.this.totalTime, WorldInfo.this.worldTime);
            }
        });
        category.addCrashSectionCallable("Level dimension", new Callable() {
            private static final String __OBFID = "CL_00000593";
            
            @Override
            public String call() {
                return String.valueOf(WorldInfo.this.dimension);
            }
        });
        category.addCrashSectionCallable("Level storage version", new Callable() {
            private static final String __OBFID = "CL_00000594";
            
            @Override
            public String call() {
                String var1 = "Unknown?";
                try {
                    switch (WorldInfo.this.saveVersion) {
                        case 19132: {
                            var1 = "McRegion";
                            break;
                        }
                        case 19133: {
                            var1 = "Anvil";
                            break;
                        }
                    }
                }
                catch (Throwable t) {}
                return String.format("0x%05X - %s", WorldInfo.this.saveVersion, var1);
            }
        });
        category.addCrashSectionCallable("Level weather", new Callable() {
            private static final String __OBFID = "CL_00000595";
            
            @Override
            public String call() {
                return String.format("Rain time: %d (now: %b), thunder time: %d (now: %b)", WorldInfo.this.rainTime, WorldInfo.this.raining, WorldInfo.this.thunderTime, WorldInfo.this.thundering);
            }
        });
        category.addCrashSectionCallable("Level game mode", new Callable() {
            private static final String __OBFID = "CL_00000597";
            
            @Override
            public String call() {
                return String.format("Game mode: %s (ID %d). Hardcore: %b. Cheats: %b", WorldInfo.this.theGameType.getName(), WorldInfo.this.theGameType.getID(), WorldInfo.this.hardcore, WorldInfo.this.allowCommands);
            }
        });
    }
    
    static {
        DEFAULT_DIFFICULTY = EnumDifficulty.NORMAL;
    }
}
