package br.com.arquerosdev.retrofit.service;

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import br.com.arquerosdev.model.ModelProfissao
import br.com.arquerosdev.retrofit.RetrofitInitializer
import br.com.arquerosdev.viewmodel.ProfisaoViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfissoesWebClient {
    fun list(res: ProfissoesResponse){
        // Lista
        val call = RetrofitInitializer().profissoesService().list()
        call.enqueue(object: Callback<List<ModelProfissao>?> {
            override fun onResponse(call: Call<List<ModelProfissao>?>?,
            response: Response<List<ModelProfissao>?>?) {
                response?.body()?.let {
                    res.sucess(it)
                }
            }

            override fun onFailure(call: Call<List<ModelProfissao>?>?,
            t: Throwable?) {
                Log.e("onFailure error", "error")
            }
        })
    }
}
