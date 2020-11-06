package br.com.arquerosdev.model

import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Estudo",primaryKeys = arrayOf("id_origem", "id_mensagem"))
data class ModelEstudo(
    @ColumnInfo(name = "id_origem")
    val id_origem: Long,

    @ColumnInfo(name = "id_mensagem")
    val id_mensagem: Int,

    @ColumnInfo(name = "id_usuario")
    val id_usuario: Int,

    @ColumnInfo(name = "id_pasta")
    val id_pasta: Int,

    @ColumnInfo(name = "tipo")
    val tipo: Int,

    @ColumnInfo(name = "mensagem")
    val mensagem: String,

    @Nullable
    @ColumnInfo(name = "criado_em", defaultValue = "NULL")
    val criado_em: String?,

    @Nullable
    @ColumnInfo(name = "deletado_em", defaultValue = "NULL")
    val deletado_em: String?
): Parcelable