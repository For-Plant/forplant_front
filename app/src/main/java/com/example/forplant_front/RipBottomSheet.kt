package com.example.forplant_front

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forplant_front.databinding.RipbottomsheetdialogBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RipBottomSheet : BottomSheetDialogFragment() {
    private lateinit var recyclerView: RecyclerView
    private var adapter: RipRecordAdapter? = null
    private lateinit var binding: RipbottomsheetdialogBinding
    lateinit var onItemSelected: (List<String>) -> Unit
    private var initialY = 0f

//    // 선택된 아이템을 반환하는 메소드
//    private val selectedItems = mutableListOf<String>()
//
//    fun getSelectedItems(): List<String> {
//        return adapter?.getSelectedItems() ?: emptyList()
//    }

    private fun getBinding(view: View): RipbottomsheetdialogBinding {
        return RipbottomsheetdialogBinding.bind(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.ripbottomsheetdialog, container, false)
        binding = getBinding(view)

        recyclerView = view.findViewById(R.id.pose_rv)
        adapter = RipRecordAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        recyclerView.adapter = adapter

        Log.d("RipBottomSheet", "RecyclerView and Adapter set")

        return view
    }

}