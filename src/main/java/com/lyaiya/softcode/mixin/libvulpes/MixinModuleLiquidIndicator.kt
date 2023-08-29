package com.lyaiya.softcode.mixin.libvulpes

import com.lyaiya.softcode.constant.KeyConstant
import com.lyaiya.softcode.constant.ModIdConstant
import com.lyaiya.softcode.util.createTranslationKey
import net.minecraft.client.resources.I18n
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.ModifyArg
import zmaster587.libVulpes.inventory.modules.ModuleLiquidIndicator

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
            return listOf(I18n.format(createTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.GUI, "module", "liquid_indicator", "empty")))
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
                createTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.GUI, "module", "liquid_indicator", "not_empty"),
                firstSection,
                secondSection,
                thirdSection
            )
        )
    }
}