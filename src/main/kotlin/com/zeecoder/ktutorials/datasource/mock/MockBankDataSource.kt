package com.zeecoder.ktutorials.datasource.mock

import com.zeecoder.ktutorials.datasource.BankDataSource
import com.zeecoder.ktutorials.exceptions.ApiBankException
import com.zeecoder.ktutorials.model.Bank
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException
import kotlin.jvm.Throws

@Repository
class MockBankDataSource : BankDataSource {

    val banks = mutableListOf(
            Bank("111","Peter's account", 1.0, 17),
            Bank("222", "Marry's account",2.0, 0),
            Bank("333", "Yoda's account",3.0, 37)
    )

    override fun retrieveBanks(): Collection<Bank> = banks

    @Throws(ApiBankException::class) //todo redundant?
    override fun retrieveBank(accountNumber: String): Bank? =
        banks
                .firstOrNull { it.accountNumber == accountNumber}
        ?: throw ApiBankException("In Repo Could no find a bank with accountNumber $accountNumber")

    override fun createBank(bank: Bank): Bank {
        val existedBank = banks.any { it.accountNumber == bank.accountNumber }
        if (existedBank){
            throw ApiBankException("bank with number ${bank.accountNumber} already exists")
        }
        banks.add(bank)
        return bank;
    }
}