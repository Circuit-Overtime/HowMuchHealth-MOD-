package com.example;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Howmuchhealth implements ModInitializer {
	public static final String MOD_ID = "howmuchhealth";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		// Server-side initialization code can go here if needed
	}
}
