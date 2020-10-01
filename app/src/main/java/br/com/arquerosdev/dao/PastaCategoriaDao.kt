package br.com.arquerosdev.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.arquerosdev.model.ModelPasta
import br.com.arquerosdev.model.ModelPastaCategoria
import br.com.arquerosdev.model.ModelUsuario

@Dao
interface PastaCategoriaDao {
    @Query("SELECT * FROM PastaCategoria")
    fun pegarPastaCategoria(): LiveData<List<ModelPastaCategoria>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(pasta: List<ModelPastaCategoria>)

}