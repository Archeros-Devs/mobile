package br.com.arquerosdev.repository

import androidx.lifecycle.LiveData
import br.com.arquerosdev.dao.ArquivoDao
import br.com.arquerosdev.dao.UsuarioAvaliaPastaDao
import br.com.arquerosdev.model.ModelArquivo
import br.com.arquerosdev.model.ModelUsuarioAvaliaPasta

class UsuarioAvaliaPastaRepository (private val usuarioAvaliaPastaDao: UsuarioAvaliaPastaDao) {

    fun pegarUsuarioAvaliaPasta():LiveData<List<ModelUsuarioAvaliaPasta>>{
        return usuarioAvaliaPastaDao.pegarUsuarioAvaliaPasta()
    }

    suspend fun insert(modelUsuarioAvaliaPasta: List<ModelUsuarioAvaliaPasta>) {
        usuarioAvaliaPastaDao.insert(modelUsuarioAvaliaPasta)
    }
}