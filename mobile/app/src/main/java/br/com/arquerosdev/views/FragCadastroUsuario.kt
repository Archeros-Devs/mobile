package br.com.arquerosdev.views

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.arquerosdev.R
import br.com.arquerosdev.model.ModelEndereco
import br.com.arquerosdev.model.ModelUsuario
import br.com.arquerosdev.viewmodel.EnderecoViewModel
import br.com.arquerosdev.viewmodel.UsuarioViewModel
import kotlinx.android.synthetic.main.frag_cad_usuario.view.*

class FragCadastroUsuario : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.frag_cad_usuario, container, false)

        view.btSalvar.setOnClickListener {
            salvarCadastro(view)
        }
        return view
    }

    fun salvarCadastro(view: View){
        //view.etSenhaConf
        val usuario = ModelUsuario(
            0,
            1,
             view.etCpf.text.toString(),
             view.etNomeCompleto.text.toString(),
             view.etSenha.text.toString(),
            "M",
             view.etEmail.text.toString(),
            "",
            1,
            false,
            1,
            view.etTelefone.text.toString()
        )
        val usuarioViewModel = ViewModelProvider(this)
            .get(UsuarioViewModel::class.java)
        usuarioViewModel.insert(usuario)

        val endereco = ModelEndereco(
            0,
            0,
            view.etCidade.text.toString(),
            view.etEstado.text.toString(),
            view.etCep.text.toString().toInt(),
            view.etEndereco.text.toString(),
            view.etNumero.text.toString(),
            view.etBairro.text.toString(),
            view.etComplemento.text.toString()
        )
        val enderecoViewModel: EnderecoViewModel = ViewModelProvider(this)
            .get(EnderecoViewModel::class.java)
        enderecoViewModel.insert(endereco)

        val frag = FragmentsLogin()
            this.activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.frag_main, frag, "Login")
            .commit()
    }
}