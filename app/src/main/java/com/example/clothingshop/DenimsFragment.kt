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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clothingshop.adapter.denimsAdapter
import com.example.clothingshop.database.AppDB
import com.example.clothingshop.modelFactory.DenimsModelFactory
import com.example.clothingshop.modelFactory.DenimsRespository
import com.example.clothingshop.viewModel.DenimsViewModel

class DenimsFragment : Fragment(R.layout.denimslist) {

    private lateinit var _denimsViewModel: DenimsViewModel
    private lateinit var _denimsAdapter: denimsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Your initialization code here
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnDenimsListCart = view.findViewById<Button>(R.id.denimViewCart)
        btnDenimsListCart.setOnClickListener {
            val intent = Intent(requireContext(), CartActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.denimslist, container, false)

        val denimsRecyclerView = view.findViewById<RecyclerView>(R.id.denim_Item_List)
        _denimsAdapter = denimsAdapter(requireContext())
        denimsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        denimsRecyclerView.adapter = _denimsAdapter

        // Initialize the ViewModel
        val denimsDao = AppDB.getDatabase(requireContext()).denimsDao()
        val denimsRepository = DenimsRespository(denimsDao)
        _denimsViewModel = ViewModelProvider(this, DenimsModelFactory(denimsRepository))
            .get(DenimsViewModel::class.java)

        // Observe the LiveData from the ViewModel
        _denimsViewModel.allDenimsInfo.observe(viewLifecycleOwner, Observer { denimsList ->
            _denimsAdapter.submitList(denimsList)
        })

        return view
    }

}