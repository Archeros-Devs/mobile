package br.com.arquerosdev

import android.os.Bundle
import br.com.arquerosdev.viewmodel.UsuarioViewModel
import br.com.arquerosdev.views.FragmentsLogin

class LoginActivity : BaseActivity() {

    private var TAG: String = "LoginActivity"

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.activity_login)

        if(icicle == null){
            val ft = supportFragmentManager.beginTransaction()
            val fragLogin = FragmentsLogin()
            ft.add(R.id.frag_main, fragLogin, "fragLogin")
            ft.commit()
        }

    }
}