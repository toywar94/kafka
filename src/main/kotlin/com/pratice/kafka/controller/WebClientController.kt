package com.pratice.kafka.controller

import com.pratice.kafka.data.SportsDataItem
import com.pratice.kafka.service.DataProcessorService
import com.pratice.kafka.service.DataReceiverService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class WebClientController(
    private val dataProcessorService: DataProcessorService,
    private val dataReceiverService: DataReceiverService
) {


    //client-server 1
    @GetMapping("/client-server-1")
    fun buffer100(): Flux<List<SportsDataItem>> {
        return dataReceiverService.buffer100()
    }

    //client-server 2
    @GetMapping("/client-server-2")
    fun concatMap(): Flux<SportsDataItem> {
        return dataReceiverService.concatMap()
    }

    //client-server 2
    @GetMapping("/client-server-3")
    fun filterWhen(): Flux<SportsDataItem> {
        return dataReceiverService.filterWhen()
    }

    //서버-1
    @GetMapping("/process-data")
    fun processData(): Flux<SportsDataItem> {
        return dataProcessorService.generateData()
    }

    @GetMapping("/streaming-data-1")
    fun streamingData_1(): Flux<Int> {
        return dataProcessorService.streamingData()
    }

    @GetMapping("/streaming-data-2")
    fun streamingData_2(): List<SportsDataItem> {
        return dataProcessorService.streamingData_2()
    }



}