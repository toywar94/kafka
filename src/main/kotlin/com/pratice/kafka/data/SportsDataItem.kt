package com.pratice.kafka.data

data class SportsDataItem(
    val id: Long,
    val sportName: String
){
    enum class Sport{
        SOCCER,
        BASKETBALL,
        SWIMMING,
        RUNNING,
        YOGA
    }
}
