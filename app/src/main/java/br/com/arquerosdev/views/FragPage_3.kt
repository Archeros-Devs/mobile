package br.com.arquerosdev.views

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.arquerosdev.LoginActivity
import br.com.arquerosdev.R
import kotlinx.android.synthetic.main.splash_1.view.*


class FragSplash_3 : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.splash_3, container, false)

        view.btnsplash.setOnClickListener{btview ->
            startActivity(Intent(activity!!, LoginActivity::class.java))
            activity!!.finish()
        }

        return view
    }

}