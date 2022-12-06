package com.example.vkcup


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.view.ViewTreeObserver
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

    //высчитывать ширину экрана и на ее основе делать количество колонок в лайаутах
  /*

   */

    override fun onStart() {
        super.onStart()

        binding.unpressedTagsGridlayout.viewTreeObserver.addOnGlobalLayoutListener(
            object :ViewTreeObserver.OnGlobalLayoutListener{
                override fun onGlobalLayout() {
                    binding.unpressedTagsGridlayout.viewTreeObserver
                        .removeOnGlobalLayoutListener(this)
                    var columnCount = binding.unpressedTagsGridlayout
                        .width/(2*binding.humorTagCheckBox.width)
                    if (columnCount<1){
                        columnCount = 1
                    }
                    binding.unpressedTagsGridlayout.columnCount = columnCount
                    binding.pressedTagsGridlayout.columnCount = columnCount
                }

            }
        )
        checkTagState(binding.carsTagCheckBox)
        checkTagState(binding.foodTagCheckBox)
        checkTagState(binding.humorTagCheckBox)
        checkTagState(binding.restaurantsTagCheckBox)
        checkTagState(binding.politicsTagCheckBox)
        checkTagState(binding.sportTagCheckBox)
        checkTagState(binding.serialsTagCheckBox)
        checkTagState(binding.newsTagCheckBox)
        checkTagState(binding.walksTagCheckBox)
        checkTagState(binding.jobTagCheckBox)
        checkTagState(binding.restTagCheckBox)
        checkTagState(binding.recipesTagCheckBox)
        checkTagState(binding.filmsTagCheckBox)


        binding.foodTagCheckBox.apply {
            setOnCheckedChangeListener { buttonView, isChecked ->
                replaceTag(this,isChecked)
            }
        }

        binding.carsTagCheckBox.apply {
            setOnCheckedChangeListener { buttonView, isChecked ->
                replaceTag(this,isChecked)
            }
        }

        binding.filmsTagCheckBox.apply {
            setOnCheckedChangeListener { buttonView, isChecked ->
                replaceTag(this,isChecked)
            }
        }

        binding.politicsTagCheckBox.apply {
            setOnCheckedChangeListener { buttonView, isChecked ->
                replaceTag(this,isChecked)
            }
        }

          binding.restaurantsTagCheckBox .apply {
            setOnCheckedChangeListener { buttonView, isChecked ->
                replaceTag(this,isChecked)
            }
        }
        binding.newsTagCheckBox .apply {
            setOnCheckedChangeListener { buttonView, isChecked ->
                replaceTag(this,isChecked)
            }
        }

        binding.serialsTagCheckBox .apply {
            setOnCheckedChangeListener { buttonView, isChecked ->
                replaceTag(this,isChecked)
            }
        }
        binding.sportTagCheckBox .apply {
            setOnCheckedChangeListener { buttonView, isChecked ->
                replaceTag(this,isChecked)
            }
        }
        binding.walksTagCheckBox .apply {
            setOnCheckedChangeListener { buttonView, isChecked ->
                replaceTag(this,isChecked)
            }
        }
        binding.restTagCheckBox .apply {
            setOnCheckedChangeListener { buttonView, isChecked ->
                replaceTag(this,isChecked)
            }
        }
        binding.jobTagCheckBox .apply {
            setOnCheckedChangeListener { buttonView, isChecked ->
                replaceTag(this,isChecked)
            }
        }
        binding.recipesTagCheckBox .apply {
            setOnCheckedChangeListener { buttonView, isChecked ->
                replaceTag(this,isChecked)
            }
        }
        binding.humorTagCheckBox.apply {
            setOnCheckedChangeListener { buttonView, isChecked ->
                replaceTag(this,isChecked)
            }
        }

    }

    private fun replaceTag(view: View, isChecked:Boolean){
        val animationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
        if (isChecked) {
            view.animate().apply {
                alpha(0f)
                duration = animationDuration.toLong()
                setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        alpha(1f)
                        binding.unpressedTagsGridlayout.removeView(view)
                        binding.pressedTagsGridlayout.addView(view,0)
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
                        binding.pressedTagsGridlayout.removeView(view)
                        binding.unpressedTagsGridlayout.addView(view,0)
                        view.animate().setListener(null)
                    }
                })
            }
        }
    }

    private fun checkTagState(checkBox: CheckBox){
        if(checkBox.isChecked&&checkBox.parent==binding.unpressedTagsGridlayout){
            binding.unpressedTagsGridlayout.removeView(checkBox)
            binding.pressedTagsGridlayout.addView(checkBox,0)
        }
    }

    companion object {
        fun newInstance() = CategoriesSelectorFragment()
    }
}