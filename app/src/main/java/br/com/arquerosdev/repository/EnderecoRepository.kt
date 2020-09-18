package br.com.arquerosdev.repository

import androidx.lifecycle.LiveData
import br.com.arquerosdev.dao.EnderecoDao
import br.com.arquerosdev.dao.UsuarioDao
import br.com.arquerosdev.model.ModelEndereco
import br.com.arquerosdev.model.ModelUsuario

class EnderecoRepository(private val enderecoDao: EnderecoDao) {

   // val modelUsuarioLogado: LiveData<ModelEndereco> = enderecoDao.getEndereco()
    /*fun getUsuarioLogado(){//usuario
        usuarioDao.getUsuarioLogado()//sobre_nome, senha
    }*/

    suspend fun insert(modelEndereco: ModelEndereco) {
        enderecoDao.insert(modelEndereco)
    }
}