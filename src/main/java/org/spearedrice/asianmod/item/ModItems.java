package org.spearedrice.asianmod.item;

import java.util.List;
import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.Level;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import org.spearedrice.asianmod.AsianMod;
import org.spearedrice.asianmod.component.ModComponents;
import org.spearedrice.asianmod.fluid.ModFluids;
import org.spearedrice.asianmod.item.armor.NephriteArmorMaterial;

public class ModItems {
	public static final ToolMaterial NEPHRITE_TOOL_MATERIAL = new ToolMaterial(
			BlockTags.INCORRECT_FOR_WOODEN_TOOL,
			455,
			5.0F,
			1.5F,
			22,
			NephriteArmorMaterial.REPAIRS_NEPHRITE_ARMOR
	);

	public static final Item NEPHRITE_HELMET = register(
			"nephrite_helmet",
			Item::new,
			new Item.Properties().humanoidArmor(NephriteArmorMaterial.INSTANCE, ArmorType.HELMET)
					.durability(ArmorType.HELMET.getDurability(NephriteArmorMaterial.BASE_DURABILITY))
	);
	public static final Item NEPHRITE_CHESTPLATE = register("nephrite_chestplate",
			Item::new,
			new Item.Properties().humanoidArmor(NephriteArmorMaterial.INSTANCE, ArmorType.CHESTPLATE)
					.durability(ArmorType.CHESTPLATE.getDurability(NephriteArmorMaterial.BASE_DURABILITY))
	);

	public static final Item NEPHRITE_LEGGINGS = register(
			"nephrite_leggings",
			Item::new,
			new Item.Properties().humanoidArmor(NephriteArmorMaterial.INSTANCE, ArmorType.LEGGINGS)
					.durability(ArmorType.LEGGINGS.getDurability(NephriteArmorMaterial.BASE_DURABILITY))
	);

	public static final Item NEPHRITE_BOOTS = register(
			"nephrite_boots",
			Item::new,
			new Item.Properties().humanoidArmor(NephriteArmorMaterial.INSTANCE, ArmorType.BOOTS)
					.durability(ArmorType.BOOTS.getDurability(NephriteArmorMaterial.BASE_DURABILITY))
	);
	public static final Item NEPHRITE_SWORD = register(
			"nephrite_sword",
			Item::new,
			new Item.Properties().sword(NEPHRITE_TOOL_MATERIAL, 1f, 1f)
	);
	public static final Item NEPHRITE_AXE = register("nephrite_axe", settings -> new AxeItem(NEPHRITE_TOOL_MATERIAL, 5.0F, -3.0F, settings), new Item.Properties());
	public static final Item NEPHRITE_HOE = register("nephrite_hoe", settings -> new HoeItem(NEPHRITE_TOOL_MATERIAL, -4.0F, 0.0F, settings), new Item.Properties());

	public static final Item ABACUS = register("abacus", Item::new, new Item.Properties());
	public static final Item NEPHRITE_JADE = register("nephrite_jade", Item::new, new Item.Properties());

	public static final Item RAW_NEPHRITE_JADE = register("raw_nephrite_jade", Item::new, new Item.Properties());
	public static final Item DRAGON_PEARL = register("dragon_pearl", Item::new, new Item.Properties());
	public static final Item INCENSE_STICK = register("incense_stick", Item::new, new Item.Properties());
	public static final Item FERMENTED_RESIDUE = register("fermented_residue", Item::new, new Item.Properties());
	public static final Item SLIPPER = register("slipper", SlipperItem::new, new Item.Properties());
	public static final Item COW_DUNG = register("cow_dung", Item::new, new Item.Properties());
	public static final Item PORCELAIN = register("porcelain", Item::new, new Item.Properties());
	public static final Item SILK_GLOVES = register("silk_gloves", Item::new, new Item.Properties());
	public static final Item SKY_LANTERN = register("sky_lantern", SkyLanternItem::new, new Item.Properties());

	public static final Item PAPER_LANTERN = register("paper_lantern", settings -> new org.spearedrice.asianmod.item.PaperLanternItem(settings), new Item.Properties());
	public static final Item FUGU = register("fugu", Item::new, new Item.Properties());
	public static final Item FLYING_NEEDLE = register("flying_needle", Item::new, new Item.Properties().stacksTo(3));

	public static final Item MERCURY_BUCKET = register(
			"mercury_bucket",
			props -> new BucketItem(ModFluids.MERCURY_STILL, props),
			new Item.Properties()
					.craftRemainder(net.minecraft.world.item.Items.BUCKET)
					.stacksTo(1)
	);
	public static final Item RICE_WINE_BUCKET = register(
			"rice_wine_bucket",
			props -> new BucketItem(ModFluids.RICE_WINE_STILL, props),
			new Item.Properties()
					.craftRemainder(net.minecraft.world.item.Items.BUCKET)
					.stacksTo(1)
	);
	public static final Item CUSTOM_SOUND_ITEM = register("custom_sound_item", Item::new, new Item.Properties());


	public static final ResourceKey<CreativeModeTab> CUSTOM_CREATIVE_TAB_KEY = ResourceKey.create(
			BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, "creative_tab")
	);
	public static final CreativeModeTab CUSTOM_CREATIVE_TAB = FabricItemGroup.builder()
			.icon(() -> new ItemStack(ModItems.NEPHRITE_SWORD))
			.title(Component.translatable("itemGroup.asianmod"))
			.displayItems((params, output) -> {
				output.accept(ModItems.SLIPPER);
				output.accept(ModItems.NEPHRITE_SWORD);
				output.accept(ModItems.NEPHRITE_HELMET);
				output.accept(ModItems.NEPHRITE_BOOTS);
				output.accept(ModItems.NEPHRITE_LEGGINGS);
				output.accept(ModItems.NEPHRITE_CHESTPLATE);
			output.accept(ModItems.NEPHRITE_JADE);
			output.accept(ModItems.DRAGON_PEARL);
			output.accept(ModItems.ABACUS);
			output.accept(ModItems.INCENSE_STICK);
			output.accept(ModItems.FERMENTED_RESIDUE);
			output.accept(ModItems.COW_DUNG);
		output.accept(ModItems.PORCELAIN);
		output.accept(ModItems.SILK_GLOVES);
			output.accept(ModItems.PAPER_LANTERN);
			output.accept(ModItems.FLYING_NEEDLE);
			output.accept(ModItems.FUGU);
				output.accept(ModItems.CUSTOM_SOUND_ITEM);
				output.accept(ModItems.NEPHRITE_AXE);
				output.accept(ModItems.NEPHRITE_HOE);
			})
			.build();

	public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(AsianMod.MOD_ID, name));
		T item = itemFactory.apply(settings.setId(itemKey));
		Registry.register(BuiltInRegistries.ITEM, itemKey, item);
		return item;
	}

	public static void initialize() {
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
				.register((itemGroup) -> {
					itemGroup.accept(ModItems.NEPHRITE_HELMET);
					itemGroup.accept(ModItems.NEPHRITE_BOOTS);
					itemGroup.accept(ModItems.NEPHRITE_LEGGINGS);
					itemGroup.accept(ModItems.NEPHRITE_CHESTPLATE);
				});

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
				.register((itemGroup) -> itemGroup.accept(ModItems.NEPHRITE_SWORD));

		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CUSTOM_CREATIVE_TAB_KEY, CUSTOM_CREATIVE_TAB);

		ItemGroupEvents.modifyEntriesEvent(CUSTOM_CREATIVE_TAB_KEY).register(itemGroup -> {
			itemGroup.accept(ModItems.DRAGON_PEARL);
		});
	}
}