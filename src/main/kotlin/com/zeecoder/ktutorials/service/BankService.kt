package com.zeecoder.ktutorials.service

import com.zeecoder.ktutorials.datasource.BankDataSource
import com.zeecoder.ktutorials.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private val dataSource: BankDataSource) {
    fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()
}