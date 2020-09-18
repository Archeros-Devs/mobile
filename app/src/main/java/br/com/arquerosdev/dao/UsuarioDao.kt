package br.com.arquerosdev.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.arquerosdev.model.ModelUsuario

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM Usuario WHERE ativo = '1'")
    fun getAsUsuarioLogado(): ModelUsuario

   /* @Query("SELECT * FROM Usuario WHERE ativo = 'LOGADO'")//sobre_nome = :sobre_nome AND senha = :senha
    fun getUsuarioLogado(): ModelUsuario
    sobre_nome: String, senha: String*/

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(modelUsuario: ModelUsuario)

    @Query("UPDATE Usuario SET ativo = 'DISABLE' WHERE cpf = :cpf")
    suspend fun deleteUsuario(cpf: String)


}