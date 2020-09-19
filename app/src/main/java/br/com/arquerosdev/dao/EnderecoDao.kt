package br.com.arquerosdev.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.arquerosdev.model.ModelEndereco

@Dao
interface EnderecoDao {
    @Query("SELECT * FROM Endereco limit 1")
    fun getEndereco(): LiveData<ModelEndereco>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(modelEndereco: ModelEndereco)

    @Query("UPDATE Endereco SET id_usuario = :id and endereco = :endereco")
    suspend fun updateUsuario(id: Int, endereco: String)
}