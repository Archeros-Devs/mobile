package br.com.arquerosdev.repository

import androidx.lifecycle.LiveData
import br.com.arquerosdev.dao.UsuarioDao
import br.com.arquerosdev.model.ModelUsuario

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    fun getAsUsuarioLogado():LiveData<ModelUsuario>{
        return usuarioDao.getAsUsuarioLogado()
    }

    fun getCheckCredenciais(email: String, senha: String): LiveData<ModelUsuario>{
        return usuarioDao.getCheckCredenciais(email, senha)
    }

    fun getSync(): LiveData<ModelUsuario>{
        return usuarioDao.getSync()
    }

    suspend fun insert(modelUsuario: ModelUsuario) {
        usuarioDao.insert(modelUsuario)
    }

    suspend fun update(modelUsuario: ModelUsuario) {
        usuarioDao.update(modelUsuario)
    }

}