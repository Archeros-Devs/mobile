package br.com.arquerosdev.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.arquerosdev.model.ModelArquivo
import br.com.arquerosdev.model.ModelEscolaridade
import br.com.arquerosdev.model.ModelPasta
import br.com.arquerosdev.model.ModelUsuario

@Dao
interface EscolaridadeDao {
    @Query("SELECT DISTINCT * FROM Escolaridade")
    fun pegarEscolaridade(): LiveData<List<ModelEscolaridade>>

    @Query("SELECT DISTINCT escolaridade FROM Escolaridade")
    fun pegarNomeEscolaridade(): LiveData<List<String>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(pasta: List<ModelEscolaridade>)

}