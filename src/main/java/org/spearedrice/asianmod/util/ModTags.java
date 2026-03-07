package org.spearedrice.asianmod.util;

import org.spearedrice.asianmod.AsianMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {

    public static class Blocks {
        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(AsianMod.MOD_ID, name));
        }

        // add soon (im kidding this doesn't do jack because i can't figure out how to get it to work)
        // public static final TagKey<Block> TRANSFORMABLE_BLOCKS = createTag("transformable_blocks");
    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(AsianMod.MOD_ID, name));
        }

        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
    }
}