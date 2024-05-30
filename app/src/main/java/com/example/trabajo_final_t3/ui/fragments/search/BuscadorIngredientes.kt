package com.example.trabajo_final_t3.ui.fragments.search

import android.annotation.SuppressLint
import android.app.SearchManager
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabajo_final_t3.R
import com.example.trabajo_final_t3.data.models.SearchIngredient.RecipeResultSearch
import com.example.trabajo_final_t3.data.models.SearchRecipesByIngredients.ListRecipeResponse
import com.example.trabajo_final_t3.databinding.FragmentBuscadorIngredientesBinding
import com.example.trabajo_final_t3.databinding.IngredientesBinding
import com.example.trabajo_final_t3.ui.adapters.SearchListIngredientsAdapter
import com.example.trabajo_final_t3.viewModel.MyViewModel


class BuscadorIngredientes : Fragment() {

    private lateinit var binding: FragmentBuscadorIngredientesBinding
    private lateinit var bindingIngredient: IngredientesBinding
    private val myViewModel by activityViewModels<MyViewModel>()
    private lateinit var adaptador: SearchListIngredientsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuscadorIngredientesBinding.inflate(layoutInflater, container, false)
        bindingIngredient = IngredientesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecycler()

        binding.svIngredientes.setIconifiedByDefault(false)

        myViewModel.getIngredienteResult().observe(viewLifecycleOwner){
            adaptador.updateList(it)
        }

        /*
        * cambiar from para poner una imangen, podría servier este
        * SearchManager.SUGGEST_COLUMN_ICON_1
        * */
        val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val to = intArrayOf(R.id.searchItemID)

        val cursorAdapter = SimpleCursorAdapter(context, R.layout.sugerencias, null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER)

        binding.svIngredientes.suggestionsAdapter = cursorAdapter

        binding.svIngredientes.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            @SuppressLint("SetTextI18n")
            override fun onQueryTextChange(newText: String?): Boolean {
                // esto se hace cada vez que cambias (escribir, borrar, cortar, pegar...) el texto
                val suggestions = ArrayList<String>()

                myViewModel.getIngredients(newText.toString()).observe(viewLifecycleOwner){ ingredientsResponse ->
                     ingredientsResponse.results.forEach {result ->
                         result.name?.let { suggestions.add(it) }
                    }
                    myViewModel.setSuggestions(ingredientsResponse)
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

                myViewModel.filterList(selection).observe(viewLifecycleOwner){
                    if (it.isNotEmpty()) myViewModel.addIngredienteResult(it[0])
                }

                binding.rvBuscadorIngredientes.visibility = View.VISIBLE
                binding.imvImagenBuscador.visibility = View.GONE
                binding.btnBuscarReceta.visibility = View.VISIBLE

                // Log.d("rv", viewModel.getSuggestions().toString())

                /*
                * hace la petición para buscar con el string que se escribe en el SearchView
                * en false escribirías y no buscaría ingredientes ni mostraría la lista
                * de sugerencias
                * */
                searchView.setQuery(selection, true)
                return true
            }

        })

        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = true

            binding.swipe.isRefreshing = false
        }

        binding.btnBuscarReceta.setOnClickListener {
            findNavController().navigate(R.id.action_tabLayoutBuscador_to_listRecipesSearch)

            myViewModel.getRecipesByIngredients(adaptador.getString()).observe(viewLifecycleOwner){
                funSetRecipeingredient(it)
            }
        }
    }

    private fun configRecycler(){
        binding.rvBuscadorIngredientes.layoutManager = LinearLayoutManager(context)
        adaptador = SearchListIngredientsAdapter(object : SearchListIngredientsAdapter.DeleteClickListener{
            override fun onDeleteClick(recipeResultSearch: RecipeResultSearch) {
                myViewModel.removeIngredientResult(recipeResultSearch)
            }
        })
        binding.rvBuscadorIngredientes.adapter = adaptador
    }

    private fun funSetRecipeingredient(lista: ListRecipeResponse){
        myViewModel.setRecipeIngredientLiveData(lista)
    }
}