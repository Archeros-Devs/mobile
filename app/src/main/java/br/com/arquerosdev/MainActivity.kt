package br.com.arquerosdev

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.arquerosdev.viewmodel.UsuarioViewModel

class MainActivity : BaseActivity() {

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
        usuarioViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
        sessaoUsuario = usuarioViewModel.modelUsuarioLogado.ativo == true
        Log.i(TAG,"UsuarioLoagado = ${sessaoUsuario}")
    }

}