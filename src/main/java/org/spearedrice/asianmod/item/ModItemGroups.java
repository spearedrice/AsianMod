package org.spearedrice.asianmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spearedrice.asianmod.AsianMod;
import org.spearedrice.asianmod.block.ModBlocks;

public class ModItemGroups {

    public static final ItemGroup NEPHRITE_JADE_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(AsianMod.MOD_ID, "nephrite_jade_items"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.NEPHRITE_JADE))
                    .displayName(Text.translatable("itemgroup.asianmod.nephrite_jade_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.NEPHRITE_JADE);
                        entries.add(ModItems.RAW_NEPHRITE_JADE);
                    }).build()
    );

    public static final ItemGroup NEPHRITE_JADE_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(AsianMod.MOD_ID, "nephrite_jade_blocks"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModBlocks.NEPHRITE_JADE_BLOCK.asItem()))
                    .displayName(Text.translatable("itemgroup.asianmod.nephrite_jade_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.NEPHRITE_JADE_BLOCK.asItem());
                        entries.add(ModBlocks.RAW_NEPHRITE_JADE_BLOCK.asItem());
                    }).build()
    );

    public static void registerItemGroups() {
        AsianMod.LOGGER.info("Registering Item Groups for " + AsianMod.MOD_ID);
    }
}