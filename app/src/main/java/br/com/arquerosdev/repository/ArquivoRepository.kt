package br.com.arquerosdev.repository

import androidx.lifecycle.LiveData
import br.com.arquerosdev.dao.ArquivoDao
import br.com.arquerosdev.model.ModelArquivo

class ArquivoRepository (private val arquivoDao: ArquivoDao) {

    fun pegarArquivo():LiveData<List<ModelArquivo>>{
        return arquivoDao.pegarArquivo()
    }

    suspend fun insert(modelArquivo: List<ModelArquivo>) {
        arquivoDao.insert(modelArquivo)
    }
}