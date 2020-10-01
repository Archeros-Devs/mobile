package br.com.arquerosdev.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.arquerosdev.model.ModelArquivo
import br.com.arquerosdev.model.ModelPasta
import br.com.arquerosdev.model.ModelUsuario

@Dao
interface ArquivoDao {
    @Query("SELECT * FROM Arquivos")
    fun pegarArquivo(): LiveData<List<ModelArquivo>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(pasta: List<ModelArquivo>)

}