package br.com.arquerosdev.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.arquerosdev.dao.UsuarioDao
import br.com.arquerosdev.model.ModelUsuario

@Database(entities = arrayOf(ModelUsuario::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun UsuarioDao(): UsuarioDao

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