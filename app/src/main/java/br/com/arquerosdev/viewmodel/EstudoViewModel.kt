package br.com.arquerosdev.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.arquerosdev.dao.ArquivoDao
import br.com.arquerosdev.database.AppDatabase
import br.com.arquerosdev.model.ModelArquivo
import br.com.arquerosdev.model.ModelEstudo
import br.com.arquerosdev.repository.ArquivoRepository
import br.com.arquerosdev.repository.EstudoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EstudoViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: EstudoRepository
    val modelArquivo: LiveData<List<ModelEstudo>>

    init {
        val estudoDao = AppDatabase.getDatabase(application).EstudoDao()
        repository = EstudoRepository (estudoDao)
        modelArquivo = repository.pegarEstudo()
    }

    fun insert(modelEstudo: List<ModelEstudo>) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(modelEstudo)
    }
}