package com.example.suntimeshouses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.suntimeshouses.databinding.ActivityAltaUsuarioBinding
import com.google.firebase.auth.FirebaseAuth

// Pantalla para el alta de usuario
class AltaUsuario : AppCompatActivity() {
/* Llamamos al binding para que podamos acceder a los objetos que vamos a necesitar
    Llamamos al Firebase, para subir los datos a la base de datos.*/
    private lateinit var binding: ActivityAltaUsuarioBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAltaUsuarioBinding.inflate(layoutInflater)
        // Iniciamos binding
        setContentView(binding.root)

        //Iniciamos Firebase
        firebaseAuth = FirebaseAuth.getInstance()

        binding.idRegistrar.setOnClickListener{
            //Recogemos datos
            val email = binding.idNuevoUsuario.text.toString()
            val password = binding.idNuevaContra.text.toString()
            val confirmPassword = binding.idRepeNuevaContra.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if (password == confirmPassword){
                /* Si no hay ningún campo vacío y las 2 contraseñas coinciden, los datos se actualizan
                 en Firebase en tiempo real y pasamos ya a la pantalla inicial para introducir nuestros datos*/

                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                        if (it.isSuccessful){
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "La contraseña no coincide", Toast.LENGTH_LONG).show()
                }

            } else {
                Toast.makeText(this, "ERROR: Campos vacíos", Toast.LENGTH_LONG).show()
            }

        }
        // Si ya somos usuarios, volvemos a la pantalla principal
        binding.idEresUsuario.setOnClickListener{
            val intentUsuario = Intent(this, MainActivity::class.java)
            startActivity(intentUsuario)
        }
    }
}