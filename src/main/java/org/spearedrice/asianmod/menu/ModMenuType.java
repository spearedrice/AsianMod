package org.spearedrice.asianmod.menu;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

import org.spearedrice.asianmod.menu.custom.BrassChestMenu;

public class ModMenuType {

    public static final MenuType<BrassChestMenu> BRASS_CHEST =
            register("brass_chest", BrassChestMenu::new);


    public static <T extends AbstractContainerMenu> MenuType<T> register(
            String name,
            MenuType.MenuSupplier<T> constructor
    ) {
        return Registry.register(
                BuiltInRegistries.MENU,
                name,
                new MenuType<>(constructor, FeatureFlagSet.of())
        );
    }

    public static void initialize() {
    }
}