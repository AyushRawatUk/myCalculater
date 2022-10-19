package com.example.mycalculater

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.function.BinaryOperator

class MainActivity : AppCompatActivity() {

    private var tvInput : TextView? = null
    private var btnOne : Button? = null
    private var btnTwo : Button? = null
    private var btnTree : Button? = null
    private var btnFour : Button? = null
    private var btnFive : Button? = null
    private var btnSix : Button? = null
    private var btnSeven : Button? = null
    private var btnEight : Button? = null
    private var btnNine : Button? = null
    private var btnZero : Button? = null
    private var btnClear : Button? = null
    private var btnDot : Button?  = null
    private var btnPlus : Button?  = null
    private var btnMinus : Button?  = null
    private var btnDivide : Button?  = null
    private var btnMultiply : Button?  = null
    private var btnEqualTo : Button?  = null
    private var firstClick : Boolean = true
    private var lastDot : Boolean = false
    private var  lastNumeric: Boolean = true
    private var  lastOperator: Boolean = false

//    private var isOperatorAdded: Boolean = true



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
        btnOne = findViewById(R.id.btnOne)
        btnTwo = findViewById(R.id.btnTwo)
        btnTree = findViewById(R.id.btnThree)
        btnFour = findViewById(R.id.btnFour)
        btnFive = findViewById(R.id.btnFive)
        btnSix = findViewById(R.id.btnSix)
        btnSeven = findViewById(R.id.btnSeven)
        btnEight = findViewById(R.id.btnEight)
        btnNine = findViewById(R.id.btnNine)
        btnZero= findViewById(R.id.btnZero)
        btnClear= findViewById(R.id.btnClear)
        btnDot = findViewById(R.id.btnDot)
        btnPlus = findViewById(R.id.btnPlus)
        btnMinus = findViewById(R.id.btnMinus)
        btnDivide = findViewById(R.id.btnDived)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnEqualTo = findViewById(R.id.btnEqualTo)


        onClickFunctions()



    }

    private fun clearFunction(){
        tvInput?.text = "0"
        firstClick = true
        lastDot = false
        lastNumeric = true
    }

//    private fun plusFunction(){
//        tvInput?.append("+")
//    }
//
//    private fun equateFunction(){
//        var equation  = tvInput?.text?.split("+")
//        Toast(equation, )
//
//    }

    private fun onClickFunctions(){
        btnOne?.setOnClickListener {
            printText("1")

        }

        btnTwo?.setOnClickListener {
            printText("2")
        }

        btnTree?.setOnClickListener {
            printText("3")
        }

        btnFour?.setOnClickListener {
            printText("4")
        }

        btnFive?.setOnClickListener {
            printText("5")
        }

        btnSix?.setOnClickListener {
            printText("6")
        }

        btnSeven?.setOnClickListener {
            printText("7")
        }

        btnEight?.setOnClickListener {
            printText("8")
        }

        btnNine?.setOnClickListener {
            printText("9")
        }

        btnZero?.setOnClickListener {
            if(!firstClick){
                printText("0")
            }

        }

        btnClear?.setOnClickListener {
            clearFunction()
        }

        btnDot?.setOnClickListener {
            onDecimal()
        }

        btnPlus?.setOnClickListener {
            onOperator("+")
        }

        btnMinus?.setOnClickListener {
            onOperator("-")
        }

        btnMultiply?.setOnClickListener {
            onOperator("*")
        }

        btnDivide?.setOnClickListener {
            onOperator("/")
        }

        btnEqualTo?.setOnClickListener {

            onEqualTo()
        }
    }

    private fun printText(numStr:String){
        if(firstClick && lastNumeric){
            tvInput?.text = numStr
            firstClick = false
            lastNumeric = true
        }else{
            tvInput?.append(numStr)
            lastNumeric = true
        }
    }

    private fun onDecimal(){
        if ((lastNumeric || lastOperator)  && !lastDot){
            tvInput?.append(".")
            lastDot = true
            lastNumeric = false
            firstClick = false
            lastOperator = false
        }
    }

    private fun onOperator(operator: String){
        tvInput?.text?.let {
            if(firstClick && (operator == "-" && !isOperatorAdded(it.toString()))){
                tvInput?.text = "-"
                firstClick = false
            }
            else if(lastNumeric && !isOperatorAdded(it.toString())){

                tvInput?.append(operator)
                lastNumeric = false
                lastOperator = true
                lastDot = false
            }


        }
    }

    private fun isOperatorAdded(value : String ) : Boolean {
        var afterValue  = StringBuilder(value).deleteCharAt(0).toString()
        return if( value.startsWith("-") && !afterValue.contains("-") ){
            false
        }else{
            value.contains("-")||
            value.contains("+")||
            value.contains("*")||
            value.contains("/")
        }
    }

    private fun onEqualTo(){

        try {

            tvInput?.text?.let {
                var prefix = ""
                var tvValue = it.toString()

                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")){
                    var splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = (one.toDouble() - two.toDouble()).toString()
                }else if (tvValue.contains("+")){
                    var splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = (one.toDouble() + two.toDouble()).toString()

                }else if (tvValue.contains("*")){
                    var splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = (one.toDouble() * two.toDouble()).toString()

                }else if (tvValue.contains("/")){
                    var splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = (one.toDouble() / two.toDouble()).toString()
                }


            }

        }catch (e: ArithmeticException){
            e.printStackTrace()
        }
    }
}