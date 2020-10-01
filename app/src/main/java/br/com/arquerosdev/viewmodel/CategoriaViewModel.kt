package br.com.arquerosdev.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.arquerosdev.database.AppDatabase
import br.com.arquerosdev.model.ModelCategoria
import br.com.arquerosdev.model.ModelPastaCategoria
import br.com.arquerosdev.repository.CategoriaRepository
import br.com.arquerosdev.repository.PastaCategoriaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriaViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: CategoriaRepository
    val modelCategoria: LiveData<List<ModelCategoria>>

    init {
        val categoriaDao = AppDatabase.getDatabase(application).CategoriaDao()
        repository = CategoriaRepository (categoriaDao)
        modelCategoria = repository.pegarCategoria()
    }


    fun insert(modelCategoria: List<ModelCategoria>) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(modelCategoria)
    }
}