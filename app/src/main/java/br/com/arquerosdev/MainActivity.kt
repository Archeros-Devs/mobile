package br.com.arquerosdev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btListarPastas.setOnClickListener { view ->
            startActivity(Intent(this,PastaListaActivity::class.java))
        }
        btCriarPasta.setOnClickListener { view ->
        }

        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.pg_explorar -> {
                    // Respond to navigation item 1 click
                    true
                }
                R.id.pg_criar -> {
                    startActivity(Intent(this, CriarPastaActivity::class.java))
                    true
                }
                R.id.pg_seguindo -> {
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }
    }

}
