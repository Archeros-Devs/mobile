package br.com.arquerosdev.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.arquerosdev.model.ModelUsuario

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM Usuario WHERE ativo = '1' limit 1")
    fun getAsUsuarioLogado(): LiveData<ModelUsuario>

    @Query("SELECT * FROM Usuario WHERE email = :email AND senha = :senha")
    fun getCheckCredenciais(email: String, senha: String): LiveData<ModelUsuario>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(modelUsuario: ModelUsuario)

    @Query("UPDATE Usuario SET ativo = 'DISABLE' WHERE cpf = :cpf")
    suspend fun deleteUsuario(cpf: String)
}