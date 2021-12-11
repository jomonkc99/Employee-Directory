package com.wrg.directory.repositories.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.wrg.directory.repositories.dao.EmployeeDao
import com.wrg.directory.repositories.model.Employee

@Database(entities = [Employee::class], version = 5, exportSchema = true)
abstract class EmployeeDatabase: RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

    companion object {
        private var instance: EmployeeDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): EmployeeDatabase {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, EmployeeDatabase::class.java,
                    "employee_database")
                    .fallbackToDestructiveMigration()
                    .build()

            return instance!!

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                //populateDatabase(instance!!)
            }
        }

    }

}