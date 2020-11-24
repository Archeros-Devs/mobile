package br.com.arquerosdev.model;

open class ModelData(private val strData: String){

    open val ano: String = strData.substring(0,4)
    open val mes: String = strData.substring(5,7)
    open val dia: String = strData.substring(8,10)
    open val horas: String = strData.substring(11,13)
    open val minuto: String = strData.substring(14,16)
    open val segundo: String = strData.substring(17,19)
}
