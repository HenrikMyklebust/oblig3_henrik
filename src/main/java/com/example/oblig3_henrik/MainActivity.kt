package com.example.oblig3_henrik

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragNavigation) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration: AppBarConfiguration =
            AppBarConfiguration.Builder(navController.graph).build()
        val myToolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.myToolbar)
        setSupportActionBar(myToolbar)

        NavigationUI.setupWithNavController(myToolbar, navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            title = when (destination.id) {
                R.id.usersFragment -> "Users"
                R.id.albumFragment -> "Albums"
                R.id.photosFragment -> "Images"
                R.id.photoFragment -> "Image"
                else -> "Default"
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (NavigationUI.onNavDestinationSelected(
            item,
            navController
        ) || super.onOptionsItemSelected(item))
    }
}