package br.com.arquerosdev.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.arquerosdev.model.ModelUsuario

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM Usuario limit 1") //WHERE ativo = '1'
    fun getAsUsuarioLogado(): LiveData<ModelUsuario>

    @Query("SELECT * FROM Usuario WHERE email = :email and  senha = :senha limit 1")
    fun getCheckCredenciais(email: String, senha: String): LiveData<ModelUsuario>

    @Query("SELECT * FROM Usuario WHERE sync = 1")
    fun getSync(): LiveData<ModelUsuario>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(modelUsuario: ModelUsuario)


    @Update
    suspend fun update(modelUsuario: ModelUsuario)

    //TODO: Rever essa ação de desativação/broqueio de usuario no app
    @Query("UPDATE Usuario SET ativo = 'DISABLE' WHERE cpf = :cpf")
    suspend fun deleteUsuario(cpf: String)
}