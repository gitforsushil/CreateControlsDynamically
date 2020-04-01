package com.example.createcontrolsdynamically

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//region Code to make app fullscreen and to hide the notification
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )
//endregion

        setContentView(R.layout.activity_main)

//region Code to setup navigation drawer
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_summary, R.id.nav_pmobile, R.id.nav_pickup, R.id.nav_dynamiccontrols
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//endregion

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(
                view,
                "This is a Toast that is created from the Floating Action Bar.",
                Snackbar.LENGTH_LONG
            )
                .setAction("Action", null).show()
        }

//region Add menu on runtime
        var menu = navView.menu
        val runtimeId = 101
        if (menu?.findItem(runtimeId) == null) {
            val runtimeMenuItem: MenuItem = menu!!.add(
                Menu.NONE,  // groupId
                runtimeId,  // itemId
                2,  // order
                "Share Text: Added at runtime" // title
            )
            runtimeMenuItem.setIcon(R.drawable.ic_menu_share);
            runtimeMenuItem.setShowAsActionFlags(
                MenuItem.SHOW_AS_ACTION_WITH_TEXT
            )
            runtimeMenuItem.setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener { menuItem ->

                //Close the open drawer
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }

//                Can use ShareCompat as well
                val shareBody =
                    "This Context Menu was generated programmatically. This text can be shared."
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Share text via Intents.")
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
                startActivity(sharingIntent)

                return@OnMenuItemClickListener true
            })

        }
//endregion

//region Menu navigate to PMobile
        var menuitm: MenuItem = navView.menu.findItem(R.id.nav_pmobile)
        menuitm?.let {
            it?.setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener {

                //Close the open drawer
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                val pm = applicationContext.packageManager
                val intent: Intent? = pm.getLaunchIntentForPackage("PMobile.Android")
                intent?.addCategory(Intent.CATEGORY_LAUNCHER)
                applicationContext.startActivity(intent)
                return@OnMenuItemClickListener true
            })
        }
//endregion

//        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        var view: View? = currentFocus
//        if (view == null) {
//            view = View(this)
//        }
//        imm.hideSoftInputFromWindow(view.windowToken, 0)
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

}
