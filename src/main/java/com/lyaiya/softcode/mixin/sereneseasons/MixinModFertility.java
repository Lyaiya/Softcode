package com.lyaiya.softcode.mixin.sereneseasons;

import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslateKeyUtil;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import sereneseasons.init.ModFertility;

@Mixin(
        value = ModFertility.class,
        remap = false
)
abstract class MixinModFertility {
    @ModifyConstant(
            method = "setupTooltips",
            constant = @Constant(stringValue = "Fertile Seasons:")
    )
    private static String modifyConstantFertileSeason(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.SERENES_SEASONS, KeyConstant.DESC, "fertile_seasons");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "setupTooltips",
            constant = @Constant(stringValue = " Year-Round")
    )
    private static String modifyConstantYearRound(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.SERENES_SEASONS, KeyConstant.DESC, "year_round");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "setupTooltips",
            constant = @Constant(stringValue = " Spring")
    )
    private static String modifyConstantSpring(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.SERENES_SEASONS, KeyConstant.DESC, "spring");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "setupTooltips",
            constant = @Constant(stringValue = " Summer")
    )
    private static String modifyConstantSummer(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.SERENES_SEASONS, KeyConstant.DESC, "summer");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "setupTooltips",
            constant = @Constant(stringValue = " Autumn")
    )
    private static String modifyConstantAutumn(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.SERENES_SEASONS, KeyConstant.DESC, "autumn");
        return I18n.format(key);
    }

    @ModifyConstant(
            method = "setupTooltips",
            constant = @Constant(stringValue = " Winter")
    )
    private static String modifyConstantWinter(String constant) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.SERENES_SEASONS, KeyConstant.DESC, "winter");
        return I18n.format(key);
    }
}
