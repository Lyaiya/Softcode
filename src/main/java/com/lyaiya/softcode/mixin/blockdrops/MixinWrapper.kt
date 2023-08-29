package com.lyaiya.softcode.mixin.blockdrops

import com.lyaiya.softcode.constant.KeyConstant
import com.lyaiya.softcode.constant.ModIdConstant
import com.lyaiya.softcode.util.createTranslationKey
import mrriegel.blockdrops.Wrapper
import net.minecraft.client.resources.I18n
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.Constant
import org.spongepowered.asm.mixin.injection.ModifyConstant

@Mixin(Wrapper::class, remap = false)
abstract class MixinWrapper {
    @ModifyConstant(
        method = ["onTooltip"],
        constant = [Constant(stringValue = "Min: ")]
    )
    private fun modifyConstantOnTooltip0(text: String): String {
        return I18n.format(createTranslationKey(ModIdConstant.BLOCK_DROPS, KeyConstant.GUI, "min"))
    }

    @ModifyConstant(
        method = ["onTooltip"],
        constant = [Constant(stringValue = "  Max: ")]
    )
    private fun modifyConstantOnTooltip1(text: String): String {
        return "  " + I18n.format(createTranslationKey(ModIdConstant.BLOCK_DROPS, KeyConstant.GUI, "max"))
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
        return I18n.format(createTranslationKey(ModIdConstant.BLOCK_DROPS, KeyConstant.GUI, "too_many_drops"))
    }
}