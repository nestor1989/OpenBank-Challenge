package com.idea3d.open_bank_challengue.data

import com.idea3d.open_bank_challengue.core.vo.Resource
import com.idea3d.open_bank_challengue.domain.DataSource
import com.idea3d.open_bank_challengue.domain.WebService
import com.idea3d.open_bank_challengue.model.ComicDetails
import com.idea3d.open_bank_challengue.model.Hero
import com.idea3d.open_bank_challengue.model.HeroDetails
import javax.inject.Inject

class DataSourceImpl @Inject constructor(private val retrofit: WebService):DataSource {

    override suspend fun getHeroList (heroName:String): Resource<List<Hero>> {
        return Resource.Success(retrofit.GetHeroList(heroName).results!!.heroList)
    }

    override suspend fun getHeroById (id:Long?): Resource<HeroDetails>{
        return Resource.Success(retrofit.GetHeroById(id).results.heroDetails.get(0))
    }

    override suspend fun getComics (id:Long?): Resource<List<ComicDetails>>{
        return Resource.Success(retrofit.GetComics(id).results.comicList)
    }
}
