package com.example.trabajo_final_t3.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.trabajo_final_t3.databinding.FragmentBuscadorNutrientesBinding
import com.example.trabajo_final_t3.ui.viewmodel.ViewModel
import kotlin.math.max


class BuscadorNutrientes : Fragment() {

    private val viewModel by activityViewModels<ViewModel>()
    private lateinit var binding: FragmentBuscadorNutrientesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuscadorNutrientesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
         * aquí se le dan valores mínimos y máximos a cada number picker.
         *
         * a los number pickers máximos se les pone el valor máximos por defecto, 100,
         * y los mínimos se quedan en 0.
         * */

        binding.nPMinProtein.minValue = 0
        binding.nPMinProtein.maxValue = 100

        binding.nPMaxProtein.minValue = 0
        binding.nPMaxProtein.maxValue = 100
        binding.nPMaxProtein.value = 100

        binding.nPMinFat.minValue = 0
        binding.nPMinFat.maxValue = 100

        binding.nPMaxFat.minValue = 0
        binding.nPMaxFat.maxValue = 100
        binding.nPMaxFat.value = 100

        binding.nPMinCarbs.minValue = 0
        binding.nPMinCarbs.maxValue = 100

        binding.nPMaxCarbs.minValue = 0
        binding.nPMaxCarbs.maxValue = 100
        binding.nPMaxCarbs.value = 100

        /*
         * esto hace que los valores de los number pickers de calorías mínimas
         * y máximas salten de 10 en 10
         *  */
        val values = Array(81) { i -> (i * 10).toString() }

        binding.nPMinCalories.minValue = 0
        binding.nPMinCalories.maxValue = values.size - 1
        binding.nPMinCalories.displayedValues = values

        binding.nPMaxCalories.minValue = 0
        binding.nPMaxCalories.maxValue = values.size - 1
        binding.nPMaxCalories.displayedValues = values
        binding.nPMaxCalories.value = values.size - 1

        binding.btnSearchRecipeByNutrients.setOnClickListener {

            /*
             * al pulsar el botón para buscar las recetas por los nutrientes
             * se guardan los valores de los number pickers en sus variables
             * correspondientes y después se comprueba si el valor mínimo es
             * mayor que el máximo, si es true los valores de estas dos
             * variables se cambian entre ellas para poder hacer la petición
             * a la api sin que de fallo o devuelva un json vacío.
             * */

            var minCarbs = binding.nPMinCarbs.value
            var maxCarbs = binding.nPMaxCarbs.value

            if (minCarbs > maxCarbs) {
                val temp = maxCarbs
                maxCarbs = minCarbs
                minCarbs = temp
            }

            var minProtein = binding.nPMinProtein.value
            var maxProtein = binding.nPMaxProtein.value

            if (minProtein > maxProtein) {
                val temp = maxProtein
                maxProtein = minProtein
                minProtein = temp
            }

            var minFat = binding.nPMinFat.value
            var maxFat = binding.nPMaxFat.value

            if (minFat > maxFat) {
                val temp = maxFat
                maxFat = minFat
                minFat = temp
            }

            var minCalories = binding.nPMinCalories.value
            var maxCalories = binding.nPMaxCalories.value

            if (minCalories > maxCalories) {
                val temp = maxCalories
                maxCalories = minCalories
                minCalories = temp
            }

            viewModel.getRecipesByNutrients(
                minCarbs, maxCarbs,
                minProtein, maxProtein,
                minFat, maxFat,
                minCalories, maxCalories,
                80
            ).observe(viewLifecycleOwner){
                binding.textView.text = it.toString()
                viewModel.setRecipesNutrientsResponse(it)
            }

            val response = viewModel.getRecipesNutrientsResponse().value
            binding.textView10.text = response.toString()
        }
    }
}