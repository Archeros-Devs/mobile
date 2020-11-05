package br.com.arquerosdev.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PastaCategoria")
data class ModelPastaCategoria (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_categoria")
    val id_categoria : Int,
    
    @ColumnInfo(name = "id_pasta")
    val id_pasta : Int?
    
    )