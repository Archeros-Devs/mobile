package br.com.arquerosdev.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Usuario")
data class ModelUsuario (
    @Expose
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,

    @ColumnInfo(name = "id_usuario")
    val id_usuario : Int,

    @ColumnInfo(name = "id_profissao")
    val id_profissao: Int?,

    @NotNull
    @ColumnInfo(name = "cpf")
    val cpf: String?,

    @NotNull
    @ColumnInfo(name = "nome")
    val nome: String?,

    @NotNull
    @ColumnInfo(name = "senha")
    val senha: String?,

    @ColumnInfo(name = "genero")
    val genero: String?,

    @NotNull
    @ColumnInfo(name = "email")
    val email: String?,

    @ColumnInfo(name = "url_img")
    val url_img: String?,

    @ColumnInfo(name = "id_escolaridade")
    val id_escolaridade: Int?,

    @ColumnInfo(name = "ativo")
    val ativo: Boolean?,

    @ColumnInfo(name = "tipo_usuario")
    val tipo_usuario: Int?,

    @ColumnInfo(name = "telefone")
    val telefone: String?,

    @Expose
    @ColumnInfo(name = "sync")
    val sync: Boolean?
)
