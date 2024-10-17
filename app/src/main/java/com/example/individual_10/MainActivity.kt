package com.example.individual_10

import com.example.individual_10.databinding.ActivityMainBinding
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private val divisas = listOf<String>("USD","CLP","EUR")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)


        binding.spinnerUno.adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,divisas)
        binding.spinnerDos.adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,divisas)

        initListener()
    }

    private fun initListener() {
        binding.btnConvert.setOnClickListener(View.OnClickListener {
            val monto = binding.textViewEntrada.text.toString().toInt()

            val divisaActual = binding.spinnerUno.selectedItem.toString()
            val divisaCambio = binding.spinnerDos.selectedItem.toString()

            val resultado =  conversorDivisas(monto.toDouble(),divisaActual,divisaCambio).toString()
            binding.txtnumeroFInal.text = resultado
        })
        binding.btnReset.setOnClickListener(View.OnClickListener {
            limpiar()
        })

        binding
    }

    private fun conversorDivisas(monto:Double,divisaActual:String,divisaCambio:String): Double {

        var resultado = monto
        when (divisaActual) {
            "USD" -> if (divisaCambio == "CLP") {
                resultado =  monto * 943; }
            else if (divisaCambio == "USD") {

                resultado =    monto * 1
            } else {
                resultado = monto * 0.89
            }

            "CLP" -> if (divisaCambio == "CLP") {
                resultado =  monto * 1
            } else if (divisaCambio == "USD") {
                resultado =  monto * 0.001
            } else {
                resultado = monto * 0.001
            }

            "EUR" -> if (divisaCambio == "CLP") {
                resultado = monto * 1018
            } else if (divisaCambio == "USD") {
                resultado = monto *1.11
            } else if (divisaCambio == "EUR") {
                resultado = monto*1
            }




        }
        return resultado
    }

    fun limpiar(){
        binding.txtnumeroFInal.text = ""
        binding.textViewEntrada.setText("")
    }
}