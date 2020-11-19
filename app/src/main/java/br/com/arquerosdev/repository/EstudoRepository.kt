package br.com.arquerosdev.repository

import androidx.lifecycle.LiveData
import br.com.arquerosdev.dao.ArquivoDao
import br.com.arquerosdev.dao.EscolaridadeDao
import br.com.arquerosdev.dao.EstudoDao
import br.com.arquerosdev.model.ModelArquivo
import br.com.arquerosdev.model.ModelEscolaridade
import br.com.arquerosdev.model.ModelEstudo

class EstudoRepository (private val estudoDao: EstudoDao) {

    fun pegarEstudo(id_pasta: Int):LiveData<List<ModelEstudo>>{
        return estudoDao.pegarEstudo(id_pasta)
    }

    suspend fun insertList(modelEstudo: List<ModelEstudo>) {
        estudoDao.insertList(modelEstudo)
    }

    suspend fun insertMsg(modelEstudo: ModelEstudo) {
        estudoDao.insertMsg(modelEstudo)
    }

    fun update(modelEstudo: ModelEstudo) {
        estudoDao.update(modelEstudo.mensagem,modelEstudo.id_origem, modelEstudo.id_mensagem, modelEstudo.criado_em, modelEstudo.deletado_em)
    }

    fun getUltimoMSG(idUsuario: Int, idPasta: Int, msg: String): ModelEstudo{
        return estudoDao.getUltimoMSG(idUsuario,idPasta,msg)
    }
}