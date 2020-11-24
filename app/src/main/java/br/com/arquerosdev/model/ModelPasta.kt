package br.com.arquerosdev.model

import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "Pasta",primaryKeys = ["id_origem", "id_pasta"])
data class ModelPasta (
    @ColumnInfo(name = "id_origem")
    val id_origem: Long,

    @ColumnInfo(name = "id_pasta")
    val id_pasta: Int,

    @ColumnInfo(name = "id_usuario")
    val id_usuario: Int,

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
    val criado_em: String?,

    @Nullable
    @ColumnInfo(name = "homologada_em", defaultValue = "NULL")
    val homologada_em: String?,

    @Nullable
    @ColumnInfo(name = "deletado_em", defaultValue = "NULL")
    val deletado_em: String?,

    @ColumnInfo(name = "latitude")
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    val longitude: Double,

    @ColumnInfo(name = "criador")
    val criador: String?

    ): Parcelable