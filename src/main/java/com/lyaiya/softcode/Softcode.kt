package com.lyaiya.softcode

import com.lyaiya.softcode.constant.ModIdConstant
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLInterModComms
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Mod(
    modid = Softcode.MOD_ID,
    dependencies = "required-after:forge@[14.23.5.2847,);required-after:${ModIdConstant.FORGELIN_CONTINUOUS};required-after:${ModIdConstant.MIXIN_BOOTER}",
    modLanguageAdapter = "io.github.chaosunity.forgelin.KotlinAdapter",
    useMetadata = true
)
object Softcode {
    const val MOD_ID = "softcode"
    const val MOD_NAME = "Softcode"

    @JvmField
    val LOGGER: Logger = LogManager.getLogger()

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