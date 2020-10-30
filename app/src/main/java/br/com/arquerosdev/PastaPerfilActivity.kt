package br.com.arquerosdev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.ViewModelProvider
import br.com.arquerosdev.model.ModelPasta
import br.com.arquerosdev.viewmodel.UsuarioViewModel
import kotlinx.android.synthetic.main.activity_pasta_perfil.*
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.adapter_pastas.*

class PastaPerfilActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasta_perfil)


        val pasta = intent.getParcelableExtra<ModelPasta>("pasta")

        val usuarioViewModel = ViewModelProvider(this)
            .get(UsuarioViewModel::class.java).modelUsuarioLogado
        usuarioViewModel.observe(this, Observer { usuario ->
            this.textDiscusao.text = usuario.nome
        })

        button.setOnClickListener {
            val it = Intent(this@PastaPerfilActivity, PastaChatActivity::class.java)
            it.putExtra("pasta", pasta as Parcelable)
            startActivity(it)
        }
    }
}