package com.jeeraphan.manow.utils

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.util.concurrent.TimeUnit

class RequestDispatcher : Dispatcher() {
    companion object {
        const val URL_TEST = "/top-headlines?sources=abc-news&apiKey=41d2725710ae481795ff89337da615fe"
    }

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            URL_TEST -> createMockedResponse("feed_test.json")
            else -> createMockedResponse(httpCode = 404)
        }
    }
}

fun createMockedResponse(mockedBodyPath: String? = null, httpCode: Int = 200, delayMills: Long = 500): MockResponse {
    return MockResponse()
            .setBodyDelay(delayMills, TimeUnit.MILLISECONDS)
            .setResponseCode(httpCode)
            .apply {
                if (mockedBodyPath != null) {
                    val body = readFromFile<String>(mockedBodyPath)
                    setBody(body)
                }
            }
}

inline fun <reified T> Any.readFromFile(filePath: String): String {
    val input = javaClass.classLoader.getResourceAsStream(filePath)
    return String(input.readBytes())
}