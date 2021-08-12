package com.example.registration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.registration.models.Course

class coursesAdapter( var courselister:List<Course>):RecyclerView.Adapter<coursesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): coursesViewHolder {
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.course_list_item,parent,false)
        return coursesViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: coursesViewHolder, position: Int) {
        var currentCourse=courselister.get(position)
        holder.tvcoursename.text=currentCourse.coursename
        holder.tvcoursecode.text=currentCourse.courseCode
        holder.tvdescription.text=currentCourse.description
        holder.tvinstructor.text=currentCourse.instructor
    }

    override fun getItemCount(): Int {
        return courselister.count()
    }
}
class coursesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    var tvcoursename=itemView.findViewById<TextView>(R.id.tvcoursename)
    var tvcoursecode=itemView.findViewById<TextView>(R.id.tvcoursecode)
    var tvdescription=itemView.findViewById<TextView>(R.id.tvdescription)
    var tvinstructor=itemView.findViewById<TextView>(R.id.tvinstructor)
}