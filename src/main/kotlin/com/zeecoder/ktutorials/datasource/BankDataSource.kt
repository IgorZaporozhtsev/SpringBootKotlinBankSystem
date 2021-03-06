package com.zeecoder.ktutorials.datasource

import com.zeecoder.ktutorials.model.Bank
import org.springframework.web.bind.annotation.RequestBody

interface BankDataSource {
    fun retrieveBanks(): Collection<Bank>
    fun retrieveBank(accountNumber: String?): Bank?
    fun createBank(bank: Bank): Bank
    fun changeBank(accountNumber: String, bank: Bank): Bank
    fun updateBank(accountNumber: String?, bank: Bank): Bank
    fun deleteBank(accountNumber: String?): Unit
}