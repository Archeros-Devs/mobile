package br.com.arquerosdev.model

import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Pasta")
data class ModelPasta (
    @Expose
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "id_pasta")
    val idpasta: Int,

    @ColumnInfo(name = "id_usuario")
    val idUsuario: Int,

    @ColumnInfo(name = "nome")
    val nome: String?,

    @ColumnInfo(name = "descricao")
    val descricao: String?,

    @ColumnInfo(name = "categorias")
    val categorias: String?,

    @ColumnInfo(name = "discussao")
    val discussao: String?,

    @ColumnInfo(name = "localizacao")
    val localizacao: String?,

    @Nullable
    @ColumnInfo(name = "criado_em", defaultValue = "NULL")
    val criadoEm: String?,

    @Nullable
    @ColumnInfo(name = "homologada_em", defaultValue = "NULL")
    val homologadaem: String?,

    @Nullable
    @ColumnInfo(name = "deletado_em", defaultValue = "NULL")
    val deletadoEm: String?

    ): Parcelable