package com.cursos.views

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import com.cursos.views.databinding.ActivityMainBinding
import com.cursos.views.databinding.ActivityWidgetsBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import java.util.*

class Widgets : AppCompatActivity() {

    private lateinit var activityContext: Context
    //Declaramos una variable que se utilizará despues, El Activity(Widgets)Binding la palabra Widgets hace referencia a la clase nombramos nosotros y que nos encontramos actualmente
    //Es decir que al hacer esto solo nos mostrará los compenentes que tiene relación con su xml generado, por ejemplo
    //si tenemos un componente con el nombre tvPrueba y esta relacionado esta activity con su xml nos va a parecer ese componente en el autocompletado cuando
    //queramos llamarlo, ahora si hay otro componente con el mismo nombre en otro xml pero no esta relacionado a esta activity, lo que hará es que no nos mostrará
    //en el autocompletado ese compoenente que se encuentra en otro xml
    private lateinit var binding: ActivityWidgetsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Hacemos que el xml se muestre o infle
        binding = ActivityWidgetsBinding.inflate(layoutInflater)
        //aqui determinamos que todos los views se van hacer uso mediante el binding
        setContentView(binding.root)
        //setContentView(R.layout.activity_widgets)

        activityContext = this

        //IMAGEVIEW ONLINE
        var ivUrl = findViewById<ImageView>(R.id.ivUrl)
        //se guarda la url de donde se encuentra la imagen
        val imageURL = "http://jotajotavm.com/img/PREMIUM-AndroidDevelopment.gif"
        //se implementa la libreria de picaso para gestionar las imagenes
        //se llama la libreria, se obtinene la url de la imagen y se coloca en el imageview
        Picasso.get().load(imageURL).into(ivUrl)

        //WEBVIEW
        var webView = findViewById<WebView>(R.id.webView)
        //se carga la configuración de la navegación web
        var webSettings: WebSettings = webView.settings
        //se habilita en la navegación codigo javascript para poder navegar en la web
        webSettings.javaScriptEnabled = true
        //se pasa la navegación por un cliente web
        webView.setWebViewClient(WebViewClient())
        //se carga la url que se desea navegar
        webView.loadUrl("http://www.google.com")
        //Esto es para poder navegar en una web dentro de la misma aplicación sin necesidad de salirse
        //o pedir que se habra el sitio por medio de una aplicación de terceros

        //VIDEOVIEW LOCAL
        var vvLocal = findViewById<VideoView>(R.id.vvLocal)
        //Se coloca en una variable el contexto en donde se podran visualizar los controles en el video
        // para poder poner play, pause, adelantar o retroceder.

        //si no se hace esto se inicia el videoview pero esta en negro y no hay manera de reproducir el video
        // ya q no tiene los controles, pero se puede indicar con programación que inicie automaticamente el video
        var mcLocal = MediaController(this)
        //Se establecen los controles en el video al tamaño del activity (eso creo)
        mcLocal.setAnchorView(vvLocal)
        //Se especifica la ruta donde se encuentra el video a reproducir de forma local
        var path = "android.resource://" + packageName + "/" + R.raw.video
        //para reproducir el video se coloca la url o la dirección en donde esta dicho video
        vvLocal.setVideoURI(Uri.parse(path))
        //Se colocan los controles de reproducción en el activity
        vvLocal.setMediaController(mcLocal)
        //Se inicializa automaticamente el video al iniciar la aplicación
        vvLocal.start()

        //VIDEOVIEW ONLINE
        var vvWeb = findViewById<VideoView>(R.id.vvWeb)
        //Se asigna los controles en el activity
        var mcWeb = MediaController(this)
        //se establece los controles al tamaño del activity
        mcWeb.setAnchorView(vvWeb)
        //Se coloca la url de donde esta el recurso en internet, es necesario habilitar en el
        //manifest los permisos de internet para poder hacer la consulta por internet
        vvWeb.setVideoPath("https://jotajotavm.com/img/video.mp4")
        //se colocan los controles de reproducción en el video
        vvWeb.setMediaController(mcWeb)


        //CALENDARVIEW
        //Se vincula el calendario y el texview con el codigo
        var cvEjemplo = findViewById<CalendarView>(R.id.cvEjemplo)
        var tvFecha = findViewById<TextView>(R.id.tvFecha)


        //En el calendario se pone un escuchador para definir que fecha fue presionado en el calendario
        cvEjemplo.setOnDateChangeListener { cv, year, month, day -> //la lambda se especifica el calenderView, y tres datos enteros
            //Esa Se establece el orden de los datos de como se va a mostrar la fecha
            var date = "$day/${month+1}/$year" //se coloca un +1 por que lo toma como un array (posicion 0 es enero)
            //se coloca en el texview la fecha seleccionada en el calendario
            tvFecha.text = "Fecha seleccionada: $date"
        }

        //Se establece una posicion o fecha al iniciar el calendario

        //se obtiene la instancia del calendario para poder gestionar las fechas
        var calendar = Calendar.getInstance()
        //Se establece la fecha que quiere que se muestre en el calendario
        calendar.set(2026, 5-1, 8) //se le tiene que restar por que la modificación de la variable date le afecto y da un mes adelantado
        //se debe de manejar las fechas en milisegundos (no se porque, no se si se refiere al tiempo)
        cvEjemplo.date = calendar.timeInMillis

        //aqui se trata de poder cambiar la configuración del orden de como se muestra los dias en el calendario
        //es decir empieza con domingo despues lunes, etc. y lo que se quiere es empezar con lunes y no domingo

        //por lo que se guarda en una variable primero el primer dia (domingo)
        var d = cvEjemplo.firstDayOfWeek
        //despues se hace la modificación en el calendario y se recorre el dia con la expresión matematica (d+1)%7 el 7 son los dias de la semana
        cvEjemplo.firstDayOfWeek = (d+1)%7


        //POGRESSBAR
        var pbDeterminado = findViewById<ProgressBar>(R.id.pbDeterminado)
        var pbSecundario = findViewById<ProgressBar>(R.id.pbSecundario)

        //Este es un progressBar
        //El progressBar Determinado se pone un tamaño maximo de 300
        pbDeterminado.max = 200
        //Se especifica de donde a donde va empezar en este caso desde cero
        pbDeterminado.progress = 0

        //Este es otro progressBar, en este no se pone un tamaño por que ya lo tiene mediante xml
        //Se especifica que el progressbar empieza desde cero y el secundario tambien
        pbSecundario.progress = 0
        pbSecundario.secondaryProgress = 0

        //Se utiliza la corutina por que si se ejecuta sin la corutina no sera visible el progreso del
        //progressbar, por que como estamos en el oncreate de la activity lo que hara es que se cargue
        //todos los widgets y por lo tanto se va a ver el progressbar lleno, por lo tanto necesitamos
        //que se ejecute el progressbar despues de crear la activity y de que se hayan cargado los widgets
        //es por eso que se ocupa la corutina.


        //SEEKBAR
        //Se vincula el componente del seekbar
        var sbNormal = findViewById<SeekBar>(R.id.sbNormal)
        //Se crea un evento para administrar los cambios que sufre el seekbar
        sbNormal.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            //Se importan los metodos del evento

            //En esta función se administrar el cambio del progress, dentro de la función
            //tenemos tres parametros uno que hace referencia al seekbar, el otro al progressbar y el ultimo
            //administra si fue movido por el usuario o fue por programación
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //se crea un if para preguntar si el seekbar fue movido por el usuario, si se cumple sale un mensaje
                if (fromUser)
                    Toast.makeText(activityContext,"Tu lo cambiaste",Toast.LENGTH_SHORT).show()
            }

            //Esta función se administra el inicio del tracking, osea cuando empiece a cambiar o se la haga click al seekbar
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //Toast.makeText(activityContext,"inicio",Toast.LENGTH_SHORT).show()
            }

            //Esta función se administra cuando se para el seekbar es decir cuando mueves el seekbar y lo sueltas ahi es donde se detiene
            //al hacer eso se ejecuta el metodo
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
               //Toast.makeText(activityContext,"se paro",Toast.LENGTH_SHORT).show()

            }
        })

        //Se invoca y lanza la corutina para que ejecute fuera del hilo principal
        GlobalScope.launch {
            progressManager(pbDeterminado)
            progressManager(pbSecundario)
            seekbarManager(sbNormal)
        }

        //RATINGBAR
        //Se vinculan los dos widgets el cual es el ratingbar y el textview
        var  rbEjemplo = findViewById<RatingBar>(R.id.rbEjemplo)
        var tvRating = findViewById<TextView>(R.id.tvRating)

        //Se especifica cual es la valoración que tendra el ratingbar
        rbEjemplo.rating = 2.5f
        //Se llama el evento para administrar los cambios que se generan en el ratingbar
        //los elementos que tienen la lambda es el maximo numero de estrellas que tendrá el ratingbar y
        //que valoración tendrá el ratingbar o cuantas estrellas tendra marcado
        rbEjemplo.setOnRatingBarChangeListener { ratingBar, rating, _ ->
            //Se coloca los valores en el ratingbar poniendo primero la valoración y despues el numero de estrallas que tiene el ratingbar
            tvRating.text = "${rating}/${ratingBar.numStars}"
        }

        //NUMBERPICKER
        var npEjemplo = findViewById<NumberPicker>(R.id.npEjemplo)
        //var tvNumberPicker = findViewById<TextView>(R.id.tvNumberPicker)

        //En el numberPicker se determina el valor minimo y maximo que tendrá este componente
        npEjemplo.minValue = 0
        npEjemplo.maxValue = 60
        //Se determinar el valor que tendrá al iniciar este componente, el número 5 es el número y no el indice
        npEjemplo.value = 5
        //Se habilita la forma de como funciona el numberpicker, en este caso funcionará como una rueda
        npEjemplo.wrapSelectorWheel = true
        //Aqui se establece el fotmato de como se visualizará la información de numberPicker
        npEjemplo.setFormatter { i-> //i representa cada elemento del numberPicker
            String.format("%02d",i) } //Se establece de cada elemento debe contener por lo menos dos digitos

        //Con este evento se caputurá el comportamiento del numberPicker
        npEjemplo.setOnValueChangedListener { numberPicker, oldVal, newVal -> //La lambda tiene 3 parametros los cuales son: el objeto, el valor anterior y el nuevo valor
            //En el textview se mosrtrará el valor anterior y el nuevo valor
            binding.tvNumberPicker.text = "NumberPicker: Antes($oldVal) - Ahora($newVal)"
        }

    }

    //Se crea una función para el seekbar para que solito se valla moviendo en 5 en 5 con un retardo de 100 milisegundos
    private fun seekbarManager(sb: SeekBar){
        while (true){
            sleep(100L)
            sb.incrementProgressBy(5)
        }
    }

    //Se crea una funcion para administrar los progressbars, la función recibe el progressbar que se va a trabajar
    private fun progressManager(pb: ProgressBar){
        //mientras que el progressbar sea menor a su maximo, se ejecuta el while
        while (pb.progress < pb.max){
            //se pone un delay de 100 milisegundos para que se vea como se va llenando la barra
            sleep(100L)
            //pb.progress += 5
            //se va incrementando en 5 en 5 el progressbar
            pb.incrementProgressBy(5)
            //con el if preguntamos si el progressbar que se desea manipular es el progressbar secundario
            if(pb.id == R.id.pbSecundario){
                //hacemos el incremento del progressbar secundario en 10 en 10
                pb.incrementSecondaryProgressBy(10)
            }
        }
    }

}