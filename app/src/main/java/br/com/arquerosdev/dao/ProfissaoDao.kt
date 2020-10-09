package br.com.arquerosdev.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.arquerosdev.model.ModelProfissao
import br.com.arquerosdev.model.ModelUsuario

@Dao
interface ProfissaoDao {
    @Query("SELECT DISTINCT * FROM Profissoes")
    fun pegarProfissoes(): LiveData<List<ModelProfissao>>

    @Query("SELECT DISTINCT nome FROM Profissoes")
    fun pegarNomeProfissoes(): LiveData<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profisao: List<ModelProfissao>)

}