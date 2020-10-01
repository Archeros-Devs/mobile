package br.com.arquerosdev.repository

import androidx.lifecycle.LiveData
import br.com.arquerosdev.dao.PastaCategoriaDao
import br.com.arquerosdev.model.ModelPastaCategoria

class PastaCategoriaRepository (private val categoriaDao: PastaCategoriaDao) {

    fun pegarPastaCategoria():LiveData<List<ModelPastaCategoria>>{
        return categoriaDao.pegarPastaCategoria()
    }

    suspend fun insert(modelPastaCategoria: List<ModelPastaCategoria>) {
        categoriaDao.insert(modelPastaCategoria)
    }
}