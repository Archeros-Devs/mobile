package br.com.arquerosdev

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.MenuItem
import android.view.animation.DecelerateInterpolator
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
import com.google.android.gms.maps.GoogleMap.OnCameraMoveListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main_crash.*
import kotlinx.android.synthetic.main.drawer_header.*
import java.io.IOException

class MainActivityCrash : NavigationDrawer(), OnMapReadyCallback,
    GoogleMap.OnInfoWindowClickListener, GoogleMap.OnCameraIdleListener, OnCameraMoveListener {
    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private lateinit var markerNovaPasta: Marker
    private var markerMap: HashMap<String, Int> = HashMap();
    private val PeruibeLocation = LatLng(-24.3173, -46.9956)
    private var modal = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_crash)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        btnDrawer.setOnClickListener { view -> drawer.openDrawer(GravityCompat.START) }
        drawer_user_name?.text = Prefs.getString("nome_usuario")

        btnCloseModal.setOnClickListener { v -> onCloseModal()}
        btnNovaPasta.setOnClickListener{ v ->
            val it = Intent(this, CriarPastaActivity::class.java)
            it.putExtra("localizacao", map.cameraPosition.target)
            startActivity(it)
        }

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.pg_explorar -> {
                    // Respond to navigation item 1 click
                    true
                }
                R.id.pg_criar -> {
                    onCriarPasta()
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

    override fun onResume() {
        super.onResume()
        callPastas()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.uiSettings.isZoomControlsEnabled = true
        map.setOnInfoWindowClickListener(this)
        map.setOnCameraIdleListener(this);
        map.setOnCameraMoveListener(this);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(PeruibeLocation, 14f))
        markerNovaPasta = map.addMarker(
            MarkerOptions()
                .position(map.cameraPosition.target)
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .title("Nova Pasta")
                .visible(false)
        )
        setPastasMarkers()
        findMyLocation()
    }

    private fun setPastasMarkers() {
        val pastaViewModel: PastaViewModel = ViewModelProvider(this).get(PastaViewModel::class.java)
        pastaViewModel.modelPasta.observe(this, Observer { listaPasta ->
            listaPasta.forEach {
                run {
                    placeMarkerOnMap(
                        it.id_pasta,
                        it.nome!!,
                        it.discussao!!,
                        LatLng(it.latitude, it.longitude)
                    )
                }
            }
        })
    }

    private fun findMyLocation() {
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_favoritos -> null
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun onCriarPasta() {
        //startActivity(Intent(this, CriarPastaActivity::class.java))
        modal = true
        cardCriarPasta.animate()
            .translationY(0F)
            .alpha(1F)
            .setDuration(500)
            .setInterpolator(DecelerateInterpolator())

        markerNovaPasta.isVisible = true
        markerNovaPasta.position = map.cameraPosition.target
    }

    private fun onCloseModal() {
        modal = false
        markerNovaPasta.isVisible = false
        cardCriarPasta.animate()
            .translationY(200F)
            .alpha(0F)
            .setDuration(500)
            .setInterpolator(DecelerateInterpolator())
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

    override fun onBackPressed() {
        if(modal) onCloseModal()
        else super.onBackPressed()
    }

    private fun getAddress(latLng: LatLng): String {
        try {
            val geocoder = Geocoder(this)
            val list = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            val addressText = list[0].getAddressLine(0)
            return addressText
        } catch (e: IOException) {
            Log.e("MapsActivity", e.localizedMessage)
        }
        return ""
    }

    override fun onCameraMove() {
        if (modal) markerNovaPasta.position = map.cameraPosition.target
    }

    override fun onCameraIdle() {
        if (modal) {
            val address = getAddress(map.cameraPosition.target)
            text_endereco.text = address
        }
    }
}
