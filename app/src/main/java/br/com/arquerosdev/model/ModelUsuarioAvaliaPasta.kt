package br.com.arquerosdev.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UsuarioAvaliaPasta")
data class ModelUsuarioAvaliaPasta (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_usuario")
    val id_usuario : Int,

    @ColumnInfo(name = "id_pasta")
    val id_pasta : Int?,

    @ColumnInfo(name = "avaliacao")
    var avaliacao: Int?,

    @ColumnInfo(name = "atualizado_em")
    val atualizado_em: String?,
    
    @ColumnInfo(name = "criado_em")
    val criado_em: String?
)

