package com.wheeliechamp.webscraping.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
        @PrimaryKey(autoGenerate = true) val uid: Int,
        @ColumnInfo(name = "first_name") val firstName: String?,
        @ColumnInfo(name = "last_name") val lastName: String?
)

/*
@Entity
class Person(name: String, mail: String, age: Int) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
    var name: String = name
    var mail: String = mail
    var age: Int = age

    fun age_s(): String = age.toString()
    fun to_s(): String = "id: $name ($mail, $age)"
}
*/