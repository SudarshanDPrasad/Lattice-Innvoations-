package com.application.latticeinnovations.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.latticeinnovations.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main Activity to load the Fragment
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}