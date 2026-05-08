package org.spearedrice.asianmod.saveddata;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class SavedBlockData extends SavedData {

    private int blocksBroken = 0;

    public SavedBlockData() {}

    public SavedBlockData(int count) {
        this.blocksBroken = count;
    }

    public int getBlocksBroken() {
        return blocksBroken;
    }

    public void incrementBlocksBroken() {
        blocksBroken++;
        setDirty();
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putInt("blocksBroken", blocksBroken);
        return tag;
    }

    public static SavedBlockData load(CompoundTag tag) {
        SavedBlockData data = new SavedBlockData();
        data.blocksBroken = tag.getInt("blocksBroken");
        return data;
    }

    public static SavedBlockData getSavedBlockData(MinecraftServer server) {
        ServerLevel level = server.overworld();

        return level.getDataStorage().computeIfAbsent(
                SavedBlockData::load,
                SavedBlockData::new,
                "asianmod_saved_block_data"
        );
    }
}