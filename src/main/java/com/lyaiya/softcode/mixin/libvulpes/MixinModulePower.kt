package com.lyaiya.softcode.mixin.libvulpes

import com.lyaiya.softcode.constant.*
import com.lyaiya.softcode.util.*
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*
import zmaster587.libVulpes.inventory.modules.*

@Mixin(ModulePower::class, remap = false)
abstract class MixinModulePower {
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
        val firstSection: String
        val secondSection: String

        textList.first().split(" / ").also {
            firstSection = it.first()
        }.last().split(" Power").also {
            secondSection = it.first()
        }
        return listOf(I18n.format(createTranslationKey(ModIdConstant.LIB_VULPES, KeyConstant.GUI, "module", "power"), firstSection, secondSection))
    }
}