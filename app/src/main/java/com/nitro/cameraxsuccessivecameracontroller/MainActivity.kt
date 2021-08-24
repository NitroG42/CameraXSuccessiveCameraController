package com.nitro.cameraxsuccessivecameracontroller

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import com.nitro.cameraxsuccessivecameracontroller.ui.theme.CameraXSuccessiveCameraControllerTheme
import com.nitro.testcamera.CameraPreview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CameraXSuccessiveCameraControllerTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, "home") {
                    composable("home") {
                        Home {
                            navController.navigate("detail")
                        }
                    }
                    composable("detail") {
                        Box {
                            CameraPreview(flashEnabled = false, modifier = Modifier.fillMaxSize())
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Home(nextScreen: () -> Unit) {
    val permission = rememberPermissionState(permission = Manifest.permission.CAMERA)

    PermissionRequired(permissionState = permission, permissionNotGrantedContent = {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Button(onClick = { permission.launchPermissionRequest() }) {
                Text("Authorize Camera")
            }
        }
    }, permissionNotAvailableContent = {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text("Camera unavailable")
        }
    }) {
        Box {
            CameraPreview(flashEnabled = false, modifier = Modifier.fillMaxSize())
            FloatingActionButton(
                onClick = nextScreen,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = -32.dp)
            ) {
                Icon(Icons.Default.ThumbUp, null, tint = MaterialTheme.colors.onPrimary)
            }
        }
    }
}