package fastcampus.part3.chapter2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fastcampus.part3.chapter2.databinding.ActivityVerifySmsBinding

class VerifyOtpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVerifySmsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifySmsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}