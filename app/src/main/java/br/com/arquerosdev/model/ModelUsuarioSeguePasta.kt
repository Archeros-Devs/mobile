package br.com.arquerosdev.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuario_Avalia_Pasta")
data class ModelUsuarioSeguePasta(
    @PrimaryKey(autoGenerate = false)    
    @ColumnInfo(name = "id_pasta")
    val id_pasta: Int,

    @ColumnInfo(name = "id_usuario")
    val idUsuario : Int?,
    
    @ColumnInfo(name = "ativo")
    val ativo: Int?

    /*    
    @ColumnInfo(name = "atualizado_em")
    val atualizadoEm: Timestamp?,

    
    @ColumnInfo(name = "criado_em")
    val criadoEm: Timestamp?*/
    
    )