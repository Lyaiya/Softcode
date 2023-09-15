package com.lyaiya.softcode.mixin.thaumicjei;

import com.buuz135.thaumicjei.ThaumcraftJEIPlugin;
import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslationKeyKt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(
        value = ThaumcraftJEIPlugin.class,
        remap = false
)
abstract class MixinThaumcraftJEIPlugin {
    @ModifyArg(
            method = "register",
            at = @At(
                    value = "INVOKE",
                    target = "Lmezz/jei/api/IModRegistry;addIngredientInfo(Ljava/util/List;Lmezz/jei/api/recipe/IIngredientType;[Ljava/lang/String;"
                            + ")V",
                    ordinal = 0
            ),
            index = 2
    )
    private String[] modifyArgIngredientInfo0(String[] descriptionKeys) {
        String key = TranslationKeyKt.create(ModIdConstant.THAUMIC_JEI, KeyConstant.DESCRIPTION, "salis_mundis");
        return new String[]{key};
    }

    @ModifyArg(
            method = "register",
            at = @At(
                    value = "INVOKE",
                    target = "Lmezz/jei/api/IModRegistry;addIngredientInfo(Ljava/lang/Object;Ljava/lang/Class;[Ljava/lang/String;)V",
                    ordinal = 0
            ),
            index = 2
    )
    private String[] modifyArgIngredientInfo1(String[] descriptionKeys) {
        String key = TranslationKeyKt.create(ModIdConstant.THAUMIC_JEI, KeyConstant.DESCRIPTION, "triple_meat_treat");
        return new String[]{key};
    }
}
