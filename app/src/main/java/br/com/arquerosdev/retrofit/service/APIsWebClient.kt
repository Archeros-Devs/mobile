package br.com.arquerosdev.retrofit.service;

import android.util.Log
import android.widget.Toast
import br.com.arquerosdev.model.ModelEscolaridade
import br.com.arquerosdev.model.ModelProfissao
import br.com.arquerosdev.model.ModelUsuario
import br.com.arquerosdev.retrofit.RetrofitInitializer
import com.google.gson.Gson
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

    fun cadastroUsuario(usuario:ModelUsuario, callbackResponse: CallbackResponse<ModelUsuario>){
        val gson = Gson()
        val jsonUsuario: String = gson.toJson(usuario)

        val call = RetrofitInitializer().apisService().insertUsuarios(jsonUsuario)
        call.enqueue(object: Callback<String?> {
            override fun onResponse(call: Call<String?>?, response: Response<String?>?) {

                if(response!!.code() == 200){
                    var resUsuario:ModelUsuario? = null
                    try{
                        val gson = Gson()
                        resUsuario = gson.fromJson(response.body(), ModelUsuario::class.java)
                    }catch (e:Exception){
                        Log.e("Error",e.toString())
                        callbackResponse.error("Já cadastrado!")
                    }
                    callbackResponse.sucess(resUsuario!!)
                }else{
                    callbackResponse.error("Hiiii algo deu errado!")
                }
            }

            override fun onFailure(call: Call<String?>?, t: Throwable?) {
                Log.i("teste", t.toString())
            }
        })
    }

}
