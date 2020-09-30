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
    @Query("SELECT * FROM Profissoes")
    fun pegarProfissoes(): LiveData<List<ModelProfissao>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profisao: List<ModelProfissao>)

}