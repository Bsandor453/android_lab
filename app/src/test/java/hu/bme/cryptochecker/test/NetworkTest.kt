package hu.bme.cryptochecker.test

import com.google.common.truth.Truth.assertThat
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import hu.bme.cryptochecker.mock.network.MockServer
import hu.bme.cryptochecker.modules.network.CryptoApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkTest {

    private lateinit var server: MockWebServer
    private lateinit var cryptoApi: CryptoApi

    @Before
    fun setUp() {
        server = MockServer.server
        cryptoApi = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoApi::class.java)
    }

    private fun enqueueMockResponse() {
        javaClass.classLoader?.let {
            val mockResponse = MockResponse()
            mockResponse.setBody("[{\"response\": \"success\"}]")
            server.enqueue(mockResponse)
        }
    }

    @Test
    fun testResponseIsReceived() {
        runBlocking {
            // Prepare fake response
            enqueueMockResponse()
            // Send Request to the MockServer
            val responseBody = cryptoApi.getCoinsMarkets("usd").body()
            // Request received by the mock server
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/coins/markets?vs_currency=usd")
        }
    }

    @After
    fun tearDown() {
        MockServer.server.shutdown()
    }

}