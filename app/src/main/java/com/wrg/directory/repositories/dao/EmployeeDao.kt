package com.wrg.directory.repositories.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wrg.directory.repositories.model.Employee

@Dao
interface EmployeeDao {

    @Query("select * from emp_table order by id asc")
    fun getAllEmployees(): List<Employee>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllEmployees(order: List<Employee?>?)

    @Query("select * from emp_table where id == :id")
    fun getEmployee(id: Int): Employee
}