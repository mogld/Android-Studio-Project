package com.example.proj1.kotlin

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.RECEIVER_EXPORTED
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.proj1.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.util.UUID

class NewsletterFragment : Fragment() {
    private var textViewNewsletterContent: TextView? = null
    private var downloadID: Long = -1
    private lateinit var fileName: String

    init {
        val uuid = UUID.randomUUID().toString()
        fileName = "newsletter_$uuid.pdf"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_newsletter, container, false)


        val downloadButton = view.findViewById<Button>(R.id.downloadButton)
        downloadButton.setOnClickListener {
            downloadNewsletter()
        }

        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()
        context?.registerReceiver(
            onComplete,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE), RECEIVER_EXPORTED
        )
    }

    override fun onStop() {
        super.onStop()
        context?.unregisterReceiver(onComplete)
    }

    private val onComplete = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)

            if (downloadID == id) {
                Toast.makeText(requireContext(), "다운로드가 완료되었습니다.", Toast.LENGTH_SHORT).show()

                openDownloadedFile()

            }
        }
    }

    private fun downloadNewsletter() {
        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            val request = DownloadManager.Request(
                Uri.parse("https://dportal.kdca.go.kr/pot/component/file/ND_fileDownload.do?q_fileSn=1302819&q_fileId=9a378033-b8d2-4e88-a954-0e59cde15e45")
            )
                .setTitle(fileName)
                .setDescription("Downloading")
                .setMimeType("application/pdf")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,
                    fileName
                )
            val downloadManager =
                requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            downloadID = downloadManager.enqueue(request)
        }
    }

    private fun openDownloadedFile() {
        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            fileName
        )
        val uri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.fileprovider",
            file
        )

        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(uri, "application/pdf")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "파일을 여는 데 실패했습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}
