package br.com.arquerosdev.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Interesses")
data class ModelInteresse (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_usuario")
    val idUsuaro : Int,
    
    @ColumnInfo(name = "id_categoria")
    val categoria : Int?
    
    )