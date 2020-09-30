package br.com.arquerosdev.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Arquivos")
data class ModelArquivo (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_arquivo")
    val idArquivo : Int,
    
    @ColumnInfo(name = "id_usuario")
    val idUsuario : Int?,

    @ColumnInfo(name = "id_pasta")
    val idPasta : Int?,

    @ColumnInfo(name = "url")
    val url : String?,

    @ColumnInfo(name = "tipo")
    val tipo : String?,

    @ColumnInfo(name = "descricao")
    val descricao : String?

)