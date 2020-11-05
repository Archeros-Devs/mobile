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

    @Query("UPDATE Estudo SET id_mensagem=:id_mensagem, criado_em=:criadoEm WHERE id_origem=:id_origem")
    fun update(id_origem: Int, id_mensagem: Int, criadoEm: String?)

    @Query("SELECT * FROM Estudo WHERE id_usuario=:id_usuario and id_pasta=:id_pasta and mensagem=:mensagem")
    fun getUltimoMSG(id_usuario: Int, id_pasta: Int, mensagem: String): ModelEstudo
}