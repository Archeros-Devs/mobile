package br.com.arquerosdev.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.arquerosdev.dao.*
import br.com.arquerosdev.model.ModelEndereco
import br.com.arquerosdev.model.ModelProfissao
import br.com.arquerosdev.model.ModelUsuario

@Database(entities = [ModelUsuario::class, ModelEndereco::class, ModelProfissao::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun UsuarioDao(): UsuarioDao
    abstract fun EnderecoDao(): EnderecoDao
    abstract fun ProfissaoDao(): ProfissaoDao
    abstract fun PastaDao(): PastaDao
    abstract fun PastaCategoriaDao(): PastaCategoriaDao
    abstract fun ArquivoDao(): ArquivoDao
    abstract fun EscolaridadeDao(): EscolaridadeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "peruibe_database.sqlite"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}