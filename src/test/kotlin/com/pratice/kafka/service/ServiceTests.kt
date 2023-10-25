package com.pratice.kafka.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
class ServiceTests {

    @Autowired
    private lateinit var coroutineService: CoroutineService;

    @Test
    fun test_coroutine_launch(){
        coroutineService.launch()
    }

    @Test
    fun test_coroutine_runBlocking(){
        coroutineService.runBlocking()
    }

    @Test
    fun test_coroutine_runBlocking2(){
        coroutineService.concurrency()
    }

    @Test
    fun test_coroutine_jobJoin(){
        coroutineService.jobJoin()
    }

    @Test
    fun test_coroutine_lightWeight(){
        coroutineService.lightWeight()
    }

    @Test
    fun test_coroutine_cancelling(){
        coroutineService.cancelling()
    }

    @Test
    fun test_async_Concurrent(){
        coroutineService.asyncConcurrent()
    }

    @Test
    fun test_simple(){
        coroutineService.simpleTest()
    }

    @Test
    fun test_simple2(){
        coroutineService.simpleTest2()
    }


}