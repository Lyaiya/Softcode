package com.lyaiya.softcode.util

import com.lyaiya.softcode.Softcode
import net.minecraft.util.ResourceLocation

fun ResourceLocation(path: String): ResourceLocation = ResourceLocation(Softcode.MOD_ID, path)