package org.spearedrice.asianmod.entity;

import net.fabricmc.api.ModInitializer;

public class AsianModEntity implements ModInitializer {
	@Override
	public void onInitialize() {
		ModEntityTypes.registerModEntityTypes();

		ModEntityTypes.registerAttributes();
	}
}