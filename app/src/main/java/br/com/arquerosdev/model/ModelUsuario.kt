package br.com.arquerosdev.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuario")
data class ModelUsuario (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_usuario")
    val idUsuario : Int,

    @ColumnInfo(name = "id_profissao")
    val idProfissao: Int?,

    @ColumnInfo(name = "cpf")
    val cpf: String?,

    @PrimaryKey
    @ColumnInfo(name = "nome")
    val nome: String?,

    @ColumnInfo(name = "senha")
    val senha: String?,

    @ColumnInfo(name = "genero")
    val genero: String?,

    @PrimaryKey
    @ColumnInfo(name = "email")
    val email: String?,

    @ColumnInfo(name = "url_img")
    val urlImg: String?,

    @ColumnInfo(name = "id_escolaridade")
    val idEscolaridade: Int?,

    @ColumnInfo(name = "ativo")
    val ativo: Boolean?,

    @ColumnInfo(name = "tipo_usuario")
    val tipoUsuario: Int?,

/*    @ColumnInfo(name = "criado_em")
    val criadoEm: Timestamp?,

    @ColumnInfo(name = "deletado_em")
    val deletadoEm: Timestamp?,*/

    @ColumnInfo(name = "telefone")
    val telefone: String?
)