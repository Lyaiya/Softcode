package com.lyaiya.softcode.mixin.libvulpes;

import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.Util;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import zmaster587.libVulpes.block.BlockMotor;

@Mixin(
        value = BlockMotor.class,
        remap = false
)
abstract class MixinBlockMotor {
    @ModifyConstant(
            method = "addInformation",
            constant = @Constant(stringValue = "Machine Speed: %.2f")
    )
    private String modifyConstantAddInformation0(String constant) {
        String key = Util.getKey(ModIdConstant.LIB_VULPES, KeyConstant.TOOLTIP, "motor", "machine_speed");
        return I18n.format(key) + "%.2f";
    }
}