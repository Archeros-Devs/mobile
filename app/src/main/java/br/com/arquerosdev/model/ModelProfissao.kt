package br.com.arquerosdev.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Profissoes")
data class ModelProfissao (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "id_profissao")
    val id_profissao: Int?,

    @ColumnInfo(name = "nome")
    val nome: String?
)