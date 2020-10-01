package br.com.arquerosdev.repository

import androidx.lifecycle.LiveData
import br.com.arquerosdev.dao.UsuarioSeguePastaDao
import br.com.arquerosdev.model.ModelUsuarioSeguePasta

class UsuarioSeguePastaRepository (private val usuarioSeguePastaDao: UsuarioSeguePastaDao) {

    fun pegarUsuarioSeguePasta():LiveData<List<ModelUsuarioSeguePasta>>{
        return usuarioSeguePastaDao.pegarUsuarioSeguePasta()
    }

    suspend fun insert(modelUsuarioSeguePasta: List<ModelUsuarioSeguePasta>) {
        usuarioSeguePastaDao.insert(modelUsuarioSeguePasta)
    }
}