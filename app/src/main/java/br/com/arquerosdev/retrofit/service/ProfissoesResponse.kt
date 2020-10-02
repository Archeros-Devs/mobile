package br.com.arquerosdev.retrofit.service

import br.com.arquerosdev.model.ModelProfissao

interface ProfissoesResponse {
    fun sucess(profissoes: List<ModelProfissao>)
}