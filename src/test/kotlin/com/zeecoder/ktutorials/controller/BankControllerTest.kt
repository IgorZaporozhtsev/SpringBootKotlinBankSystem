package com.zeecoder.ktutorials.controller

import com.ninjasquad.springmockk.MockkBean
import com.zeecoder.ktutorials.datasource.BankDataSource
import com.zeecoder.ktutorials.exceptions.BankException
import com.zeecoder.ktutorials.model.Bank
import com.zeecoder.ktutorials.util.ApiError
import io.mockk.every
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc //need this in Spring Boot test
internal class BankControllerTest{

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var bankDataSource: BankDataSource

    private val jsonMediaType = MediaType.APPLICATION_JSON

    private val baseUrl = "/api/banks"

    @Nested
    @DisplayName("getBanks()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {

        @Test
        fun `should return all banks`(){

            val banks = listOf(
                    Bank("111","Peter's account", 1.0, 17),
                    Bank("222", "Marry's account",2.0, 0),
                    Bank("333", "Yoda's account",3.0, 37))

            every { bankDataSource.retrieveBanks() } returns banks

            //when


            // then
            val mvcResult = mockMvc.get("$baseUrl")
            mvcResult
                    .andDo{ println() }
                    .andExpect {
                        status { isOk() }
                        content { contentType(jsonMediaType) }
                        jsonPath("$[0].accountName"){
                            value("Peter's account")
                        }
                    }
        }
    }

    @Nested
    @DisplayName("getBankById()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBankById {
        @Test
        fun `should return the bank with given account number`(){
            //given
            val bankAccount = Bank("333", "Yoda's account", 3.0, 37)
            every { bankDataSource.retrieveBank(bankAccount.accountNumber) } returns bankAccount

            //when
            val mvcResult = mockMvc.get("$baseUrl/${bankAccount.accountNumber}")

            //then
            mvcResult
                    .andDo { print() }
                    .andExpect {
                        status { isOk() }
                        content { contentType(jsonMediaType) }
                        jsonPath("$.accountName") { value("Yoda's account") }
                        jsonPath("$.trust") { value("3.0") }
                        jsonPath("$.transactionFee") { value("37") }
                    }
        }


        @Test
        fun `should return Bad Request if the account number does not exist`(){

            //given
            val accountNumber = "555"
            every { bankDataSource.retrieveBank(accountNumber) } throws BankException("Could no find a bank with accountNumber: $accountNumber")
            //when
            val mvcResult = mockMvc.get("$baseUrl/$accountNumber")

            //then
            val error = ApiError("Could no find a bank with accountNumber: $accountNumber")
            //todo look through all exception flow carefully
            mvcResult
                    .andDo { print() }
                    .andExpect {
                        status {
                            isNotFound()
                        }
                        content {
                            jsonPath("$.errorMessage") { value(error.errorMessage) }
                        }
                    }
        }
    }
    

}