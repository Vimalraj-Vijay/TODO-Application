package com.vimalraj.todoapplication.multipleviewtype.viewmodel

import com.google.gson.annotations.SerializedName
import com.vimalraj.todoapplication.R

enum class IconType(val icon: Int, val contentDescription: String) {
    @SerializedName("VIEW_AND_PAY")
    VIEW_AND_PAY(icon = R.drawable.ic_item_pay_bills, contentDescription = ""),

    @SerializedName("KNOW_MORE_ABOUT_BILLING")
    KNOW_MORE_ABOUT_BILLING(icon = R.drawable.ic_item_bill_info, contentDescription = ""),

    @SerializedName("PAST_PAYMENTS")
    PAST_PAYMENTS(icon = R.drawable.ic_item_payment_history, contentDescription = ""),
}