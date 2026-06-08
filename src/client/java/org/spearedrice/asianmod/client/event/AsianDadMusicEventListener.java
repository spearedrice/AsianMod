package org.spearedrice.asianmod.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import org.spearedrice.asianmod.entity.AsianDadEntity;
import org.spearedrice.asianmod.sound.CustomSounds;

public class AsianDadMusicEventListener {
	private static boolean isMusicPlaying = false;
	private static SimpleSoundInstance musicInstance = null;
	private static final float DETECTION_RANGE = 50.0F;

	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> onClientTick());
	}

	private static void onClientTick() {
		Minecraft client = Minecraft.getInstance();
		Player player = client.player;
		Level level = client.level;

		if (player == null || level == null) {
			if (isMusicPlaying) {
				stopMusic(client);
			}
			return;
		}

		boolean asianDadNearby = level.getEntities(null, player.getBoundingBox().inflate(DETECTION_RANGE))
				.stream()
				.anyMatch(entity -> entity instanceof AsianDadEntity);

		if (asianDadNearby && !isMusicPlaying) {
			startMusic(client);
		} else if (!asianDadNearby && isMusicPlaying) {
			stopMusic(client);
		}
	}

	private static void startMusic(Minecraft client) {
		if (client.getSoundManager() != null && client.player != null && musicInstance == null) {

			musicInstance = SimpleSoundInstance.forUI(CustomSounds.RED_SUN, 1.0F);
			client.getSoundManager().play(musicInstance);
			isMusicPlaying = true;
		}
	}

	private static void stopMusic(Minecraft client) {
		if (client.getSoundManager() != null && musicInstance != null) {
			client.getSoundManager().stop(musicInstance);
			musicInstance = null;
		}
		isMusicPlaying = false;
	}
}