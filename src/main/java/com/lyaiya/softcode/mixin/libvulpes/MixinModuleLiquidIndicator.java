package com.lyaiya.softcode.mixin.libvulpes;

import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslationKeyKt;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import zmaster587.libVulpes.inventory.modules.ModuleLiquidIndicator;

@Mixin(
        value = ModuleLiquidIndicator.class,
        remap = false
)
abstract class MixinModuleLiquidIndicator {
    @Shadow
    IFluidHandler tile;

    @ModifyArg(
            method = "renderForeground",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;add(Ljava/lang/Object;)Z",
                    ordinal = 0
            )
    )
    private Object modifyArgAdd0(Object object) {
        FluidStack fluidStack = this.tile.getTankProperties()[0].getContents();
        assert fluidStack != null;
        String key = TranslationKeyKt.create(ModIdConstant.LIB_VULPES, KeyConstant.GUI, "module", "liquid_indicator", "not_empty");
        return I18n.format(key, fluidStack.getLocalizedName(), fluidStack.amount, this.tile.getTankProperties()[0].getCapacity());
    }

    @ModifyArg(
            method = "renderForeground",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;add(Ljava/lang/Object;)Z",
                    ordinal = 1
            )
    )
    private Object modifyArgAdd1(Object object) {
        String key = TranslationKeyKt.create(ModIdConstant.LIB_VULPES, KeyConstant.GUI, "module", "liquid_indicator", "empty");
        return I18n.format(key);
    }
}
