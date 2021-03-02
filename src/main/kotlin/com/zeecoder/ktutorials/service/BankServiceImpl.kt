package com.zeecoder.ktutorials.service

import com.zeecoder.ktutorials.datasource.BankDataSource
import com.zeecoder.ktutorials.model.Bank
import org.springframework.stereotype.Service

@Service
class BankServiceImpl(private val dataSource: BankDataSource) :BankService{
    override fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()
    override fun getBank(accountNumber: String): Bank? = dataSource.retrieveBank(accountNumber)
    override fun addBank(bank: Bank): Bank = dataSource.createBank(bank);
    override fun changeBank(accountNumber: String, bank: Bank): Bank = dataSource.changeBank(accountNumber, bank)
    override fun updateBank(accountNumber: String, bank: Bank): Bank = dataSource.updateBank(accountNumber, bank)
    override fun deleteBank(accountNumber: String): Bank = dataSource.deleteBank(accountNumber)
}