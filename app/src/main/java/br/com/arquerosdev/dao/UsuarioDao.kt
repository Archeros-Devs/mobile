package br.com.arquerosdev.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.arquerosdev.model.ModelUsuario

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM Usuario WHERE status = 'LOGADO'")//sobre_nome = :sobre_nome AND senha = :senha
    fun getUsuarioLogado(): LiveData<ModelUsuario>//sobre_nome: String, senha: String

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(modelUsuario: ModelUsuario)

    @Query("UPDATE Usuario SET status = 'DISABLE' WHERE cnpj = :cnpj")
    suspend fun deleteUsuario(cnpj: String)
}