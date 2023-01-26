package com.example.weathermvvm.data.local

import android.content.Context
import androidx.room.*
import com.example.weathermvvm.data.local.utils.Converters
import com.example.weathermvvm.domain.model.favorite.FavoritePlaces
import com.example.weathermvvm.domain.repository.local.FavoritePlacesDAO

@Database(entities = [FavoritePlaces::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun FavoritePlacesDAO() : FavoritePlacesDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "favorite-places.db").allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}