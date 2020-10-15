package br.com.arquerosdev.views

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.arquerosdev.MainActivity
import br.com.arquerosdev.R
import br.com.arquerosdev.model.ModelUsuario
import br.com.arquerosdev.retrofit.service.APIsWebClient
import br.com.arquerosdev.retrofit.service.CallbackResponse
import br.com.arquerosdev.viewmodel.ProfisaoViewModel
import br.com.arquerosdev.viewmodel.UsuarioViewModel
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_login.view.*


class FragmentsLogin : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_login, container, false)
        view.bt_login.setOnClickListener { btview ->
            checarCredenciais(view)
        }

        view.bt_cadastro.setOnClickListener { view ->
            if(verifyAvailableNetwork(activity!!)){
                val frag = FragCadastroUsuario()
                activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.frag_main, frag, "cadastro")
                    .commit()
            }else{
                Toast.makeText(context, "Necessita de Intenert para cadastro!", Toast.LENGTH_LONG).show()
            }

        }

        return view
    }

    fun checarCredenciais(view: View){

        val isConect = verifyAvailableNetwork(activity!!)
        val usuarioViewModel = ViewModelProvider(this)
            .get(UsuarioViewModel::class.java)

        if(!view.edEmail.text.toString().isNullOrBlank() || !view.edSenha.text.toString().isNullOrBlank()){
            if(isConect){
                val login = JsonObject()

                login.addProperty("email",view.edEmail.text.toString())
                login.addProperty("senha",view.edSenha.text.toString())

                APIsWebClient().loginUsuario(login, object : CallbackResponse<Map<String, Any>> {
                    override fun sucess(response: Map<String, Any>) {
                        //TODO: Salvar Token para ser usado em outras APIs(salvar na tabela / file / sabe Deus)
                        Toast.makeText(activity!!,response.get("token").toString(),Toast.LENGTH_LONG).show()
                        val it = Intent(activity!!, MainActivity::class.java)
                        startActivity(it)
                        activity!!.finish()
                    }

                    override fun error(response: String) {
                        Toast.makeText(activity!!,response,Toast.LENGTH_LONG).show()
                    }
                })
            }else{
                    usuarioViewModel.getCheckCredenciais(view.edEmail.text.toString(),view.edSenha.text.toString())
                    .observe(activity!!, Observer{ usuario ->
                        if(usuario!=null && usuario.ativo!!){
                            val it = Intent(activity!!, MainActivity::class.java)
                            startActivity(it)
                            activity!!.finish()
                        }else{
                            Toast.makeText(context, getString(R.string.login_invalido), Toast.LENGTH_LONG).show()
                        }
                    })
            }
        }else{
            Toast.makeText(context, getString(R.string.campos_vazios), Toast.LENGTH_LONG).show()
        }
    }

    //TODO: fazer essa funcao publica para ser utlilizada em varios locais
    private fun verifyAvailableNetwork(activity: Activity):Boolean{
        val cm = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}