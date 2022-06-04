package com.idea3d.open_bank_challengue.domain

import com.idea3d.open_bank_challengue.core.Constants.Companion.API_KEY
import com.idea3d.open_bank_challengue.core.Constants.Companion.HASH
import com.idea3d.open_bank_challengue.core.Constants.Companion.TS
import com.idea3d.open_bank_challengue.model.Data
import com.idea3d.open_bank_challengue.model.HeroList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("/v1/public/characters?ts=$TS&limit=100&apikey=$API_KEY&hash=$HASH")

    suspend fun GetHeroList(@Query(value = "nameStartsWith")heroName:String): Data
}