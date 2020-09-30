package br.com.arquerosdev.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Categoria")
data class ModelCategoria (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_categoria")
    val idCategoria : Int,
    
    @ColumnInfo(name = "categoria")
    val categoria : String?
    
    )