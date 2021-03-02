package com.zeecoder.ktutorials.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest{

    private val mockDataSource = MockBankDataSource()

    @Test
    fun `should provide a collections of banks`(){
        //when
        val banks = mockDataSource.retrieveBanks()
        
        //then
        assertThat(banks.size).isGreaterThanOrEqualTo(3)

    }
    
    @Test
    fun `should provide mock data`(){
        //when
        val banks = mockDataSource.retrieveBanks()

        //then
        assertThat(banks).allMatch { it.accountNumber?.isNotBlank()!! }  //todo operator !!  //allMatch - все должны подпадать под условие
        assertThat(banks).anyMatch { it.trust != 0.0}
        assertThat(banks).anyMatch { it.transactionFee != 0}         //anyMatch - какой либо должен попадать под условие

    }
}