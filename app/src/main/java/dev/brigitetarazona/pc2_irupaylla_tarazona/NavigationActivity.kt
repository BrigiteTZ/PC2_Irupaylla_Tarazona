package dev.brigitetarazona.pc2_irupaylla_tarazona

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dev.brigitetarazona.pc2_irupaylla_tarazona.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarNavigation.toolbar)

        binding.appBarNavigation.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_navigation)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top-level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController) // Automáticamente maneja nav_home, nav_gallery y nav_slideshow

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow -> {
                    // Permite que el NavController maneje estos ítems
                    val handled = menuItem.onNavDestinationSelected(navController)
                    drawerLayout.closeDrawer(navView)
                    handled
                }
                R.id.nav_register_user -> {
                    // Maneja manualmente el ítem "Registrar Usuario"
                    val intent = Intent(this, RegisterActivity::class.java)
                    startActivity(intent)
                    drawerLayout.closeDrawer(navView)
                    true
                }
                else -> {
                    drawerLayout.closeDrawer(navView)
                    false
                }
            }
        }






        // Actualiza los títulos de los ítems
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        val menu = navigationView.menu
        menu.findItem(R.id.nav_home).title = "Principal"
        menu.findItem(R.id.nav_gallery).title = "Registro de Movimientos"
        menu.findItem(R.id.nav_slideshow).title = "Resumen Financiero"
        menu.findItem(R.id.nav_register_user).title = "Registrar Usuario"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.navigation, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_navigation)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
