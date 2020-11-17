package br.com.arquerosdev.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.arquerosdev.model.ModelPasta

@Dao
interface PastaDao {
    @Query("SELECT * FROM Pasta ORDER BY id_origem DESC")
    fun pegarPasta(): LiveData<List<ModelPasta>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pasta: ModelPasta)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserList(listModelPasta: List<ModelPasta>)

    /*@Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(pasta: ModelPasta)*/

    @Query("UPDATE Pasta SET criado_em =:criadoEm, deletado_em =:deletadoEm, homologada_em=:homologadaem, id_pasta=:idpasta WHERE id_origem =:id_origem")
    suspend fun update(criadoEm: String?, deletadoEm: String?, homologadaem: String?, idpasta: Int, id_origem: Long)

    @Query("SELECT * FROM Pasta WHERE nome=:titulo limit 1")
    fun getPasta(titulo: String): ModelPasta
}