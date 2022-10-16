package com.lyaiya.softcode.mixin.thaumicjei

import com.buuz135.thaumicjei.*
import com.lyaiya.softcode.*
import com.lyaiya.softcode.util.*
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*

@Mixin(ThaumcraftJEIPlugin::class, remap = false)
abstract class MixinThaumcraftJEIPlugin {
    @ModifyConstant(
        method = ["register"],
        constant = [Constant(stringValue = "To create Salis Mundis, take 3 Vis Crystals of different types and combine them with Redstone Dust by crafting them with a flint and a bowl.")]
    )
    private fun modifyConstantRegister0(text: String): String {
        return I18n.format(getTranslationKey(ModId.THAUMIC_JEI, KeyConstants.DESCRIPTION, "salis_mundis"))
    }

    @ModifyConstant(
        method = ["register"],
        constant = [Constant(stringValue = "To create the Triple Meat Treat, take 3 different kinds of meat nuggets (produced by cooking meat in the Infernal Furnace) and mix them with sugar.")]
    )
    private fun modifyConstantRegister1(text: String): String {
        return I18n.format(getTranslationKey(ModId.THAUMIC_JEI, KeyConstants.DESCRIPTION, "triple_meat_treat"))
    }
}