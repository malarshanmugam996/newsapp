package com.example.newsapp.data.local


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.data.model.Article


@Database(
    entities = [ArticleEntity::class], // ✅ ONLY ENTITY
    version = 2,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,

                    "app_database"
                ).fallbackToDestructiveMigration() // ✅ REQUIRED
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

