package com.example.mithosaanjh.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.mithosaanjh.R
import com.example.mithosaanjh.databinding.ActivityHomeBinding
import com.example.mithosaanjh.ui.fragment.DashboardFragment
import com.example.mithosaanjh.ui.fragment.NotificationFragment
import com.example.mithosaanjh.ui.fragment.ProfileFragment
import com.example.mithosaanjh.ui.fragment.SearchFragment

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager : FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameBottom, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(DashboardFragment())

        binding.bottomView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navHome -> replaceFragment(DashboardFragment())
                R.id.navSearch -> replaceFragment(SearchFragment())
                R.id.navNotification -> replaceFragment(NotificationFragment())
                R.id.navPerson -> replaceFragment(ProfileFragment())
                else -> {}
            }
            true
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}