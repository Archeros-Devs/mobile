package br.com.arquerosdev.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.arquerosdev.model.ModelArquivo
import br.com.arquerosdev.model.ModelPasta
import br.com.arquerosdev.model.ModelUsuario
import br.com.arquerosdev.model.ModelUsuarioAvaliaPasta

@Dao
interface UsuarioAvaliaPastaDao {
    @Query("SELECT * FROM Usuario_Avalia_Pasta")
    fun pegarUsuarioAvaliaPasta(): LiveData<List<ModelUsuarioAvaliaPasta>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(pasta: List<ModelUsuarioAvaliaPasta>)

}