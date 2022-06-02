package com.idea3d.open_bank_challengue.repository

import com.idea3d.open_bank_challengue.core.vo.Resource
import com.idea3d.open_bank_challengue.model.Hero

interface Repo {

    suspend fun getHeroList(heroName:String): Resource<List<Hero>>

}