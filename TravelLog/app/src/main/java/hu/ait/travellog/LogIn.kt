package hu.ait.travellog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import hu.ait.travellog.databinding.ActivityLogInBinding

class LogIn : AppCompatActivity() {

    lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun loginClick(v: View) {
        //throw RuntimeException("Demo exception")


        if (!isFormValid()){
            return
        }

        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            binding.etEmail.text.toString(), binding.etPassword.text.toString()
        ).addOnSuccessListener {
            Toast.makeText(this@LogIn,
                "Login OK",
                Toast.LENGTH_LONG).show()

            // navigate to other Activity
            startActivity(Intent(this, MarkerDetails::class.java))

        }.addOnFailureListener{
            Toast.makeText(this@LogIn,
                "Error: ${it.message}",
                Toast.LENGTH_LONG).show()
        }

    }

    fun registerClick(v: View){
        if (!isFormValid()){
            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            binding.etEmail.text.toString(), binding.etPassword.text.toString()
        ).addOnSuccessListener {
            Toast.makeText(this@LogIn,
                "Registration OK",
                Toast.LENGTH_LONG).show()
        }.addOnFailureListener{
            Toast.makeText(this@LogIn,
                "Error: ${it.message}",
                Toast.LENGTH_LONG).show()
        }
    }

    fun isFormValid(): Boolean {
        return when {
            binding.etEmail.text.isEmpty() -> {
                binding.etEmail.error = "This field can not be empty"
                false
            }
            binding.etPassword.text.isEmpty() -> {
                binding.etPassword.error = "The password can not be empty"
                false
            }
            else -> true
        }
    }
}