package org.spearedrice.asianmod.command;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.core.BlockPos;

public class BlockPosArgumentType implements ArgumentType<BlockPos> {

    @Override
    public BlockPos parse(StringReader reader) throws CommandSyntaxException {
        try {
            String string = reader.readString();

            string = string.replace("{", "").replace("}", "");

            String[] split = string.split(",");

            int x = Integer.parseInt(split[0].trim());
            int y = Integer.parseInt(split[1].trim());
            int z = Integer.parseInt(split[2].trim());

            return new BlockPos(x, y, z);
        } catch (Exception e) {
            throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherParseException()
                    .create("Invalid BlockPos format. Expected {x, y, z}");
        }
    }
}