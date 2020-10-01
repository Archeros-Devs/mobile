package br.com.arquerosdev.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.arquerosdev.model.ModelUsuarioSeguePasta

@Dao
interface UsuarioSeguePastaDao {
    @Query("SELECT * FROM UsuarioSeguePasta")
    fun pegarUsuarioSeguePasta(): LiveData<List<ModelUsuarioSeguePasta>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(pasta: List<ModelUsuarioSeguePasta>)
}