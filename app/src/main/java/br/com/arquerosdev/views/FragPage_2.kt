package br.com.arquerosdev.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.arquerosdev.R
import kotlinx.android.synthetic.main.splash_1.view.*


class FragSplash_2 : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.splash_2, container, false)

        view.btnsplash.setOnClickListener{btview ->
            val frag = FragSplash_3()
            activity!!.supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
                )
                .replace(R.id.frag_main, frag, "splash_3")
                .addToBackStack("flagSplash_2")
                .commit()
        }
        return view
    }

}