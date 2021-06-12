package com.wheeliechamp.webscraping.room

import androidx.room.*
import com.wheeliechamp.webscraping.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}
/*
@Dao
interface PersonDao {
    @Query("SELECT * FROM person")
    fun loadAllPeaple(): Array<Person>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPerson(person: Person)
}
*/