package br.com.arquerosdev.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.arquerosdev.model.ModelPasta

@Dao
interface PastaDao {
    @Query("SELECT * FROM Pasta ORDER BY ID DESC")
    fun pegarPasta(): LiveData<List<ModelPasta>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pasta: ModelPasta)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserList(listModelPasta: List<ModelPasta>)

    @Query("UPDATE Pasta SET criado_em =:criadoEm, deletado_em =:deletadoEm, homologada_em=:homologadaem, id_pasta=:idpasta " +
            "WHERE id_usuario = :idUsuario and nome =:nome and discussao =:discussao and descricao =:descricao and localizacao=:localizacao")
    suspend fun update(
        idUsuario: Int, nome: String?, discussao: String?, descricao: String?, localizacao: String?,
        criadoEm: Long?, deletadoEm: Long?, homologadaem: Long?, idpasta: Int)
}