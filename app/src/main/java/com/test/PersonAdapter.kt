package com.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.databinding.PersonItemBinding

class PersonAdapter(
    val personList: ArrayList<Person>,
    val onPersonSelectedListener: OnPersonSelectedListener
) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {


    class PersonViewHolder(val personItemBinding: PersonItemBinding) :
        RecyclerView.ViewHolder(personItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val personItemBinding =
            PersonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(personItemBinding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.personItemBinding.tvName.text = personList[position].name

        holder.personItemBinding.tvName.setOnClickListener {
            if (!personList[position].isAlredySelected) {
                personList[position].isAlredySelected = true
                onPersonSelectedListener.onPersonSelect(personList[position].name,false)
            }
            else{
                onPersonSelectedListener.onPersonSelect(personList[position].name,true)
                personList[position].isAlredySelected = false
            }
        }
    }

    override fun getItemCount(): Int = personList.size

    interface OnPersonSelectedListener {

        fun onPersonSelect(name: String,isSelected : Boolean)
    }
}