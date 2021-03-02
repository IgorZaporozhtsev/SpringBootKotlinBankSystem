package com.zeecoder.ktutorials.controller

import com.zeecoder.ktutorials.model.Bank
import com.zeecoder.ktutorials.service.BankServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/banks")
class BankController(@Autowired private val service: BankServiceImpl) {

    @GetMapping
    fun getBanks():Collection<Bank> = service.getBanks()

    @GetMapping("/{accountNumber}")
    fun getBankById(@PathVariable accountNumber: String): Bank? = service.getBank(accountNumber);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody bank: Bank): Bank = service.addBank(bank)

    @PatchMapping("/{accountNumber}")
    fun changeBank(@PathVariable accountNumber: String, @RequestBody bank: Bank): Bank {
        return service.changeBank(accountNumber, bank)
    }

    @PutMapping("/{accountNumber}")
    fun updateBank(@PathVariable accountNumber: String, @RequestBody bank: Bank): Bank =
            service.updateBank(accountNumber, bank)

    @DeleteMapping
    fun deleteBank(@PathVariable accountNumber: String): Bank = service.deleteBank(accountNumber)
}