package br.com.arquerosdev

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import br.com.arquerosdev.model.ModelProfissao
import br.com.arquerosdev.retrofit.service.ProfissoesWebClient
import br.com.arquerosdev.viewmodel.ProfisaoViewModel


class SplashActivity : BaseActivity(), AdapterView.OnItemSelectedListener {

    //TODO: move para a dashboard
    //private lateinit var usuarioViewModel: UsuarioViewModel

    private var sessaoUsuario: Boolean = false
    private var TAG:String = "SplashActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //val profissoes: List<ModelProfissao> = it
        val profissaoViewModel: ProfisaoViewModel = ViewModelProvider(this)
            .get(ProfisaoViewModel::class.java)
        profissaoViewModel.insert(profissoes)

        ProfissoesWebClient().list()

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