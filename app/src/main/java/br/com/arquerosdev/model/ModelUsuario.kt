package br.com.arquerosdev.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuario")
data class ModelUsuario (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_usuario")
    val id_usuario : Int,

    @ColumnInfo(name = "id_profissao")
    val id_profissao: Int?,

    @ColumnInfo(name = "cpf")
    val cpf: String?,

    @ColumnInfo(name = "nome")
    val nome: String?,

    @ColumnInfo(name = "senha")
    val senha: String?,

    @ColumnInfo(name = "genero")
    val genero: String?,

    @ColumnInfo(name = "email")
    val email: String?,

    @ColumnInfo(name = "url_img")
    val url_Img: String?,

    @ColumnInfo(name = "id_escolaridade")
    val idEscolaridade: Int?,

    @ColumnInfo(name = "ativo")
    val ativo: Boolean?,

    @ColumnInfo(name = "tipo_usuario")
    val tipo_usuario: Int?,

    @ColumnInfo(name = "criado_em")
    val criadoEm: String?,

    @ColumnInfo(name = "deletado_em")
    val deletadoEm: String?,

    @ColumnInfo(name = "telefone")
    val telefone: String?
)
