package com.example.suntimeshouses

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suntimeshouses.adapter.CasasAdapter
import com.example.suntimeshouses.adapter.CasasProvider
import com.example.suntimeshouses.databinding.ActivityGaleriaHousesBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*

class GaleriaHouses : AppCompatActivity() {

    /* En esta pantalla se ven las listas de las casas que ya están disponibles, según la fecha
    que introduzca el usuario*/
    private lateinit var binding: ActivityGaleriaHousesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGaleriaHousesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /* para empezar, iniciamos el Calendario, para que el usuario elija el rango de días en los
        cuales va a necesitar una estancia*/
        showDateRangerPicker()

        //Iniciamos el RecycleView
        initRecycleView()
    }


// Configuración de Calendario
    private fun showDateRangerPicker(){
        //Le ponemos un título
        val dateRangePicker = MaterialDatePicker.Builder
            .dateRangePicker()
            .setTitleText("Seleccione tiempo de estancia")
            .build()


        // Llamamos al Fragment
        dateRangePicker.show(
            supportFragmentManager,"date_range_picker"
        )

    // Recogemos las fechas elegidas por el usuario
        dateRangePicker.addOnPositiveButtonClickListener { datePicked ->

            val fechaComienzo = datePicked.first
            val fechaFinal = datePicked.second

            if (fechaComienzo != null && fechaFinal != null){

                binding.idFechaEntrada.text = conversionFecha(fechaComienzo)
                binding.idFechaSalida.text= conversionFecha(fechaFinal)
            }
        }


    }

    //Convertimos las fechas de formato
    private fun conversionFecha(time:Long):String{

        val date = Date(time)
        val format = SimpleDateFormat( "dd-MM-yyyy", Locale.getDefault())

        return  format.format(date)
    }

// Configuración del RecycleView
    private fun initRecycleView() {
        val manager = LinearLayoutManager(this)
    // Le añadimos una separación entre items.
        val decoration = DividerItemDecoration(this, manager.orientation)
        binding.idrecyclercasas.layoutManager = LinearLayoutManager(this)
        binding.idrecyclercasas.adapter = CasasAdapter(CasasProvider.listaCasas,
            clickContact = { position -> onContact(position)}
        )
        binding.idrecyclercasas.addItemDecoration(decoration)
    }

    //Configuración del botón de contact.
    private fun onContact(position: Int){

      /*  val intent = Intent(this@GaleriaHouses, Casas_descripcion::class.java)
        intent.putExtra("cabecera", CasasProvider.listaCasas[position].titulo)
        intent.putExtra( "imagenCasa", CasasProvider.listaCasas[position].foto)
        intent.putExtra("descripcionCasa", CasasProvider.listaCasas[position].descripcion)
        startActivity(intent)*/

        // Llamamos al AlertDialog que hemos configurado, "dialog_contact"
       val view = View.inflate(this@GaleriaHouses,R.layout.dialog_contact, null)

        val builder = AlertDialog.Builder(this@GaleriaHouses)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)

        // Controlamos los botones del AlertDialog

        val btnTelefono = view.findViewById<ImageButton>(R.id.btnTelefono)
        btnTelefono.setOnClickListener{ checkPermission()}

        val btnMail = view.findViewById<ImageButton>(R.id.btnMail)
        btnMail.setOnClickListener{
            val intentMail = Intent(Intent.ACTION_SENDTO,
                Uri.fromParts("mailto", "info@suntimeshouses.com",null))
            startActivity(Intent.createChooser(intentMail, "Enviar Mail..."))
        }

        val btnWhapp = view.findViewById<ImageButton>(R.id.btnwhapok)
        btnWhapp.setOnClickListener{

            val code: String = "+34"
            val telefon: String = "633692380"

            whatsApp(code, telefon.toString().trim())

        }


    }

    private fun whatsApp(code: String, telefono: String) {

        try {

            val packageManager = this.packageManager
            val i = Intent(Intent.ACTION_VIEW)
            val url = "https://api.whatsapp.com/send?phone=" + "$code $telefono" +
                    "Necesito mas información del piso" + URLEncoder.encode("hELLO")
            i.setPackage("com.whatsapp")
            i.data = Uri.parse(url)
            if (i.resolveActivity(packageManager) != null) {
                startActivity(i)
            } else {
                Toast.makeText(this,"Please Install Whatsapp", Toast.LENGTH_SHORT).show()
            }

        } catch (e: Exception){
            Toast.makeText(this, "" + e.stackTrace, Toast.LENGTH_SHORT).show()
        }

    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            //Permiso no aceptado por el usuario
               requestPhonePermission()
        } else {
            // llamar por telefono
            llamada()
        }
    }
// He puesto el número de telefono de Master D, me parece un detalle gracioso.
    private fun llamada() {
        startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:976 300 903")))
    }

    private fun requestPhonePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this, Manifest.permission.CALL_PHONE)) {
            // El usuario ya ha rechazado los permisos
            Toast.makeText(this, "Permisos rechazados", Toast.LENGTH_SHORT).show()
        } else {
            //pedir permisos
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 777)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 777) // nuestros permisos
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                llamada()
            }else{
                // el permiso no ha sido aceptado por primera vez
                Toast.makeText(this, "Permisos rechazados por primera vez", Toast.LENGTH_SHORT).show()
                }

    }
}
