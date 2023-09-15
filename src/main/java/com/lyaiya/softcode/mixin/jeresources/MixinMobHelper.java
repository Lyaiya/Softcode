package com.lyaiya.softcode.mixin.jeresources;

import com.lyaiya.softcode.constant.KeyConstant;
import com.lyaiya.softcode.constant.ModIdConstant;
import com.lyaiya.softcode.util.TranslateKeyUtil;
import jeresources.entry.MobEntry;
import jeresources.util.MobHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(
        value = MobHelper.class,
        remap = false
)
abstract class MixinMobHelper {
    /**
     * @author Lyaiya
     * @reason Color localization
     */
    @Overwrite
    public static String getExpandedName(@NotNull MobEntry entry) {
        EntityLivingBase entity = entry.getEntity();
        String entityName = entity.getName();
        if (entity instanceof EntitySheep) {
            EntitySheep sheep = (EntitySheep) entity;
            String colorKey = TranslateKeyUtil.getKey(ModIdConstant.JUST_ENOUGH_RESOURCES, KeyConstant.COLOR, sheep.getFleeceColor().getName());
            String colorFormatKey = TranslateKeyUtil.getKey(ModIdConstant.JUST_ENOUGH_RESOURCES, KeyConstant.FORMAT, KeyConstant.COLOR);
            return I18n.format(colorFormatKey, I18n.format(colorKey), entityName);
        }
        return entityName;
    }
}
