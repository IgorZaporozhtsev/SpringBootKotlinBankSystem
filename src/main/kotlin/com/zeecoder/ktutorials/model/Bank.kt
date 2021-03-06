package com.zeecoder.ktutorials.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Bank(
        //if we have val Kotlin generate getter
        //if we have var Kotlin generate getter and setter
        @JsonProperty("account_number")
        var accountNumber: String?,
        var accountName: String?,
        @JsonProperty("trust")
        var trust: Double,
        @JsonProperty("default_transaction_fee")
        var transactionFee: Int
)