package br.com.arquerosdev.views

import android.app.Activity
import android.content.Context
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
import br.com.arquerosdev.R
import br.com.arquerosdev.viewmodel.UsuarioViewModel
import kotlinx.android.synthetic.main.fragment_login.view.*

class FragmentsLogin : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_login, container, false)
        view.bt_login.setOnClickListener { btview ->
            Log.e("teste", "*********")
            checarCredenciais(view)
        }

        view.bt_cadastro.setOnClickListener { view ->
            val frag = FragCadastroUsuario()
            activity!!.getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_main, frag, "cadastro")
                .commit()
        }

        return view
    }

    fun checarCredenciais(view: View){

        val isConexted = verifyAvailableNetwork(activity!!)
        Log.e("teste",isConexted.toString())

        val usuarioViewModel = ViewModelProvider(this)
            .get(UsuarioViewModel::class.java)

        if(!view.edEmail.text.toString().isNullOrBlank() || !view.edSenha.text.toString().isNullOrBlank()){
            if(isConexted){
                
            }else{
                val logadoComSucesso = usuarioViewModel.getCheckCredenciais(view.edEmail.text.toString(),view.edSenha.text.toString())
                    .observe(activity!!, Observer{ usuario ->
                        //TODO: Arrumar essa regra
                        if(!usuario.ativo!!){
                            //TODO: camada para o dashboard
                            Toast.makeText(context, "Falta fazer o Dashboard :D", Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(context, getString(R.string.login_invalido), Toast.LENGTH_LONG).show()
                        }
                    })
            }
        }else{
            Toast.makeText(context, getString(R.string.campos_vazios), Toast.LENGTH_LONG).show()
        }
    }

    fun verifyAvailableNetwork(activity: Activity):Boolean{
        val cm = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}