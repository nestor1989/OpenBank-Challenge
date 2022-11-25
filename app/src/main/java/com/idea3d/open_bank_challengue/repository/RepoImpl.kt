package com.idea3d.open_bank_challengue.repository

import com.idea3d.open_bank_challengue.core.vo.Resource
import com.idea3d.open_bank_challengue.data.DataSourceImpl
import com.idea3d.open_bank_challengue.domain.DataSource
import com.idea3d.open_bank_challengue.model.ComicDetails
import com.idea3d.open_bank_challengue.model.Hero
import com.idea3d.open_bank_challengue.model.HeroDetails
import com.idea3d.open_bank_challengue.model.HeroEntity
import javax.inject.Inject

class RepoImpl @Inject constructor(private val dataSource: DataSource):Repo {
    override suspend fun getHeroList(heroName:String): Resource<List<Hero>> {
        return dataSource.getHeroList(heroName)
    }

    override suspend fun getHeroById(id: Long?): Resource<HeroDetails> {
        return dataSource.getHeroById(id)
    }

    override suspend fun getComics(id: Long?): Resource<List<ComicDetails>> {
        return dataSource.getComics(id)
    }

    override suspend fun getFavHeroes(): Resource<List<HeroEntity>> {
        return dataSource.getFavoriteHeroes()
    }

    override suspend fun insertHero(heroEntity: HeroEntity) {
        dataSource.insertHero(heroEntity)
    }

}