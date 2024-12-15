package com.mobileassistant.smartvisionbuildnblog.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mobileassistant.smartvisionbuildnblog.R
import com.mobileassistant.smartvisionbuildnblog.databinding.FragmentHomeBinding
import com.mobileassistant.smartvisionbuildnblog.model.MenuItem
import com.mobileassistant.smartvisionbuildnblogui.home.DashboardAdapter

private const val NO_OF_COLUMNS = 2

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupUi()
        return root
    }

    private fun setupUi() {
        with(binding.recyclerView) {
            layoutManager = GridLayoutManager(context, NO_OF_COLUMNS)
            val items: List<MenuItem> = listOf(
                MenuItem(
                    getString(R.string.menu_item_3),
                    R.drawable.object_detection_icon,
                    ::navigateToDetectObjects
                ), MenuItem(
                    getString(R.string.menu_item_5),
                    R.drawable.barcode_code_scanner,
                    ::navigateToBarcodeScanner
                )
            )
            val adapter = DashboardAdapter(context, items)
            setAdapter(adapter)
        }
    }

    private fun navigateToDetectObjects() = findNavController().navigate(R.id.nav_object_detection)

    private fun navigateToBarcodeScanner() = findNavController().navigate(R.id.nav_scan_code)


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}