package com.idea3d.open_bank_challengue.repository

import com.idea3d.open_bank_challengue.core.vo.Resource
import com.idea3d.open_bank_challengue.data.DataSource
import com.idea3d.open_bank_challengue.model.ComicDetails
import com.idea3d.open_bank_challengue.model.Hero
import com.idea3d.open_bank_challengue.model.HeroDetails

class RepoImpl(private val dataSource: DataSource):Repo {
    override suspend fun getHeroList(heroName:String): Resource<List<Hero>> {
        return dataSource.getHeroList(heroName)
    }

    override suspend fun getHeroById(id: Long?): Resource<HeroDetails> {
        return dataSource.getHeroById(id)
    }

    override suspend fun getComics(id: Long?): Resource<List<ComicDetails>> {
        return dataSource.getComics(id)
    }

}