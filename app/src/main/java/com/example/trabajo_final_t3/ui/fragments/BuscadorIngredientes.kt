package com.example.trabajo_final_t3.ui.fragments

import android.annotation.SuppressLint
import android.app.SearchManager
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabajo_final_t3.R
import com.example.trabajo_final_t3.data.models.ingredients.Resultado
import com.example.trabajo_final_t3.databinding.FragmentBuscadorIngredientesBinding
import com.example.trabajo_final_t3.databinding.IngredientesBinding
import com.example.trabajo_final_t3.ui.adapters.Ingredientes
import com.example.trabajo_final_t3.ui.viewmodel.ViewModel

class BuscadorIngredientes : Fragment() {

    private lateinit var binding: FragmentBuscadorIngredientesBinding
    private lateinit var bindingIngredient: IngredientesBinding
    private val viewModel by activityViewModels<ViewModel>()
    private lateinit var adaptador: Ingredientes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBuscadorIngredientesBinding.inflate(layoutInflater, container, false)
        bindingIngredient = IngredientesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val to = intArrayOf(R.id.searchItemID)

        val cursorAdapter = SimpleCursorAdapter(context, R.layout.sugerencias, null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER)

        binding.svIngredientes.suggestionsAdapter = cursorAdapter

        binding.svIngredientes.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                // esto se hace cuando pulsas la lupa del teclado al terminar de buscar
                /*viewModel.getIngredients(query.toString()).observe(viewLifecycleOwner){
                    binding.tv.text = ""
                    binding.imvImagenBuscador.visibility = View.GONE
                    it.results.forEach {

                        binding.tv.text = """${binding.tv.text} 
                            |${it.name}""".trimMargin()
                    }
                }*/
//                binding.tv.text = query.toString()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // esto se hace cada vez que cambias (escribir, borrar, cortar, pegar...) el texto
                var suggestions = ArrayList<String>()

                binding.tv.text
                viewModel.getIngredients(newText.toString()).observe(viewLifecycleOwner){ ingredientsResponse ->
                     ingredientsResponse.results.forEach {result ->
                        suggestions.add(result.name)
                    }
                }

                val cursor =
                    MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))

                newText?.let {
                    suggestions.forEachIndexed { index, suggestion ->
                        if (suggestion.contains(newText, true))
                            cursor.addRow(arrayOf(index, suggestion))
                    }
                }

                cursorAdapter.changeCursor(cursor)
                return true
            }
        })

        // https://alitalhacoban.medium.com/searchview-with-suggestion-kotlin-ddc33d987aa7
        binding.svIngredientes.setOnSuggestionListener(object : SearchView.OnSuggestionListener{
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            @SuppressLint("Range")
            override fun onSuggestionClick(position: Int): Boolean {
                val searchView = binding.svIngredientes

                val cursor = searchView.suggestionsAdapter.getItem(position) as Cursor

                val selection = cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))

                viewModel.getIngredients(selection).observe(viewLifecycleOwner){
                    var resultado = ArrayList<Resultado>()

                    it.results.forEach { i ->
                        if (i.name == selection) resultado.add(i)
                    }

                    viewModel.setSuggestions(resultado)
                }

                // todo lo que devuelve en las sugerencias lo mete en el rv

                binding.rvBuscadorIngredientes.visibility = View.VISIBLE
                binding.imvImagenBuscador.visibility = View.GONE

                binding.rvBuscadorIngredientes.layoutManager = LinearLayoutManager(context)
                adaptador = Ingredientes(
                    viewModel.getSuggestions(),
                    selection,
                )
                binding.rvBuscadorIngredientes.adapter = adaptador

                Log.d("rv", viewModel.getSuggestions().toString())

                searchView.setQuery(selection, true)
                return true
            }

        })

        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = true



            binding.swipe.isRefreshing = false
        }

    }
}