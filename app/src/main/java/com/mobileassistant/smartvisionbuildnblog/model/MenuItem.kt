package com.mobileassistant.smartvisionbuildnblog.model

data class MenuItem(
    val menuText: String, val iconResId: Int, val itemClickListener: () -> Unit
)
