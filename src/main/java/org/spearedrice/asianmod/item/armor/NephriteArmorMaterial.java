package org.spearedrice.asianmod.item.armor;

import java.util.Map;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import org.spearedrice.asianmod.AsianMod;

public class NephriteArmorMaterial {

    public static final int BASE_DURABILITY = 20;

    public static final ResourceKey<EquipmentAsset> NEPHRITE_ARMOR_MATERIAL_KEY =
            ResourceKey.create(
                    EquipmentAssets.ROOT_ID,
                    Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "nephrite")
            );

    public static final TagKey<Item> REPAIRS_NEPHRITE_ARMOR =
            TagKey.create(
                    BuiltInRegistries.ITEM.key(),
                    Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "repairs_nephrite_armor")
            );

    public static final ArmorMaterial INSTANCE = new ArmorMaterial(
            BASE_DURABILITY,
            Map.of(
                    ArmorType.HELMET, 3,
                    ArmorType.CHESTPLATE, 9,
                    ArmorType.LEGGINGS, 7,
                    ArmorType.BOOTS, 4
            ),
            12,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            2.0F,
            0.1F,
            REPAIRS_NEPHRITE_ARMOR,
            NEPHRITE_ARMOR_MATERIAL_KEY
    );
}