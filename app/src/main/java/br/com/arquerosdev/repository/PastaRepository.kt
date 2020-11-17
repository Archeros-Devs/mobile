package br.com.arquerosdev.repository

import androidx.lifecycle.LiveData
import br.com.arquerosdev.dao.PastaDao
import br.com.arquerosdev.model.ModelPasta

class PastaRepository (private val pastaDao: PastaDao) {

    fun pegarPasta():LiveData<List<ModelPasta>>{
        return pastaDao.pegarPasta()
    }

    suspend fun insert(modelPasta: ModelPasta) {
        pastaDao.insert(modelPasta)
    }

    suspend fun inserList(listModelPasta: List<ModelPasta>) {
        pastaDao.inserList(listModelPasta)
    }

    suspend fun update(mpst: ModelPasta){
        pastaDao.update(mpst.criado_em, mpst.deletado_em, mpst.homologada_em, mpst.id_pasta, mpst.id_origem)
    }

    fun getPasta(titulo: String): ModelPasta {
        return pastaDao.getPasta(titulo)
    }
}