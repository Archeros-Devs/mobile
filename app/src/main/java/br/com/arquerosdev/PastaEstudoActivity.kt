package br.com.arquerosdev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import br.com.arquerosdev.model.ModelPasta

class PastaEstudoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasta_estudo)

        val pasta = intent.getParcelableExtra<ModelPasta>("pasta")


    }
}