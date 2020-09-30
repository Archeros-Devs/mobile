package br.com.arquerosdev.repository

import androidx.lifecycle.LiveData
import br.com.arquerosdev.dao.ProfissaoDao
import br.com.arquerosdev.model.ModelProfissao

class ProfissaoRepository(private val profissaoDao: ProfissaoDao) {

    fun pegarProfissoes():LiveData<List<ModelProfissao>>{
        return profissaoDao.pegarProfissoes()
    }

    suspend fun insert(modelProfissao: List<ModelProfissao>) {
        profissaoDao.insert(modelProfissao)
    }
}