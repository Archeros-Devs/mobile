package br.com.arquerosdev.retrofit.service

import br.com.arquerosdev.model.ModelProfissao

interface CallbackResponse<T> {
    fun sucess(response: T)

    fun error(response: String)
}