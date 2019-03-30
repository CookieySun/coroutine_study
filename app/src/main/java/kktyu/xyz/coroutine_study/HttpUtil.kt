package kktyu.xyz.coroutine_study

import okhttp3.OkHttpClient
import okhttp3.Request

class HttpUtil {
    //叩きたいREST APIのURLを引数とします
    fun httpGET1(url: String): String? {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        val response = client.newCall(request).execute()
        return response.body()?.string()
    }
}