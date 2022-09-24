package com.coderz.f1.recyclerviewclickdemo.feature_demo.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.coderz.f1.recyclerviewclickdemo.databinding.ActivityMainBinding
import com.coderz.f1.recyclerviewclickdemo.feature_demo.domain.model.Person
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), PeopleAdapter.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val peopleAdapter = PeopleAdapter(this)

        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = peopleAdapter
                setHasFixedSize(true)
            }
        }

        viewModel.clickedPerson.observe(this as LifecycleOwner) { person ->
            binding.textView.text = person.fullName
        }

        viewModel.people.observe(this as LifecycleOwner) {
            peopleAdapter.submitList(it)
        }
    }

    override fun onItemClick(person: Person) {
        viewModel.onEvent(ClickedEvent.ItemClicked(person))
    }
}