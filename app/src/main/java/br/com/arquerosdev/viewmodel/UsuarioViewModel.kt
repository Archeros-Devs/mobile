package br.com.arquerosdev.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.arquerosdev.database.AppDatabase
import br.com.arquerosdev.model.ModelUsuario
import br.com.arquerosdev.repository.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsuarioViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: UsuarioRepository
    val modelUsuarioLogado: LiveData<ModelUsuario>

    init {
        val usuarioDao = AppDatabase.getDatabase(application).UsuarioDao()
        repository = UsuarioRepository(usuarioDao)
        modelUsuarioLogado = repository.modelUsuarioLogado
    }

/*    fun getUsuarioLogado(usuario: String, senha: String): LiveData<Usuario> {
        return getUsuarioLogado(usuario, senha)
    }*/

    fun insert(modelUsuario: ModelUsuario) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(modelUsuario)
    }
}