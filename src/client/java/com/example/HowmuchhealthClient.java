package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.EntityHitResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HowmuchhealthClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(Howmuchhealth.MOD_ID);

	private static final String HEART_EMOJI = "\u2764"; // Unicode for the heart emoji

	@Override
	public void onInitializeClient() {
		LOGGER.info("Client initialized for Howmuchhealth!");

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			PlayerEntity player = client.player;
			if (player != null && !player.isSpectator()) {
				Entity target = getEntityLookingAt(player, 25);
				if (target instanceof LivingEntity) {
					LivingEntity livingTarget = (LivingEntity) target;
					float health = livingTarget.getHealth();
					float maxHealth = livingTarget.getMaxHealth();
					player.sendMessage(Text.literal(HEART_EMOJI + " " + health + "/" + maxHealth), true);
				}
			}
		});
	}

	private Entity getEntityLookingAt(PlayerEntity player, double maxDistance) {
		MinecraftClient client = MinecraftClient.getInstance();
		Entity targetedEntity = client.crosshairTarget instanceof EntityHitResult ? ((EntityHitResult) client.crosshairTarget).getEntity() : null;

		if (targetedEntity != null && player.distanceTo(targetedEntity) <= maxDistance) {
			return targetedEntity;
		}
		return null;
	}
}
