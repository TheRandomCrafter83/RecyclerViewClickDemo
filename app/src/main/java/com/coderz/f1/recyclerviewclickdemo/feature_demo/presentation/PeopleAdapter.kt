package com.coderz.f1.recyclerviewclickdemo.feature_demo.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coderz.f1.recyclerviewclickdemo.databinding.PersonItemBinding
import com.coderz.f1.recyclerviewclickdemo.feature_demo.domain.model.Person

class PeopleAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Person, PeopleAdapter.PersonViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = PersonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class PersonViewHolder(private val binding: PersonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val person = getItem(position)
                        listener.onItemClick(person)
                    }
                }
            }
        }

        fun bind(person: Person) {
            binding.apply {
                textviewName.text = person.fullName
                textviewPhone.text = person.phoneNumber
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(person: Person)
    }

    class DiffCallback : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean =
            oldItem.firstName == newItem.firstName

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean =
            oldItem == newItem

    }
}