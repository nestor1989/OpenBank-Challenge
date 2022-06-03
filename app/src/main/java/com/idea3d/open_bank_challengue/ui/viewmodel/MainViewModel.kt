package com.idea3d.open_bank_challengue.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.idea3d.open_bank_challengue.core.vo.Resource
import com.idea3d.open_bank_challengue.repository.Repo
import kotlinx.coroutines.Dispatchers

class MainViewModel (private val repo: Repo): ViewModel() {

    val fetchHerosList= liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getHeroList("name"))
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }
}