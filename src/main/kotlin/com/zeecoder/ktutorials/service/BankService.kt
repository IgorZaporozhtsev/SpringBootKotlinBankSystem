package com.zeecoder.ktutorials.service

import com.zeecoder.ktutorials.model.Bank

interface BankService {
    fun getBanks(): Collection<Bank>
    fun getBank(accountNumber: String): Bank?
    fun addBank(bank: Bank): Bank
    fun changeBank(accountNumber: String, bank: Bank): Bank
    fun updateBank(accountNumber: String, bank: Bank): Bank
    fun deleteBank(accountNumber: String): Unit
}