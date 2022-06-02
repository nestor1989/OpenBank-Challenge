package com.idea3d.open_bank_challengue.repository

import com.idea3d.open_bank_challengue.core.vo.Resource
import com.idea3d.open_bank_challengue.data.DataSource
import com.idea3d.open_bank_challengue.model.Hero

class RepoImpl(private val dataSource: DataSource):Repo {
    override suspend fun getHeroList(heroName:String): Resource<List<Hero>> {
        return dataSource.getHeroList(heroName)
    }

}