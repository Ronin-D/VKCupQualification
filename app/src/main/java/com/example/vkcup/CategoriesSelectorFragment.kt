package com.example.vkcup

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import com.example.vkcup.databinding.FragmentCategoriesSelectorBinding
private const val KEY_CHECKED_TAGS_COUNT = "checked count"
class CategoriesSelectorFragment : Fragment() {

    private lateinit var binding:FragmentCategoriesSelectorBinding
    private var checkedCount = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesSelectorBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_CHECKED_TAGS_COUNT,checkedCount)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkedCount = savedInstanceState?.getInt(KEY_CHECKED_TAGS_COUNT)?:0
    }

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
        updateUI()

        binding.foodTagCheckBox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                replaceTag(this,isChecked)
            }
        }

        binding.carsTagCheckBox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                replaceTag(this,isChecked)
            }
        }

        binding.filmsTagCheckBox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                replaceTag(this,isChecked)
            }
        }

        binding.politicsTagCheckBox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                replaceTag(this,isChecked)
            }
        }

          binding.restaurantsTagCheckBox .apply {
            setOnCheckedChangeListener { _, isChecked ->
                replaceTag(this,isChecked)
            }
        }
        binding.newsTagCheckBox .apply {
            setOnCheckedChangeListener { _, isChecked ->
                replaceTag(this,isChecked)
            }
        }

        binding.serialsTagCheckBox .apply {
            setOnCheckedChangeListener { _, isChecked ->
                replaceTag(this,isChecked)
            }
        }
        binding.sportTagCheckBox .apply {
            setOnCheckedChangeListener { _, isChecked ->
                replaceTag(this,isChecked)
            }
        }
        binding.walksTagCheckBox .apply {
            setOnCheckedChangeListener { _, isChecked ->
                replaceTag(this,isChecked)
            }
        }
        binding.restTagCheckBox .apply {
            setOnCheckedChangeListener { _, isChecked ->
                replaceTag(this,isChecked)
            }
        }
        binding.jobTagCheckBox .apply {
            setOnCheckedChangeListener { _, isChecked ->
                replaceTag(this,isChecked)
            }
        }
        binding.recipesTagCheckBox .apply {
            setOnCheckedChangeListener { _, isChecked ->
                replaceTag(this,isChecked)
            }
        }
        binding.humorTagCheckBox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                replaceTag(this,isChecked)
            }
        }
    }

    private fun replaceTag(view: View, isChecked:Boolean){
        view.isEnabled = false
        val animationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
        if (isChecked) {
            view.animate().apply {
                alpha(0f)
                duration = animationDuration.toLong()
                setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        alpha(1f)
                        checkedCount++
                        binding.unpressedTagsGridlayout.removeView(view)
                        binding.pressedTagsGridlayout.addView(view,0)
                        view.isEnabled = true
                        updateUI()
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
                        checkedCount--
                        binding.pressedTagsGridlayout.removeView(view)
                        binding.unpressedTagsGridlayout.addView(view,0)
                        view.isEnabled = true
                        updateUI()
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

    private fun updateUI(){
        if (checkedCount!=0){
            binding.continueButton.visibility = View.VISIBLE
        }
        else{
            binding.continueButton.visibility = View.GONE
        }
    }

    companion object {
        fun newInstance() = CategoriesSelectorFragment()
    }
}