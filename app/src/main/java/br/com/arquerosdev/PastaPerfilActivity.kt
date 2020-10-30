package br.com.arquerosdev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import br.com.arquerosdev.model.ModelPasta
import kotlinx.android.synthetic.main.activity_pasta_perfil.*

class PastaPerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasta_perfil)

        val pasta = intent.getParcelableExtra<ModelPasta>("pasta")



        button.setOnClickListener {
            val it = Intent(this@PastaPerfilActivity, PastaChatActivity::class.java)
            it.putExtra("pasta", pasta as Parcelable)
            startActivity(it)
        }
    }
}