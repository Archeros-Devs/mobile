package br.com.arquerosdev.retrofit.service

import br.com.arquerosdev.model.ModelEscolaridade
import br.com.arquerosdev.model.ModelProfissao
import retrofit2.Call
import retrofit2.http.GET

interface APIsService {
    @GET("profissoes")
    fun listProfissao(): Call<List<ModelProfissao>>

    @GET("escolaridades")
    fun listEscolaridade(): Call<List<ModelEscolaridade>>
}