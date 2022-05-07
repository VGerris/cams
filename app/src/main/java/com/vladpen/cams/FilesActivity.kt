package com.vladpen.cams

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vladpen.*
import com.vladpen.cams.databinding.ActivityFilesBinding

class FilesActivity: AppCompatActivity() {
    private val binding by lazy { ActivityFilesBinding.inflate(layoutInflater) }
    private lateinit var remotePath: String
    private var streamId: Int = -1
    private lateinit var stream: StreamDataModel
    private var sftpData: UrlDataModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initActivity()
    }

    private fun initActivity() {
        streamId = intent.getIntExtra("streamId", -1)
        stream = StreamData.getById(streamId) ?: return

        sftpData = Utils.parseUrl(stream.sftp, 22)
        if (sftpData == null) {
            videoScreen()
            return
        }
        remotePath = intent.getStringExtra("remotePath") ?: sftpData!!.path

        binding.toolbar.btnBack.setOnClickListener {
            back()
        }
        this.onBackPressedDispatcher.addCallback(callback)

        val label = binding.toolbar.tvToolbarLabel
        label.text = stream.name
        if (remotePath != sftpData!!.path) {
            Effects.setTextViewClickable(this, label, R.color.files_link)
            label.setOnClickListener {
                filesHome()
            }
        }
        binding.toolbar.tvToolbarLink.text = getString(R.string.live)
        binding.toolbar.tvToolbarLink.setTextColor(getColor(R.color.live_link))
        binding.toolbar.tvToolbarLink.setOnClickListener {
            videoScreen()
        }

        val files = FileData(this, stream.sftp).getFiles(remotePath)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = FilesAdapter(files, remotePath, streamId, stream.sftp)
    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            back()
        }
    }

    private fun back() {
        if (sftpData == null || remotePath == sftpData?.path) {
            videoScreen()
        } else {
            val intent = Intent(this, FilesActivity::class.java)
                .putExtra("streamId", streamId)
                .putExtra("remotePath", FileData.getParentPath(remotePath))
            startActivity(intent)
        }
    }

    private fun filesHome() {
        val intent = Intent(this, FilesActivity::class.java)
            .putExtra("streamId", streamId)
        startActivity(intent)
    }

    private fun videoScreen() {
        val intent = Intent(this, VideoActivity::class.java)
            .putExtra("streamId", streamId)
        startActivity(intent)
    }
}
