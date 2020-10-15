package br.com.arquerosdev.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.arquerosdev.database.AppDatabase
import br.com.arquerosdev.model.ModelPasta
import br.com.arquerosdev.repository.PastaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PastaViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: PastaRepository
    val modelPasta: LiveData<List<ModelPasta>>

    init {
        val pastaDao = AppDatabase.getDatabase(application).PastaDao()
        repository = PastaRepository (pastaDao)
        modelPasta = repository.pegarPasta()
    }

    fun insert(modelPasta: List<ModelPasta>) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(modelPasta)
    }

    fun inserList(listModelPasta: List<ModelPasta>) = viewModelScope.launch(Dispatchers.IO) {
        repository.inserList(listModelPasta)
    }
}