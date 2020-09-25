package br.com.arquerosdev.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pasta")
data class ModelPasta (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_pasta")
    val idpasta : Int,

    @ColumnInfo(name = "id_usuario")
    val idUsuaro : Int?,
    
    @ColumnInfo(name = "nome")
    val nome: String?,

    @ColumnInfo(name = "descricao")
    val descricao : String?,

    @ColumnInfo(name = "discussao")
    val discussao : String?,

    @ColumnInfo(name = "localizacao")
    val localizacao : String?

    /*    
    @ColumnInfo(name = "homologada_em")
    val criadoEm: Timestamp?,

    
    @ColumnInfo(name = "criado_em")
    val homologadaem: Timestamp?,

    @ColumnInfo(name = "deletado_em")
    val deletadoEm: Timestamp?,*/

    )