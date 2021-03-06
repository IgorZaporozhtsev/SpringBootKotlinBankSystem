package com.zeecoder.ktutorials.service

import com.zeecoder.ktutorials.datasource.BankDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class BankServiceTest{

    private val dataSource: BankDataSource = mockk(relaxed = true)
    //relaxed - true мы не используем given птому что нам не важно поведение given в методе every
    // relaxed - возвращает emptyList в нашем случае

    private val bankService = BankServiceImpl(dataSource)

    @Test
    fun `should call its data source to retrieve banks`(){
        //given
        //every { dataSource.retrieveBanks() } returns emptyList()

        //when
        bankService.getBanks()

        //then
        verify(exactly = 1) { dataSource.retrieveBanks() }
    }
}