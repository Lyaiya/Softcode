package com.lyaiya.softcode

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.*
import org.apache.logging.log4j.*

@Mod(
    modid = Softcode.MOD_ID,
    dependencies = "required-after:forge@[14.23.5.2847,);required-after:forgelin_continuous",
    modLanguageAdapter = "io.github.chaosunity.forgelin.KotlinAdapter",
    useMetadata = true
)
object Softcode {
    const val MOD_ID = "softcode"
    const val MOD_NAME = "Softcode"

    @JvmField
    val LOGGER: Logger = LogManager.getLogger(MOD_NAME)

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        LOGGER.info("preInit")
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        LOGGER.info("init")
    }

    @Mod.EventHandler
    fun imcEvent(event: FMLInterModComms.IMCEvent) {
        LOGGER.info("imcEvent")
    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        LOGGER.info("postInit")
    }

}