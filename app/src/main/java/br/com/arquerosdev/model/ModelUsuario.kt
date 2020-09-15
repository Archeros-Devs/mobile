package br.com.arquerosdev.model;

import androidx.room.ColumnInfo
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Usuario")
data class ModelUsuario (
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "primeiro_nome")
    val nome: String?,
    @ColumnInfo(name = "sobre_nome")
    val sobreNome: String?,
    @ColumnInfo(name = "sexo")
    val sexo: String?,
    @ColumnInfo(name = "cep")
    val cep: String?,
    @ColumnInfo(name = "profissao")
    val profissao: String?,
    @ColumnInfo(name = "escolaridade")
    val escolaridade: String?,
    @ColumnInfo(name = "telefone")
    val telefone: String?,
    @ColumnInfo(name = "status")
    val status: String?,
    @ColumnInfo(name = "criado_em")
    val criadoEm: String?,
    @ColumnInfo(name = "deletado_em")
    val deletadoEm: String?,
    @ColumnInfo(name = "senha")
    val senha: String?,
    @ColumnInfo(name = "cnpj")
    val cnpj: String?,
    @ColumnInfo(name = "fk_grupo_id")
    val fkGrupoId: String?
)
