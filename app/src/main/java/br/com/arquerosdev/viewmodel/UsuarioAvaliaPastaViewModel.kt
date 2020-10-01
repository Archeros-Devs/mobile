package br.com.arquerosdev.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.arquerosdev.dao.ArquivoDao
import br.com.arquerosdev.database.AppDatabase
import br.com.arquerosdev.model.ModelArquivo
import br.com.arquerosdev.model.ModelUsuarioAvaliaPasta
import br.com.arquerosdev.repository.ArquivoRepository
import br.com.arquerosdev.repository.UsuarioAvaliaPastaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsuarioAvaliaPastaViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: UsuarioAvaliaPastaRepository
    val modelUsuarioAvaliaPasta: LiveData<List<ModelUsuarioAvaliaPasta>>

    init {
        val usuarioAvaliaPasta = AppDatabase.getDatabase(application).UsuarioAvaliaPastaDao()
        repository = UsuarioAvaliaPastaRepository (usuarioAvaliaPasta)
        modelUsuarioAvaliaPasta = repository.pegarUsuarioAvaliaPasta()
    }

    fun insert(modelUsuarioAvaliaPasta: List<ModelUsuarioAvaliaPasta>) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(modelUsuarioAvaliaPasta)
    }
}