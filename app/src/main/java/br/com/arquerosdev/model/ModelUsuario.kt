package br.com.arquerosdev.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Usuario")
data class ModelUsuario (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_usuario")
    val idUsuario : Int,

    @NotNull
    @ColumnInfo(name = "cpf")
    val cpf: String?,

    @NotNull
    @ColumnInfo(name = "nome")
    val nome: String?,

    @ColumnInfo(name = "genero")
    val genero: String?,

    @NotNull
    @ColumnInfo(name = "email")
    val email: String?,

    @ColumnInfo(name = "telefone")
    val telefone: String?,

    @ColumnInfo(name = "id_profissao")
    val idProfissao: Int?,

    @NotNull
    @ColumnInfo(name = "senha")
    val senha: String?,

    @ColumnInfo(name = "url_img")
    val urlImg: String?,

    @ColumnInfo(name = "id_escolaridade")
    val idEscolaridade: Int?
)
