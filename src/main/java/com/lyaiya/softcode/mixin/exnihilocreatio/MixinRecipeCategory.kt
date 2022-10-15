package com.lyaiya.softcode.mixin.exnihilocreatio

import com.lyaiya.softcode.*
import com.lyaiya.softcode.util.*
import exnihilocreatio.compatibility.jei.barrel.compost.*
import exnihilocreatio.compatibility.jei.barrel.fluiditemtransform.*
import exnihilocreatio.compatibility.jei.barrel.fluidontop.*
import exnihilocreatio.compatibility.jei.barrel.fluidtransform.*
import exnihilocreatio.compatibility.jei.crook.*
import exnihilocreatio.compatibility.jei.crucible.*
import exnihilocreatio.compatibility.jei.hammer.*
import exnihilocreatio.compatibility.jei.sieve.*
import net.minecraft.client.resources.*
import org.spongepowered.asm.mixin.*
import org.spongepowered.asm.mixin.injection.*
import org.spongepowered.asm.mixin.injection.callback.*

@Mixin(
    value = [
        CompostRecipeCategory::class, FluidItemTransformRecipeCategory::class, FluidOnTopRecipeCategory::class,
        FluidTransformRecipeCategory::class, CrookRecipeCategory::class, CrucibleHeatSourceRecipeCategory::class,
        CrucibleRecipeCategory::class, HammerRecipeCategory::class, SieveRecipeCategory::class
    ],
    remap = false
)
abstract class MixinRecipeCategory {
    @Inject(method = ["getTitle"], at = [At("RETURN")], cancellable = true)
    private fun injectGetTitle(cir: CallbackInfoReturnable<String>) {
        cir.returnValue = I18n.format(javaClass.getReplacedTranslationKey(ModId.EX_NIHILO_CREATIO, ClassConstants.RECIPE_CATEGORY))
    }
}