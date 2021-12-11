package com.wrg.directory.views.activities

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.wrg.directory.R
import com.wrg.directory.repositories.model.Employee
import com.wrg.directory.repositories.service.EmployeeDatabase
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        if (intent!=null) {

            val id = intent.getIntExtra("id", 0)

            AsyncTask.execute {
                val employee: Employee = EmployeeDatabase.getInstance(this).employeeDao().getEmployee(id)

                runOnUiThread {
                    txtName.setText(employee.name)
                    txtCompanyName.setText(employee.company?.name)
                    txtUsername.setText(employee.username)
                    txtEmail.setText(employee.email)
                    txtPhone.setText(employee.phone)
                    txtWeb.setText(employee.website)
                    txtAddress.setText(employee.address?.street + ", " + employee.address?.suite + ", " + employee.address?.city + ", " + employee.address?.zipcode)
                    if (employee.company != null) {
                        txtCompany.setText(employee.company.name + ", " + employee.company.catchPhrase + ", " + employee.company.bs)
                    }
                    Glide.with(this).load(employee.profile_image).into(imgProfile)
                }
            }
        }
        btnBack.setOnClickListener { finish() }
    }
}