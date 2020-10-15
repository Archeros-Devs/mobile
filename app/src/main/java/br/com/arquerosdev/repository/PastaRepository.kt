package br.com.arquerosdev.repository

import androidx.lifecycle.LiveData
import br.com.arquerosdev.dao.PastaDao
import br.com.arquerosdev.model.ModelPasta

class PastaRepository (private val pastaDao: PastaDao) {

    fun pegarPasta():LiveData<List<ModelPasta>>{
        return pastaDao.pegarPasta()
    }

    suspend fun insert(modelPasta: List<ModelPasta>) {
        pastaDao.insert(modelPasta)
    }

    suspend fun inserList(listModelPasta: List<ModelPasta>) {
        pastaDao.inserList(listModelPasta)
    }
}