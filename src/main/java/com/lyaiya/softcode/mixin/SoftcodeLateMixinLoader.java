package com.lyaiya.softcode.mixin;

import com.lyaiya.softcode.Tags;
import com.lyaiya.softcode.constant.ModIdConstant;
import net.minecraftforge.fml.common.Loader;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoftcodeLateMixinLoader implements ILateMixinLoader {
    private final Map<String, String> mixinConfigs = new HashMap<>();

    public SoftcodeLateMixinLoader() {
        put(mixinConfigs, ModIdConstant.IN_WORLD_CRAFTING);
        put(mixinConfigs, ModIdConstant.EX_NIHILO_CREATIO);
        put(mixinConfigs, ModIdConstant.THAUMCRAFT);
        put(mixinConfigs, ModIdConstant.THAUMIC_JEI);
        put(mixinConfigs, ModIdConstant.IMMERSIVE_ENGINEERING);
        put(mixinConfigs, ModIdConstant.BLOCK_DROPS);
        put(mixinConfigs, ModIdConstant.IC2);
        put(mixinConfigs, ModIdConstant.CONTROLLING);
        put(mixinConfigs, ModIdConstant.LIB_VULPES);
        put(mixinConfigs, ModIdConstant.ADVANCED_ROCKETRY);
        put(mixinConfigs, ModIdConstant.SERENES_SEASONS);
        put(mixinConfigs, ModIdConstant.JUST_ENOUGH_RESOURCES);
        put(mixinConfigs, ModIdConstant.INDUSTRIAL_FOREGOING);
    }

    private void put(Map<String, String> map, String modid) {
        map.put(String.format("mixins.%s.%s.json", Tags.MOD_ID, modid), modid);
    }

    @Override
    public List<String> getMixinConfigs() {
        return new ArrayList<>(mixinConfigs.keySet());
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        return mixinIfModLoaded(mixinConfig);
    }

    public boolean mixinIfModLoaded(String mixinConfig) {
        if (mixinConfigs.containsKey(mixinConfig)) {
            final String modid = mixinConfigs.get(mixinConfig);
            if (modid != null) {
                return Loader.isModLoaded(modid);
            }
        }
        return false;
    }

}
