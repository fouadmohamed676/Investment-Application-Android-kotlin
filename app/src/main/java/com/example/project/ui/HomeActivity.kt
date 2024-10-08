package com.example.project.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project.R
import com.example.project.databinding.ActivityHomeBinding
import com.example.project.fragment.AccountFragment
import com.example.project.fragment.HomeFragment
import com.example.project.fragment.InvestFragment

@Suppress("DEPRECATION", "UNUSED_EXPRESSION")
class HomeActivity :AppCompatActivity(R.layout.activity_home) {
    private lateinit var binding: ActivityHomeBinding
    private var homeFragment = HomeFragment()
    private var investFragment= InvestFragment()
    private var accountFragment= AccountFragment()
    var bundle = Bundle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(homeFragment)

        homeFragment.arguments = bundle
        binding.bottomNavigationView.setOnItemSelectedListener {

            homeFragment.arguments=bundle
            when(it.itemId){

                R.id.invest->{
                    homeFragment.arguments = bundle
                    supportFragmentManager.beginTransaction()
                        .replace((R.id.frame_layout), homeFragment)
                        .commitNow()
                }
                R.id.precedent->{
                    investFragment.arguments = bundle
                    supportFragmentManager.beginTransaction()
                        .replace((R.id.frame_layout), investFragment)
                        .commitNow()
                }
                R.id.account->{
                    accountFragment.arguments = bundle
                    supportFragmentManager.beginTransaction()
                        .replace((R.id.frame_layout), accountFragment)
                        .commitNow()
                }

            }
            true

        }



    }



    private fun replaceFragment(fragment: HomeFragment) {

        val transaction = supportFragmentManager.beginTransaction()
        accountFragment.arguments = bundle
        homeFragment.arguments = bundle
        investFragment.arguments = bundle
        transaction.replace(R.id.frame_layout, fragment)
        transaction.commit()
    }
    private var doubleBackToExitPressedOnce: Boolean = false

    @Suppress("OVERRIDE_DEPRECATION")
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            finish()
            finishAffinity()
            super.finish()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "إضغط مره اخرى للخروج", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            doubleBackToExitPressedOnce = false
        }, 2000)
    }
}