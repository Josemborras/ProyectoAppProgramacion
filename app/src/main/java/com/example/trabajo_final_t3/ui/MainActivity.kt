package com.example.trabajo_final_t3.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.trabajo_final_t3.R
import com.example.trabajo_final_t3.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //sacar el navHost (contenedor) que esta en nuestro layaout
        val navHost = supportFragmentManager.findFragmentById(R.id.navContainer) as NavHost
         navController = navHost.navController


        //Para sincronizar nuestro menu
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.HomeFragment,
                R.id.buscadorIngredientes,
                R.id.ShoppingListFragment
            )
        )

        //setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)
    }

    //metodo para cambiar el toolbar desde cada fragment (no son todos iguales)
    fun changeToolbar(toolbarNew: androidx.appcompat.widget.Toolbar, showBack: Boolean, navController: NavController){
        setSupportActionBar(toolbarNew)
        supportActionBar?.setDisplayHomeAsUpEnabled(showBack)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    //metodo para cambiar el titulo del toolbar desde cada fragment
    fun changeToolbarTitle(title: String){
        supportActionBar?.title = title
    }
}