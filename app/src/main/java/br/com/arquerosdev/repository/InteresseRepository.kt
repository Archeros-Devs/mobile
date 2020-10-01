package br.com.arquerosdev.repository

import androidx.lifecycle.LiveData
import br.com.arquerosdev.dao.ArquivoDao
import br.com.arquerosdev.dao.InteresseDao
import br.com.arquerosdev.model.ModelArquivo
import br.com.arquerosdev.model.ModelInteresse

class InteresseRepository (private val interesseDao: InteresseDao) {

    fun pegarInteresse():LiveData<List<ModelInteresse>>{
        return interesseDao.pegarInteresse()
    }

    suspend fun insert(modelInteresse: List<ModelInteresse>) {
        interesseDao.insert(modelInteresse)
    }
}