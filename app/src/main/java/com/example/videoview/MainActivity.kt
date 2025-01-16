package com.example.videoview

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.videoview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val videoList = listOf(R.raw.videoone, R.raw.videotwo, R.raw.videothree)
    private var currentVideoIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        playVideo(currentVideoIndex)


        binding.btnStop.setOnClickListener {
            binding.videoView.stopPlayback()
        }

        binding.btnNext.setOnClickListener {
            currentVideoIndex = (currentVideoIndex + 1) % videoList.size
            playVideo(currentVideoIndex)
        }

        binding.btnPrevious.setOnClickListener {
            currentVideoIndex = (currentVideoIndex - 1 + videoList.size) % videoList.size
            playVideo(currentVideoIndex)
        }


        binding.videoView.setOnClickListener {
            if (binding.videoView.isPlaying) {
                binding.videoView.pause()
            } else {
                binding.videoView.start()
            }
        }
    }

    private fun playVideo(index: Int) {
        binding.videoView.setVideoURI(Uri.parse("android.resource://${packageName}/${videoList[index]}"))
        binding.videoView.start()
    }
}