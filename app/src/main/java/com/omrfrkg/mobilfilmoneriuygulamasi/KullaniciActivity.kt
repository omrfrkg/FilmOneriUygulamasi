package com.omrfrkg.mobilfilmoneriuygulamasi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.omrfrkg.mobilfilmoneriuygulamasi.Fragments.KullaniciAnasayfaFragment
import com.omrfrkg.mobilfilmoneriuygulamasi.databinding.ActivityKullaniciBinding

class KullaniciActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKullaniciBinding


    private lateinit var toolbar : Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val fragmentViewModel : fragmentViewModel by viewModels()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val veri = intent.getIntExtra("kID",99)
        val veri2 = intent.getStringExtra("kAdiSoyadi")

       /* val bundle = Bundle()
        bundle.putInt("key", veri)

        val fragment = KullaniciAnasayfaFragment()
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()*/

        fragmentViewModel.setData(veri)

        binding = ActivityKullaniciBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer)
        navigationView = findViewById(R.id.navigationView)

        navController = findNavController(R.id.fragmentContainerView)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.id_kullaniciAnasayfa_fragment,R.id.id_kullaniciFilmOneriAyarSayfasi_fragment,R.id.id_kullaniciFavori_fragment,R.id.id_duyuruSayfasi_fragment,R.id.id_kullaniciAyar_fragment,R.id.id_kullaniciCikisYap_fragment),drawerLayout)

        setupActionBarWithNavController(navController,drawerLayout)

        navigationView.setupWithNavController(navController)



        var navigationView2 : NavigationView = findViewById(R.id.navigationView)
        var headerView = navigationView2.getHeaderView(0)

        var tvHeaderName : TextView = headerView.findViewById(R.id.kullaniciAdi)

        tvHeaderName.setText("Hoşgeldin "+veri2);

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}