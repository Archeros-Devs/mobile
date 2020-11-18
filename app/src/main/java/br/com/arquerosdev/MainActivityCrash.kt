package br.com.arquerosdev

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.arquerosdev.model.ModelPasta
import br.com.arquerosdev.retrofit.service.APIsWebClient
import br.com.arquerosdev.retrofit.service.CallbackResponse
import br.com.arquerosdev.viewmodel.PastaViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main_crash.*
import kotlinx.android.synthetic.main.drawer_header.*
import kotlin.concurrent.thread

class MainActivityCrash : NavigationDrawer(), OnMapReadyCallback,
    GoogleMap.OnInfoWindowClickListener {
    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private var markerMap: HashMap<String, Int> = HashMap();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_crash)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        btnDrawer.setOnClickListener { view -> drawer.openDrawer(GravityCompat.START) }
        drawer_user_name?.text = Prefs.getString("nome_usuario")

        //btListarPastas.setOnClickListener { view ->
        //    startActivity(Intent(this,PastaListaActivity::class.java))
        //}

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.pg_explorar -> {
                    // Respond to navigation item 1 click
                    true
                }
                R.id.pg_criar -> {
                    startActivity(Intent(this, CriarPastaActivity::class.java))
                    false
                }
                R.id.pg_seguindo -> {
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.getUiSettings().setZoomControlsEnabled(true)
        map.setOnInfoWindowClickListener(this)
        val currentLatLng = LatLng(-24.3173, -46.9956)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 14f))
        setMapMarkers()
        setUpMap()
    }

    private fun setMapMarkers() {
        val pastaViewModel: PastaViewModel = ViewModelProvider(this).get(PastaViewModel::class.java)
        pastaViewModel.modelPasta.observe(this, Observer { listaPasta ->
            listaPasta.forEach {
                run {
                    placeMarkerOnMap(
                        it.id_pasta,
                        it.nome!!,
                        it.descricao!!,
                        LatLng(it.latitude, it.longitude)
                    )
                }
            }
        })
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            return
        }

        map.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 16f))
            }
        }
    }

    private fun placeMarkerOnMap(
        id_pasta: Int,
        title: String = "",
        descricao: String = "",
        location: LatLng
    ) {
        val markerOptions = MarkerOptions()
            .position(location)
            .snippet(descricao)
            .title(title)
        val marker = map.addMarker(markerOptions)
        markerMap.put(marker.id, id_pasta)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_favoritos -> null
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onResume() {
        super.onResume()
        callPastas()
    }

    private fun callPastas(){
        if(!Prefs.getString("token").isNullOrEmpty()){
            val pastaViewModel: PastaViewModel = ViewModelProvider(this)
                .get(PastaViewModel::class.java)
            APIsWebClient().listPastas(object : CallbackResponse<JsonObject> {
                override fun sucess(response: JsonObject) {

                    val listaPasta = response.getAsJsonArray("pastas")

                    val gson = Gson()
                    val typeResponse = object : TypeToken<List<ModelPasta>>() {}.type
                    val listModelPasta: List<ModelPasta> = gson.fromJson(listaPasta, typeResponse)
                    pastaViewModel.inserList(listModelPasta)
                }

                override fun error(response: String) {
                    Toast.makeText(
                        this@MainActivityCrash,
                        "Falha ao baixar as pastas!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }

    override fun onInfoWindowClick(marker: Marker?) {
        if (marker == null) return
        Log.e("teste", marker.title.toString())

        val id_pasta: Int = markerMap[marker.id] ?: return
        val pastaViewModel: PastaViewModel = ViewModelProvider(this).get(PastaViewModel::class.java)
        Thread {
            val pasta = pastaViewModel.getPasta(id_pasta)
            runOnUiThread {
                val it = Intent(this@MainActivityCrash, PastaPerfilActivity::class.java)
                it.putExtra("pasta", pasta as Parcelable)
                startActivity(it)
            }
        }.start()

    }
}
