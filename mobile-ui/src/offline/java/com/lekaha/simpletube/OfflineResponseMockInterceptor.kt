package com.lekaha.simpletube

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class OfflineResponseMockInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        var path = request.url().encodedPath()
        path = RES_DIR + "/" + request.method() + "/" + path.replaceFirst("/".toRegex(), "")
        val stream = context.assets.open(path)

        val json = parseStream(stream)

        return Response.Builder()
            .body(ResponseBody.create(MEDIA_JSON, json))
            .request(chain.request())
            .message("OK")
            .protocol(Protocol.HTTP_2)
            .code(200)
            .build()
    }

    @Throws(IOException::class)
    private fun parseStream(stream: InputStream): String {
        val builder = StringBuilder()
        val inputStream = BufferedReader(InputStreamReader(stream, "UTF-8"))
        var line: String? = null

        while (inputStream.readLine()?.apply { line = this } != null) {
            builder.append(line)
        }

        inputStream.close()
        return builder.toString()
    }

    companion object {

        private val MEDIA_JSON = MediaType.parse("application/json")
        private val RES_DIR = "response"
    }
}
