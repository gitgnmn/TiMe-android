package com.kth.id2216.group3.time

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.ViewGroup
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
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint


/**
 * Activity handling the main part of the app
 * Contains several fragments : [.CategoryFragment], [.CreateTimerFragment], [.CategoriesFragment]
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
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
                        R.id.nav_home, R.id.nav_settings
                ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

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
        Log.d("Switching Activity", "TimerActivity")
        val intent = Intent(this@MainActivity, TimerActivity::class.java)

        // Send the data to timerActivity
        val hourView:TextView = (view as ViewGroup).getChildAt(1) as TextView
        val hours = hourView.text.toString()
        println("This should be the hours: $hours")
        intent.putExtra("hours", hours)

        val goalView:TextView = (view as ViewGroup).getChildAt(2) as TextView
        val goal = goalView.text.toString()
        println("This should be the goal: $hours")
        intent.putExtra("goal", goal)

        val nameView:TextView = (view as ViewGroup).getChildAt(3) as TextView
        val name = nameView.text.toString()
        println("This should be the name: $name")
        intent.putExtra("name", name)

        val categoryView:TextView = (view as ViewGroup).getChildAt(4) as TextView
        val category = categoryView.text.toString()
        println("This should be the category: $category")
        intent.putExtra("category", category)
        // End of putting extra data into intent

        startActivity(intent)
    }

    fun toggleTimer(view: View?){
        var timerIsRunning = 1  // Stuck on first listItem Need to be a global value
        val btnStart = findViewById(R.id.item_button_start) as Button // Stuck on first listItem
        val btnPause = findViewById(R.id.item_button_stop) as Button // Stuck on first listItem

        if(timerIsRunning == 1) {
            btnPause.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.INVISIBLE);
        }
        else if(timerIsRunning == 0){
            btnPause.setVisibility(View.INVISIBLE);
            btnStart.setVisibility(View.VISIBLE);
        }
    }
}