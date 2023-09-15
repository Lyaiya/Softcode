package com.lyaiya.softcode;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = Softcode.MOD_ID,
        dependencies = "required-after:forge@[14.23.5.2847,);required-after:mixinbooter@[8.6,)",
        useMetadata = true
)
public class Softcode {
    public static final String MOD_ID = "softcode";

    public static Logger LOGGER = LogManager.getLogger();

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("preInit");
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        LOGGER.info("init");
    }

    @Mod.EventHandler
    public static void imcEvent(FMLInterModComms.IMCEvent event) {
        LOGGER.info("imcEvent");
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        LOGGER.info("postInit");
    }
}