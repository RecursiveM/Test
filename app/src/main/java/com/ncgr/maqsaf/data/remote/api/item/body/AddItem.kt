package com.ncgr.maqsaf.data.remote.api.item.body

data class AddItem(
    val order_id : String = "",
    val item_id : String = "",
    val type: String ="",
    val withMilk: Boolean = false,
    val sugar_amount: Int = 0,
)
