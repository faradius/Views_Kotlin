package com.cursos.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Buttons : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buttons)

        //BUTTONS

        var btnButton = findViewById<Button>(R.id.btnButton)
        btnButton.setOnClickListener {
            Toast.makeText(this, "btnBoton Pulsado", Toast.LENGTH_SHORT).show()
        }

        var imageButton = findViewById<ImageButton>(R.id.imageButton)
        imageButton.setOnClickListener {
            Toast.makeText(this, "imageButton Pulsado", Toast.LENGTH_SHORT).show()
        }


        //CHIPS
        var cgCondiciones = findViewById<ChipGroup>(R.id.cgCondiciones)

        //Se crea una variable de tipo chip
        var chip: Chip

        //Se recorre cada uno de los elementos del chipgroup(el padre) que contiene los chips (hijos)
        for(i in 0..cgCondiciones.childCount-1){
            //Se obtiene cada uno de los chips hijos que estan dentro del chipgroup y para manipular
            //sus atributos se tiene que castear o convertir de View a tipo Chip
            chip = cgCondiciones.getChildAt(i) as Chip
            //se pone el texto de cada chip centrado
            chip.textAlignment = View.TEXT_ALIGNMENT_CENTER

            //se habilita el evento con la funcionalidad de que al presionar a la "x" de cada chip
            //se elimine el chip del chipgroup
            chip.setOnCloseIconClickListener {
                cgCondiciones.removeView(it)
            }
            //se habilita el evento con la funcionalidad de mostrar un mensaje cuando se presiona en
            //en cada chip
            chip.setOnClickListener {
                //es necesario convertir cada uno de los views a tipo chip para obtener el atributo que contiene el texto
                var aux = it as Chip
                Toast.makeText(this,"${aux.text} pulsado", Toast.LENGTH_SHORT).show()
            }
        }

        //Agregar Chip desde Codigo

        //se crea una variable de tipo chip pero se hace uso de su constructor
        val chipNew = Chip(this)
        //se le agrega el texto al chip
        chipNew.text = "Nueva Opción"
        //se le agrega una imagen al chip
        chipNew.chipIcon = ContextCompat.getDrawable(this, R.drawable.ic_email)
        //se habilita o desabilita el poder visualizar el icono agregado en el chip (ic_email)
        chipNew.isChipIconVisible = true
        //lo que hace es habilitar el icono de la x para cerrar el chip
        chipNew.isCloseIconVisible = true

        //se habilita de que el chip se le puede hacer click (efecto click)
        chipNew.isClickable = true
        //se desactiva el click del chip
        //chipNew.isClickable = false
        //dentro del group chip se le agrega un nuevo chip de tipo view (se castea)
        cgCondiciones.addView(chipNew as View)
        //al nuevo chip se le agrega el evento de cerrar el chip y permite borrar el elemento
        chipNew.setOnCloseIconClickListener { cgCondiciones.removeView(chipNew as View) }



        //RADIO BUTTONS
        //Es otra forma de hacer referencia a un compenente de la vista del XML
        var rgVacaciones = findViewById<View>(R.id.rgVacaciones) as RadioGroup

        //se obtiene el radioButton hijo de la posición 1 (montaña) y como es una vista es necesario
        //castearlo para que sea un radioButton
        var rb = rgVacaciones.getChildAt(1) as RadioButton

        //del grupo de rariobuttons queremos que se autoseleccione desde el codigo el radiobutton con
        //el indice 1 que es montaña
        rgVacaciones.check(rb.id)

        //CHEBOX
        var cbSeguro = findViewById<CheckBox>(R.id.cbSeguro)
        cbSeguro.isEnabled = true
        cbSeguro.isChecked = true


        //TOGGLE BUTTON
        var tgNormal : ToggleButton = findViewById(R.id.tgNormal)
        tgNormal.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) Toast.makeText(this,"Toggle Activado", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this,"Toggle Desactivado", Toast.LENGTH_SHORT).show()
        }

        //SWITCH
        var swNormal : Switch = findViewById(R.id.swNormal)
        swNormal.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) Toast.makeText(this,"Switch Activado", Toast.LENGTH_SHORT).show()
            else Toast.makeText(this,"Switch Desactivado", Toast.LENGTH_SHORT).show()
        }

        //FLOATING BUTTON
        var fanButton = findViewById<FloatingActionButton>(R.id.fabButton)
        fanButton.setOnClickListener {
            Toast.makeText(this, "FabButton Pulsado", Toast.LENGTH_SHORT).show()
        }



    }

    //Se crea una función que es llamada desde el XML, tiene que tener el mismo nombre y debe ser abierta
    //no debe ser privada por que si no truena la aplicacion y esto no da indicio de que haya error en el codigo

    //siempre que se hace referencia a la propiedad onclick en cualquier elemento del XML siempre se envia
    //como parametro la vista el objeto view sobre el que se ha hecho referencia, ya que todos los objetos
    //como radiobutton, buttons, chips, etc. heredan de view
     fun onRadioButtonClicked(view:View){
        //Se puede hacer una comprobación para verificar que el view que se le esta haciendo click sea un RadioButton
        if (view is RadioButton){
            //En una variable se almacena el estado de que el radioButton esta chekeado
            var checked = view.isChecked

            //hacemos un switch o when para mostrar un mensaje dependiendo de cual radiobutton esta checkeado
            when(view.id){ //aqui se busca por medio de su ID
                R.id.rbPlya -> {
                    //se comprueba si esta checkeado el radiobutton de playa, y si es asi manda un mensaje
                    if (checked) Toast.makeText(this,"Vamos a la playa", Toast.LENGTH_SHORT).show()
                }
                R.id.rbMontaña -> {
                    if (checked) Toast.makeText(this,"Vamos a la montaña", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    fun onCheckBoxClicked(view:View){
        if (view is CheckBox){
            var checked = view.isChecked
            when(view.id){
                R.id.cbSeguro -> {
                    if (checked) Toast.makeText(this,"Seguro Contratado", Toast.LENGTH_SHORT).show()
                    else Toast.makeText(this, "Seguro Anulado", Toast.LENGTH_SHORT).show()
                }
                R.id.cbCancelable -> {
                    if (checked) Toast.makeText(this,"La reserva podrá ser cancelada", Toast.LENGTH_SHORT).show()
                    else Toast.makeText(this,"La reserva no podrá ser cancelada", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}