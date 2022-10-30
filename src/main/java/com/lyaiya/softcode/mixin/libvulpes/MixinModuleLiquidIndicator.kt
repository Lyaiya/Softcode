package com.lyaiya.softcode.mixin.libvulpes

import com.lyaiya.softcode.constant.*
import com.lyaiya.softcode.util.*
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*
import zmaster587.libVulpes.inventory.modules.*

@Mixin(ModuleLiquidIndicator::class, remap = false)
abstract class MixinModuleLiquidIndicator {
    @ModifyArg(
        method = ["renderForeground"],
        at = At(
            value = "INVOKE",
            target = "drawTooltip(Lnet/minecraft/client/gui/inventory/GuiContainer;Ljava/util/List;IIFLnet/minecraft/client/gui/FontRenderer;)V",
            ordinal = 0
        ),
        index = 1
    )
    private fun modifyArgRenderForeground(textList: List<String>): List<String> {
        if ("Empty" in textList) {
            return listOf(I18n.format(getTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.GUI, "module", "liquid_indicator", "empty")))
        }
        val firstSection: String
        val secondSection: String
        val thirdSection: String

        textList.first().split(": ", limit = 2).also {
            firstSection = it.first()
        }.last().split(" / ", limit = 2).also {
            secondSection = it.first()
        }.last().split(" mB", limit = 2).also {
            thirdSection = it.first()
        }
        return listOf(
            I18n.format(
                getTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.GUI, "module", "liquid_indicator", "not_empty"),
                firstSection,
                secondSection,
                thirdSection
            )
        )
    }
}