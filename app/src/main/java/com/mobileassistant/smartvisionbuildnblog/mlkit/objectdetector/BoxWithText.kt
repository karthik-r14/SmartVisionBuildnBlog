package com.mobileassistant.smartvisionbuildnblog.mlkit.objectdetector

import android.graphics.Rect
import android.util.Pair

data class BoxWithText(
    val text: String, val rect: Rect, val additionalInfo: Pair<Boolean, String> = Pair(false, "")
)
