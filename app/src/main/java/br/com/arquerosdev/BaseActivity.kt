package br.com.arquerosdev

import androidx.appcompat.app.AppCompatActivity
import br.com.arquerosdev.inferfaces.InterfaceUsuarioLogado

open class BaseActivity : AppCompatActivity(), InterfaceUsuarioLogado {
    override fun getUsuarioLogado(){}

    override fun onResume() {
        super.onResume()
        getUsuarioLogado();
    }
}