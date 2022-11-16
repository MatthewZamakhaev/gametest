package aaa.bbb.playing

import aaa.bbb.playing.databinding.ActivityFirstBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animationRotateCenter: Animation = AnimationUtils.loadAnimation(
            this, R.anim.rotate_center)

        binding.imageView4.startAnimation(animationRotateCenter)
        binding.imageView5.startAnimation(animationRotateCenter)

        binding.imageView4.setOnClickListener {
            binding.imageView4.startAnimation(animationRotateCenter)
            binding.imageView5.startAnimation(animationRotateCenter)
        }

        binding.imageView5.setOnClickListener {
            binding.imageView4.startAnimation(animationRotateCenter)
            binding.imageView5.startAnimation(animationRotateCenter)
        }

        binding.button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}