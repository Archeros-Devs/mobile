package br.com.arquerosdev.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.arquerosdev.dao.ArquivoDao
import br.com.arquerosdev.database.AppDatabase
import br.com.arquerosdev.model.ModelArquivo
import br.com.arquerosdev.repository.ArquivoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArquivoViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: ArquivoRepository
    val modelArquivo: LiveData<List<ModelArquivo>>

    init {
        val arquivoDao = AppDatabase.getDatabase(application).ArquivoDao()
        repository = ArquivoRepository (arquivoDao)
        modelArquivo = repository.pegarArquivo()
    }

    fun insert(modelArquivo: List<ModelArquivo>) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(modelArquivo)
    }
}