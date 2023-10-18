package com.pratice.kafka.controller

import com.pratice.kafka.data.SportsDataItem
import com.pratice.kafka.service.CoroutineService
import com.pratice.kafka.service.DataProcessorService
import com.pratice.kafka.service.DataReceiverService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class CoroutineController(
    private val coroutineService: CoroutineService
) {

    @GetMapping("/test")
    fun test() {
        coroutineService.lightWeight()
    }
}