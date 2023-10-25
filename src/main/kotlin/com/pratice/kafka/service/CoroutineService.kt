package com.pratice.kafka.service

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service
import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

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
//        job.cancel()
//        job.join()
        job.cancelAndJoin()
        println("main : Now i can quit.")
    }

    fun asyncConcurrent() = runBlocking {
        val time = measureTimeMillis {
            val one = async { doSomethingUsefulOne() }
            val two = async { doSomethingUsefulTwo() }
            println("the answer is ${one.await() + two.await()}")
        }
        println("Completed in ${time} ms")
    }

    suspend fun doSomethingUsefulOne(): Int {
        delay(1000L)
        return 13;
    }

    suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L)
        return 29;
    }

    fun simpleTest(){
        simple().forEach { value -> println(value) }
    }

    fun simple(): Sequence<Int> = sequence {
        for(i in 1..3){
            Thread.sleep(100L)
            yield(i)
        }
    }


    fun simpleTest2() = runBlocking<Unit> {
        simple2().forEach { value -> println(value) }
    }

    suspend fun simple2(): List<Int> {
        delay(1000)
        return listOf(1,2,3)
    }



}