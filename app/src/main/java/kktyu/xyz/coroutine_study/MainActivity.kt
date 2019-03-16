package kktyu.xyz.coroutine_study

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.eclipsesource.json.Json
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val URL = "http://weather.livedoor.com/forecast/webservice/json/v1?city=400040" //サンプルとしてライブドアのお天気Webサービスを利用します

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { onParallelGetButtonClick() }
    }

    //非同期処理でHTTP GETを実行します。
    private fun onParallelGetButtonClick() = GlobalScope.launch(Dispatchers.Main) {
        val http = HttpUtil()
        //Mainスレッドでネットワーク関連処理を実行するとエラーになるためBackgroundで実行
        withContext(Dispatchers.Default) { http.httpGET1(URL) }.let {
            //minimal-jsonを使って　jsonをパース
            val result = Json.parse(it).asObject()
            textView.text = result.get("description").asObject().get("text").asString()
        }
    }
}