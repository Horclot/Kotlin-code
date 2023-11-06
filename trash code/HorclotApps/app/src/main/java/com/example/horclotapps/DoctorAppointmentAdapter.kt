package com.example.horclotapps

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class DoctorAppointmentAdapter(
    context: Context,
    resource: Int,
    objects: List<DoctorAppointment>
) : ArrayAdapter<DoctorAppointment>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val doctorAppointment = getItem(position)
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.list_item_layout, null)

        val doctorNameTextView = view.findViewById<TextView>(R.id.doctorNameTextView)
        val appointmentDateTextView = view.findViewById<TextView>(R.id.appointmentDateTextView)

        // Установите текст для TextView согласно данным из DoctorAppointment
        doctorNameTextView.text = doctorAppointment?.doctorName
        appointmentDateTextView.text = doctorAppointment?.appointmentDate

        return view
    }
}