package br.com.arquerosdev.repository

import androidx.lifecycle.LiveData
import br.com.arquerosdev.dao.UsuarioDao
import br.com.arquerosdev.model.ModelUsuario

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    val modelAsUsuarioLogado: ModelUsuario = usuarioDao.getAsUsuarioLogado()
    /*fun getUsuarioLogado(){//usuario
        usuarioDao.getUsuarioLogado()//sobre_nome, senha
    }*/

    suspend fun insert(modelUsuario: ModelUsuario) {
        usuarioDao.insert(modelUsuario)
    }
}