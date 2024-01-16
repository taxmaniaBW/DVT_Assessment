package com.utick.dvtcodingassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.utick.dvtcodingassessment.ui.HomeScreen
import com.utick.dvtcodingassessment.ui.theme.DVTCodingAssessmentTheme
import android.Manifest.permission.*
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.utick.dvtcodingassessment.ui.util.Permission

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val event: Permission = Permission.Initial
            var permissionEvent by remember { mutableStateOf(event)}
            val locationPermissions = arrayOf(
                ACCESS_FINE_LOCATION,
                ACCESS_COARSE_LOCATION
            )
            val locationPermissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestMultiplePermissions(),
                onResult = { permissions ->
                    val locationPermissionsGranted = permissions.values.reduce { acc, isPermissionGranted ->
                        acc && isPermissionGranted
                    }

                    permissionEvent = when (locationPermissionsGranted) {
                        true ->  Permission.Granted
                        false ->  when(shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)){
                            true -> Permission.ShowRationale
                            false -> Permission.ShouldDirectToSettings()
                        }
                    }

                })
            DVTCodingAssessmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when(permissionEvent) {
                        is Permission.Granted -> {
                            HomeScreen(context = this)
                        }
                        is Permission.ShowRationale -> {
                            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(all = 12.dp))
                            {
                                Text("We need location permissions to give you location based weather forecast✔️")
                                Button(onClick = { locationPermissionLauncher.launch(locationPermissions) }) {
                                    Text("Give permissions")
                                }
                            }

                        }
                        is Permission.ShouldDirectToSettings -> {
                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", packageName, null)).also {
                                startActivity(it)
                            }

                        }
                        else -> {
                            SideEffect {
                                locationPermissionLauncher.launch(locationPermissions)
                            }
                        }
                    }

                }
            }
        }
    }
}
