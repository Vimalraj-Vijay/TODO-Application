package com.vimalraj.todoapplication.multipleviewtype.viewmodel

import com.google.gson.annotations.SerializedName

enum class ViewType {
    @SerializedName("billing_regular")
    BILLING_REGULAR,

    @SerializedName("claim_regular")
    CLAIM_REGULAR,

    @SerializedName("vcs_regular")
    VCS_REGULAR,

    @SerializedName("invoice_constraint")
    INVOICE
}