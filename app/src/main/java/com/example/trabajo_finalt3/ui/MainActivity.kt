import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.trabajo_finalt3.R
import com.example.trabajo_finalt3.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.navContainer) as NavHost
        val navController = navHost.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.bottomNav.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navContainer)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun changeToolbar(toolbarNew: Toolbar, showBack: Boolean, navController: NavController){
        setSupportActionBar(toolbarNew)
        supportActionBar?.setDisplayHomeAsUpEnabled(showBack)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    fun changeToolbarTitle(title: String){
        supportActionBar?.title = title
    }
}