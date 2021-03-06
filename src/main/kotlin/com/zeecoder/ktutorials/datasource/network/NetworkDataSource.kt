package com.zeecoder.ktutorials.datasource.network

import com.zeecoder.ktutorials.datasource.BankDataSource
import com.zeecoder.ktutorials.datasource.network.dto.BankList
import com.zeecoder.ktutorials.model.Bank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import java.io.IOException

@Repository("network")
class NetworkDataSource(
        @Autowired private val restTemplate: RestTemplate
) : BankDataSource {

    override fun retrieveBanks(): Collection<Bank> {
        val response: ResponseEntity<BankList> =
                restTemplate.getForEntity<BankList>("http://54.193.31.159/banks", BankList::class.java)

        return response.body?.results
                 ?: throw IOException("Could not fetch banks from the network")
    }

    override fun retrieveBank(accountNumber: String?): Bank? {
        TODO("Not yet implemented")
    }

    override fun createBank(bank: Bank): Bank {
        TODO("Not yet implemented")
    }

    override fun changeBank(accountNumber: String, bank: Bank): Bank {
        TODO("Not yet implemented")
    }

    override fun updateBank(accountNumber: String?, bank: Bank): Bank {
        TODO("Not yet implemented")
    }

    override fun deleteBank(accountNumber: String?) {
        TODO("Not yet implemented")
    }
}