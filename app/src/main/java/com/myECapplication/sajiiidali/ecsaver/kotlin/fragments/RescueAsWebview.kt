package com.myECapplication.sajiiidali.ecsaver.kotlin.fragments

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.content.IntentFilter
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.myECapplication.sajiiidali.ecsaver.R
import com.myECapplication.sajiiidali.ecsaver.databinding.ShowWebviewLayoutBinding
import com.myECapplication.sajiiidali.ecsaver.kotlin.BroadCastReceiver.OnDownloadComplete
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class RescueAsWebview : Fragment() {
    private var mRewardedAd: RewardedAd? = null
    private var _binding: ShowWebviewLayoutBinding? = null
    private val binding get() = _binding
    var executor: ExecutorService? = null
    var handler : Handler? = null
    var onDownloadComplete : OnDownloadComplete? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ShowWebviewLayoutBinding.inflate(inflater, container, false)
        return binding!!.root

    }
    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        executor = Executors.newSingleThreadExecutor()
        handler = Handler(Looper.getMainLooper())
        onDownloadComplete = OnDownloadComplete()
        loadRewardedAds()


        val args = RescueAsWebviewArgs.fromBundle(requireArguments())

        binding?.webview?.settings?.javaScriptEnabled = true
        binding?.webview?.settings?.loadWithOverviewMode = true
        binding?.webview?.settings?.useWideViewPort = true
        binding?.webview?.settings?.builtInZoomControls = true
        binding?.webview?.webViewClient = MyWebViewClient(binding?.progressCircular!!)
        binding?.webview?.loadUrl(args.url!!)

        binding?.webview?.setDownloadListener { url, contentDisposition, mimeType, _, contentLength ->
            if (url != null){
                executor?.execute {
                    val request = DownloadManager.Request(Uri.parse(url))
                    request.allowScanningByMediaScanner()
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED) //Notify client once download is completed!
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url,contentDisposition,mimeType))

                    val dm = requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
                    dm!!.enqueue(request)
                    handler?.post {
                        showRewardAds()
                        Toast.makeText(activity, "See Notification Panel", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun loadRewardedAds() {
        val adRequest = AdRequest.Builder().build()
        RewardedAd.load(requireContext(),resources.getString(R.string.Rewarded_ad), adRequest, object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mRewardedAd = null
            }

            override fun onAdLoaded(rewardedAd: RewardedAd) {
                mRewardedAd = rewardedAd
            }
        })
    }
    private fun showRewardAds() {
        if (mRewardedAd != null) {
            mRewardedAd?.show(requireActivity()) {
                fun onUserEarnedReward(rewardItem: RewardItem) {
                    var rewardAmount = rewardItem.amount
                    var rewardType = rewardItem.type
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        requireActivity().unregisterReceiver(onDownloadComplete)
    }
    override fun onPause() {
        super.onPause()
        executor?.shutdown()
    }
    override fun onResume() {
        super.onResume()
        requireActivity().registerReceiver(onDownloadComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }
}
class MyWebViewClient( progressBar: ProgressBar) : WebViewClient() {
    private val myProgressBar = progressBar

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        myProgressBar.visibility = View.VISIBLE
        super.onPageStarted(view, url, favicon)
    }
    override fun onPageFinished(view: WebView?, url: String?) {
        myProgressBar.visibility = View.GONE
    }


}