package com.lyaiya.softcode.mixin.industrialforegoing;

import com.buuz135.industrial.book.IFManual;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslationKeyKt;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(
        value = IFManual.class,
        remap = false
)
abstract class MixinIFManual {
    @ModifyConstant(
            method = "buildManual",
            constant = @Constant(stringValue = "Introduction")
    )
    private static String modifyConstantBuildManual0(String constant) {
        return I18n.format(TranslationKeyKt.create(ModIdConstant.INDUSTRIAL_FOREGOING, "manual", "introduction"));
    }

    @ModifyConstant(
            method = "buildManual",
            constant = @Constant(stringValue = "Upgrades")
    )
    private static String modifyConstantBuildManual1(String constant) {
        return I18n.format(TranslationKeyKt.create(ModIdConstant.INDUSTRIAL_FOREGOING, "manual", "upgrades"));
    }
}
