package br.com.arquerosdev.repository

import androidx.lifecycle.LiveData
import br.com.arquerosdev.dao.ArquivoDao
import br.com.arquerosdev.dao.EscolaridadeDao
import br.com.arquerosdev.dao.EstudoDao
import br.com.arquerosdev.model.ModelArquivo
import br.com.arquerosdev.model.ModelEscolaridade
import br.com.arquerosdev.model.ModelEstudo

class EstudoRepository (private val estudoDao: EstudoDao) {

    fun pegarEstudo():LiveData<List<ModelEstudo>>{
        return estudoDao.pegarEstudo()
    }

    suspend fun insert(modelEstudo: List<ModelEstudo>) {
        estudoDao.insert(modelEstudo)
    }
}