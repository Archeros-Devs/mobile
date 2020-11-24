package br.com.arquerosdev.model;

open class ModelDataBanido(private val strData: String){

    open val ano: String = strData.substring(0,4)
    open val mes: String = strData.substring(5,7)
    open val dia: String = strData.substring(8,10)
}
