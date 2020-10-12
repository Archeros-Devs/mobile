package br.com.arquerosdev.retrofit.service

import br.com.arquerosdev.model.ModelEscolaridade
import br.com.arquerosdev.model.ModelProfissao

interface EscolaridadeResponse {
    fun sucess(escolaridades: List<ModelEscolaridade>)
}