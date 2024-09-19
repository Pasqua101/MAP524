package com.senecacollege.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Defining buttons
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnAdd, btnSub, btnDiv, btnMult, btnEqual, btnAC, btnDecimal, btnBackSpace;

    //Initializing EditText
     private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing buttons
        btn0 = findViewById(R.id.button0);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);

        btnAdd = findViewById(R.id.additionButton);
        btnSub = findViewById(R.id.subtractionButton);
        btnDiv = findViewById(R.id.divisionButton);
        btnMult = findViewById(R.id.muliplicationButton);
        btnEqual = findViewById(R.id.equalsButton);
        btnAC = findViewById(R.id.clearButton);
        btnDecimal = findViewById(R.id.decimalButton);
        btnBackSpace = findViewById(R.id.backSpaceButton);

        btn0.setOnClickListener(MainActivity.this);
        btn1.setOnClickListener(MainActivity.this);
        btn2.setOnClickListener(MainActivity.this);
        btn3.setOnClickListener(MainActivity.this);
        btn4.setOnClickListener(MainActivity.this);
        btn5.setOnClickListener(MainActivity.this);
        btn6.setOnClickListener(MainActivity.this);
        btn7.setOnClickListener(MainActivity.this);
        btn8.setOnClickListener(MainActivity.this);
        btn9.setOnClickListener(MainActivity.this);

        btnAdd.setOnClickListener(MainActivity.this);
        btnSub.setOnClickListener(MainActivity.this);
        btnDiv.setOnClickListener(MainActivity.this);
        btnMult.setOnClickListener(MainActivity.this);
        btnEqual.setOnClickListener(MainActivity.this);
        btnAC.setOnClickListener(MainActivity.this);
        btnDecimal.setOnClickListener(MainActivity.this);
        btnBackSpace.setOnClickListener(MainActivity.this);
    }

    public void onClick(View view){
        editText = findViewById(R.id.editText);
        String currentText = editText.getText().toString();
        char lastChar = currentText.isEmpty() ? ' ' : currentText.charAt(currentText.length() - 1);

        if (view.getId() == R.id.button0){
            currentText += "0";
        }
        else if (view.getId() == R.id.button1){
            currentText += "1";
        }
        else if(view.getId() == R.id.button2){
            currentText += "2";
        }
        else if (view.getId() == R.id.button3){
            currentText += "3";
        }
        else if (view.getId() == R.id.button4){
            currentText += "4";
        }
        else if (view.getId() == R.id.button5){
            currentText += "5";
        }
        else if (view.getId() == R.id.button6){
            currentText += "6";
        }
        else if (view.getId() == R.id.button7){
            currentText += "7";
        }
        else if (view.getId() == R.id.button8){
            currentText += "8";
        }
        else if (view.getId() == R.id.button9){
            currentText += "9";
        }
        else if (view.getId() == R.id.clearButton){
            currentText = ""; // Overwriting the EditText element instead of appending to it.
        }
        else if (view.getId() == R.id.additionButton){
            if (lastChar != '+' && lastChar != '/' && lastChar != '-' && lastChar != 'x' && !currentText.isEmpty()) { //If the last character is not an operator, allow the user to use the selected operator. Otherwise don't
                currentText += "+";
            }
        }
        else if (view.getId() == R.id.subtractionButton){
            if (lastChar != '+' && lastChar != '/' && lastChar != '-' && lastChar != 'x' && !currentText.isEmpty()) {
                currentText += "-";
            }

        }
        else if (view.getId() == R.id.divisionButton){
            if (lastChar != '+' && lastChar != '/' && lastChar != '-' && lastChar != 'x' && !currentText.isEmpty()) {
                currentText += "/";
            }
        }
        else if (view.getId() == R.id.muliplicationButton){
            if (lastChar != '+' && lastChar != '/' && lastChar != '-' && lastChar != 'x' && !currentText.isEmpty()) {
                currentText += "x";
            }
        }
        else if (view.getId() == R.id.equalsButton){
            if (currentText.length() > 0 && !currentText.equals(".")){ //Preventing the total function from running if nothing is in the equation
                String equation = editText.getText().toString();
                currentText = getTotal(equation);
            }
        }
        else if (view.getId() == R.id.decimalButton){
            if (lastChar != '.' && !currentText.endsWith("+") && !currentText.endsWith("-") && !currentText.endsWith("x") && !currentText.endsWith("/")) {
                // Check if the current number already contains a decimal point
                int lastIndex = currentText.lastIndexOf('.');
                if (lastIndex == -1 || lastIndex < currentText.lastIndexOf('+') || lastIndex < currentText.lastIndexOf('-') || lastIndex < currentText.lastIndexOf('x') || lastIndex < currentText.lastIndexOf('/')) {
                    currentText += ".";
                }
            }
        }
        else if (view.getId() == R.id.backSpaceButton){
            if(currentText.length() > 0){ //If the length of the string is more than 0, remove the last character from the string
                currentText = currentText.substring(0, currentText.length() - 1);
            }
        }
        editText.setText(currentText);
    }

    private String getTotal(String equation){ //Will find the total of the equation entered and convert it back to a string. (May have to do a while true loop and loop through the string to find each operator.)
        String[] components = equation.split("[+\\-x/]");
        char[] operators = new char[equation.length()];
        int operatorCount = 0;

        // Extract operators
        for (char c : equation.toCharArray()) {
            if (c == '+' || c == '-' || c == 'x' || c == '/') {
                operators[operatorCount] = c;
                operatorCount++;
            }
        }

        // Initialize the total with the first number
        double total = Double.parseDouble(components[0]);
        int componentIndex = 1;

        // Iterate through the operators and components to perform calculations
        for (int i = 0; i < operatorCount; i++) {
            char operator = operators[i];
            double nextNumber = Double.parseDouble(components[componentIndex]);


            switch (operator) {
                case '+':
                    total += nextNumber;
                    break;
                case '-':
                    total -= nextNumber;
                    break;
                case 'x':
                    total *= nextNumber;
                    break;
                case '/':
                    if (nextNumber != 0) {
                        total /= nextNumber;
                    } else {
                        return "Error: Division by zero";
                    }
                    break;
            }

            componentIndex++;
        }

        //Converting the total to a string with no decimal, if it's a whole number
        DecimalFormat decimalFormat = new DecimalFormat("#.###"); //Formatting to three decimal places
        String formattedTotal = decimalFormat.format(total);

        if (formattedTotal.endsWith(".0")){
            formattedTotal = formattedTotal.substring(0, formattedTotal.length() - 2);
        }
        // Convert the total to a string and return it
        return formattedTotal;
    }
}

