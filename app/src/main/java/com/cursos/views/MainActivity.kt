package com.cursos.views

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // otra forma de crearlos
        // var tvText: TextView = findViewById(R.id.tvHolaMundo)

        var tvText = findViewById<TextView>(R.id.tvHolaMundo)
        tvText.text= "cambiado desde el codigo"
        tvText.setTextColor(Color.RED)
        tvText.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD)

        tvText.setOnClickListener {
            Toast.makeText(this, "Text", Toast.LENGTH_SHORT).show()
            tvText.setTextColor(Color.GREEN)
        }

        var etEjemplo = findViewById<EditText>(R.id.etEjemplo)


        etEjemplo.addTextChangedListener {
            if (etEjemplo.text.length == 0) etEjemplo.setError("Campo Vacio")
        }
        etEjemplo.setSelection(3)
        var inicio = etEjemplo.selectionStart
        var fin = etEjemplo.selectionEnd
        etEjemplo.setSelection(inicio, fin)
        etEjemplo.selectAll()



        /*
        tvText.apply {
            text = "cambiado desde el codigo"
            setTextColor(Color.RED)
            setTypeface(Typeface.SANS_SERIF,Typeface.BOLD)

            setOnClickListener {
                tvText.setTextColor(Color.GREEN)
            }
        }
        */


        /*
        * text                       Recibe texto plano simple
        * textPersonName             Texto correspondiente al nombre de una persona
        * textPassword               Protege los caracteres que se van escribiendo con puntos
        * numberPassword             Contraseña de solo números enmascarada con puntos
        * textEmailAddress           Texto que sera usado en un campo para emails
        * phone                      Texto asociado a un número de telefono
        * textPostalAddress          Para ingresar textos asociados a una dirección postal
        * textMultiline              Permite multiples lineas en el campo de texto
        * time                       Texto para determinar la hora
        * date                       Texto para determinar la fecha
        * number                     Texto con caracteres numericos
        * numberSigned               Permite números con signo
        * numberDecimal              Para ingresar números decimales
        * */

        var autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        var countries : Array<String> = resources.getStringArray(R.array.countries_array)

        var adapter : ArrayAdapter<String> = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,countries)

        autoCompleteTextView.setAdapter(adapter)

        var multiAutoCompleteTextView = findViewById<MultiAutoCompleteTextView>(R.id.multiAutoCompleteTextView)
        multiAutoCompleteTextView.setAdapter(adapter)
        multiAutoCompleteTextView.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())

    }
}