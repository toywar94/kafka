package com.pratice.kafka.service

import com.pratice.kafka.data.SportsDataItem
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

@Service
class DataProcessorService(

) {

    fun generateData(): Flux<SportsDataItem> {
        return Flux.range(1, 200)
            .flatMap { id ->
                val randomSports = SportsDataItem.Sport.values().random()
                val dataItem = SportsDataItem(id.toLong(), randomSports.name)
                val delayDuration =
                    if (id % 20 == 0) Duration.ofSeconds(1)
                    else Duration.ofMillis(50)

                Mono.just(dataItem).delayElement(delayDuration)

            }.log()
    }

    fun streamingData(): Flux<Int> {
        return Flux.range(1, 200)
            .delayElements(Duration.ofMillis(100))
            .log()
    }

    fun streamingData_2(): List<SportsDataItem> {
        val sportsDataList = mutableListOf<SportsDataItem>()

        SportsDataItem.Sport.values().forEachIndexed { index, sport ->
            val id = index.toLong() + 1
            val sportsDataItem = SportsDataItem(id, sport.name)
            sportsDataList.add(sportsDataItem)

        }
        return sportsDataList
    }

}
