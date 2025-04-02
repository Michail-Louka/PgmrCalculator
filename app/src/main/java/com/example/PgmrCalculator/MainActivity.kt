package com.example.PgmrCalculator
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.lang.System.exit
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//try
//      Arrays
        var numArray:Array<Double?> =arrayOfNulls(2)
        var Oper:String =""
        var isDecimal = false
        var countDecimal =1
        //Operator flags
        var firstOparatorExist = false
        var operatorExist:Boolean = false
        var newOperExist = false
        //First Number Flag
        var firstNumExist:Boolean = false
        var firstNegative = false
        //Second Number Flag
        var newNumExist=false
        //Modes flag
        var programmerMod = false


        var addNum = StringBuilder()
//      Buttons
        var num = findViewById<TextView>(R.id.num)
        num.text =addNum.append(num.text)
        var isOdd = findViewById<TextView>(R.id.isOd)
        val num0 = findViewById<Button>(R.id.num0)
        val num1 = findViewById<Button>(R.id.num1)
        val num2= findViewById<Button>(R.id.num2)
        val num3= findViewById<Button>(R.id.num3)
        val num4= findViewById<Button>(R.id.num4)
        val num5= findViewById<Button>(R.id.num5)
        val num6= findViewById<Button>(R.id.num6)
        val num7 = findViewById<Button>(R.id.num7)
        val num8 = findViewById<Button>(R.id.num8)
        val num9 = findViewById<Button>(R.id.num9)
        val decimal =findViewById<Button>(R.id.decimal)
        //Oparators Buttons
        val numplus = findViewById<Button>(R.id.numPlus)
        val numdiv = findViewById<Button>(R.id.numDiv)
        val nummult = findViewById<Button>(R.id.numMulti)
        val num_ = findViewById<Button>(R.id.num_)

        val numClr = findViewById<Button>(R.id.numClr)
        val numequal = findViewById<Button>(R.id.numEqual)
        val Mode = findViewById<Button>(R.id.Arrow)
        val btnErase = findViewById<ImageButton>(R.id.btnEraser)
        fun culcClear (){
            numArray[0] = null
            numArray[1] = null
            operatorExist = false
            newNumExist = false
            firstNumExist = false
            firstOparatorExist = false
            isDecimal =false
            countDecimal = 1
            Oper = ""
            isOdd.text = addNum.clear()
            num.text = addNum.clear()
        }
        fun power(num:Double,pwr:Int):Double{
            var number:Double
            number = num
            if(pwr ==1){
                return number
            }
            for(i in 1..pwr-1){
                number = number*num
            }
            return number
        }

        fun numQueue(number:Double){
            if(!firstNumExist &&!newNumExist) {
                //if is the first time we press a number
               num.text = addNum.clear()
              if(isDecimal){

                   if (!firstNegative) {
                       numArray[0] =  (number/power(10.0,countDecimal))
                   } else {
                       numArray[0] =  -(number/power(10.0,countDecimal))
                   }
                  countDecimal++
              }else{
                  if(!firstNegative){
                        numArray[0] = number
                  }else{
                        numArray[0] = -number
                  }
              }




               num.text = addNum.append(numArray[0])
               firstNumExist = true
            }else if(!operatorExist && !newNumExist){
                //if we press a num for the second time before operator,it make it *10
                if(isDecimal){
                    if (!firstNegative) {
                        numArray[0] = numArray[0]!! + (number/(power(10.0,countDecimal)))
                    } else {
                        numArray[0] = numArray[0]!! -  (number/(power(10.0,countDecimal)))
                    }
                    countDecimal++
                }else{
                    if (!firstNegative) {
                    numArray[0] = (numArray[0]!! * 10.0f) + number
                    }else {
                    numArray[0] = (numArray[0]!! * 10.0f) - number
                    }
                }
                num.text = addNum.clear()
                num.text = addNum.append(numArray[0])
            }else if(operatorExist && !newNumExist) {
                //if press number after operator
                newNumExist=true
                operatorExist = true
                firstNegative = false
                if (isDecimal){
                    numArray[1] =(number/power(10.0,countDecimal))
                }else{
                    numArray[1] = number}


                num.text = addNum.append(numArray[1])
            }else if(operatorExist){
                    //if we press a num for the second time,it make it *10
                if(isDecimal){
                    if (!firstNegative) {
                        numArray[1] = numArray[1]!! + (number/power(10.0,countDecimal))
                    } else {
                        numArray[1] = numArray[1]!! - (number/power(10.0,countDecimal))
                    }
                    countDecimal++
                }else{
                    if (!firstNegative) {
                        numArray[1] = (numArray[1]!! * 10.0f) + number
                    }else {
                        numArray[1] = (numArray[1]!! * 10.0f) - number
                    }
                }

                num.text = addNum.clear()
                num.text = addNum.append(numArray[0]).append(Oper).append(numArray[1])
            }else{
                operatorExist= false
                newNumExist =false
                firstNumExist = false
            }
        }
        fun result(){
            isDecimal=false
            countDecimal=1
            if(numArray[0]!=null&&numArray[1]!=null){
            if (Oper == "+") {
                numArray[0] = numArray[0]!! + numArray[1]!!
            } else if (Oper == "-") {
                numArray[0] = numArray[0]!! - numArray[1]!!
            } else if (Oper == "*") {
                numArray[0] = numArray[0]!! * numArray[1]!!
            } else if (Oper == "/") {
                if (numArray[1] != 0.0) {
                    numArray[0] = numArray[0]!! / numArray[1]!!
                }
            }else if(Oper =="mod"){
                numArray[0] =numArray[1]?.let { numArray[0]?.mod(it)
                }
            }else if(Oper =="quot"){
                numArray[0] = numArray[0]!!/ numArray[1]!!
            }else if(Oper =="^"){
                numArray[0] =numArray[1]?.let { numArray[0]?.pow(it)}
            }else if (Oper=="%"){
                numArray[0] = (numArray[0]!! /numArray[1]!!)*100
            }
                //  if number is odd

            if ((numArray[0]?.mod(2.0)) ==1.0){
                isOdd.text = "odd"
            }else if((numArray[0]?.mod(2.0)) ==0.0){
                isOdd.text = ""
            }
            operatorExist = false
                newNumExist =false
            num.text = addNum.clear()
            num.text = addNum.append(numArray[0])
            }
        }
        fun operatorAction( operator:String){
            if(firstNumExist &&!operatorExist){
                operatorExist = true
                newNumExist = false
                Oper = operator
                isDecimal=false
                countDecimal=1

                num.text = addNum.append(Oper)
            }else if(!firstNumExist&&operator=="-") {
                firstNegative = true
                num.text = "-"
            }
        }
        //clear culculator

        fun erase(){
            if(firstNumExist &&!newNumExist&&!operatorExist) {
                culcClear()
            }else if( operatorExist&&!newNumExist){
                Oper=""
                operatorExist = false

                num.text = addNum.clear()
                num.text = addNum.append(numArray[0])
            }else if(newNumExist) {
                newNumExist=false
                numArray[1] = null
                num.text = addNum.clear()
                num.text = addNum.append(numArray[0]).append(Oper)
            }
            isDecimal=false
            countDecimal=1

        }

        //Numbers
        num0.setOnClickListener {
            numQueue(0.0)
        }
        num1.setOnClickListener{
            numQueue(1.0)
        }
        num2.setOnClickListener{
            numQueue(2.0)
        }
        num3.setOnClickListener{
            numQueue(3.0)
        }
        num4.setOnClickListener{
            numQueue(4.0)
        }
        num5.setOnClickListener{
            numQueue(5.0)
        }
        num6.setOnClickListener{
            numQueue(6.0)
        }
        num7.setOnClickListener{
            numQueue(7.0)
        }
        num8.setOnClickListener{
            numQueue(8.0)
        }
        num9.setOnClickListener{
            numQueue(9.0)
        }

        decimal.setOnClickListener{
            isDecimal = true
        }
        numClr.setOnClickListener{
            culcClear()
        }
        btnErase.setOnClickListener(){
            erase()
        }
        //Actions
        numplus.setOnClickListener(){
            if (!programmerMod){
            operatorAction("+")}else{
                operatorAction("mod")
            }
        }
        num_.setOnClickListener(){
            if (!programmerMod){
                operatorAction("-")}else{
                operatorAction("quot")
            }
        }
        nummult.setOnClickListener(){
            if (!programmerMod){
                operatorAction("*")}else{
                operatorAction("^")
            }
        }
        numdiv.setOnClickListener(){
            if (!programmerMod){
                operatorAction("/")}else{
                operatorAction("%")
            }
        }
        //change mode
        Mode.setOnClickListener(){
            if(programmerMod){
                programmerMod=false
                numplus.text = "+"
                num_.text = "-"
                nummult.text = "*"
                numdiv.text = "/"
                Toast.makeText(this,"Classic mode",Toast.LENGTH_LONG).show()
            }else{
                programmerMod=true
                numplus.text = "mod"
                num_.text = "quot"
                nummult.text = "^"
                numdiv.text = "%"
                Toast.makeText(this,"Programmer mode",Toast.LENGTH_LONG).show()
            }
        }
//        ==
        numequal.setOnClickListener(){
            result()
        }

    }
}

