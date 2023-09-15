package com.lyaiya.softcode.mixin.inworldcrafting;

import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import xt9.inworldcrafting.common.recipe.ExplodeItemRecipe;
import xt9.inworldcrafting.integrations.jei.ExplodeItemRecipeWrapper;

@Mixin(
        value = ExplodeItemRecipeWrapper.class,
        remap = false
)
abstract class MixinExplodeItemRecipeWrapper {
    @Shadow
    private ExplodeItemRecipe recipe;

    @ModifyArg(
            method = "drawInfo",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow(Ljava/lang/String;FFI)I"
            ),
            index = 0
    )
    private String modifyArgDrawStringWithShadow(String text) {
        String key = InWorldCraftingKt.getRecipeWrapperKey(getClass()) + ".success_chance";
        return I18n.format(key, recipe.getSurviveChance());
    }
}
