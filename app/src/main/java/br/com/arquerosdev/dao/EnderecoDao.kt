package br.com.arquerosdev.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.arquerosdev.model.ModelEndereco
import br.com.arquerosdev.model.ModelUsuario

@Dao
interface EnderecoDao {
    @Query("SELECT * FROM Endereco limit 1")
    fun getEndereco(): LiveData<ModelEndereco>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(modelEndereco: ModelEndereco)

    @Query("UPDATE Endereco SET id_endereco = :id and endereco = :endereco")
    suspend fun updateUsuario(id: Int, endereco: String)

    @Query("SELECT * FROM Endereco WHERE sync = 1")
    fun getSync(): LiveData<ModelEndereco>
}