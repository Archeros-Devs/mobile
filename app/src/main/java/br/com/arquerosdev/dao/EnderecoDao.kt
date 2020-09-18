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
    @Query("SELECT * FROM Endereco WHERE id_usuario = :id")//sobre_nome = :sobre_nome AND senha = :senha
    fun getEndereco(id: Int): LiveData<ModelEndereco>//sobre_nome: String, senha: String

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(modelEndereco: ModelEndereco)

    @Query("UPDATE Endereco SET id_usuario = :id and endereco = :endereco")
    suspend fun updateUsuario(id: Int, endereco: String)
}