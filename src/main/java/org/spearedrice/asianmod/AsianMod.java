package org.spearedrice.asianmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.item.FuelRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spearedrice.asianmod.block.ModBlocks;
import org.spearedrice.asianmod.item.ModItemGroups;
import org.spearedrice.asianmod.item.ModItems;

public class AsianMod implements ModInitializer {
	public static final String MOD_ID = "asianmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing " + MOD_ID);
		// item
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		FuelRegistry>INSTANCE

	}
}