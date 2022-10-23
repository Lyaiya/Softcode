package com.lyaiya.softcode.mixin.inworldcrafting

import com.lyaiya.softcode.constant.*
import com.lyaiya.softcode.util.*
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*
import org.spongepowered.asm.mixin.injection.callback.*
import xt9.inworldcrafting.integrations.jei.*

@Mixin(
    value = [
        BurnItemRecipeCategory::class, ExplodeBlockRecipeCategory::class, ExplodeItemRecipeCategory::class,
        FluidToFluidRecipeCategory::class, FluidToItemRecipeCategory::class
    ],
    remap = false
)
abstract class MixinRecipeCategory {
    @Inject(
        method = ["getTitle"],
        at = [At("RETURN")],
        cancellable = true
    )
    private fun injectGetTitle(cir: CallbackInfoReturnable<String>) {
        cir.returnValue = I18n.format(javaClass.getReplacedTranslationKey(ModIdConstant.IN_WORLD_CRAFTING, ClassConstant.RECIPE_CATEGORY))
    }
}