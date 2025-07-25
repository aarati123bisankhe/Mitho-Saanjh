package com.example.mithosaanjh.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mithosaanjh.R
import com.example.mithosaanjh.databinding.FragmentDashboardBinding
import com.example.mithosaanjh.ui.activity.TableBookingActivity

class DashboardFragment : Fragment() {

    lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bookATableButton.setOnClickListener({
            val intent = Intent(requireContext(), TableBookingActivity::class.java)
            startActivity(intent)
        })

        binding.button.setOnClickListener {
            val fragment = NotificationFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameBottom, fragment)  // Replace with your fragment container ID
            transaction.addToBackStack(null)
            transaction.commit()
        }


    }


}