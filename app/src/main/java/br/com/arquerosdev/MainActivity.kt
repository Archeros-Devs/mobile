package br.com.arquerosdev

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.arquerosdev.viewmodel.UsuarioViewModel

class MainActivity : BaseActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var usuarioViewModel: UsuarioViewModel
    private var sessaoUsuario: Boolean = false
    private var TAG:String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(sessaoUsuario){
            Log.i(TAG,"chama home")
        }else{
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    override fun getUsuarioLogado() {
        super.getUsuarioLogado()

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

}