 package com.example.suntimeshouses

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.suntimeshouses.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


 class MainActivity : AppCompatActivity() {
     private lateinit var binding: ActivityMainBinding
     private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        screenSplash.setKeepOnScreenCondition{ false }

        /* He utilizado un pequeño video de fondo guardado en la nube desde Firebase, para
        la pantalla de login, pero ralentizaba la aplicación, asíque desistí por realizarlo
        Pero el código desactivado es totalmente funcional si se cambia el ImageView de
        la activity_main por un videoView.

         val videoView = binding.videoFondo
      val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)

        val onlineUri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/suntimes-houses-2023.appspot.com/o/Cascada.mp4?alt=media&token=61e30d5b-4c10-40e1-afc9-e471eb46e5ec4")
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(onlineUri)
        videoView.requestFocus()
        videoView.start()

        */

        // Recogemos los datos
        val txtmail = binding.CampoEmail.editText
        val txtpass = binding.CampoContra.editText
        val btnentrar = binding.btnEntrar
        firebaseAuth=Firebase.auth
        btnentrar.setOnClickListener {
            signIn(txtmail?.text.toString(),txtpass?.text.toString())
        }

    /* Comando si olvidamos la contraseña. Nos sale un Alertdialog, con un campo para
    para que podamos introducir nuestro mail y reestablecer una nueva contraseña.
    Totalmente funcional. Pruébenla
     */
        binding.idolvidarPassword.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.mensaje_forgot,null)
            val mailUsuario = view.findViewById<EditText>(R.id.idEditMailUsuario)

            builder.setView(view)
            val dialog = builder.create()

            //BOTON ACEPTAR
            view.findViewById<Button>(R.id.btnAceptar).setOnClickListener {
                compararEmail(mailUsuario)
                dialog.dismiss()
            }

            //BOTON CANCELAR
            view.findViewById<Button>(R.id.btnCancel).setOnClickListener {
                //Si CANCELA
                dialog.dismiss()
            }
            if(dialog.window !=null){
                dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            }
            dialog.show()
        }

        //Opción que nos permite ir a la pantalla de Nuevo Usuario = "activity_alta_usuario.xml"
        binding.idNuevoUsuario.setOnClickListener(){
            val intent = Intent(this, AltaUsuario::class.java)
            startActivity(intent)
        }

    }
// Comparamos los datos introducidos con los datos guardados en nuestra base de datos (mail y contraseña)
     private fun signIn(email: String, password: String){
         firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
             task -> if (task.isSuccessful){
               //  val user = firebaseAuth.currentUser  "Esto lo ponemos si queremos que se nos muestre el uid del usuario
             //También podemos enviar un Toast con el uid del usuario "+ user?.uid.toString()"
             Toast.makeText(baseContext, " BIENVENIDO "  , Toast.LENGTH_SHORT).show()
             val galeriaintent = Intent(this, GaleriaHouses::class.java)
             startActivity(galeriaintent)
         }
             else{
                 Toast.makeText(baseContext, "Contraseña o Mail incorrectos", Toast.LENGTH_SHORT).show()
             }
         }

     }
//Comparamos el mail que el usuario introdujo, para reestablecer su contraseña
     private fun compararEmail(email: EditText){
    // Si el campo mail está vacío, que salga
         if (email.text.toString().isEmpty()){
             return
         }
        // Si el Mail no existe, que salga
         if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
             return
         }
        /* Función de Firebase, que nos permite realizar el reestablecimiento de nuestra contraseña
           Si la base de datos comprueba que tiene ese mail, se envía un link al mail del usuario*/
         firebaseAuth.sendPasswordResetEmail(email.text.toString()).addOnCompleteListener{
             task->
             if(task.isSuccessful){
                 Toast.makeText(this, "Revisa tu correo", Toast.LENGTH_SHORT).show()
             }
         }
     }

}