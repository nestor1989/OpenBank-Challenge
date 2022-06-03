package com.idea3d.open_bank_challengue.data

import com.idea3d.open_bank_challengue.core.vo.Resource
import com.idea3d.open_bank_challengue.core.vo.RetrofitClient
import com.idea3d.open_bank_challengue.model.Hero

class DataSource {

    suspend fun getHeroList (heroName:String): Resource<List<Hero>> {
        return Resource.Success(RetrofitClient.webService.GetHeroList(heroName).results!!.heroList)
    }
}
