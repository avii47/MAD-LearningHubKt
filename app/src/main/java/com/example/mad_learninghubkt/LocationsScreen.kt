package com.example.mad_learninghubkt

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.AppOpsManagerCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState


//defining the location LatLng
val slState = LatLng(
    7.8731, 80.7718
)
val defaultCameraPosition = CameraPosition.fromLatLngZoom(slState, 7f)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun LocationsScreen() {
    Scaffold {
        Text(
            text = "Our Locations",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
        )
    }

    val cameraPositionState = rememberCameraPositionState{
        position = defaultCameraPosition
    }

    var isMapLoaded by remember {
        mutableStateOf(false)
    }

    Box(Modifier.fillMaxSize()) {
        GoogleMapView(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 100.dp),
            cameraPositionState = cameraPositionState,
            onMapLoaded = {
                isMapLoaded = true
            }
        )
        if (!isMapLoaded) {
            AnimatedVisibility(visible = !isMapLoaded) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun GoogleMapView(
    modifier: Modifier = Modifier,
    cameraPositionState: CameraPositionState,
    onMapLoaded: () -> Unit)
{
    val locationState = rememberMarkerState(
        position = slState
    )

    val mapUiSettings by remember {
        mutableStateOf(MapUiSettings(compassEnabled = false))
    }

    val mapProperties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    var showInfoWindow by remember {
        mutableStateOf(true)
    }

    GoogleMap(
        modifier = modifier,
        onMapLoaded = onMapLoaded,
        cameraPositionState = cameraPositionState,
        uiSettings = mapUiSettings,
        properties = mapProperties
    ){

        MarkerInfoWindowContent(
            state = locationState,
            draggable = true,
            onClick = {
                if (showInfoWindow) {
                    locationState.showInfoWindow()
                } else {
                    locationState.hideInfoWindow()
                }
                showInfoWindow = !showInfoWindow
                return@MarkerInfoWindowContent false
            },
            title = "India Map Title"
        ) {
            Column {
                Text(text = "NIBM Sri Lanka")
                Text(text = "any text1")
                Text(text = "any text2")

            }
        }

        //simple marker
//        Marker(
//            state = locationState,
//            draggable = true,
//            onClick = {
//                if (showInfoWindow) {
//                    locationState.showInfoWindow()
//                } else {
//                    locationState.hideInfoWindow()
//                }
//                showInfoWindow = !showInfoWindow
//                return@Marker false
//            },
//            title = "Sri Lanka Map Title"
//        )

    }
}


