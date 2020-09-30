package br.com.arquerosdev.retrofit

import br.com.arquerosdev.retrofit.service.ProfissoesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class RetrofitInitializer {

    // Exemplo de uma property
    private val retrofit =  Retrofit.Builder()
            //.baseUrl("http://backendcliente-env.eba-bpyvbu54.us-east-1.elasticbeanstalk.com/")
            .baseUrl("http://10.0.2.2:8082/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    /** Exemplo de uma Single-Expression function
     *  nesse tipo implementação, o retorno ProfissoesService explícito se torna opcional
     *  Original
     *  fun noteService(): ProfissoesService {
     *   return retrofit.create(ProfissoesService::class.java)
     *  }
     */
    //TODO: FALTA CONEXAO COM A API
    fun profissoesService() = retrofit.create(ProfissoesService::class.java)

}