package com.wrg.directory.repositories.model

import androidx.room.Entity

@Entity
data class Geo(val lat: String?,
               val lng: String?,) {
}