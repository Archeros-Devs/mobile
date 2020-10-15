package br.com.arquerosdev.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.arquerosdev.R
import br.com.arquerosdev.retrofit.service.APIsWebClient
import br.com.arquerosdev.viewmodel.UsuarioViewModel
import androidx.lifecycle.Observer
import br.com.arquerosdev.model.ModelUsuario
import br.com.arquerosdev.retrofit.service.CallbackResponse

class FragService : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_service, container, false)

        val bundle = arguments

        when(bundle!!.getString("service")){
            "cadastro" -> enviaUsuarioEndereco()
        }

        return view
    }

    private fun enviaUsuarioEndereco() {
        //TODO: Fazer do lado do servidor
/*        val enderecoViewModel = ViewModelProvider(this)
            .get(EnderecoViewModel::class.java)
        enderecoViewModel.getSync()
            .observe(activity!!, Observer{ endereco ->
                val gson = Gson()
                val jsonEndereco: String = gson.toJson(endereco)

                APIsWebClient().requestApi(jsonEndereco)
            })*/

        val usuarioViewModel = ViewModelProvider(this)
            .get(UsuarioViewModel::class.java)
        usuarioViewModel.getSync()
            .observe(activity!!, Observer{ usuario ->
                APIsWebClient().cadastroUsuario(usuario, object : CallbackResponse<ModelUsuario> {
                    override fun sucess(response: ModelUsuario) {

                        usuarioViewModel.update(response)
                        Toast.makeText(activity!!,getText(R.string.cadastro_sucesso),Toast.LENGTH_LONG).show()

                        val fragLogin = FragmentsLogin()
                        activity!!.supportFragmentManager.beginTransaction()
                        .replace(R.id.frag_main, fragLogin, "fragLogin")
                        .commit()
                    }

                    override fun error(response: String) {
                        Toast.makeText(activity!!,response,Toast.LENGTH_LONG).show()
                        val frag = FragCadastroUsuario()
                        activity!!.supportFragmentManager.beginTransaction()
                            .replace(R.id.frag_main, frag, "cadastro")
                            .commit()
                    }
                })
            })


    }

}