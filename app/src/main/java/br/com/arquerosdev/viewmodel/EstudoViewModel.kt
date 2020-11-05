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

    init {
        val estudoDao = AppDatabase.getDatabase(application).EstudoDao()
        repository = EstudoRepository (estudoDao)

    }

    fun getEstudoChat(id_pasta: Int): LiveData<List<ModelEstudo>>{
        return repository.pegarEstudo(id_pasta)
    }

    fun insertMsg(modelEstudo: ModelEstudo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertMsg(modelEstudo)
    }

    fun insert(modelEstudo: List<ModelEstudo>) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(modelEstudo)
    }

    fun update(modelEstudo: ModelEstudo) = viewModelScope.launch(Dispatchers.IO){
        repository.update(modelEstudo)
    }

    fun getUltimoMSG(id_usuario: Int, id_pasta: Int, msg: String): ModelEstudo{
        return repository.getUltimoMSG(id_usuario, id_pasta, msg)
    }
}