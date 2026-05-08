package org.spearedrice.asianmod.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.commands.synchronization.SuggestionProviders;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.permissions.Permissions;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class AsianModCommands implements ModInitializer {

    private static int executeDedicatedCommand(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("Called /dedicated_command."), false);
        return 1;
    }

    private static int executeRequiredCommand(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("Called /required_command."), false);
        return 1;
    }

    private static int executeSubCommandOne(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("Called /command sub_command_one."), false);
        return 1;
    }

    private static int executeCommandTwo(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("Called /command_two."), false);
        return 1;
    }

    private static int executeSubCommandTwo(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("Called /sub_command_two."), false);
        return 1;
    }

    private static int executeRedirectedBy(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("Called /redirected_by."), false);
        return 1;
    }

    private static int executeCommandWithArg(CommandContext<CommandSourceStack> context) {
        int value = IntegerArgumentType.getInteger(context, "value");
        context.getSource().sendSuccess(() -> Component.literal(
                "Called /command_with_arg with value = %s".formatted(value)
        ), false);
        return 1;
    }

    private static int executeWithOneArg(CommandContext<CommandSourceStack> context) {
        int value1 = IntegerArgumentType.getInteger(context, "value_one");
        context.getSource().sendSuccess(() -> Component.literal(
                "Called /command_with_two_args with value one = %s".formatted(value1)
        ), false);
        return 1;
    }

    private static int executeWithTwoArgs(CommandContext<CommandSourceStack> context) {
        int value1 = IntegerArgumentType.getInteger(context, "value_one");
        int value2 = IntegerArgumentType.getInteger(context, "value_two");
        context.getSource().sendSuccess(() -> Component.literal(
                "Called /argtater2 with value one = %s and value two = %s".formatted(value1, value2)
        ), false);
        return 1;
    }

    private static int executeCommon(int value1, int value2, CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal(
                "Called /command_with_common_exec with value 1 = %s and value 2 = %s".formatted(value1, value2)
        ), false);
        return 1;
    }

    private static int executeCustomArgCommand(CommandContext<CommandSourceStack> context) {
        BlockPos arg = context.getArgument("block_pos", BlockPos.class);
        context.getSource().sendSuccess(() -> Component.literal(
                "Called /command_with_custom_arg with block pos = %s".formatted(arg)
        ), false);
        return 1;
    }

    private static int executeCommandWithSuggestions(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        var entityType = ResourceArgument.getSummonableEntityType(context, "entity");
        context.getSource().sendSuccess(() -> Component.literal(
                "Called /command_with_suggestions with entity = %s".formatted(entityType.value().toShortString())
        ), false);
        return 1;
    }

    private static int executeCommandWithCustomSuggestions(CommandContext<CommandSourceStack> context) {
        String name = StringArgumentType.getString(context, "player_name");
        context.getSource().sendSuccess(() -> Component.literal(
                "Called /command_with_custom_suggestions with value = %s".formatted(name)
        ), false);
        return 1;
    }

    @Override
    public void onInitialize() {

        ArgumentTypeRegistry.registerArgumentType(
                Identifier.fromNamespaceAndPath("asianmod", "block_pos"),
                BlockPosArgumentType.class,
                SingletonArgumentInfo.contextFree(BlockPosArgumentType::new)
        );

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal("test_command").executes(context -> {
                context.getSource().sendSuccess(() -> Component.literal("Called /test_command."), false);
                return 1;
            }));
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            if (environment.includeDedicated) {
                dispatcher.register(Commands.literal("dedicated_command")
                        .executes(AsianModCommands::executeDedicatedCommand));
            }
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal("required_command")
                    .requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_MODERATOR))
                    .executes(AsianModCommands::executeRequiredCommand));
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal("command_one")
                    .then(Commands.literal("sub_command_one")
                            .executes(AsianModCommands::executeSubCommandOne)));
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal("command_two")
                    .executes(AsianModCommands::executeCommandTwo)
                    .then(Commands.literal("sub_command_two")
                            .executes(AsianModCommands::executeSubCommandTwo)));
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            var redirectedBy = dispatcher.register(
                    Commands.literal("redirected_by")
                            .executes(AsianModCommands::executeRedirectedBy)
            );

            dispatcher.register(
                    Commands.literal("to_redirect")
                            .executes(AsianModCommands::executeRedirectedBy)
                            .redirect(redirectedBy)
            );
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal("command_with_arg")
                    .then(Commands.argument("value", IntegerArgumentType.integer())
                            .executes(AsianModCommands::executeCommandWithArg)));
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal("command_with_two_args")
                    .then(Commands.argument("value_one", IntegerArgumentType.integer())
                            .executes(AsianModCommands::executeWithOneArg)
                            .then(Commands.argument("value_two", IntegerArgumentType.integer())
                                    .executes(AsianModCommands::executeWithTwoArgs))));
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal("command_with_common_exec")
                    .then(Commands.argument("value_one", IntegerArgumentType.integer())
                            .executes(ctx -> executeCommon(
                                    IntegerArgumentType.getInteger(ctx, "value_one"),
                                    0,
                                    ctx))
                            .then(Commands.argument("value_two", IntegerArgumentType.integer())
                                    .executes(ctx -> executeCommon(
                                            IntegerArgumentType.getInteger(ctx, "value_one"),
                                            IntegerArgumentType.getInteger(ctx, "value_two"),
                                            ctx)))));
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal("command_with_custom_arg")
                    .then(Commands.argument("block_pos", new BlockPosArgumentType())
                            .executes(AsianModCommands::executeCustomArgCommand)));
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal("command_with_suggestions")
                    .then(Commands.argument("entity", ResourceArgument.resource(registryAccess, Registries.ENTITY_TYPE))
                            .suggests(SuggestionProviders.cast(SuggestionProviders.SUMMONABLE_ENTITIES))
                            .executes(AsianModCommands::executeCommandWithSuggestions)));
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal("command_with_custom_suggestions")
                    .then(Commands.argument("player_name", StringArgumentType.string())
                            .suggests(new PlayerSuggestionProvider())
                            .executes(AsianModCommands::executeCommandWithCustomSuggestions)));
        });
    }
}