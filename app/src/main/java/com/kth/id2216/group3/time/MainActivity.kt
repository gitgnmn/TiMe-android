package com.kth.id2216.group3.time

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint


/**
 * Activity handling the main part of the app
 * Contains several fragments : [.CategoryFragment], [.CreateTimerFragment], [.CategoriesFragment]
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    //val EXTRA_MESSAGE = "com.kth.id2216.group3.time.extra.MESSAGE"

    //private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_new_category, R.id.nav_settings
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { navController.navigate(R.id.createTimer) }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in arrayOf(R.id.createTimer, R.id.nav_categories))
                fab.hide()
            else
                fab.show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun launchTimerActivity(view: View?) {
        val intent = Intent(applicationContext, TimerActivity::class.java)
        val id = view?.findViewById<View>(R.id.item_timer_id) as TextView
        intent.putExtra("Timer id", id.text.toString())
        Log.d("Switching Activity", "TimerActivity with id: " + id.text.toString())
        startActivity(intent)
    }

    fun toggleTimer(view: View?) {
        var timerIsRunning = 1  // Stuck on first listItem Need to be a global value
        val btnStart = findViewById<Button>(R.id.item_button_start) // Stuck on first listItem
        val btnPause = findViewById<Button>(R.id.item_button_stop) // Stuck on first listItem

        if (timerIsRunning == 1) {
            btnPause.visibility = View.VISIBLE
            btnStart.visibility = View.INVISIBLE
        } else if (timerIsRunning == 0) {
            btnPause.visibility = View.INVISIBLE
            btnStart.visibility = View.VISIBLE
        }
    }
}