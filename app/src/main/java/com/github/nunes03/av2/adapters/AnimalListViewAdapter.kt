package com.github.nunes03.av2.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.github.nunes03.av2.R
import com.github.nunes03.av2.database.entities.AnimalEntity

class AnimalListViewAdapter(
    private val context: Activity,
    private val animals: ArrayList<AnimalEntity>
) :
    ArrayAdapter<AnimalEntity>(context, R.layout.adapter_animal_list_view_adapter, animals) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = this.context.layoutInflater

        val rowView = inflater.inflate(R.layout.adapter_animal_list_view_adapter, null, true)

        val title = rowView.findViewById<TextView>(R.id.titleAnimalListViewAdapter)
        val userInfo = rowView.findViewById<TextView>(R.id.userInfoAnimalListViewAdapter)
        val kindInfo = rowView.findViewById<TextView>(R.id.kindInfoAnimalListViewAdapter)

        title.text = "${this.animals[position].id} - ${this.animals[position].name}"
        userInfo.text = "Usuário: ${this.animals[position].user?.id} - ${this.animals[position].user?.name}"
        kindInfo.text = "Espécie: ${this.animals[position].kind?.id} - ${this.animals[position].kind?.name}"

        return rowView
    }
}