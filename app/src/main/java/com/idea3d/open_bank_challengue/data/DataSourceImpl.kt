package com.idea3d.open_bank_challengue.data

import com.idea3d.open_bank_challengue.core.AppDataBase
import com.idea3d.open_bank_challengue.core.vo.Resource
import com.idea3d.open_bank_challengue.domain.DataSource
import com.idea3d.open_bank_challengue.domain.HeroDao
import com.idea3d.open_bank_challengue.domain.WebService
import com.idea3d.open_bank_challengue.model.ComicDetails
import com.idea3d.open_bank_challengue.model.Hero
import com.idea3d.open_bank_challengue.model.HeroDetails
import com.idea3d.open_bank_challengue.model.HeroEntity
import javax.inject.Inject

class DataSourceImpl @Inject constructor(
    private val retrofit: WebService,
    private val heroDao: HeroDao
    ):DataSource {

    override suspend fun getHeroList (heroName:String): Resource<List<Hero>> {
        return Resource.Success(retrofit.GetHeroList(heroName).results!!.heroList)
    }

    override suspend fun getHeroById (id:Long?): Resource<HeroDetails>{
        return Resource.Success(retrofit.GetHeroById(id).results.heroDetails.get(0))
    }

    override suspend fun getComics (id:Long?): Resource<List<ComicDetails>>{
        return Resource.Success(retrofit.GetComics(id).results.comicList)
    }

    override suspend fun insertHero(heroEntity: HeroEntity) {
        heroDao.addFavoriteHero(heroEntity)
    }

    override suspend fun getFavoriteHeroes(): Resource<List<HeroEntity>> {
        return Resource.Success(heroDao.getAllFavoriteHeroes())
    }
}
