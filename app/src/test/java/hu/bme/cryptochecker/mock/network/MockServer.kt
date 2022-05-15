package hu.bme.cryptochecker.mock.network

import okhttp3.mockwebserver.MockWebServer

class MockServer {
    companion object {
        val server = MockWebServer()
    }
}