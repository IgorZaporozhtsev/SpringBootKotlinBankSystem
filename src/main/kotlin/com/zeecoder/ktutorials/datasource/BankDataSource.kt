package com.zeecoder.ktutorials.datasource

import com.zeecoder.ktutorials.model.Bank

interface BankDataSource {
    fun retrieveBanks(): Collection<Bank>
    fun retrieveBank(accountNumber: String): Bank?
}