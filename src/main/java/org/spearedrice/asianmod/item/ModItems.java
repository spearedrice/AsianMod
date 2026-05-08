package org.spearedrice.asianmod.item;

import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.equipment.ArmorType;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;

import org.spearedrice.asianmod.AsianMod;
import org.spearedrice.asianmod.component.ModComponents;
import org.spearedrice.asianmod.entity.ModEntityTypes;
import org.spearedrice.asianmod.fluid.ModFluids;
import org.spearedrice.asianmod.item.armor.NephriteArmorMaterial;
import org.spearedrice.asianmod.item.custom.AbacusItem;
import org.spearedrice.asianmod.item.custom.IncenseStick;
import org.spearedrice.asianmod.item.custom.SlipperItem;

public class ModItems {

    public static final ToolMaterial NEPHRITE_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL,
            455,
            5.0F,
            1.5F,
            22,
            NephriteArmorMaterial.REPAIRS_NEPHRITE_ARMOR
    );

    public static final Item NEPHRITE_HELMET = register("nephrite_helmet",
            Item::new,
            new Item.Properties().humanoidArmor(NephriteArmorMaterial.INSTANCE, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(NephriteArmorMaterial.BASE_DURABILITY)));

    public static final Item NEPHRITE_CHESTPLATE = register("nephrite_chestplate",
            Item::new,
            new Item.Properties().humanoidArmor(NephriteArmorMaterial.INSTANCE, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(NephriteArmorMaterial.BASE_DURABILITY)));

    public static final Item NEPHRITE_LEGGINGS = register("nephrite_leggings",
            Item::new,
            new Item.Properties().humanoidArmor(NephriteArmorMaterial.INSTANCE, ArmorType.LEGGINGS)
                    .durability(ArmorType.LEGGINGS.getDurability(NephriteArmorMaterial.BASE_DURABILITY)));

    public static final Item NEPHRITE_BOOTS = register("nephrite_boots",
            Item::new,
            new Item.Properties().humanoidArmor(NephriteArmorMaterial.INSTANCE, ArmorType.BOOTS)
                    .durability(ArmorType.BOOTS.getDurability(NephriteArmorMaterial.BASE_DURABILITY)));

    public static final Item NEPHRITE_SWORD = register(
            "nephrite_sword",
            Item::new,
            new Item.Properties().sword(NEPHRITE_TOOL_MATERIAL, 1f, 1f));

    public static final Item NEPHRITE_AXE = register(
            "nephrite_axe",
            settings -> new AxeItem(NEPHRITE_TOOL_MATERIAL, 5.0F, -3.0F, settings),
            new Item.Properties());

    public static final Item NEPHRITE_HOE = register(
            "nephrite_hoe",
            settings -> new HoeItem(NEPHRITE_TOOL_MATERIAL, -4.0F, 0.0F, settings),
            new Item.Properties());

    public static final Item ABACUS = register(
            "abacus",
            AbacusItem::new,
            new Item.Properties().component(ModComponents.CLICK_COUNT_COMPONENT, 0));

    public static final Item INCENSE_STICK = register(
            "incense_stick",
            IncenseStick::new,
            new Item.Properties());

    public static final Item FERMENTED_RESIDUE = register(
            "fermented_residue",
            Item::new,
            new Item.Properties());

    public static final Item SLIPPER = Registry.register(
            BuiltInRegistries.ITEM,
            Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "slipper"),
            new SlipperItem(new Item.Properties().stacksTo(1))
    );

    public static final Item COW_DUNG = register("cow_dung", Item::new, new Item.Properties());

    public static final Item PORCELAIN_SHARD = Registry.register(
            BuiltInRegistries.ITEM,
            Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "porcelain_shard"),
            new Item(new Item.Properties())
    );

    public static final Item PORCELAIN = Registry.register(
            BuiltInRegistries.ITEM,
            Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "porcelain"),
            new Item(new Item.Properties())
    );

    public static final Item WIND_CHIME = register("wind_chime", Item::new, new Item.Properties());

    public static final Item SILK_GLOVES = register("silk_gloves", Item::new, new Item.Properties());

    public static final Item PAPER_LANTERN = register("paper_lantern", Item::new, new Item.Properties());

    public static final Item ASIAN_DAD_SLIPPERS = register("asian_dad_slippers", Item::new, new Item.Properties().stacksTo(1));

    public static final Item THROWING_KNIVES = ASIAN_DAD_SLIPPERS;

    public static final Item ASIAN_DAD_SPAWN_EGG = register(
            "asian_dad_spawn_egg",
            SpawnEggItem::new,
            new Item.Properties().spawnEgg(ModEntityTypes.ASIAN_DAD));

    public static final FoodProperties FUGU_FOOD = new FoodProperties.Builder()
            .alwaysEdible()
            .build();

    public static final Consumable FUGU_CONSUMABLE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    new MobEffectInstance(MobEffects.POISON, 6 * 20, 1), 1.0f))
            .build();

    public static final Item FUGU = register(
            "fugu",
            Item::new,
            new Item.Properties().food(FUGU_FOOD, FUGU_CONSUMABLE));

    public static <T extends Item> T register(String name, Function<Item.Properties, T> factory, Item.Properties props) {
        ResourceKey<Item> key = ResourceKey.create(
                Registries.ITEM,
                Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, name)
        );

        T item = factory.apply(props.setId(key));
        Registry.register(BuiltInRegistries.ITEM, key, item);
        return item;
    }

    public static void initialize() {

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS)
                .register(e -> {
                    e.accept(COW_DUNG);
                    e.accept(FERMENTED_RESIDUE);
                });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register(e -> {
                    e.accept(NEPHRITE_HELMET);
                    e.accept(NEPHRITE_CHESTPLATE);
                    e.accept(NEPHRITE_LEGGINGS);
                    e.accept(NEPHRITE_BOOTS);
                    e.accept(NEPHRITE_SWORD);
                    e.accept(NEPHRITE_AXE);
                    e.accept(NEPHRITE_HOE);
                });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT)
                .register(e -> e.accept(ASIAN_DAD_SLIPPERS));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS)
                .register(e -> {
                    e.accept(PAPER_LANTERN);
                    e.accept(SILK_GLOVES);
                });

        CompostingChanceRegistry.INSTANCE.add(COW_DUNG, 0.3f);
        CompostingChanceRegistry.INSTANCE.add(FERMENTED_RESIDUE, 0.3f);

        FuelRegistryEvents.BUILD.register((builder, ctx) -> {
            builder.add(COW_DUNG, 30 * 20);
            builder.add(FERMENTED_RESIDUE, 30 * 20);
        });

        public static final Item ACID_BUCKET = register(
                "mercury_bucket",
                props -> new BucketItem(ModFluids.MERCURY_STILL, props),
                new Item.Properties()
                        .craftRemainder(Items.BUCKET)
                        .stacksTo(1)
        );
    }
}