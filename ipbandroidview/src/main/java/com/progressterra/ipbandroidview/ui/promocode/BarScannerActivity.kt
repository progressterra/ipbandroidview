package com.progressterra.ipbandroidview.ui.promocode

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.budiyev.android.codescanner.CodeScanner
import com.progressterra.ipbandroidview.databinding.ActivityBarScannerLibBinding

internal class BarScannerActivity : AppCompatActivity() {

    private var _binding: ActivityBarScannerLibBinding? = null
    private val binding: ActivityBarScannerLibBinding
        get() = _binding!!

    private val codeScanner: CodeScanner by lazy {
        CodeScanner(this, binding.scannerView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBarScannerLibBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding.btnBack.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        codeScanner.isAutoFocusEnabled = true
        codeScanner.setDecodeCallback {
            if (it.text.isNotEmpty()) {
                runOnUiThread {
                    val intent = Intent()
                    intent.putExtra(PromoCodeFragment.INTENT_EXTRA_NAME, it.text)
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onPause()
    }
}