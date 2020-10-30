package br.com.arquerosdev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pasta_perfil.*

class PastaPerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasta_perfil)

        button.setOnClickListener {
            startActivity(Intent(this@PastaPerfilActivity, PastaChatActivity::class.java))
        }
    }
}