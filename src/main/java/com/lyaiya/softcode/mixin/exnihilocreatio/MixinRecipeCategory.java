package com.lyaiya.softcode.mixin.exnihilocreatio;

import com.lyaiya.softcode.constant.ClassConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslationKeyKt;
import exnihilocreatio.compatibility.jei.barrel.compost.CompostRecipeCategory;
import exnihilocreatio.compatibility.jei.barrel.fluiditemtransform.FluidItemTransformRecipeCategory;
import exnihilocreatio.compatibility.jei.barrel.fluidontop.FluidOnTopRecipeCategory;
import exnihilocreatio.compatibility.jei.barrel.fluidtransform.FluidTransformRecipeCategory;
import exnihilocreatio.compatibility.jei.crook.CrookRecipeCategory;
import exnihilocreatio.compatibility.jei.crucible.CrucibleHeatSourceRecipeCategory;
import exnihilocreatio.compatibility.jei.crucible.CrucibleRecipeCategory;
import exnihilocreatio.compatibility.jei.hammer.HammerRecipeCategory;
import exnihilocreatio.compatibility.jei.sieve.SieveRecipeCategory;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(
        value = {CompostRecipeCategory.class, FluidItemTransformRecipeCategory.class, FluidOnTopRecipeCategory.class,
                FluidTransformRecipeCategory.class, CrookRecipeCategory.class, CrucibleHeatSourceRecipeCategory.class, CrucibleRecipeCategory.class,
                HammerRecipeCategory.class, SieveRecipeCategory.class},
        remap = false
)
abstract class MixinRecipeCategory {
    @Inject(
            method = "getTitle",
            at = @At("RETURN"),
            cancellable = true
    )
    private void injectGetTitle(CallbackInfoReturnable<String> cir) {
        String key = TranslationKeyKt.createReplaced(this.getClass(), ModIdConstant.EX_NIHILO_CREATIO, ClassConstant.RECIPE_CATEGORY);
        cir.setReturnValue(I18n.format(key));
    }
}
