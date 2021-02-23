package com.zeecoder.ktutorials.model

data class Bank(
        //if we have val Kotlin generate getter
        //if we have var Kotlin generate getter and setter
        var accountNumber: String,
        var accountName: String,
        var trust: Double,
        val transactionFee: Int
)