package com.lyaiya.softcode.mixin.exnihilocreatio

import com.lyaiya.softcode.constant.*
import com.lyaiya.softcode.util.*
import exnihilocreatio.items.ore.*
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*
import org.spongepowered.asm.mixin.injection.callback.*

@Mixin(ItemOre::class, remap = false)
abstract class MixinItemOre {
    @Inject(
        method = ["getItemStackDisplayName"],
        at = [At(value = "RETURN")],
        cancellable = true
    )
    private fun injectGetItemStackDisplayName(cir: CallbackInfoReturnable<String>) {
        val splitString = cir.returnValue.split(" ", limit = 2)
        val firstString = splitString.first().lowercase()
        val lastString = splitString.last()
        val key = getTranslationKey(ModIdConstant.EX_NIHILO_CREATIO, KeyConstant.MATERIAL, firstString)
        cir.returnValue = I18n.format(key) + lastString
    }
}