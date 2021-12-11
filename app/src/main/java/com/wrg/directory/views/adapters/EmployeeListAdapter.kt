package com.wrg.directory.views.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wrg.directory.R
import com.wrg.directory.repositories.model.Employee
import com.wrg.directory.views.activities.DetailsActivity

class EmployeeListAdapter(private var context: Context, private var employeeList: List<Employee>): RecyclerView.Adapter<EmployeeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_employee, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee = employeeList.get(position)
        holder.txtName.setText(employee.name)
        holder.txtCompanyName.setText(employee.company?.name)
        Glide.with(context).load(employee.profile_image).into(holder.imgProfile)

        holder.layoutContainer.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("id", employee.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imgProfile: ImageView = view.findViewById(R.id.imgProfile)
        var txtName: TextView = view.findViewById(R.id.txtName)
        var txtCompanyName: TextView = view.findViewById(R.id.txtCompanyName)
        var layoutContainer: LinearLayout = view.findViewById(R.id.layoutContainer)
    }
}