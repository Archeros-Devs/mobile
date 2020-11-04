package br.com.arquerosdev.model

import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Estudo")
data class ModelEstudo (
    @Expose
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "id_messagem")
    val idMessagem: Int,

    @ColumnInfo(name = "id_usuario")
    val idUsuario: Int,

    @ColumnInfo(name = "id_pasta" )
    val idPasta : Int,

    @ColumnInfo(name = "tipo")
    val tipo: Int,

    @ColumnInfo(name = "menssagem")
    val menssagem: String,

    @Nullable
    @ColumnInfo(name = "criado_em", defaultValue = "NULL")
    val criadoEm: String?,

    @Nullable
    @ColumnInfo(name = "deletado_em", defaultValue = "NULL")
    val deletadoEm: String?
): Parcelable