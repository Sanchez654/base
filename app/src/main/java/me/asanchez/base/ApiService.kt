package me.asanchez.base

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("getItems")
    fun getItems(): Call<List<Item>>

    @FormUrlEncoded
    @POST("actualizar.php")
    fun actualizarDatos(
        @Field("nombre") nombre: String,
        @Field("email") email: String,
        @Field("contacto") contacto: String,
        @Field("direccion") direccion: String
    ): Call<ResponseBody>

    companion object {
        fun create(baseUrl: String): ApiService {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}

