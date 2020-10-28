package br.com.arquerosdev.retrofit.service;

import android.util.Log
import br.com.arquerosdev.Prefs
import br.com.arquerosdev.model.ModelEscolaridade
import br.com.arquerosdev.model.ModelPasta
import br.com.arquerosdev.model.ModelProfissao
import br.com.arquerosdev.model.ModelUsuario
import br.com.arquerosdev.retrofit.RetrofitInitializer
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Date
import java.sql.Timestamp
import java.sql.Types.TIMESTAMP
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

class APIsWebClient {
    fun listProfissoes(res: CallbackResponse<List<ModelProfissao>>){
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

    fun listEscolaridade(res: CallbackResponse<List<ModelEscolaridade>>){
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
                    callbackResponse.error("CPF ou email já cadastrados")//gson.toJson(response.errorBody())
                }
            }

            override fun onFailure(call: Call<String?>?, t: Throwable?) {
                Log.i("teste", t.toString())
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
                        Log.e("Error",e.toString())
                    }
                }else{
                    callbackResponse.error("Login inválido!")//gson.toJson(response.errorBody())
                }
            }

            override fun onFailure(call: Call<String?>?, t: Throwable?) {
                Log.i("teste", t.toString())
            }
        })
    }

    fun listPastas(res: CallbackResponse<JsonObject>){
        val call = RetrofitInitializer().apisService().listPastas()
        call.enqueue(object: Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>?,
                                    response: Response<JsonObject?>?) {
                response?.body()?.let {
                    res.sucess(it)
                }
            }

            override fun onFailure(call: Call<JsonObject?>?,
                                   t: Throwable?) {
                Log.e("onFailure error", "error "+t.toString())
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
                            0,
                            response.body()?.get("id_pasta").toString().toDouble().roundToInt(),
                            response.body()?.get("id_usuario").toString().toDouble().roundToInt(),
                            response.body()?.get("nome").toString(),
                            response.body()?.get("descricao").toString(),
                            response.body()?.get("categorias").toString(),
                            response.body()?.get("discussao").toString(),
                            response.body()?.get("localizacao").toString(),
                            response.body()?.get("criado_em").toString(),
                            response.body()?.get("homologada_em").toString(),
                            response.body()?.get("deletado_em").toString()
                        )

                        callbackResponse.sucess(pasta)
                    }catch (e:Exception){
                        Log.e("Error",e.toString())
                    }
                }
            }

            override fun onFailure(call: Call<JsonObject?>?, t: Throwable?) {
                Log.i("teste", t.toString())
            }
        })
    }
}
