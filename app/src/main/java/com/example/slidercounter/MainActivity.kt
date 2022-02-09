package com.example.slidercounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.slidercounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        val initialTextViewTranslationY = binding.textViewProgress.translationY

        binding.seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.textViewProgress.text = progress.toString()

                val translationDistance = (initialTextViewTranslationY +
                        progress * resources.getDimension(R.dimen.text_anim_step) * -1)

                binding.textViewProgress.animate().translationY(translationDistance)

                if(!fromUser) {
                    binding.textViewProgress.animate().setDuration(500).rotationBy(360f)
                        .translationY(initialTextViewTranslationY)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        binding.buttonReset.setOnClickListener{ v ->
            binding.seekBar.progress = 0
        }
    }
}