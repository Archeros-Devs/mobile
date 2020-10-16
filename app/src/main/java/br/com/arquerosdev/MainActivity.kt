package br.com.arquerosdev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btListarPastas.setOnClickListener { view ->
            startActivity(Intent(this,PastaListaActivity::class.java))
        }
        btCriarPasta.setOnClickListener { view ->
            startActivity(Intent(this, CriarPastaActivity::class.java))
        }
    }
}
