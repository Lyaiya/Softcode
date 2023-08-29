package com.lyaiya.softcode.mixin.thaumicjei

import com.buuz135.thaumicjei.ThaumcraftJEIPlugin
import com.lyaiya.softcode.constant.KeyConstant
import com.lyaiya.softcode.constant.ModIdConstant
import com.lyaiya.softcode.util.createTranslationKey
import net.minecraft.client.resources.I18n
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.Constant
import org.spongepowered.asm.mixin.injection.ModifyConstant

@Mixin(ThaumcraftJEIPlugin::class, remap = false)
abstract class MixinThaumcraftJEIPlugin {
    @ModifyConstant(
        method = ["register"],
        constant = [Constant(stringValue = "To create Salis Mundis, take 3 Vis Crystals of different types and combine them with Redstone Dust by crafting them with a flint and a bowl.")]
    )
    private fun modifyConstantRegister0(text: String): String {
        return I18n.format(createTranslationKey(ModIdConstant.THAUMIC_JEI, KeyConstant.DESCRIPTION, "salis_mundis"))
    }

    @ModifyConstant(
        method = ["register"],
        constant = [Constant(stringValue = "To create the Triple Meat Treat, take 3 different kinds of meat nuggets (produced by cooking meat in the Infernal Furnace) and mix them with sugar.")]
    )
    private fun modifyConstantRegister1(text: String): String {
        return I18n.format(createTranslationKey(ModIdConstant.THAUMIC_JEI, KeyConstant.DESCRIPTION, "triple_meat_treat"))
    }
}