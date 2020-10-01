package br.com.arquerosdev.repository

import androidx.lifecycle.LiveData
import br.com.arquerosdev.dao.CategoriaDao
import br.com.arquerosdev.dao.PastaCategoriaDao
import br.com.arquerosdev.model.ModelCategoria
import br.com.arquerosdev.model.ModelPastaCategoria

class CategoriaRepository (private val categoriaDao: CategoriaDao) {

    fun pegarCategoria():LiveData<List<ModelCategoria>>{
        return categoriaDao.pegarCategoria()
    }

    suspend fun insert(modelCategoria: List<ModelCategoria>) {
        categoriaDao.insert(modelCategoria)
    }
}