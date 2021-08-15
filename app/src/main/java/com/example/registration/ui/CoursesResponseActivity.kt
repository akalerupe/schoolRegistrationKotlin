package com.example.registration.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.registration.R
import com.example.registration.models.CoursesResponse

class CoursesResponseActivity( var coursesResponseList:List<CoursesResponse>):RecyclerView.Adapter<coursesResponseViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): coursesResponseViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.courses_response_list_item,parent,false)
        return  coursesResponseViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: coursesResponseViewHolder, position: Int) {
        var currentCourse=coursesResponseList.get(position)
        holder.tvdatecreated.text=currentCourse.date_created
        holder.tvdateupdate.text=currentCourse.date_updated
        holder.tvcreator.text=currentCourse.created_by
        holder.tvupdator.text=currentCourse.updated_by
        holder.tvactive.text= currentCourse.active.toString()
        holder.tvcourseId.text=currentCourse.course_id
        holder.tvcourseName.text=currentCourse.course_name
        holder.tvdescription.text=currentCourse.description
        holder.tvinstructor.text=currentCourse.course_instructor
    }

    override fun getItemCount(): Int {
        return coursesResponseList.count()
    }
}
class coursesResponseViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    var tvdatecreated=itemView.findViewById<TextView>(R.id.tvdatecreated)
    var tvdateupdate=itemView.findViewById<TextView>(R.id.tvdateupdate)
    var tvcreator=itemView.findViewById<TextView>(R.id.tvcreator)
    var tvupdator=itemView.findViewById<TextView>(R.id.tvupdator)
    var tvactive=itemView.findViewById<TextView>(R.id.tvactive)
    var tvcourseId=itemView.findViewById<TextView>(R.id.tvcourseId)
    var tvcourseName=itemView.findViewById<TextView>(R.id.tvcourseName)
    var tvdescription=itemView.findViewById<TextView>(R.id.tvcoursedescription)
    var tvinstructor=itemView.findViewById<TextView>(R.id.tvinstructor1)
}

