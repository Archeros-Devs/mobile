package br.com.arquerosdev.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.arquerosdev.model.*

@Dao
interface EstudoDao {
    @Query("SELECT * FROM Estudo")
    fun pegarEstudo(): LiveData<List<ModelEstudo>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(pasta: List<ModelEstudo>)

}