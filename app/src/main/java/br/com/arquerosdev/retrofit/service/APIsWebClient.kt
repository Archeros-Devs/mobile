package br.com.arquerosdev.retrofit.service;

import android.util.Log
import br.com.arquerosdev.model.ModelEscolaridade
import br.com.arquerosdev.model.ModelProfissao
import br.com.arquerosdev.retrofit.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APIsWebClient {
    fun listProfissoes(res: ProfissoesResponse){
        // Lista
        val call = RetrofitInitializer().apisService().listProfissao()
        call.enqueue(object: Callback<List<ModelProfissao>?> {
            override fun onResponse(call: Call<List<ModelProfissao>?>?,
                                    response: Response<List<ModelProfissao>?>?) {
                response?.body()?.let {
                    res.sucess(it)
                }
            }

            override fun onFailure(call: Call<List<ModelProfissao>?>?,
                                   t: Throwable?) {
                Log.e("onFailure error", "error "+t.toString())
            }
        })
    }

    fun listEscolaridade(res: EscolaridadeResponse){
        // Lista
        val call = RetrofitInitializer().apisService().listEscolaridade()
        call.enqueue(object: Callback<List<ModelEscolaridade>?> {
            override fun onResponse(call: Call<List<ModelEscolaridade>?>?,
                                    response: Response<List<ModelEscolaridade>?>?) {
                response?.body()?.let {
                    res.sucess(it)
                }
            }

            override fun onFailure(call: Call<List<ModelEscolaridade>?>?,
                                   t: Throwable?) {
                Log.e("onFailure error", "error "+t.toString())
            }
        })
    }
}
