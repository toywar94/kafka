package com.pratice.kafka.service

import com.pratice.kafka.data.SportsDataItem
import lombok.extern.slf4j.Slf4j
import org.apache.kafka.common.requests.DeleteAclsResponse.log
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Service
@Slf4j
class DataReceiverService(
    private val webClient: WebClient
) {

    fun callProcessData(): Flux<SportsDataItem> {
        return webClient
            .get()
            .uri("/process-data")
            .retrieve()
            .bodyToFlux(SportsDataItem::class.java)
    }

    fun callStreamingData(): Flux<SportsDataItem> {
        return webClient
            .get()
            .uri("/streaming-data-2")
            .retrieve()
            .bodyToFlux(SportsDataItem::class.java)
    }


    fun buffer100(): Flux<List<SportsDataItem>>{
        val sportsDataItems = callProcessData()
        val sportsDataBuffer100 = sportsDataItems.buffer(100)
//        log.info("buffer : ${sportsDataBuffer100.subscribe { a -> log.info("a ${a}") }}")
        return sportsDataBuffer100
    }

    fun concatMap(): Flux<SportsDataItem> {
        val sportsDataItems = callProcessData()

        return sportsDataItems
            .concatMap { item ->
                val resultMono = Mono.just(item).subscribeOn(Schedulers.parallel())
                resultMono
        }
        .doOnNext { result ->
            log.info("Processed Item: ${result}")
        }
    }

    fun filterWhen(): Flux<SportsDataItem> {
        val sportsDataItem = callProcessData()

        return sportsDataItem
            .filterWhen { item ->
                val resultMono = Mono.just(item.sportName == SportsDataItem.Sport.YOGA.name)
                    .subscribeOn(Schedulers.parallel())

                resultMono
            }
    }

}