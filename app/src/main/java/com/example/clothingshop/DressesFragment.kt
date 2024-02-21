package com.example.clothingshop

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clothingshop.adapter.DressesAdapter
import com.example.clothingshop.database.AppDB
import com.example.clothingshop.modelFactory.DressesRespository
import com.example.clothingshop.modelFactory.DressesViewModelFactory
import com.example.clothingshop.viewModel.DressesViewModel

class DressesFragment : Fragment(R.layout.dressesitem) {

    private lateinit var _dressesViewModel: DressesViewModel
    private lateinit var _dressesAdapter:DressesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Your initialization code here
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnDressesListCart = view.findViewById<Button>(R.id.BtndressListCart)
        btnDressesListCart.setOnClickListener {
            val intent = Intent(requireContext(), CartActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(_inflater : LayoutInflater, _container : ViewGroup?, savedInstanceState: Bundle?) : View? {
        val view = _inflater.inflate(R.layout.dresseslist, _container, false)

        val dressesRecyclerView = view.findViewById<RecyclerView>(R.id.dress_cycle_list)
        _dressesAdapter = DressesAdapter(requireContext())
        dressesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        dressesRecyclerView.adapter = _dressesAdapter

        // Initialize the ViewModel
        val dressesDao = AppDB.getDatabase(requireContext()).dressesDao()
        val dressesRepository = DressesRespository(dressesDao)
        _dressesViewModel = ViewModelProvider(this,DressesViewModelFactory(dressesRepository))
            .get(DressesViewModel::class.java)

        // Observe the LiveData from the ViewModel
        _dressesViewModel.allDressesInfo.observe(viewLifecycleOwner, Observer { dressesList ->
            _dressesAdapter.submitList(dressesList)
        })
        return view
    }
}