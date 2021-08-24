package com.nitro.testcamera

import androidx.camera.core.CameraSelector
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CameraPreview(flashEnabled: Boolean, modifier: Modifier = Modifier) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val controller = remember {
        LifecycleCameraController(context).apply {
            cameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build()
        }
    }
    DisposableEffect(true) {
        controller.bindToLifecycle(lifecycleOwner)
        onDispose {
        }
    }

    controller.enableTorch(flashEnabled)

    AndroidView(
        factory = { ctx ->
            PreviewView(ctx).also {
                it.controller = controller
            }
        },
        modifier = modifier
    )
}