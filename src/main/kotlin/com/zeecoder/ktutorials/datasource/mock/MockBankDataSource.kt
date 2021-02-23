package com.zeecoder.ktutorials.datasource.mock

import com.zeecoder.ktutorials.datasource.BankDataSource
import com.zeecoder.ktutorials.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource {

    val banks = listOf(
            Bank("111","Peter's account", 1.0, 17),
            Bank("222", "Marry's account",2.0, 0),
            Bank("333", "Yoda's account",3.0, 37)
    )

    override fun retrieveBanks(): Collection<Bank> = banks

    override fun retrieveBank(accountNumber: String): Bank =
        banks
                .first { it.accountNumber == accountNumber}
}