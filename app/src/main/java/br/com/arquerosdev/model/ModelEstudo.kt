package br.com.arquerosdev.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Estudo")
data class ModelEstudo (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_messagem")
    val idMessagem: Int,

    @ColumnInfo(name = "id_usuario")
    val idUsuario: Int?,

    @ColumnInfo(name = "id_pasta" )
    val idPasta : Int?,

    
    @ColumnInfo(name = "tipo")
    val tipo: Int?,

    
    @ColumnInfo(name = "menssagem")
    val menssagem: String?

    /*    @ColumnInfo(name = "criado_em")
    val criadoEm: Timestamp?,

    @ColumnInfo(name = "deletado_em")
    val deletadoEm: Timestamp?,*/
)