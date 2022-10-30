package com.lyaiya.softcode.mixin.blockdrops

import com.lyaiya.softcode.constant.*
import com.lyaiya.softcode.util.*
import mrriegel.blockdrops.*
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*
import org.spongepowered.asm.mixin.injection.callback.*

@Mixin(Category::class, remap = false)
abstract class MixinCategory {
    @Inject(
        method = ["getTitle"],
        at = [At("RETURN")],
        cancellable = true
    )
    private fun injectGetTitle(cir: CallbackInfoReturnable<String>) {
        cir.returnValue = I18n.format(createTranslationKey(ModIdConstant.BLOCK_DROPS, KeyConstant.GUI, KeyConstant.RECIPE_CATEGORY, "block_drops"))
    }
}