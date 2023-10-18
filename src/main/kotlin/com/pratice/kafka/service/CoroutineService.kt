package com.pratice.kafka.service

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service
import kotlinx.coroutines.*
import kotlin.concurrent.thread

@Service
class CoroutineService(

) {

    fun launch() {
        GlobalScope.launch {
            delay(1000L)
            println("World")
        }
        println("Coroutine")
        Thread.sleep(1000L)
    }

    fun runBlocking() = runBlocking {
        GlobalScope.launch {
            delay(1000L)
            println("World")
        }
        println("Coroutine")
        Thread.sleep(1000L)

    }

    //job.join이 끝날 때까지 현재 코루틴에게 기다리라고 명령하는 함수.
    fun jobJoin() = runBlocking {
        val job = launch {
            delay(1000L)
            println("World!")
        }
        println("Hello")
        job.join()
        println("Done")
    }

    fun concurrency() = runBlocking{
        doWorld()
        println("Done!!!!")
    }

    suspend fun doWorld() = coroutineScope {
        launch {
            delay(2000L)
            println("World 2")
        }

        launch {
            delay(1000L)
            println("World 1")
        }

        println("Hello")
    }

    fun lightWeight() = runBlocking{
        repeat(100_000){
            launch {
                delay(1000L)
                print(".")
            }
//            thread {
//                Thread.sleep(1000L)
//                print(".")
//            }
        }
    }

    fun cancelling() = runBlocking{
        val job = launch {
            repeat(100) { i ->
                println("job : sleeping ${i}...")
                delay(500L)
            }
        }
        delay(1700L)
        println("main : i'm tried of waiting!")
        job.cancel()
        job.join()
        println("main : Now i can quit.")
    }




}