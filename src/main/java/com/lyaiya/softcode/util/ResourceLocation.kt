package com.lyaiya.softcode.util

import com.lyaiya.softcode.*
import net.minecraft.util.ResourceLocation

fun ResourceLocation(path: String): ResourceLocation = ResourceLocation(Softcode.MOD_ID, path)

