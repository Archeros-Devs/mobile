package br.com.arquerosdev.retrofit.service

import br.com.arquerosdev.model.ModelProfissao
import retrofit2.Call
import retrofit2.http.GET

interface ProfissoesService {
    @GET("profissoes")
    fun list(): Call<List<ModelProfissao>>
}