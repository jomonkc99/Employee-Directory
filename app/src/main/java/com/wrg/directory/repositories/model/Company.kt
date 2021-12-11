package com.wrg.directory.repositories.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class Company(@ColumnInfo(name = "company_name") val name: String?,
                   val catchPhrase: String?,
                   val bs: String?) {
}