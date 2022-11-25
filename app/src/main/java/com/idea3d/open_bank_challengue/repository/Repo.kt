package com.idea3d.open_bank_challengue.repository

import com.idea3d.open_bank_challengue.core.vo.Resource
import com.idea3d.open_bank_challengue.model.ComicDetails
import com.idea3d.open_bank_challengue.model.Hero
import com.idea3d.open_bank_challengue.model.HeroDetails
import com.idea3d.open_bank_challengue.model.HeroEntity

interface Repo {

    suspend fun getHeroList(heroName:String): Resource<List<Hero>>
    suspend fun getHeroById (id:Long?): Resource <HeroDetails>
    suspend fun getComics (id:Long?): Resource<List<ComicDetails>>
    suspend fun getFavHeroes():Resource<List<HeroEntity>>
    suspend fun insertHero(heroEntity: HeroEntity)

}