package br.com.arquerosdev.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.arquerosdev.dao.ArquivoDao
import br.com.arquerosdev.database.AppDatabase
import br.com.arquerosdev.model.ModelArquivo
import br.com.arquerosdev.model.ModelInteresse
import br.com.arquerosdev.repository.ArquivoRepository
import br.com.arquerosdev.repository.InteresseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InteresseViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: InteresseRepository
    val modelInteresse: LiveData<List<ModelInteresse>>

    init {
        val interesseDao = AppDatabase.getDatabase(application).InteresseDao()
        repository = InteresseRepository (interesseDao)
        modelInteresse = repository.pegarInteresse()
    }

    fun insert(modelInteresse: List<ModelInteresse>) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(modelInteresse)
    }
}