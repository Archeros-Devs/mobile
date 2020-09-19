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
        val usuarioViewModel = ViewModelProvider(this)
            .get(UsuarioViewModel::class.java)

        if(!view.edEmail.text.toString().isNullOrBlank() || !view.edSenha.text.toString().isNullOrBlank()){
            usuarioViewModel.getCheckCredenciais(view.edEmail.text.toString(),view.edSenha.text.toString())
        }else{
            Toast.makeText(context, "campos vazios!", Toast.LENGTH_LONG).show()
            Log.e("teste", "*********")
        }
    }
}