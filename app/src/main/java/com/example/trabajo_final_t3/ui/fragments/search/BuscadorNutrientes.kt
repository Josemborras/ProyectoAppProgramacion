package com.example.trabajo_final_t3.ui.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.trabajo_final_t3.R
import com.example.trabajo_final_t3.data.models.SearchRecipesByIngredients.ListRecipeResponse
import com.example.trabajo_final_t3.databinding.FragmentBuscadorNutrientesBinding
import com.example.trabajo_final_t3.viewModel.MyViewModel


class BuscadorNutrientes : Fragment() {

    private val myViewModel by activityViewModels<MyViewModel>()
    private lateinit var binding: FragmentBuscadorNutrientesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuscadorNutrientesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // variables para guardar los numberPickers
        val npMinProtein = binding.nPMinProtein
        val npMaxProtein = binding.nPMaxProtein
        val npMinFat = binding.nPMinFat
        val npMaxFat = binding.nPMaxFat
        val npMinCarbs = binding.nPMinCarbs
        val npMaxCarbs = binding.nPMaxCarbs
        val npMinCalories = binding.nPMinCalories
        val npMaxCalories = binding.nPMaxCalories

        // aquí se le dan valores mínimos y máximos a cada number picker.
        npMinProtein.minValue = 0
        npMinProtein.maxValue = 100

        npMaxProtein.minValue = 0
        npMaxProtein.maxValue = 100

        npMinFat.minValue = 0
        npMinFat.maxValue = 100

        npMaxFat.minValue = 0
        npMaxFat.maxValue = 100

        npMinCarbs.minValue = 0
        npMinCarbs.maxValue = 100

        npMaxCarbs.minValue = 0
        npMaxCarbs.maxValue = 100

        /*
         * esto hace que los valores de los number pickers de calorías mínimas
         * y máximas salten de 10 en 10
         *  */

        // values = ["0", "10", "20", "30", "40", +76 more]
        val values = Array(81) { i -> (i * 10).toString() }

        npMinCalories.minValue = 0
        npMinCalories.maxValue = values.size - 1
        npMinCalories.displayedValues = values

        npMaxCalories.minValue = 0
        npMaxCalories.maxValue = values.size - 1
        npMaxCalories.displayedValues = values
        // binding.nPMaxCalories.value = values.size - 1

        binding.btnSearchRecipeByNutrients.setOnClickListener {
            /*
             * al pulsar el botón para buscar las recetas por los nutrientes
             * se guardan los valores de los number pickers en sus variables
             * correspondientes y después se comprueba si el valor mínimo es
             * mayor que el máximo, si es true los valores de estas dos
             * variables se cambian entre ellas para poder hacer la petición
             * a la api sin que de fallo o devuelva un json vacío. El valor
             * de los number pickers también se cambiarán
             * */

            var minCarbs = npMinCarbs.value
            var maxCarbs = npMaxCarbs.value

            var minProtein = npMinProtein.value
            var maxProtein = npMaxProtein.value

            var minFat = npMinFat.value
            var maxFat = npMaxFat.value

            var minCalories = npMinCalories.value
            var maxCalories = npMaxCalories.value * 10

            if (minCarbs > maxCarbs) {
                val temp = maxCarbs
                maxCarbs = minCarbs
                minCarbs = temp

                npMinCarbs.value = minCarbs
                npMaxCarbs.value = maxCarbs
            }

            if (minProtein > maxProtein) {
                val temp = maxProtein
                maxProtein = minProtein
                minProtein = temp

                npMinProtein.value = minProtein
                npMaxProtein.value = maxProtein
            }

            if (minFat > maxFat) {
                val temp = maxFat
                maxFat = minFat
                minFat = temp

                npMinFat.value = minFat
                npMaxFat.value = maxFat
            }

            if (minCalories > maxCalories) {
                val temp = maxCalories
                maxCalories = minCalories
                minCalories = temp

                npMaxCalories.value = minCalories
                npMaxCalories.value = maxCalories
            }


            /*
             * este if es para comprobar si los valores son diferentes
             * de 0, si lo son hará la petición y pasará a la lista de recetas,
             * si son iguales a 0 manda un toast
             * */
            if (
                maxProtein == 0 ||
                maxCarbs == 0 ||
                maxFat == 0 ||
                maxCalories == 0
            ) {
                Toast.makeText(context, "No recipes found for these nutritional values", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else {
                myViewModel.getRecipesByNutrients(
                    minCarbs, maxCarbs,
                    minProtein, maxProtein,
                    minFat, maxFat,
                    minCalories, maxCalories,
                    80
                ).observe(viewLifecycleOwner){
                    funSetRecipeingredient(it)

                    findNavController().navigate(R.id.action_tabLayoutBuscador_to_listRecipesSearch)
                }
            }
        }
    }

    private fun funSetRecipeingredient(lista: ListRecipeResponse){
        myViewModel.setRecipeIngredientLiveData(lista)
    }
}