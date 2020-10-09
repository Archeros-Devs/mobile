package br.com.arquerosdev.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.arquerosdev.dao.ArquivoDao
import br.com.arquerosdev.database.AppDatabase
import br.com.arquerosdev.model.ModelArquivo
import br.com.arquerosdev.model.ModelEscolaridade
import br.com.arquerosdev.repository.ArquivoRepository
import br.com.arquerosdev.repository.EscolaridadeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EscolaridadeViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: EscolaridadeRepository
    val modelEscolaridade: LiveData<List<ModelEscolaridade>>
    val listNomeEscolaridade: LiveData<List<String>>

    init {
        val escolaridadeDao = AppDatabase.getDatabase(application).EscolaridadeDao()
        repository = EscolaridadeRepository (escolaridadeDao)
        modelEscolaridade = repository.pegarEscolaridade()
        listNomeEscolaridade = repository.pegarNomeEscolaridade()
    }

    fun insert(modelEscolaridade: List<ModelEscolaridade>) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(modelEscolaridade)
    }
}