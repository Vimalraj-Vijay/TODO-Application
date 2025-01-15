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

    @SerializedName("SUBMIT_A_CLAIM")
    SUBMIT_A_CLAIM(icon = R.drawable.ic_submit_claim, contentDescription = ""),

    @SerializedName("CLAIMS_HISTORY")
    CLAIMS_HISTORY(icon = R.drawable.ic_claim_history, contentDescription = ""),

    @SerializedName("INSURANCE_INFO")
    INSURANCE_INFO(icon = R.drawable.ic_insurance_info, contentDescription = ""),

    @SerializedName("CONTACT_INFO")
    CONTACT_INFO(icon = R.drawable.ic_contact, contentDescription = ""),
}