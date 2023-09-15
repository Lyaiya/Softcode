package com.lyaiya.softcode.mixin.immersiveengineering;

import blusunrize.immersiveengineering.common.util.compat.jei.IERecipeCategory;
import blusunrize.immersiveengineering.common.util.compat.jei.arcfurnace.ArcFurnaceRecipeCategory;
import com.google.common.base.CaseFormat;
import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslateKeyUtil;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
        value = ArcFurnaceRecipeCategory.class,
        remap = false
)
abstract class MixinArcFurnaceRecipeCategory<T, W extends IRecipeWrapper> extends IERecipeCategory<T, W> {
    public MixinArcFurnaceRecipeCategory(
            String uniqueName, String localKey, IDrawable background, Class<T> recipeClass, ItemStack... displayStacks
    ) {
        super(uniqueName, localKey, background, recipeClass, displayStacks);
    }

    // TODO: 或许有更好的实现方式
    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void injectInit(CallbackInfo ci) {
        if (uniqueName.contains(".")) {
            String[] splitStr = uniqueName.split("\\.", 2);
            String forwardStr = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, splitStr[0]);
            String backStr = splitStr[1];

            String key = TranslateKeyUtil.getKey(ModIdConstant.IMMERSIVE_ENGINEERING,
                                                 KeyConstant.GUI,
                                                 KeyConstant.RECIPE_CATEGORY,
                                                 forwardStr,
                                                 backStr);
            localizedName = I18n.format(key);
        }
    }
}
