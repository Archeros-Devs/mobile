package br.com.arquerosdev.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.arquerosdev.database.AppDatabase
import br.com.arquerosdev.model.ModelPastaCategoria
import br.com.arquerosdev.repository.PastaCategoriaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PastaCategoriaViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: PastaCategoriaRepository
    val modelPastaCategoria: LiveData<List<ModelPastaCategoria>>

    init {
        val pastacategoriaDao = AppDatabase.getDatabase(application).PastaCategoriaDao()
        repository = PastaCategoriaRepository (pastacategoriaDao)
        modelPastaCategoria = repository.pegarPastaCategoria()
    }


    fun insert(modelPastaCategoria: List<ModelPastaCategoria>) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(modelPastaCategoria)
    }
}