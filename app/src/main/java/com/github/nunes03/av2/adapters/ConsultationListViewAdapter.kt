package com.github.nunes03.av2.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.github.nunes03.av2.R
import com.github.nunes03.av2.database.entities.ConsultationEntity

class ConsultationListViewAdapter(
    private val context: Activity,
    private val consultations: ArrayList<ConsultationEntity>
) : ArrayAdapter<ConsultationEntity>(
    context,
    R.layout.adapter_consultation_list_view_adapter,
    consultations
) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = this.context.layoutInflater

        val rowView = inflater.inflate(R.layout.adapter_consultation_list_view_adapter, null, true)

        val title = rowView.findViewById<TextView>(R.id.titleConsultationListViewAdapter)
        val scheduledTime = rowView.findViewById<TextView>(R.id.scheduledTimeConsultationListViewAdapter)
        val animalInfo = rowView.findViewById<TextView>(R.id.animalInfoConsultationListViewAdapter)

        title.text =
            "${this.consultations[position].id} - ${this.consultations[position].description}"
        scheduledTime.text =
            "Horário: ${this.consultations[position].scheduledTime.toString()}"
        animalInfo.text =
            "Animal: ${this.consultations[position].animal?.id} - ${this.consultations[position].animal?.name}"

        return rowView
    }
}