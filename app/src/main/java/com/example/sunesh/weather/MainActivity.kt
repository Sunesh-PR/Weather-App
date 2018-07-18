package com.example.sunesh.weather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// ...



}
    fun btn(view: View) {
        val url:String = "https://api.openweathermap.org/data/2.5/weather?id=1267887&appid=21f0e2bbd1da56aad1f618440a12e904"

        var json:JsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,Response.Listener {respone ->

            try {
                val main:JSONObject = respone.getJSONObject("main")
                val array = respone.getJSONArray("weather")
                val obj = array.getJSONObject(0)
                val Weather: String = obj.getString("main")
                val description = obj.getString("description")
                var temp = main.getDouble("temp")
                var humidityval = main.getDouble("humidity")
                var pressureval = main.getDouble("pressure")
                val cityname = respone.getString("name")
                temp = temp-273.15
                temp=Math.round(temp*100.0)/100.0
                temperature.text=temp.toString()+" C"
                if(Weather=="Clouds"){
                    weather.text= "Cloudy"
                    backGround.setImageResource(R.drawable.cloudy)
                }
                humidity.text= "Humidity: "+humidityval.toString()
                pressure.text= "Pressure: "+pressureval.toString()

                city.text= cityname




            }catch(e: JSONException){
                e.printStackTrace()
            }

        },Response.ErrorListener {

        })
        val queue:RequestQueue = Volley.newRequestQueue(this)
        queue.add(json)

    }
}
