package br.com.arquerosdev.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Escolaridade")
data class ModelEscolaridade (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_escolaridade")
    val id_escolaridade : Int,
    
    @ColumnInfo(name = "escolaridade")
    val escolaridade : String?
    
    )