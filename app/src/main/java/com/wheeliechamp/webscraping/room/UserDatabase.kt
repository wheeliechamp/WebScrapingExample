package com.wheeliechamp.webscraping.room

import android.content.Context
import androidx.room.*
import com.wheeliechamp.webscraping.model.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: UserDatabase? = null
        private val lock = Any()
        fun getInstance(context: Context): UserDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            UserDatabase::class.java, "User.db")
                            .allowMainThreadQueries()
                            .build()
                }
                return INSTANCE!!
            }
        }
    }


}
/*
@Database(entities = arrayOf(Person::class), version = 1)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}
*/