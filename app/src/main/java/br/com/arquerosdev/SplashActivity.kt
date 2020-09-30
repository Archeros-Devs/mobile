package br.com.arquerosdev

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import br.com.arquerosdev.model.ModelProfissao
import br.com.arquerosdev.retrofit.RetrofitInitializer
import br.com.arquerosdev.viewmodel.ProfisaoViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BaseActivity(), AdapterView.OnItemSelectedListener {

    //TODO: move para a dashboard
    //private lateinit var usuarioViewModel: UsuarioViewModel

    private var sessaoUsuario: Boolean = false
    private var TAG:String = "SplashActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Lista
        val call = RetrofitInitializer().profissoesService().list()
        call.enqueue(object: Callback<List<ModelProfissao>?> {
            override fun onResponse(call: Call<List<ModelProfissao>?>?,
                                    response: Response<List<ModelProfissao>?>?) {
                response?.body()?.let {
                    val profissoes: List<ModelProfissao> = it

                    Log.e(TAG, "foiiii")
                    val profissaoViewModel: ProfisaoViewModel = ViewModelProvider(this@SplashActivity)
                        .get(ProfisaoViewModel::class.java)
                    profissaoViewModel.insert(profissoes)
                }
            }

            override fun onFailure(call: Call<List<ModelProfissao>?>?,
                                   t: Throwable?) {
                Log.e(TAG, "error")
            }
        })

        if(sessaoUsuario){
            Log.i(TAG,"chama home")
        }else{
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    override fun getUsuarioLogado() {
        super.getUsuarioLogado()

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

}