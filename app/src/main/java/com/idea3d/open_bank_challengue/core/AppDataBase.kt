package com.idea3d.open_bank_challengue.core

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.idea3d.open_bank_challengue.domain.HeroDao
import com.idea3d.open_bank_challengue.model.HeroEntity

@Database(entities = [HeroEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun heroesDao(): HeroDao

    companion object{

        private var INSTANCE: AppDataBase? = null

        fun getDabaBase(context: Context):AppDataBase{
            INSTANCE = INSTANCE ?: Room.databaseBuilder(context.applicationContext, AppDataBase::class.java,  "tabla hero").build()
            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}