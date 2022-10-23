package com.lyaiya.softcode.mixin.blockdrops

import com.lyaiya.softcode.constant.*
import com.lyaiya.softcode.util.*
import mrriegel.blockdrops.Wrapper
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*

@Mixin(Wrapper::class, remap = false)
abstract class MixinWrapper {
    @ModifyConstant(
        method = ["onTooltip"],
        constant = [Constant(stringValue = "Min: ")]
    )
    private fun modifyConstantOnTooltip0(text: String): String {
        return I18n.format(getTranslationKey(ModIdConstant.BLOCK_DROPS, KeyConstant.GUI, "min"))
    }

    @ModifyConstant(
        method = ["onTooltip"],
        constant = [Constant(stringValue = "  Max: ")]
    )
    private fun modifyConstantOnTooltip1(text: String): String {
        return "  " + I18n.format(getTranslationKey(ModIdConstant.BLOCK_DROPS, KeyConstant.GUI, "max"))
    }

    @ModifyConstant(
        method = ["onTooltip"],
        constant = [Constant(stringValue = "Fortune ")]
    )
    private fun modifyConstantOnTooltip2(text: String): String {
        return I18n.format("enchantment.lootBonusDigger") + " "
    }

    @ModifyConstant(
        method = ["onTooltip"],
        constant = [Constant(stringValue = "There are too many possible drops. Use left and right key to cycle.")]
    )
    private fun modifyConstantOnTooltip3(text: String): String {
        return I18n.format(getTranslationKey(ModIdConstant.BLOCK_DROPS, KeyConstant.GUI, "too_many_drops"))
    }
}