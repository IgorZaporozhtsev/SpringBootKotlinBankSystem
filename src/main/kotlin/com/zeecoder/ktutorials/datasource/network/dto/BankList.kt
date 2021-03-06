package com.zeecoder.ktutorials.datasource.network.dto

import com.zeecoder.ktutorials.model.Bank

data class BankList (
        val results: Collection<Bank>
)