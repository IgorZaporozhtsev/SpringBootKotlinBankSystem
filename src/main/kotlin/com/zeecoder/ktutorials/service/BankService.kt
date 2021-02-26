package com.zeecoder.ktutorials.service

import com.zeecoder.ktutorials.datasource.BankDataSource
import com.zeecoder.ktutorials.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private val dataSource: BankDataSource) {
    fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()
    fun getBank(accountNumber: String): Bank? = dataSource.retrieveBank(accountNumber)
    fun addBank(bank: Bank): Bank = dataSource.createBank(bank);
    fun changeBank(bank: Bank): Bank = dataSource.changeBank(bank)
    fun updateBank(bank: Bank): Bank = dataSource.updateBank(bank)
    fun deleteBank(bank: Bank): Bank = dataSource.deleteBank(bank)
}