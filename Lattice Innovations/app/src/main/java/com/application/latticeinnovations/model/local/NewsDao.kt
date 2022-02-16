package com.application.latticeinnovations.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.application.latticeinnovations.model.remote.Article

/**
 * Dao class to save in the Room Data Base
 */
@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(article: List<Article>)


    @Query("select * from article where source like :query")
    fun getDb(query:String) : LiveData<List<Article>>
}