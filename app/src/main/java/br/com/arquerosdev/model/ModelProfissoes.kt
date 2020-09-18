package br.com.arquerosdev.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "Profissoes")
data class ModelProfissoes (
    @ColumnInfo(name = "id_profissao")
    val id_profissao: Int?,

    @ColumnInfo(name = "nome")
    val nome: String?
)