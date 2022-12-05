package com.example.vkcup


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.vkcup.databinding.FragmentCategoriesSelectorBinding



class CategoriesSelectorFragment : Fragment() {

    private lateinit var binding:FragmentCategoriesSelectorBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesSelectorBinding.inflate(inflater,container,false)
        return binding.root
    }



    override fun onStart() {
        super.onStart()

        checkTagState(binding.carsTagCheckBox)
        checkTagState(binding.foodTagCheckBox)
        checkTagState(binding.checkBox41)
        checkTagState(binding.restaurantsTagCheckBox2)
        checkTagState(binding.politicsTagCheckBox)
        checkTagState(binding.filmsTagCheckBox)

        binding.foodTagCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            replaceTag(binding.foodTagCheckBox,isChecked)
        }

        binding.carsTagCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
           replaceTag(binding.carsTagCheckBox,isChecked)
        }

        binding.filmsTagCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            replaceTag(binding.filmsTagCheckBox,isChecked)
        }

        binding.politicsTagCheckBox.apply {
            setOnCheckedChangeListener { buttonView, isChecked ->
                replaceTag(this,isChecked)
            }
        }

          binding.restaurantsTagCheckBox2 .apply {
            setOnCheckedChangeListener { buttonView, isChecked ->
                replaceTag(this,isChecked)
            }
        }
        binding.checkBox41 .apply {
            setOnCheckedChangeListener { buttonView, isChecked ->
                replaceTag(this,isChecked)
            }
        }


    }

    private fun replaceTag(view: View, isChecked:Boolean){
        val animationDuration = resources.getInteger(android.R.integer.config_longAnimTime)
        if (isChecked) {
            view.animate().apply {
                alpha(0f)
                duration = animationDuration.toLong()
                setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        alpha(1f)
                        binding.unpressedTagsGridlayout.removeView(view)
                        binding.linear.addView(view,0)
                        view.animate().setListener(null)
                    }
                })

            }

        }
        else{
            view.animate().apply {
                alpha(0f)
                duration = animationDuration.toLong()
                setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        alpha(1f)
                        binding.linear.removeView(view)
                        binding.unpressedTagsGridlayout.addView(view,0)
                        view.animate().setListener(null)
                    }
                })
            }
        }
    }

    private fun checkTagState(checkBox: CheckBox){
        if(checkBox.isChecked){
            binding.unpressedTagsGridlayout.removeView(checkBox)
            binding.linear.addView(checkBox,0)
        }
    }

    companion object {
        fun newInstance() = CategoriesSelectorFragment()
    }
}