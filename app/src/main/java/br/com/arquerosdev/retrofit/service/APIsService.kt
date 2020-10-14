package br.com.arquerosdev.retrofit.service

import br.com.arquerosdev.model.ModelEscolaridade
import br.com.arquerosdev.model.ModelProfissao
import br.com.arquerosdev.model.ModelUsuario
import com.google.gson.annotations.JsonAdapter
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIsService {
    @GET("profissoes")
    fun listProfissao(): Call<List<ModelProfissao>>

    @GET("escolaridades")
    fun listEscolaridade(): Call<List<ModelEscolaridade>>

    @Headers("Content-Type: application/json")
    @POST("usuarios")
    fun insertUsuarios(@Body usuario: String): Call<String>

    @POST("enderecos")
    fun insertEndereco(@Body enderecos: String): Call<String>
}