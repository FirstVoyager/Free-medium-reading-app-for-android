package limitless.android.freemediumreadingapp.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.*
import limitless.android.freemediumreadingapp.R
import limitless.android.freemediumreadingapp.databinding.ActivityMediumBinding
import java.net.CookieHandler

class MediumActivity : AppCompatActivity() {

    companion object {
        val url = "url_link"
    }

    lateinit var binding: ActivityMediumBinding
    var chromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            if (newProgress >= 85) {
                binding.pb.visibility = View.INVISIBLE
                binding.webView.visibility = View.VISIBLE
                binding.loading.visibility = View.INVISIBLE
            }else{
                binding.pb.visibility = View.VISIBLE
            }
            binding.pb.setProgress(newProgress)
        }
    }
    var webViewClient = object : WebViewClient(){
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            CookieManager.getInstance().removeAllCookies(null)
            return false
//            return super.shouldOverrideUrlLoading(view, request)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CookieManager.getInstance().removeAllCookies(null)
        binding =  ActivityMediumBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val link = intent.getStringExtra(url)
        if (link == null) {
            finish()
            return;
        }
        binding.webView.loadUrl(link)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = webViewClient
        binding.webView.webChromeClient = chromeClient
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack())
            binding.webView.goBack()
        else
            super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

}