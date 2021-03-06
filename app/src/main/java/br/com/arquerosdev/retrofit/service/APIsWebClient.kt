package br.com.arquerosdev.retrofit.service;

import android.util.Log
import br.com.arquerosdev.Prefs
import br.com.arquerosdev.model.*
import br.com.arquerosdev.retrofit.RetrofitInitializer
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt

class APIsWebClient {
    fun listProfissoes(callbackResponse: CallbackResponse<List<ModelProfissao>>){
        // Lista
        val call = RetrofitInitializer().apisService().listProfissao()
        call.enqueue(object: Callback<List<ModelProfissao>?> {
            override fun onResponse(call: Call<List<ModelProfissao>?>?,
                                    response: Response<List<ModelProfissao>?>?) {
                response?.body()?.let {
                    callbackResponse.sucess(it)
                }
            }

            override fun onFailure(call: Call<List<ModelProfissao>?>?,
                                   t: Throwable?) {
                Log.e("onFailure", t.toString())
                callbackResponse.error(t.toString())
            }
        })
    }

    fun listEscolaridade(callbackResponse: CallbackResponse<List<ModelEscolaridade>>){
        // Lista
        val call = RetrofitInitializer().apisService().listEscolaridade()
        call.enqueue(object: Callback<List<ModelEscolaridade>?> {
            override fun onResponse(call: Call<List<ModelEscolaridade>?>?,
                                    response: Response<List<ModelEscolaridade>?>?) {
                response?.body()?.let {
                    callbackResponse.sucess(it)
                }
            }

            override fun onFailure(call: Call<List<ModelEscolaridade>?>?,
                                   t: Throwable?) {
                Log.e("onFailure", t.toString())
                callbackResponse.error(t.toString())
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
                        val gsonUsuario = Gson()
                        resUsuario = gsonUsuario.fromJson(response.body(), ModelUsuario::class.java)
                    }catch (e:Exception){
                        Log.e("onFailure",e.toString())
                        callbackResponse.error("Já cadastrado!")
                    }
                    callbackResponse.sucess(resUsuario!!)
                }else{
                    callbackResponse.error("CPF ou email já cadastrados")//gson.toJson(response.errorBody())
                }
            }

            override fun onFailure(call: Call<String?>?, t: Throwable?) {
                Log.e("onFailure", t.toString())
                callbackResponse.error(t.toString())
            }
        })
    }

    fun loginUsuario(login:JsonObject, callbackResponse: CallbackResponse<Map<String, Any>>){
        val call = RetrofitInitializer().apisService().loginUsuario(login)
        call.enqueue(object: Callback<String?> {
            override fun onResponse(call: Call<String?>?, response: Response<String?>?) {

                if(response!!.code() == 200){
                    try{
                        val gson = Gson()
                        val typeResponse = object : TypeToken<Map<String, Any>>() {}.type
                        val jbResponse: Map<String, Any> = gson.fromJson(response.body(), typeResponse)
                        callbackResponse.sucess(jbResponse)
                    }catch (e:Exception){
                        Log.e("onFailure",e.toString())
                    }
                }else{
                    callbackResponse.error("Login inválido!")//gson.toJson(response.errorBody())
                }
            }

            override fun onFailure(call: Call<String?>?, t: Throwable?) {
                Log.e("onFailure", t.toString())
                callbackResponse.error(t.toString())
            }
        })
    }

    fun listPastas(callbackResponse: CallbackResponse<JsonObject>){
        val call = RetrofitInitializer().apisService().listPastas("Bearer "+Prefs.getString("token"))
        call.enqueue(object: Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>?,
                                    response: Response<JsonObject?>?) {
                response?.body()?.let {
                    callbackResponse.sucess(it)
                }
            }

            override fun onFailure(call: Call<JsonObject?>?,
                                   t: Throwable?) {
                Log.e("onFailure", t.toString())
                callbackResponse.error(t.toString())
            }
        })
    }

    fun listEstudo(idPasta: String, callbackResponse: CallbackResponse<JsonObject>){
        val call = RetrofitInitializer().apisService().listEstudos("Bearer "+Prefs.getString("token"), idPasta)
        call.enqueue(object: Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>?,
                                    response: Response<JsonObject?>?) {
                response?.body()?.let {
                    callbackResponse.sucess(it)
                }
            }

            override fun onFailure(call: Call<JsonObject?>?,
                                   t: Throwable?) {
                Log.e("onFailure", t.toString())
                callbackResponse.error(t.toString())
            }
        })
    }

    fun criarPasta(pasta: String ,callbackResponse: CallbackResponse<ModelPasta>){
        val call = RetrofitInitializer().apisService().criarPastas("Bearer "+Prefs.getString("token"), pasta)
        call.enqueue(object: Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>?, response: Response<JsonObject?>?) {

                if(response!!.code() == 200){
                    try{
                        
                        val pasta = ModelPasta(
                            response.body()?.get("id_origem").toString().toLong(),
                            response.body()?.get("id_pasta").toString().toDouble().roundToInt(),
                            response.body()?.get("id_usuario").toString().toDouble().roundToInt(),
                            response.body()?.get("nome").toString(),
                            response.body()?.get("descricao").toString(),
                            response.body()?.get("categorias").toString(),
                            response.body()?.get("discussao").toString(),
                            response.body()?.get("localizacao").toString(),
                            response.body()?.get("criado_em").toString(),
                            response.body()?.get("homologada_em").toString(),
                            response.body()?.get("deletado_em").toString(),
                            response.body()?.get("latitude").toString().toDouble(),
                            response.body()?.get("longitude").toString().toDouble(),
                            response.body()?.get("criador").toString()
                        )

                        callbackResponse.sucess(pasta)
                    }catch (e:Exception){
                        Log.e("onFailure",e.toString())
                        callbackResponse.error(e.toString())
                    }
                }
            }

            override fun onFailure(call: Call<JsonObject?>?, t: Throwable?) {
                Log.e("onFailure", t.toString())
                callbackResponse.error(t.toString())
            }
        })
    }

    fun criarEstudo(estudo: String , idPasta: Int,callbackResponse: CallbackResponse<ModelEstudo>){
        val call = RetrofitInitializer().apisService().criarEstudo("Bearer "+Prefs.getString("token"), estudo, idPasta.toString())
        call.enqueue(object: Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>?, response: Response<JsonObject?>?) {

                if(response!!.code() == 200){
                    try{

                        val pasta = ModelEstudo(
                            response.body()?.get("id_origem").toString().toDouble().toLong(),
                            response.body()?.get("id_mensagem").toString().toDouble().roundToInt(),
                            response.body()?.get("id_usuario").toString().toDouble().roundToInt(),
                            response.body()?.get("id_pasta").toString().toDouble().roundToInt(),
                            response.body()?.get("tipo").toString().toDouble().roundToInt(),
                            response.body()?.get("mensagem").toString(),
                            response.body()?.get("criado_em").toString(),
                            if (response.body()?.get("deletado_em")!!.isJsonNull) "" else response.body()?.get("deletado_em").toString(),
                            response.body()?.get("usuario")!!.asJsonObject.get("nome").asString
                        )

                        callbackResponse.sucess(pasta)
                    }catch (e:Exception){
                        Log.e("onFailure",e.toString())
                        callbackResponse.error(e.toString())
                    }
                }
            }

            override fun onFailure(call: Call<JsonObject?>?, t: Throwable?) {
                Log.e("onFailure", t.toString())
                callbackResponse.error(t.toString())
            }
        })
    }
}
