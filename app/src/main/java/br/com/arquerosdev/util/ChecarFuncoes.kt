package br.com.arquerosdev.util

import android.content.Context
import android.widget.EditText
import br.com.arquerosdev.R

open class ChecarFuncoes(context: Context) {

    var ctx:Context

    init {ctx = context}

    //TODO criar um validador de CFF
    fun cpfNaoValido(cpf: String): Boolean {
        return false
    }

    //TODO criar um validador de email, se contem @ e .com
    fun emailNaoValido(email:String): Boolean {
        return false
    }
}