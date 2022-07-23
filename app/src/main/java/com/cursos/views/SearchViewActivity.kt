package com.cursos.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView

class SearchViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_view)

        //SEARCHVIEW
        //Se crea un array de strings
        val users = arrayOf("Alberto", "Alvaro", "Ana", "Ampare", "Bartolo", "Bernardo","Carla","Carlos","Carolina")
        //Se especifica como se mostrará la lista por medio de un adaptador
        //En el adaptador se especifica que sea de tipo string y en el constructor del adaptador determinamos que sea de esta actividad
        //despues se determina que se utilizará un diseño predeterminado que tiene android y será utilizado para mostrar los elementos en ese layout
        //despues en el tercer parametro se pone la variable users para poder utilizar el array, es como se llenará la lista en el layout
        val adapterUsers : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1,users)

        //Vinculamos los elementos SearchView y el ListView
        var svUsers = findViewById<SearchView>(R.id.svUsers)
        var lvUsers = findViewById<ListView>(R.id.lvUsers)

        //En la listView le asignaremos el adaptador ya creado
        lvUsers.adapter = adapterUsers

        //En el searchView llamamos al evento que se encargará de gestionar los cambios que sufra en el searchView
        svUsers.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            //En esta función se trata la consulta que hará el searchView y se ejecutará cuando se envia el texto o se le de enter
            override fun onQueryTextSubmit(query: String?): Boolean {
                //Cada vez que se envia el dato que el focus se limpie
                svUsers.clearFocus()
                //Se analiza que lo que se escriba tenga relación a lo que esta almacenado en el array
                //si esto se cumple se hará un filtro en el adaptador o se mostrará las palabras que solamente concuerden con la consulta
                if (users.contains(query)) adapterUsers.filter.filter(query)
                return false
            }

            //En esta función va estar analizando los cambios que sufrá el searchView, es decir hará un filtro de acuerdo por cada caracter escrito en el componente
            override fun onQueryTextChange(query: String?): Boolean {
                adapterUsers.filter.filter(query)
                return false
            }

        })
    }
}