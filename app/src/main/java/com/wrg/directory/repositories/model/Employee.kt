package com.wrg.directory.repositories.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emp_table")
data class Employee(@PrimaryKey val id: Int,
                    val name: String?,
                    val username: String?,
                    val email: String?,
                    val profile_image: String?,
                    @Embedded val address: Address?,
                    val phone: String?,
                    val website: String?,
                    @Embedded val company: Company?)

