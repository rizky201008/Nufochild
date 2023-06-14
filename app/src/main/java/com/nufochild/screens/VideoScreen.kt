/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

@file:Suppress("DEPRECATION")

package com.nufochild.screens

import android.app.Activity
import android.content.pm.ActivityInfo
import android.view.View
import android.view.WindowManager
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun VideoScreen(videoId: String) {
    val activity = LocalContext.current as? Activity
    AndroidView(
        modifier = Modifier
            .fillMaxSize()
            .padding(90.dp)
            .clip(RoundedCornerShape(10.dp)),
        factory = {
            val view = YouTubePlayerView(it)
            view.addYouTubePlayerListener(
                object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        // Set orientasi layar ke landscape
                        activity?.requestedOrientation =
                            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

                        // Set jendela aktif ke mode fullscreen
                        activity?.window?.let { window ->
                            WindowCompat.setDecorFitsSystemWindows(window, false)
                            WindowInsetsControllerCompat(window, view)
                                .systemBarsBehavior =
                                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

                            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                            window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)

                            // Sembunyikan tombol navigasi bawah
                            view.systemUiVisibility = (
                                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
                        }

                        CoroutineScope(Dispatchers.Main).launch {
                            delay(5000) // Menunda selama 3 detik
                            youTubePlayer.loadVideo(videoId, 0f)
                        }
                    }
                }
            )
            view
        })
}

