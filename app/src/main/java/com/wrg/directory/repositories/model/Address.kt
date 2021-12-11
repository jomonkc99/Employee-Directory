package com.wrg.directory.repositories.model

import androidx.room.Embedded
import androidx.room.Entity

@Entity
data class Address(val street: String?,
                   val suite: String?,
                   val city: String?,
                   val zipcode: String?,
                   @Embedded val geo: Geo?) {
}
