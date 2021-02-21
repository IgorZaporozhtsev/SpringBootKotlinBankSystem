package com.zeecoder.ktutorials.datasource.mock

import com.zeecoder.ktutorials.datasource.BankDataSource
import com.zeecoder.ktutorials.model.Bank
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class MockBankDataSource : BankDataSource {

    val banks = listOf(
            Bank(UUID.randomUUID().toString(), 1.0, 17),
            Bank(UUID.randomUUID().toString(), 2.0, 0),
            Bank(UUID.randomUUID().toString(), 3.0, 37)
    )

    override fun getBanks(): Collection<Bank> = banks
}