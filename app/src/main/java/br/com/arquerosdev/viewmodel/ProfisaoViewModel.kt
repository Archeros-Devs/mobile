package br.com.arquerosdev.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.arquerosdev.database.AppDatabase
import br.com.arquerosdev.model.ModelProfissao
import br.com.arquerosdev.repository.ProfissaoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfisaoViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: ProfissaoRepository
    val modelProfissao: LiveData<List<ModelProfissao>>

    init {
        val profissaoDao = AppDatabase.getDatabase(application).ProfissaoDao()
        repository = ProfissaoRepository(profissaoDao)
        modelProfissao = repository.pegarProfissoes()
    }

    fun insert(modelProfissao: List<ModelProfissao>) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(modelProfissao)
    }
}