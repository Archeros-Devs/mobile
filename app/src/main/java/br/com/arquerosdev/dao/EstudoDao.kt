package br.com.arquerosdev.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.arquerosdev.model.*

@Dao
interface EstudoDao {
    @Query("SELECT * FROM Estudo WHERE id_pasta=:id")
    fun pegarEstudo(id: Int): LiveData<List<ModelEstudo>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(estudo: List<ModelEstudo>)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertMsg(estudo: ModelEstudo)
}