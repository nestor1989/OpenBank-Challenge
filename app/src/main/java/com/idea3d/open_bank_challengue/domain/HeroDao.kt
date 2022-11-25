package com.idea3d.open_bank_challengue.domain

import androidx.room.*
import com.idea3d.open_bank_challengue.model.HeroEntity

@Dao
interface HeroDao {
    @Query("SELECT * FROM HeroEntity ")
    suspend fun getAllFavoriteHeroes(): List<HeroEntity>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteHero(hero: HeroEntity)
}