package com.zeecoder.ktutorials.datasource.mock

import com.zeecoder.ktutorials.datasource.BankDataSource
import com.zeecoder.ktutorials.exceptions.ApiBankException
import com.zeecoder.ktutorials.model.Bank
import org.springframework.stereotype.Repository
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
    override fun retrieveBank(accountNumber: String?): Bank? =
        banks
            .firstOrNull { it.accountNumber == accountNumber}
                ?: throw ApiBankException("In Repo Could no find a bank with accountNumber $accountNumber", "GEEX001")
                //?: throw ApiBankException("User is not present")

    override fun createBank(bank: Bank): Bank {
        val existedBank = banks.any { it.accountNumber == bank.accountNumber }
        if (existedBank){
            throw ApiBankException("bank with number ${bank.accountNumber} already exists", "GEEX002")
        }
        banks.add(bank)
        return bank;
    }

    override fun updateBank(accountNumber: String?, bank: Bank): Bank {

        val currentBank = (banks
                .firstOrNull { it.accountNumber == accountNumber }
                ?: throw ApiBankException("In Repo Could no find a bank with accountNumber $accountNumber", "GEEX001"))


        if (bank.accountNumber == null) {
            throw NoSuchElementException("accountNumber: ${bank.accountNumber} is not present")
        }
        if (bank.accountName == null) {
            throw NoSuchElementException("accountName: ${bank.accountName} is not present")
        }
        if (bank.trust == 0.0) {
            throw NoSuchElementException("trust: ${bank.trust} is not preset")
        }
        if (bank.transactionFee == 0) {
            throw NoSuchElementException("transactionFee: ${bank.transactionFee} is not present")
        }

        banks.remove(currentBank)
        banks.add(bank)

        return bank
    }

    override fun changeBank(accountNumber: String, bank: Bank): Bank {

        val retrievedBank: Bank = banks.first { it.accountNumber == accountNumber }

        if(bank.accountNumber != null) retrievedBank.accountNumber = bank.accountNumber
        if( bank.accountName != null) retrievedBank.accountName = bank.accountName
        if( bank.trust != 0.0) retrievedBank.trust = bank.trust
        if(  bank.transactionFee != 0) retrievedBank.transactionFee = bank.transactionFee

        return retrievedBank;
    }

    override fun deleteBank(accountNumber: String): Bank {
        TODO("Not yet implemented")
    }
}