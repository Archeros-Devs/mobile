package br.com.arquerosdev.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.arquerosdev.dao.ArquivoDao
import br.com.arquerosdev.dao.UsuarioSeguePastaDao
import br.com.arquerosdev.database.AppDatabase
import br.com.arquerosdev.model.ModelArquivo
import br.com.arquerosdev.model.ModelUsuarioSeguePasta
import br.com.arquerosdev.repository.ArquivoRepository
import br.com.arquerosdev.repository.UsuarioSeguePastaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsuarioSeguePastaViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: UsuarioSeguePastaRepository
    val modelUsuarioSeguePasta: LiveData<List<ModelUsuarioSeguePasta>>

    init {
        val usuarioSeguePastaDao = AppDatabase.getDatabase(application).UsuarioSeguePastaDao()
        repository = UsuarioSeguePastaRepository (usuarioSeguePastaDao)
        modelUsuarioSeguePasta = repository.pegarUsuarioSeguePasta()
    }

    fun insert(modelUsuarioSeguePasta: List<ModelUsuarioSeguePasta>) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(modelUsuarioSeguePasta)
    }
}