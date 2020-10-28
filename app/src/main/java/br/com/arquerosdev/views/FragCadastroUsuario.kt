package br.com.arquerosdev.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.arquerosdev.R
import br.com.arquerosdev.model.ModelEndereco
import br.com.arquerosdev.model.ModelUsuario
import br.com.arquerosdev.util.ChecarFuncoes
import br.com.arquerosdev.viewmodel.EnderecoViewModel
import br.com.arquerosdev.viewmodel.EscolaridadeViewModel
import br.com.arquerosdev.viewmodel.ProfisaoViewModel
import br.com.arquerosdev.viewmodel.UsuarioViewModel
import kotlinx.android.synthetic.main.frag_cad_usuario.*
import kotlinx.android.synthetic.main.frag_cad_usuario.view.*

class FragCadastroUsuario : Fragment() {

    var idEscolaridade = 0
    var idProfissao = 0
    lateinit var funcoes:ChecarFuncoes

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.frag_cad_usuario, container, false)

        configuracoesViewsProfissoes(view)
        configuracoesViewsEscolaridade(view)

        //TODO: Salvar dados no bundle para recupear o cadastro não enviado
        //TODO: Criar funcao para chegar de tem dados a recuperar no bundle

        funcoes = ChecarFuncoes(context!!)

        view.btSalvar.setOnClickListener {
            if(view != null ){
                val isValido = checarCampos(view)
                if(isValido){salvarCadastro(view)}
            }

        }
        return view
    }

    private fun configuracoesViewsProfissoes(view: View) {
        val profissaoViewModel: ProfisaoViewModel = ViewModelProvider(this)
            .get(ProfisaoViewModel::class.java)

        profissaoViewModel.listNomeProfissao.observe(activity!!, Observer { listaNome ->
            val adapter = ArrayAdapter<String>(
                context!!,
                android.R.layout.simple_dropdown_item_1line,
                listaNome
            )
            actvProfissao.setAdapter(adapter)
            actvProfissao.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->
                    val itemSelecionado = parent.getItemAtPosition(position).toString()
                    //Pegar id do Objeto na tabela
                    profissaoViewModel.modelProfissao.observe(activity!!, Observer { idLinha ->
                        idProfissao = idLinha.indexOfFirst { it.nome == itemSelecionado }
                    })
                }
            actvProfissao.onFocusChangeListener = View.OnFocusChangeListener { view, b ->
                if (b) {
                    actvProfissao.showDropDown()
                }
            }
        })
    }

    private fun configuracoesViewsEscolaridade(view: View) {
        val escolaridadeViewModel: EscolaridadeViewModel = ViewModelProvider(this)
            .get(EscolaridadeViewModel::class.java)
        escolaridadeViewModel.listNomeEscolaridade.observe(activity!!, Observer { listaNome ->
            val adapter = ArrayAdapter<String>(
                context!!,
                android.R.layout.simple_dropdown_item_1line,
                listaNome
            )
            actvEscolaridade.setAdapter(adapter)
            actvEscolaridade.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->
                    val itemSelecionado = parent.getItemAtPosition(position).toString()
                    //Pegar id do Objeto na tabela
                    escolaridadeViewModel.modelEscolaridade.observe(
                        activity!!,
                        Observer { idLinha ->
                            idEscolaridade =
                                idLinha.indexOfFirst { it.escolaridade == itemSelecionado }
                        })
                }
            actvEscolaridade.onFocusChangeListener = View.OnFocusChangeListener { view, b ->
                if (b) {
                    actvEscolaridade.showDropDown()
                }
            }
        })
    }

    //TODO: Mover essa funcao para a classe ChecarFuncoes
    private fun checarCampos(view: View?): Boolean {
        val strNomeCompleto = view!!.etNomeCompleto.text.toString()
        if(strNomeCompleto.isBlank() || strNomeCompleto.length < 3){
            view.etNomeCompleto.requestFocus()
            view.etNomeCompleto.error = getText(R.string.msg_error_nome_completo)
            return false
        }

        val strCPF = view.etCpf.text.toString()
        if(strCPF.isBlank() || strCPF.length < 9 || funcoes.cpfNaoValido(strCPF)){
            view.etCpf.requestFocus()
            view.etCpf.error = getText(R.string.msg_error_cpf)
            return false
        }

        val strEmail = view.etEmail.text.toString()
        if(strEmail.isBlank() || strEmail.length < 8 || funcoes.emailNaoValido(strEmail)){
            view.etEmail.requestFocus()
            view.etEmail.error = getText(R.string.msg_error_email)
            return false
        }

        //TODO: Colocar componente para ver a senha
        val strSenha = view.etSenha.text.toString()
        if(strSenha.isBlank() || strSenha.length < 6){
            view.etSenha.requestFocus()
            view.etSenha.error = getText(R.string.msg_error_senha)
            return false
        }

        //TODO: Colocar componente para ver a senha
        val strSenhaConf = view.etSenhaConf.text.toString()
        if(strSenhaConf != strSenha){
            view.etSenhaConf.requestFocus()
            view.etSenhaConf.error = getText(R.string.msg_error_senha_conf)
            return false
        }

        var checarGenero = view.genero_m.isChecked
        if(!view.genero_m.isChecked && !checarGenero){
            checarGenero = false
        }
        if(!view.genero_f.isChecked && !checarGenero){
            checarGenero = false
        }
        if(!view.genero_outros.isChecked && !checarGenero){
            checarGenero = false
        }
        if(!checarGenero){
            view.genero_m.requestFocus()
            Toast.makeText(activity, getText(R.string.msg_error_gerero), Toast.LENGTH_LONG).show()
            return false
        }

        val strTelefone = view.etTelefone.text.toString()
        if(strTelefone.isBlank() || strTelefone.length < 10){
            view.etTelefone.requestFocus()
            view.etTelefone.error = getText(R.string.msg_error_telefone)
            return false
        }

        val strActvProfissao = view.actvProfissao.text.toString()
        if(strActvProfissao.isBlank() || strActvProfissao.length < 2){
            view.actvProfissao.requestFocus()
            view.actvProfissao.error = getText(R.string.msg_error_profissao)
            return false
        }

        val strActvEscolaridade = view.actvEscolaridade.text.toString()
        if(strActvEscolaridade.isBlank() || strActvEscolaridade.length < 2){
            view.actvEscolaridade.requestFocus()
            view.actvEscolaridade.error = getText(R.string.msg_error_escolaridade)
            return false
        }

        val strEtEndereco = view.etEndereco.text.toString()
        if(strEtEndereco.isBlank() || strEtEndereco.length < 4){
            view.etEndereco.requestFocus()
            view.etEndereco.error = getText(R.string.msg_error_endereco)
            return false
        }

        //TODO: Criar um validador de CEP
        val strEtCep = view.etCep.text.toString()
        if(strEtCep.isBlank() || strEtCep.length < 8){
            view.etCep.requestFocus()
            view.etCep.error = getText(R.string.msg_error_endereco)
            return false
        }

        val strEtBairro = view.etBairro.text.toString()
        if(strEtBairro.isBlank() || strEtBairro.length < 3){
            view.etBairro.requestFocus()
            view.etBairro.error = getText(R.string.msg_error_endereco)
            return false
        }

        val strEtCidade = view.etCidade.text.toString()
        if(strEtCidade.isBlank() || strEtCidade.length < 3){
            view.etCidade.requestFocus()
            view.etCidade.error = getText(R.string.msg_error_endereco)
            return false
        }

        val strEtEstado = view.etEstado.text.toString()
        if(strEtEstado.isBlank() || strEtEstado.length < 2){
            view.etEstado.requestFocus()
            view.etEstado.error = getText(R.string.msg_error_endereco)
            return false
        }

        return true
    }

    fun salvarCadastro(view: View){

        var genero = "outros"
        if (view.genero_m.isChecked) genero = "masculino"
        if (view.genero_f.isChecked) genero = "feminino"

        //view.etSenhaConf
        val usuario = ModelUsuario(
            0,
            0,
            idProfissao,
            view.etCpf.text.toString(),
            view.etNomeCompleto.text.toString(),
            view.etSenha.text.toString(),
            genero,
            view.etEmail.text.toString(),
            "",// TODO: criar funcaio para captura de img via File ou Camera
            idProfissao,
            true,//TODO: Nico VALIDAR DEPOIS -> ATIVAR DEPOIS NO LADO DO SERVIDOR
            0,
            view.etTelefone.text.toString(),
            true
        )
        val usuarioViewModel = ViewModelProvider(this)
            .get(UsuarioViewModel::class.java)
        usuarioViewModel.insert(usuario)

        val endereco = ModelEndereco(
            0,//TODO: Nico gerar automatico no SERVIDOR
            0,
            view.etCidade.text.toString(),
            view.etEstado.text.toString(),
            view.etCep.text.toString().toInt(),
            view.etEndereco.text.toString(),
            view.etNumero.text.toString(),
            view.etBairro.text.toString(),
            view.etComplemento.text.toString(),
            true
        )
        val enderecoViewModel: EnderecoViewModel = ViewModelProvider(this)
            .get(EnderecoViewModel::class.java)
        enderecoViewModel.insert(endereco)

        val bundle = Bundle()
        bundle.putString("service","cadastro")

        val frag = FragService()
        frag.arguments = bundle
            this.activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.frag_main, frag, "Service")
            .commit()
    }
}