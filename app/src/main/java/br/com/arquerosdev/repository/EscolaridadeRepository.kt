package br.com.arquerosdev.repository

import androidx.lifecycle.LiveData
import br.com.arquerosdev.dao.ArquivoDao
import br.com.arquerosdev.dao.EscolaridadeDao
import br.com.arquerosdev.model.ModelArquivo
import br.com.arquerosdev.model.ModelEscolaridade

class EscolaridadeRepository (private val escolaridadeDao: EscolaridadeDao) {

    fun pegarEscolaridade():LiveData<List<ModelEscolaridade>>{
        return escolaridadeDao.pegarEscolaridade()
    }

    suspend fun insert(modelEscolaridade: List<ModelEscolaridade>) {
        escolaridadeDao.insert(modelEscolaridade)
    }
}