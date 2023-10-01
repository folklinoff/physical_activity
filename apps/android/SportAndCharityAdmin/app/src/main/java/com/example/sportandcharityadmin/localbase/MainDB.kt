package com.example.sportandcharityadmin.localbase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sportandcharityadmin.localbase.entities.CardDataItem
import com.example.sportandcharityadmin.localbase.entities.PathDataItem
import com.example.sportandcharityadmin.localbase.entities.WorkShiftDataItem
import net.sqlcipher.database.SupportFactory


@Database(
    entities = [CardDataItem::class, PathDataItem::class, WorkShiftDataItem::class],
    version = 1,
    exportSchema = false
)
abstract class MainDB : RoomDatabase() {
    abstract fun getDao(): MainDao

    companion object {
        @Volatile
        private var INSTANCE: MainDB? = null
        fun getDatabase(context: Context, supportFactory: SupportFactory): MainDB {
            return INSTANCE ?: synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDB::class.java,
                    "main.db"
                ).openHelperFactory(supportFactory)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }


        }

    }
}