package br.com.arquerosdev.views

import androidx.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.arquerosdev.R
import br.com.arquerosdev.model.ModelEndereco
import br.com.arquerosdev.model.ModelUsuario
import br.com.arquerosdev.viewmodel.EnderecoViewModel
import br.com.arquerosdev.viewmodel.EscolaridadeViewModel
import br.com.arquerosdev.viewmodel.ProfisaoViewModel
import br.com.arquerosdev.viewmodel.UsuarioViewModel
import kotlinx.android.synthetic.main.frag_cad_usuario.*
import kotlinx.android.synthetic.main.frag_cad_usuario.view.*

class FragCadastroUsuario : Fragment() {

    var idEscolaridade = 0
    var idProfissao = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.frag_cad_usuario, container, false)

        configuracoesViewsProfissoes(view)
        configuracoesViewsEscolaridade(view)

        view.btSalvar.setOnClickListener {
            val isValido = checarCampos(view)
            if(isValido){salvarCadastro(view)}
        }
        return view
    }

    private fun configuracoesViewsProfissoes(view: View) {
        val profissaoViewModel: ProfisaoViewModel = ViewModelProvider(this)
            .get(ProfisaoViewModel::class.java)

        profissaoViewModel.listNomeProfissao.observe(activity!!, Observer{ listaNome ->
            val adapter = ArrayAdapter<String>(
                context!!,
                android.R.layout.simple_dropdown_item_1line,
                listaNome
            )
            actvProfissao.setAdapter(adapter)
            actvProfissao.onItemClickListener = AdapterView.OnItemClickListener{
                    parent,view,position,id->
                val itemSelecionado = parent.getItemAtPosition(position).toString()
                //Pegar id do Objeto na tabela
                profissaoViewModel.modelProfissao.observe(activity!!, Observer{ idLinha ->
                    idProfissao = idLinha.indexOfFirst { it.nome == itemSelecionado}
                })
            }
            actvProfissao.onFocusChangeListener = View.OnFocusChangeListener{
                    view, b ->  if(b){ actvProfissao.showDropDown() }
            }
        })
    }

    private fun configuracoesViewsEscolaridade(view: View) {
        val escolaridadeViewModel: EscolaridadeViewModel = ViewModelProvider(this)
            .get(EscolaridadeViewModel::class.java)
        escolaridadeViewModel.listNomeEscolaridade.observe(activity!!, Observer{ listaNome ->
            val adapter = ArrayAdapter<String>(
                context!!,
                android.R.layout.simple_dropdown_item_1line,
                listaNome
            )
            actvEscolaridade.setAdapter(adapter)
            actvEscolaridade.onItemClickListener = AdapterView.OnItemClickListener{
                    parent,view,position,id->
                val itemSelecionado = parent.getItemAtPosition(position).toString()
                //Pegar id do Objeto na tabela
                escolaridadeViewModel.modelEscolaridade.observe(activity!!, Observer{ idLinha ->
                    idProfissao = idLinha.indexOfFirst { it.escolaridade == itemSelecionado}
                })
            }
            actvEscolaridade.onFocusChangeListener = View.OnFocusChangeListener{
                    view, b ->  if(b){ actvEscolaridade.showDropDown() }
            }
        })
    }

    private fun checarCampos(view: View?): Boolean {
        
        return false
    }

    fun salvarCadastro(view: View){

        var genero = "outros"
        if (view.genero_m.isChecked) genero = "M"
        if (view.genero_f.isChecked) genero = "F"

        //view.etSenhaConf
        val usuario = ModelUsuario(
            0,
            idProfissao,
             view.etCpf.text.toString(),
             view.etNomeCompleto.text.toString(),
             view.etSenha.text.toString(),
             genero,
             view.etEmail.text.toString(),
            "",
            idProfissao,
            true,//TODO: VALIDAR DEPOIS -> ATIVAR DEPOIS NO LADO DO SERVIDOR
            0,
            view.etTelefone.text.toString()
        )
        val usuarioViewModel = ViewModelProvider(this)
            .get(UsuarioViewModel::class.java)
        //usuarioViewModel.insert(usuario)

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
        //enderecoViewModel.insert(endereco)

        val frag = FragmentsLogin()
            this.activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.frag_main, frag, "Login")
            //.commit()
    }
}