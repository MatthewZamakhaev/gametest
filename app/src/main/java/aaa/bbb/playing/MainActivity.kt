package aaa.bbb.playing

import aaa.bbb.playing.databinding.ActivityMainBinding
import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var a = 0
    var b = 0
    var count = randomCount()


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        binding.textView.text = "Score: $a"
        binding.textView3.text = "Get $count points for 3 throws"

        binding.bt1.setOnClickListener {
                playGame()
                b += 1
        }

        binding.imageButton.setOnClickListener {
            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
        }
    }

    private fun randomDiceValue(): Int {
        val random = Random()
        return random.nextInt(6) + 1
    }

    private fun randomCount(): Int {
        val random = Random()
        return random.nextInt(36) + 1
    }

    private fun init(){
        val pref: SharedPreferences =
            getSharedPreferences("Data", MODE_PRIVATE)
        a = pref.getInt("2", a)
        count = pref.getInt("3", count)
        b = pref.getInt("4", b)
    }

    @SuppressLint("SetTextI18n")
    private fun temp(){
        binding.bt1.isClickable = false
        binding.textView4.visibility = View.VISIBLE
        Handler().postDelayed({
            a = 0
            binding.textView.text = "Score: $a"
            binding.textView4.visibility = View.INVISIBLE
            count = randomCount()
            binding.textView3.text = "Get $count points for 3 throws"
            binding.bt1.isClickable = true
            b = 1
        }, 3000)
    }

    @SuppressLint("SetTextI18n")
    private fun playGame(){
        val mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.clip)
        val value1 = randomDiceValue()
        val value2 = randomDiceValue()

        mediaPlayer.start()
        binding.textView3.text = "Get $count points for 3 throws"
        val res1 = resources.getIdentifier(
            "dice_$value1",
            "drawable", "aaa.bbb.playing")
        val res2 = resources.getIdentifier(
            "dice_$value2",
            "drawable", "aaa.bbb.playing")

        a += value1 + value2
        if (a >= count && b <= 3) {
            Toast.makeText(applicationContext, "You win!", Toast.LENGTH_SHORT).show()
            temp()
        } else if (b == 3 && a < count) {
            Toast.makeText(applicationContext, "Unfortunately, you have lost!", Toast.LENGTH_SHORT)
                .show()
            temp()
        }
        binding.textView.text = "Score: $a"

        val pref: SharedPreferences =
            getSharedPreferences("Data", MODE_PRIVATE)
        val editor = pref.edit()
        editor.putInt("2", a).apply()
        editor.putInt("3", count).apply()
        editor.putInt("4", b).apply()

        binding.imageView.setImageResource(res1)
        binding.imageView2.setImageResource(res2)
    }
}