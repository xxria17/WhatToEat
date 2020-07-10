package com.example.whattoeat

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.IOException
import java.lang.IllegalArgumentException
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var gpsTracker: GpsTracker

    private final val GPS_ENABLE_REQUEST_CODE: Int = 2001
    private final val PERMISSION_REQUEST_CODE: Int = 100
    var REQUEST_PERMISSION = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (checkLocationServicesStatus()) {
            checkRunTimePermission()
        } else {
            showDialogForLocationServiceSetting()
        }

        var button: Button = findViewById(R.id.button)

        button.setOnClickListener { view ->
            gpsTracker = GpsTracker(this)
            var latitude: Double = gpsTracker.getLatitude()
            var longitude: Double = gpsTracker.getLongitude()

            var address: String = getCurrentAddress(latitude, longitude)
            Log.d("MainActivity", "Address  :: $address")

            Log.d("MainActivity", "latitude : $latitude  \n longitude : $longitude")

            startActivity(Intent(this, RouletteActivity::class.java))
            finish()
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.size == REQUEST_PERMISSION.size) {
            var check_result : Boolean = true

            for (result: Int in grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false
                    break
                }
            }

            if (check_result) {
                //위치값 가져오기
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUEST_PERMISSION[0])
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUEST_PERMISSION[1])) {
                    Toast.makeText(this, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "퍼미션이 거부되었습니다. 설정에서 퍼미션을 허용해야 합니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun checkRunTimePermission() {
        var hasFineLocationPermission: Int = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        var hasCoarseLocationPermission: Int = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {
            //위치 값 가져올 수 있음
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUEST_PERMISSION[0])) {
                Toast.makeText(this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(this, REQUEST_PERMISSION, PERMISSION_REQUEST_CODE)
            } else {
                ActivityCompat.requestPermissions(this, REQUEST_PERMISSION, PERMISSION_REQUEST_CODE)
            }
        }
    }

    fun getCurrentAddress(latitude: Double, longitude: Double): String {

        var geocoder: Geocoder = Geocoder(this, Locale.getDefault())
        var addresses: List<Address>

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 7)
        } catch (ioException: IOException) {
            Toast.makeText(this, "지오코더 서비스 사용 불가", Toast.LENGTH_SHORT).show()
            return "지오코더 서비스 사용 불가"
        } catch (illegalArgumentException: IllegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_SHORT).show()
            return "잘못된 GPS 좌표"
        }

        if (addresses == null || addresses.isEmpty()) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_SHORT).show()
            return "주소 미발견"
        }

        var address: Address = addresses[0]
        return address.getAddressLine(0).toString() + "\n"
    }


    private fun showDialogForLocationServiceSetting() {
        var builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("위치 서비스 활성화")
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n 위치 설정을 수정하시겠습니까?")
        builder.setCancelable(true)
        builder.setPositiveButton("설정") {
            dialogInterface, i ->  startActivityForResult(Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), GPS_ENABLE_REQUEST_CODE)
        }
        builder.setNegativeButton("취소") {
            dialogInterface, i -> dialogInterface.cancel()
        }
        builder.create().show()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when {
            requestCode == GPS_ENABLE_REQUEST_CODE -> {
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {
                        Log.d("MainActivity", "onActivityResult: GPS 활성화 되었음")
                        checkRunTimePermission()
                        return
                    }
                }
            }
        }
    }

    public fun checkLocationServicesStatus(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

}