package br.com.arquerosdev.repository

import androidx.lifecycle.LiveData
import br.com.arquerosdev.dao.EnderecoDao
import br.com.arquerosdev.dao.UsuarioDao
import br.com.arquerosdev.model.ModelEndereco
import br.com.arquerosdev.model.ModelUsuario

class EnderecoRepository(private val enderecoDao: EnderecoDao) {

    fun getEndereco():LiveData<ModelEndereco>{
        return enderecoDao.getEndereco()
    }

    suspend fun insert(modelEndereco: ModelEndereco) {
        enderecoDao.insert(modelEndereco)
    }

    fun getSync():LiveData<ModelEndereco>{
        return enderecoDao.getSync()
    }
}