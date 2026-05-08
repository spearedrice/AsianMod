package org.spearedrice.asianmod.attachment;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;

public class AsianModAttachments {

    public static final AttachmentType<String> ASIANMOD_STRING_ATTACHMENT = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath("asianmod", "asian_string_attachment")
    );

    public static final AttachmentType<BlockPos> ASIANMOD_BLOCK_POS_ATTACHMENT = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath("asianmod", "asian_block_pos_attachment"),
            builder -> builder
                    .initializer(() -> new BlockPos(0, 0, 0))
                    .syncWith(
                            BlockPos.STREAM_CODEC,
                            AttachmentSyncPredicate.all()
                    )
    );

    public static final AttachmentType<BlockPos> ASIANMOD_PERSISTENT_ATTACHMENT = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath("asianmod", "asian_persistent_block_pos_attachment"),
            builder -> builder
                    .initializer(() -> new BlockPos(0, 0, 0))
                    .persistent(BlockPos.CODEC)
                    .copyOnDeath()
    );
}