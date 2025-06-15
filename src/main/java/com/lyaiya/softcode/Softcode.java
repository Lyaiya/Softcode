package com.lyaiya.softcode;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = Tags.MOD_ID,
        dependencies = "required-after:forge@[14.23.5.2847,);required-after:mixinbooter@[8.6,)",
        useMetadata = true
)
public class Softcode {
    public static Logger LOGGER = LogManager.getLogger();

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        LOGGER.debug("preInit");
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        LOGGER.debug("init");
    }

    @Mod.EventHandler
    public static void imcEvent(FMLInterModComms.IMCEvent event) {
        LOGGER.debug("imcEvent");
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        LOGGER.debug("postInit");
    }
}