package com.zeecoder.ktutorials.datasource

import com.zeecoder.ktutorials.model.Bank

interface BankDataSource {
    fun getBanks(): Collection<Bank>
}