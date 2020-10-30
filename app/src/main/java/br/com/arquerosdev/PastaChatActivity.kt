package br.com.arquerosdev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.arquerosdev.model.ModelPasta

class PastaChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pasta_chat)

        val pasta = intent.getParcelableExtra<ModelPasta>("pasta")

    }
}