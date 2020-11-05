package br.com.arquerosdev.retrofit.service

import android.util.Log
import br.com.arquerosdev.Prefs
import br.com.arquerosdev.model.ModelEscolaridade
import br.com.arquerosdev.model.ModelProfissao
import br.com.arquerosdev.retrofit.RetrofitInitializer
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*

interface APIsService {
    @GET("profissoes")
    fun listProfissao(): Call<List<ModelProfissao>>

    @GET("escolaridades")
    fun listEscolaridade(): Call<List<ModelEscolaridade>>

    @Headers("Content-Type: application/json")
    @POST("usuarios")
    fun insertUsuarios(@Body usuario: String): Call<String>

    @Headers("Content-Type: application/json")
    @POST("login")
    fun loginUsuario(@Body login: JsonObject): Call<String>

    @POST("enderecos")
    fun insertEndereco(@Body enderecos: String): Call<String>

    @GET("pastas")
    fun listPastas(@Header("Authorization") token: String?): Call<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("pastas")
    fun criarPastas(@Header("Authorization") token: String?, @Body pastas: String): Call<JsonObject>

    @GET("pastas/{idPasta}/estudos")
    fun listEstudos(@Header("Authorization") token: String?, @Path("idPasta") idPasta: String?): Call<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("pastas/{idPasta}/estudos")
    fun criarEstudo(@Header("Authorization") token: String?, @Body estudo: String, @Path("idPasta") idPasta: String?): Call<JsonObject>
}