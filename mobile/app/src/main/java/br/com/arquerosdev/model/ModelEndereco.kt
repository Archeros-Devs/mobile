package br.com.arquerosdev.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Endereco")
data class ModelEndereco (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_endereco")
    var idEndereco: Int,

    @ColumnInfo(name = "id_usuario")
    var idUsuario: Int?,

    @ColumnInfo(name = "cidade")
        var cidade: String?,

    @ColumnInfo(name = "estado")
        var estado: String?,

    @ColumnInfo(name = "cep")
        var cep: Int?,

    @ColumnInfo(name = "endereco")
        var endereco: String?,

    @ColumnInfo(name = "numero")
        var numero: String?,

    @ColumnInfo(name = "bairro")
        var bairro: String?,

    @ColumnInfo(name = "complemento")
        var complemento: String?

)