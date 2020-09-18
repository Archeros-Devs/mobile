package br.com.arquerosdev.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.arquerosdev.dao.EnderecoDao
import br.com.arquerosdev.dao.UsuarioDao
import br.com.arquerosdev.model.ModelEndereco
import br.com.arquerosdev.model.ModelUsuario

@Database(entities = [ModelUsuario::class, ModelEndereco::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun UsuarioDao(): UsuarioDao
    abstract fun EnderecoDao(): EnderecoDao


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
                    "peruibe_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}