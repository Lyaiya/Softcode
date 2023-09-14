package com.lyaiya.softcode.mixin.industrialforegoing;

import com.buuz135.industrial.jei.fluiddictionary.FluidDictionaryCategory;
import com.buuz135.industrial.jei.machineproduce.MachineProduceCategory;
import com.buuz135.industrial.jei.manual.ManualCategory;
import com.buuz135.industrial.jei.petrifiedgen.PetrifiedBurnTimeCategory;
import com.buuz135.industrial.jei.stonework.StoneWorkCategory;
import com.lyaiya.softcode.constant.ClassConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslationKeyKt;
import net.minecraft.client.resources.I18n;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(
        value = {FluidDictionaryCategory.class, MachineProduceCategory.class, ManualCategory.class, PetrifiedBurnTimeCategory.class,
                StoneWorkCategory.class},
        remap = false
)
abstract class MixinCategory {
    @Inject(
            method = "getTitle",
            at = @At("RETURN"),
            cancellable = true
    )
    private void injectGetTitle(@NotNull CallbackInfoReturnable<String> cir) {
        cir.setReturnValue(I18n.format(TranslationKeyKt.createReplaced(this.getClass(),
                                                                       ModIdConstant.INDUSTRIAL_FOREGOING,
                                                                       "Category",
                                                                       ClassConstant.RECIPE_CATEGORY)));
    }
}
