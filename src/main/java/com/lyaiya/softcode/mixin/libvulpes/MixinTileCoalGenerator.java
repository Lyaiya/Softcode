package com.lyaiya.softcode.mixin.libvulpes;

import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslateKeyUtil;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import zmaster587.libVulpes.tile.TileInventoriedForgePowerMachine;
import zmaster587.libVulpes.tile.energy.TileCoalGenerator;

@Mixin(
        value = TileCoalGenerator.class,
        remap = false
)
abstract class MixinTileCoalGenerator extends TileInventoriedForgePowerMachine {
    protected MixinTileCoalGenerator(int energy, int invSize) {
        super(energy, invSize);
    }

    @ModifyArg(
            method = "update",
            at = @At(
                    value = "INVOKE",
                    target = "Lzmaster587/libVulpes/inventory/modules/ModuleText;setText(Ljava/lang/String;)V"
            ),
            index = 0
    )
    private String modifyArgSetText(String text) {
        String key = TranslateKeyUtil.getKey(ModIdConstant.LIB_VULPES, KeyConstant.GUI, "coal_generator", "generating");
        return I18n.format(key, this.getLastAmtGenerated());
    }
}
