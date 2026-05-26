package org.spearedrice.asianmod.saveddata;

import com.mojang.serialization.Codec;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

public class SavedBlockData extends SavedData {

	private int blocksBroken = 0;

	private static final Codec<SavedBlockData> CODEC = Codec.INT.xmap(
			SavedBlockData::new, // Create a new 'SavedBlockData' from the stored number.
			SavedBlockData::getBlocksBroken // Return the number from the 'SavedBlockData' to be saved
	);

	private static final SavedDataType<SavedBlockData> TYPE = new SavedDataType<>(
			"saved_block_data", // The unique name for this saved data.
			SavedBlockData::new, // If there's no 'SavedBlockData', yet create one and refresh fields.
			CODEC, // The codec used for serialization/deserialization.
			null // A data fixer, which is not needed here.
	);

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

	public static SavedBlockData getSavedBlockData(MinecraftServer server) {
		ServerLevel level = server.getLevel(ServerLevel.OVERWORLD);

		if (level == null) {
			return new SavedBlockData(); // Return a new instance if the level is null.
		}

		return level.getDataStorage().computeIfAbsent(TYPE);
	}
}