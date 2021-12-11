package com.wrg.directory.views.activities

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wrg.directory.R
import com.wrg.directory.repositories.model.Employee
import com.wrg.directory.repositories.repository.EmployeeRepository
import com.wrg.directory.repositories.service.EmployeeDatabase
import com.wrg.directory.views.adapters.EmployeeListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Type


class MainActivity : AppCompatActivity() {

    lateinit var employeeListAdapter: EmployeeListAdapter
    lateinit var employees: List<Employee>
    var recylce_employees: List<Employee> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AsyncTask.execute {
            employees = EmployeeDatabase.getInstance(this).employeeDao().getAllEmployees()

            if (employees.size > 0) {
                setEmployeeList(employees)
            }
            else {
                getEmployeeList()
            }
        }
        setSearch()
    }

    fun getEmployeeList() {

        runOnUiThread {
            EmployeeRepository.getEmployeeList(this).observe(this, Observer {
                if (it!=null) {
                    val gson = Gson()
                    val type: Type = object : TypeToken<List<Employee?>?>() {}.type
                    val employeeList: List<Employee> = gson.fromJson(it.toString(), type)
                    AsyncTask.execute {
                        EmployeeDatabase.getInstance(this).employeeDao().insertAllEmployees(employeeList)
                        employees = EmployeeDatabase.getInstance(this).employeeDao().getAllEmployees()
                        setEmployeeList(employees)
                    }
                }

            })
        }
    }

    fun setEmployeeList(list: List<Employee>) {
        runOnUiThread {
            recylce_employees = list
            val layoutManager = LinearLayoutManager(applicationContext)
            rvEmployees.layoutManager = layoutManager
            rvEmployees.itemAnimator = DefaultItemAnimator()
            employeeListAdapter = EmployeeListAdapter(this, recylce_employees)
            rvEmployees.adapter = employeeListAdapter
        }
    }

    fun setSearch() {
        edtSearch.addTextChangedListener(afterTextChanged = { editable ->
            doSearch(editable.toString())
        })
    }

    fun doSearch(searchTerm: String) {
        val searchList= ArrayList<Employee>()
        for (employee in employees){
            if (employee.name?.contains(searchTerm, true) == true || employee.email?.contains(searchTerm, true) == true) {
                searchList.add(employee)
            }
        }
        recylce_employees = searchList
        employeeListAdapter = EmployeeListAdapter(this, recylce_employees)
        rvEmployees.adapter = employeeListAdapter
    }
}