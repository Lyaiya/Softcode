package com.lyaiya.softcode.mixin.advancedrocketry;

import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.Util;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import zmaster587.advancedRocketry.integration.jei.MachineRecipe;

@Mixin(
        value = MachineRecipe.class,
        remap = false
)
abstract class MixinMachineRecipe {
    @ModifyArg(
            method = "drawInfo",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/String;format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;",
                    ordinal = 0
            )
    )
    private String modifyArgPower(String constant) {
        String key = Util.getKey(ModIdConstant.ADVANCED_ROCKETRY, KeyConstant.GUI, "power");
        return I18n.format(key);
    }

    @ModifyArg(
            method = "drawInfo",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/String;format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;",
                    ordinal = 0
            )
    )
    private String modifyArgTime(String constant) {
        String key = Util.getKey(ModIdConstant.ADVANCED_ROCKETRY, KeyConstant.GUI, "time");
        return I18n.format(key);
    }
}