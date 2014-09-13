package com.example.calculatorproject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Vibrator;


//current bug where hitting function before hitting numberButton crashes program 
//due to null value being parsed in computoin method

//equal button doesn't do anything, possible rework might be needed to do contitional testing before doing computation



/*
 * add decimal button, undo button, backspace button, negative button
 */
public class MainActivity extends Activity {

	private double num1, num2;
	private String display="";
	protected TextView result;
	public Vibrator vib;
	private short vibLength=30;
	char operator='a';

	private String defaultText="0";
	

	Button oneSign;
	Button twoSign;
	Button threeSign;
	Button fourSign;
	Button fiveSign;
	Button sixSign;
	Button sevenSign;
	Button eightSign;
	Button nineSign;
	Button zeroSign;

	Button plusSign;
	Button minusSign;
	Button multiSign;
	Button divideSign;
	Button clearSign;
	Button equalSign;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		num1=0;
		num2=0;

		result=(TextView)findViewById(R.id.result_id);
		vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

		oneSign = (Button)findViewById(R.id.Btn1_id);
		twoSign = (Button)findViewById(R.id.Btn2_id);
		threeSign = (Button)findViewById(R.id.Btn3_id);
		fourSign = (Button)findViewById(R.id.Btn4_id);
		fiveSign = (Button)findViewById(R.id.Btn5_id);
		sixSign= (Button)findViewById(R.id.Btn6_id);
		sevenSign = (Button)findViewById(R.id.Btn7_id);
		eightSign = (Button)findViewById(R.id.Btn8_id);
		nineSign = (Button)findViewById(R.id.Btn9_id);
		zeroSign = (Button)findViewById(R.id.Btn0_id);

		oneSign.setOnClickListener(numberButtons);
		twoSign.setOnClickListener(numberButtons);
		threeSign.setOnClickListener(numberButtons);
		fourSign.setOnClickListener(numberButtons);
		fiveSign.setOnClickListener(numberButtons);
		sixSign.setOnClickListener(numberButtons);
		sevenSign.setOnClickListener(numberButtons);
		eightSign.setOnClickListener(numberButtons);
		nineSign.setOnClickListener(numberButtons);
		zeroSign.setOnClickListener(numberButtons);

		plusSign = (Button) findViewById(R.id.Btnplus_id);
		plusSign.setOnClickListener(functionButtons);

		minusSign = (Button) findViewById(R.id.Btnminus_id);
		minusSign.setOnClickListener(functionButtons);
		
		multiSign = (Button) findViewById(R.id.Btnmulti_id);
		multiSign.setOnClickListener(functionButtons);
		
		divideSign = (Button) findViewById(R.id.Btndivide_id);
		divideSign.setOnClickListener(functionButtons);
		
		clearSign = (Button) findViewById(R.id.Btnclear_id);
		clearSign.setOnClickListener(clearButton);

		equalSign = (Button) findViewById(R.id.Btnequal_id);
		equalSign.setOnClickListener(functionButtons);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void insert(String x){
		display+=x;
		result.setText(display);
	}

	public double computin(double a, double b){
		
		double resultant=0.0;
		
		switch(operator){
		
		case 'a':
			resultant=a+b;
			break;
		case 's':
			resultant=a-b;
			break;
		case 'm':
			resultant=a*b;
			break;
		case 'd':
			
			resultant=a/b;
			break;
		case 'q':
			
			break;
		default:
			result.setText("Shit just happened yo");
		
		}
		return resultant;
		
		
	}

	public double add(double x, double y){return x+y;}

	public double sub(double x, double y){return x-y;}

	public double mult(double x, double y){return x*y;}

	public double div(double x, double y){return x/y;}


	View.OnClickListener functionButtons = new OnClickListener(){

		public void onClick(View v){

			int id=v.getId();
			vib.vibrate(vibLength); //all buttons vibrate

			
			//use try to prevent app from shitting itself whenever user initially hits function
			//java.lang.NumberFomrmatException
			try{
				num2=Double.parseDouble(display);
	
				num1=computin(num1,num2);
				num2=0;
				display="";
				result.setText(""+num1);
			}catch(NumberFormatException e){}
			
			
			
			switch(id){

			case R.id.Btnplus_id:
				operator = 'a';
				break;
			case R.id.Btnminus_id:
				operator = 's';
				break;
			case R.id.Btnmulti_id:
				operator = 'm';
				break;
			case R.id.Btndivide_id:
				operator = 'd';
				break;
			case R.id.Btnequal_id:
				
				break;
			default:
					
			
				
			//currently causes application to crash FIXED
			//below code causes it to parse "" as double, crashing app
			//need to either conditionalize or create new button handler
			//code segment moved to clearButton handler
			/*
			case R.id.Btnclear_id:
				num1=0;
				num2=0;
				display="";
				operator = 'a';
				//result.setText("");
				break;
			*/
				
				
			}
			

			

			
		}

	};


	View.OnClickListener numberButtons = new OnClickListener(){

		public void onClick(View v){

			int id=v.getId();
			vib.vibrate(vibLength); //all buttons vibrate

			switch(id){

			case R.id.Btn0_id:
				insert("0");
				break;
			case R.id.Btn1_id:
				insert("1");
				break;
			case R.id.Btn2_id:
				insert("2");
				break;
			case R.id.Btn3_id:
				insert("3");
				break;
			case R.id.Btn4_id:
				insert("4");
				break;
			case R.id.Btn5_id:
				insert("5");
				break;
			case R.id.Btn6_id:
				insert("6");
				break;
			case R.id.Btn7_id:
				insert("7");
				break;
			case R.id.Btn8_id:
				insert("8");
				break;
			case R.id.Btn9_id:
				insert("9");
				break;
			}

		}
	};
	
	View.OnClickListener clearButton = new OnClickListener(){
		public void onClick(View v){
			
			vib.vibrate(vibLength); //all buttons vibrate
			
			num1=0;
			num2=0;
			display="";
			operator = 'a';
			result.setText(defaultText);
		}
	};





}
