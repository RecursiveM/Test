package com.ncgr.maqsaf.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.ncgr.maqsaf.MyApplication
import com.ncgr.maqsaf.domain.auth.model.UserPreference
import com.ncgr.maqsaf.domain.auth.repository.SharedPreferenceRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SharedPreferenceRepositoryImp : SharedPreferenceRepository {
    override fun saveUser(token: String, uid:String,selectedZoneColor:String): Flow<Resource<Boolean>> =
        flow {
            emit(Resource.Loading())

            val sharedPreferences: SharedPreferences =
                MyApplication.instance.getSharedPreferences("myPref", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.apply {
                this.putString("Token", token)
                this.putString("Uid", uid)
                if (selectedZoneColor != "") this.putString("ZoneColor", selectedZoneColor)
            }.apply()

            emit(Resource.Success(true))
        }

    override fun deleteSavedUser(): Flow<Resource<Boolean>> =
        flow {
            emit(Resource.Loading())

            val sharedPreferences: SharedPreferences =
                MyApplication.instance.getSharedPreferences("myPref", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.apply {
                this.clear()
            }.apply()

            emit(Resource.Success(true))
        }

    override fun getSavedUser(): Flow<Resource<UserPreference>> =
        flow {
            emit(Resource.Loading())

            val sharedPreferences: SharedPreferences =
                MyApplication.instance.getSharedPreferences("myPref", Context.MODE_PRIVATE)
            val allPrefs = sharedPreferences.all

            val userPrefs = UserPreference(
                allPrefs["Token"].toString(),
                allPrefs["Uid"].toString(),
                allPrefs["ZoneColor"].toString(),
            )

            emit(Resource.Success(userPrefs))
        }
}