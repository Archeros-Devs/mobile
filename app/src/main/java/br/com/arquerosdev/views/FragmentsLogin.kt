package br.com.arquerosdev.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import br.com.arquerosdev.R
import kotlinx.android.synthetic.main.fragment_login.view.*

class FragmentsLogin : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_login, container, false)
        view.bt_cadastro.setOnClickListener { view ->
            val frag = FragCadastroUsuario()
            activity!!.getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_main, frag, "fragmnetId")
                .commit()
        }

        return view
    }
}