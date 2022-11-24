package com.idea3d.open_bank_challengue.domain

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.idea3d.open_bank_challengue.model.HeroEntity

interface HeroDao {
    @Query("SELECT * FROM HeroEntity ")
    suspend fun getAllFavoriteHeroes(): List<HeroEntity>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteHero(hero: HeroEntity)
}