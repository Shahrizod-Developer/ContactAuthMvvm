package uz.gita.contactauth.data.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*

interface BaseDao<T> {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t: T)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: List<T>)

    @Update
    fun update(t: T)

    @Update
    fun update(data: List<T>)

    @Delete
    fun delete(t: T)

    @Query("Delete from contact")
    fun delete()

    @Delete
    fun delete(data: List<T>)

}