package br.com.arquerosdev.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.arquerosdev.dao.EnderecoDao
import br.com.arquerosdev.database.AppDatabase
import br.com.arquerosdev.model.ModelEndereco
import br.com.arquerosdev.model.ModelUsuario
import br.com.arquerosdev.repository.EnderecoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EnderecoViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: EnderecoRepository
    val modelEndereco: LiveData<ModelEndereco>

    init {
        val enderecoDao: EnderecoDao = AppDatabase.getDatabase(application).EnderecoDao()
        repository = EnderecoRepository(enderecoDao)
        modelEndereco = repository.getEndereco()
    }

    fun insert(modelEndereco: ModelEndereco) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(modelEndereco)
    }
}