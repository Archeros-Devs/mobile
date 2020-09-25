package br.com.arquerosdev.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Profissoes")
data class ModelProfissoes (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_profissao")
    val id_profissao: Int,

    @ColumnInfo(name = "nome")
    val nome: String?
)