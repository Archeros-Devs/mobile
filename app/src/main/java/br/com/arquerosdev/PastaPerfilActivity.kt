package br.com.arquerosdev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.arquerosdev.model.ModelData
import br.com.arquerosdev.model.ModelPasta
import br.com.arquerosdev.viewmodel.UsuarioViewModel
import kotlinx.android.synthetic.main.activity_pasta_perfil.*

class PastaPerfilActivity : AppCompatActivity() {
//    private var nameView:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasta_perfil)

        val pasta = intent.getParcelableExtra<ModelPasta>("pasta")

        this.textNomeUsuario.text = pasta?.criador.toString()

        textNomePasta.text= pasta?.nome.toString()
        val data = ModelData(pasta?.criado_em.toString())
        textCriadoEm.text = "${data.dia}/${data.mes}/${data.ano} ${data.horas}:${data.minuto}"
        cardDiscusao.text = pasta?.discussao.toString()
        cardDescricao.text = pasta?.descricao.toString()
        cardlocalizacao.text = pasta?.localizacao.toString()

        button.setOnClickListener {
            val it = Intent(this@PastaPerfilActivity, PastaEstudoActivity::class.java)
            it.putExtra("pasta", pasta as Parcelable)
            startActivity(it)
        }
    }
}