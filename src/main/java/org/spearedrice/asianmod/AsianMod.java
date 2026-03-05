package org.spearedrice.asianmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
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
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		FuelRegistryEvents.BUILD.register((builder, context) -> {
			builder.add(ModItems.COW_DUNG, 600);
		});

		ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, tooltipLines) -> {
			if (itemStack.isOf(ModItems.RAW_NEPHRITE_JADE)) {
				tooltipLines.add(Text.translatable("tooltip.asianmod.raw_nephrite_jade").formatted(Formatting.GRAY));
			}
			if (itemStack.isOf(ModBlocks.RAW_NEPHRITE_JADE_BLOCK.asItem())) {
				tooltipLines.add(Text.translatable("tooltip.asianmod.raw_nephrite_jade_block").formatted(Formatting.GRAY));
			}
			if (itemStack.isOf(ModBlocks.ASIAN_BLOCK.asItem())) {
				tooltipLines.add(Text.translatable("tooltip.asianmod.asian_block").formatted(Formatting.GRAY));
			}
			if (itemStack.isOf(ModItems.BOK_CHOY)) {
				tooltipLines.add(Text.translatable("tooltip.asianmod.bok_choy").formatted(Formatting.GRAY));
			}
			if (itemStack.isOf(ModItems.CHISEL)) {
				if (MinecraftClient.getInstance().options.sneakKey.isPressed()) {
					tooltipLines.add(Text.translatable("tooltip.asianmod.chisel.shift").formatted(Formatting.YELLOW));
				} else {
					tooltipLines.add(Text.translatable("tooltip.asianmod.chisel.normal").formatted(Formatting.GRAY));
				}
			}
			if (itemStack.isOf(ModItems.COW_DUNG)) {
				tooltipLines.add(Text.translatable("tooltip.asianmod.cow_dung").formatted(Formatting.GRAY));
			}
			if (itemStack.isOf(ModItems.NEPHRITE_JADE)) {
				tooltipLines.add(Text.translatable("tooltip.asianmod.nephrite_jade").formatted(Formatting.GRAY));
			}
			if (itemStack.isOf(ModBlocks.NEPHRITE_JADE_BLOCK.asItem())) {
				tooltipLines.add(Text.translatable("tooltip.asianmod.nephrite_jade_block").formatted(Formatting.GRAY));
			}
		});
	}
}