package com.example.snapshots

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.snapshots.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding        : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupBottomNav()
    }

    private fun setupBottomNav(){
        mBinding.bnvNavegacion.setOnItemSelectedListener {
            findNavController(R.id.nav_host_fragment).navigateUp()
            when(it.itemId){
                R.id.action_home -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)
                }
                R.id.action_add -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.addFragment)
                }
                R.id.action_profile -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.profileFragment)
                }
            }
            true
        }
    }
}