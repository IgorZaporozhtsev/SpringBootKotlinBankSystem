package com.zeecoder.ktutorials.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.zeecoder.ktutorials.datasource.BankDataSource
import com.zeecoder.ktutorials.exceptions.ApiBankException
import com.zeecoder.ktutorials.model.Bank
import com.zeecoder.ktutorials.exceptions.ApiException
import io.mockk.every
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc //need this in Spring Boot test
internal class BankControllerTest @Autowired constructor(
        val mockMvc: MockMvc,
        val objectMapper: ObjectMapper
){



    @MockkBean
    lateinit var bankDataSource: BankDataSource

    private val jsonMediaType = MediaType.APPLICATION_JSON

    private val baseUrl = "/api/banks"

    @Nested
    @DisplayName("getBanks()")
    @TestInstance(Lifecycle.PER_CLASS)
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
    @TestInstance(Lifecycle.PER_CLASS)
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
            every { bankDataSource.retrieveBank(accountNumber) } throws ApiBankException("from Test Could no find a bank with accountNumber: $accountNumber", "GEEX001")
            //when
            val mvcResult = mockMvc.get("$baseUrl/$accountNumber")

            //then
            val exception = ApiException("User is not present. from Test Could no find a bank with accountNumber: $accountNumber")

            mvcResult
                    .andDo { print() }
                    .andExpect {
                        status {
                            isNotFound()
                        }
                        content {
                            jsonPath("$.errorMessage") { value(exception.errorMessage) }
                        }
                    }
        }
    }
    
    @Nested
    @DisplayName("addBank()")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostNewBank{

        @Test
        fun `should add new bank`(){
            //given
            val newBank = Bank("444", "Sara's", 32.3, 23)
            every { bankDataSource.createBank(newBank) } returns newBank
            
            //when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }

            //then
            performPost
                    .andDo { print() }
                    .andExpect {
                        status { isCreated() }
                        content {
                            contentType(jsonMediaType)
                            json(objectMapper.writeValueAsString(newBank))
                        }
                    }
            
        }
        
        @Test
        fun `should return BAD REQUEST if bank with guven account already exist`(){
            //given
            val invalidBank = Bank("444", "Sara's", 32.3, 23)
            every { bankDataSource.createBank(invalidBank) } throws ApiBankException(
                    "bank with number ${invalidBank.accountNumber} already exists", "GEEX002")
            
            //when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }
            
            //then
            performPost
                    .andDo { print() }
                    .andExpect {
                        status { isBadRequest() }
                    }
        }
    }
    
    @Nested
    @DisplayName("updateBank")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class UpdateBank{

        @Test
        fun `should update an existing bank`(){
            //given
            val existedBank = Bank("444", "Sara's", 32.3, 23)

            every { bankDataSource.updateBank(existedBank.accountNumber, existedBank) } returns existedBank

            //when
            val performPatch = mockMvc.put("$baseUrl/${existedBank.accountNumber}") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(existedBank)
            }

            //then
            performPatch
                    .andDo { print() }
                    .andExpect {
                        status { isOk() }
                        content {
                            contentType(jsonMediaType)
                            json(objectMapper.writeValueAsString(existedBank))
                        }
                    }


            every { bankDataSource.retrieveBank(existedBank.accountNumber) } returns existedBank

            mockMvc.get("$baseUrl/${existedBank.accountNumber}")
                    .andExpect { content { json( objectMapper.writeValueAsString(existedBank)) } }

        }
    }

    @Nested
    @DisplayName("deleteBank")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class DeleteBank{
        @Test
        fun `should delete bank by account number`(){
            val existedBank = Bank("555", "Kim's", 43.3, 52)
            //given
            val list = mutableListOf<Bank>()
            list.add(existedBank)

            // return existedBank when we call .deleteBank() method
            every { bankDataSource.deleteBank(existedBank.accountNumber) } returns Unit
            //when

            val performDelete = mockMvc.delete("$baseUrl/${existedBank.accountNumber}") {
                contentType = MediaType.APPLICATION_JSON
            }

            //then
            performDelete
                    .andDo { print() }
                    .andExpect {
                        status { isNoContent() }
                    }


            //given
            val apiBankException = ApiBankException("from Test Could no find a bank with accountNumber: ${existedBank.accountNumber}", "GEEX001")
            every { bankDataSource.retrieveBank(existedBank.accountNumber) } throws apiBankException

            //when
            mockMvc.get("$baseUrl/${existedBank.accountNumber}")
                    .andExpect { status { isNotFound() } }
        }
    }

}