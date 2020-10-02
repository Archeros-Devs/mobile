package br.com.arquerosdev.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.arquerosdev.dao.*
import br.com.arquerosdev.model.*

@Database(entities = [
    ModelUsuario::class,
    ModelEndereco::class,
    ModelProfissao::class,
    ModelArquivo::class,
    ModelCategoria::class,
    ModelEscolaridade::class,
    ModelEstudo::class,
    ModelInteresse::class,
    ModelPasta::class,
    ModelPastaCategoria::class,
    ModelUsuarioAvaliaPasta::class,
    ModelUsuarioSeguePasta::class

], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun UsuarioDao(): UsuarioDao
    abstract fun EnderecoDao(): EnderecoDao
    abstract fun ProfissaoDao(): ProfissaoDao
    abstract fun PastaDao(): PastaDao
    abstract fun PastaCategoriaDao(): PastaCategoriaDao
    abstract fun ArquivoDao(): ArquivoDao
    abstract fun EscolaridadeDao(): EscolaridadeDao
    abstract fun EstudoDao(): EstudoDao
    abstract fun InteresseDao(): InteresseDao
    abstract fun CategoriaDao(): CategoriaDao
    abstract fun UsuarioAvaliaPastaDao(): UsuarioAvaliaPastaDao
    abstract fun UsuarioSeguePastaDao(): UsuarioSeguePastaDao

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