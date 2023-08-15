package com.example.orangetask.news.networking

import com.example.orangetask.models.Article
import com.example.orangetask.utils.Resource
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException

@RunWith(JUnit4::class)
class ResponseHandlerTest {


    @Test
    fun `when exception code is 401 then return unauthorised`() {
        val httpException = HttpException(Response.error<Article>(401, mock()))
        assertEquals("Unauthorised", "no data")
    }

    @Test
    fun `when timeout then return timeout error`() {
        val socketTimeoutException = SocketTimeoutException()
        assertEquals("Timeout", "time out ")
    }
}