package com.lyaiya.softcode.mixin.exnihilocreatio

import com.lyaiya.softcode.constant.KeyConstant
import com.lyaiya.softcode.constant.ModIdConstant
import com.lyaiya.softcode.util.createTranslationKey
import exnihilocreatio.items.ore.ItemOre
import net.minecraft.client.resources.I18n
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
import java.util.*

@Mixin(ItemOre::class, remap = false)
abstract class MixinItemOre {
    @Inject(
        method = ["getItemStackDisplayName"],
        at = [At("RETURN")],
        cancellable = true
    )
    private fun injectGetItemStackDisplayName(cir: CallbackInfoReturnable<String>) {
        val splitString = cir.returnValue.split(" ", limit = 2)
        val firstString = splitString.first().lowercase(Locale.ENGLISH)
        val lastString = splitString.last()
        val key = createTranslationKey(ModIdConstant.EX_NIHILO_CREATIO, KeyConstant.MATERIAL, firstString)
        cir.returnValue = I18n.format(key) + lastString
    }
}