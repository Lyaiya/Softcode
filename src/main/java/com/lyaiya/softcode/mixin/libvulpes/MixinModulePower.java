package com.lyaiya.softcode.mixin.libvulpes;

import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslateKeyUtil;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import zmaster587.libVulpes.api.IUniversalEnergy;
import zmaster587.libVulpes.inventory.modules.ModulePower;

@Mixin(
        value = ModulePower.class,
        remap = false
)
abstract class MixinModulePower {
    @Shadow
    IUniversalEnergy tile;

    @ModifyArg(
            method = "renderForeground",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;add(Ljava/lang/Object;)Z",
                    ordinal = 0
            )
    )
    private Object modifyArgAdd(Object object) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.LIB_VULPES, KeyConstant.GUI, "module", "power");
        return I18n.format(key, this.tile.getUniversalEnergyStored(), this.tile.getMaxEnergyStored());
    }
}
