package com.lekaha.simpletube

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.HashMap
import java.util.TreeMap
import okio.Buffer

class OfflineRequestMockInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        var path = request.url().encodedPath()
        path = REQ_DIR + "/" + request.method() + "/" + path.replaceFirst("/".toRegex(), "")
        val stream = context.assets.open(path + ".json")

        val json = parseStream(stream)
        val errorJson = "{\"errors\":[{\"code\":400,\"message\":\"Failed\"}]}"

        val retMap = Gson().fromJson<Map<String, Any>>(
            json, object : TypeToken<TreeMap<String, Any>>() {}.type
        )

        var queryMap: MutableMap<String, Any> = TreeMap()

        if (request.method() == "GET") {
            val url = request.url()
            for (i in 0 until url.querySize()) {
                queryMap[url.queryParameterName(i)] = url.queryParameterValue(i)
            }

        } else {
            if (request.body() is FormBody) {
                val body = request.body() as FormBody?
                if (body != null) {
                    for (i in 0 until body.size()) {
                        queryMap[body.encodedName(i)] = body.encodedValue(i)
                    }
                }
            } else {
                queryMap = Gson().fromJson<Map<String, Any>>(
                    bodyToString(request), object : TypeToken<HashMap<String, Any>>() {

                    }.type
                ).toMutableMap()
            }
        }

        return if (validate(retMap, queryMap)) {
            chain.proceed(request)
        } else Response.Builder()
            .body(ResponseBody.create(MEDIA_JSON, errorJson))
            .request(chain.request())
            .message("Server Error")
            .protocol(Protocol.HTTP_2)
            .code(500)
            .build()

    }

    private fun equal(left: Any, right: Any): Boolean {
        if (left is Number && right is Number) {
            return left.toByte() == right.toByte()
        }

        return if (left is String && right is String) {
            left == right
        } else left is Boolean && right is Boolean
                && left === right

    }

    private fun validate(correctness: String, test: String): Boolean {
        return equal(correctness, test)
    }

    private fun validate(
        correctness: Map<String, Any>,
        test: Map<String, Any>
    ): Boolean {

        if (correctness.size > test.size) {
            return false
        }

        for ((key, value) in correctness) {
            if (value is Map<*, *>) {
                // deeper validate
                return validate(
                    value as Map<String, Any>,
                    test[key] as Map<String, Any>
                )
            } else if (value is List<*>) {
                return value == test[key] as List<*>
            } else {
                if (!equal(test[key]!!, value)) {
                    Log.e(
                        "Offline", "request validate failed: parameter '" + key
                                + "' (" + test[key] + " != " + value + ")"
                    )
                    return false
                }
            }
        }

        return true
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
        private val REQ_DIR = "request"

        private fun bodyToString(request: Request): String {

            try {
                val copy = request.newBuilder().build()
                val buffer = Buffer()
                copy.body().writeTo(buffer)
                return buffer.readUtf8()
            } catch (e: IOException) {
                return "did not work"
            }

        }
    }
}
