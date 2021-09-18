package com.example.catganisation.data.remote.mock

import okhttp3.*
import okio.Buffer


class LoggingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response: String
        val code: Int

        if (isValidAuth(chain.request().body())) {
            response = "{\"status\": " + 200 + ",\"message\": \"OK\"}"
            code = 200

        } else {
            response = "Invalid credentials"
            code = 401
        }

        return Response.Builder()
            .code(code)
            .message(response)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_1)
            .body(ResponseBody.create(MediaType.parse("application/json"), response))
            .addHeader("content-type", "application/json")
            .build()
    }

    private fun isValidAuth(request: RequestBody?): Boolean {
        val buffer = Buffer()
        request?.writeTo(buffer)
        val body = buffer.readUtf8()
        return body == "username=1&password=1"
    }
}