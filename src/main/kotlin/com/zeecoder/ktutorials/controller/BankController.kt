package com.zeecoder.ktutorials.controller

import com.zeecoder.ktutorials.model.Bank
import com.zeecoder.ktutorials.service.BankService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/banks")
class BankController(@Autowired private val service: BankService) {



    @GetMapping
    fun getBanks():Collection<Bank> = service.getBanks()

    @GetMapping("/{accountNumber}")
    fun getBankById(@PathVariable accountNumber: String): Bank? = service.getBank(accountNumber);
}