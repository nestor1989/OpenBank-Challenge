package com.idea3d.open_bank_challengue.data

import com.idea3d.open_bank_challengue.core.vo.Resource
import com.idea3d.open_bank_challengue.core.vo.RetrofitClient
import com.idea3d.open_bank_challengue.model.ComicDetails
import com.idea3d.open_bank_challengue.model.ComicList
import com.idea3d.open_bank_challengue.model.Hero
import com.idea3d.open_bank_challengue.model.HeroDetails

class DataSource {

    suspend fun getHeroList (heroName:String): Resource<List<Hero>> {
        return Resource.Success(RetrofitClient.webService.GetHeroList(heroName).results!!.heroList)
    }

    suspend fun getHeroById (id:Long?): Resource<HeroDetails>{
        return Resource.Success(RetrofitClient.webService.GetHeroById(id).results.heroDetails.get(0))
    }

    suspend fun getComics (id:Long?): Resource<List<ComicDetails>>{
        return Resource.Success(RetrofitClient.webService.GetComics(id).results.comicList)
    }
}
