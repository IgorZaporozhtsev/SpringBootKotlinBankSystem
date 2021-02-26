package com.zeecoder.ktutorials.datasource

import com.zeecoder.ktutorials.model.Bank

interface BankDataSource {
    fun retrieveBanks(): Collection<Bank>
    fun retrieveBank(accountNumber: String): Bank?
    fun createBank(bank: Bank): Bank
    fun changeBank(bank: Bank): Bank
    fun updateBank(bank: Bank): Bank
    fun deleteBank(bank: Bank): Bank
}