package com.example.fcpfapp;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;



public class MainActivity extends Activity {

	private TextView ding_view;
	private RadioGroup actors_rgp;
	private RadioButton radioButtonThree;
	private int actors=4;
	private EditText editText[][]=new EditText[3][4];
	private int huoNiao[]={0,0,0,0};
	private EditText price_edit;
	private double price=0.5;
	private int tuoNiao[]={0,0,0,0};
	private int huXi[]={0,0,0,0};
	double result[]={0,0,0,0};
	private TextView result_view[]=new TextView[4];
	private Button calculate_btn,clean_btn,exit_btn;
	private MyCalculateOnClickListener myCal=new MyCalculateOnClickListener();
	private MyOnFocusChangListener myOnFocusChangListener=new MyOnFocusChangListener();
	private MyClearOnClickListener myClearOnClickListener=new MyClearOnClickListener();

	/*Button btnCount;
	Button btnClean;
	Button btnExit;
	
	EditText edtPrice;
	EditText edtHuoJia;
	EditText edtHuoYi;
	EditText edtHuoBin;
	EditText edtHuoDing;
	EditText edtTuoJia;
	EditText edtTuoYi;
	EditText edtTuoBin;
	EditText edtTuoDing;
	EditText edtHuJia;
	EditText edtHuYi;
	EditText edtHuBin;
	EditText edtHuDing;
	TextView txtJGJia;
	TextView txtJGYi;
	TextView txtJGBin;
	TextView txtJGDing;*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findObjeck();
		actors_rgp.setOnCheckedChangeListener(new MyOncheckChangListener());
		calculate_btn.setOnClickListener(myCal);
		setEditFocusListener();
		
		exit_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		clean_btn.setOnClickListener(myClearOnClickListener);
	}

	

	private void findObjeck() {
		// TODO Auto-generated method stub
		/* btnCount=(Button)findViewById(R.id.buttonJiSuan);
		 btnClean=(Button)findViewById(R.id.buttonclean);
		 btnExit=(Button)findViewById(R.id.buttonexit);
		 edtPrice=(EditText)findViewById(R.id.eidtTextprice);
		 edtHuoJia=(EditText)findViewById(R.id.EditTextTbirdJias);
		 edtHuoYi=(EditText)findViewById(R.id.EditTextBirdyi);
		 edtHuoBin=(EditText)findViewById(R.id.EditTextBirdbin);
		 edtHuoDing=(EditText)findViewById(R.id.eidtTextBirdDing);
		 edtTuoJia=(EditText)findViewById(R.id.EditTextTbirdJias);
		 edtTuoYi=(EditText)findViewById(R.id.EditTextTuoYi);
		 edtTuoBin=(EditText)findViewById(R.id.EditTextTuoBin);
		 edtTuoDing=(EditText)findViewById(R.id.EditTextTuoDing);
		 edtHuJia=(EditText)findViewById(R.id.EditTextHuJia);
		 edtHuYi=(EditText)findViewById(R.id.EditTextHuYi);
		 edtHuBin=(EditText)findViewById(R.id.EditTextHuBin);
		 edtHuDing=(EditText)findViewById(R.id.EditTextHuDing);
		 txtJGJia=(TextView)findViewById(R.id.TextViewJGJia);
		 txtJGYi=(TextView)findViewById(R.id.TextViewJGYi);
		 txtJGBin=(TextView)findViewById(R.id.TextViewJGBin);
		 txtJGDing=(TextView)findViewById(R.id.TextViewJGDing);*/
		 
	}
	private void init()
	{
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(editText[i][j].getText().toString().equals(""))
					editText[i][j].setText("0");
			}
		}
		if(price_edit.getText().toString().equals(""))
			price_edit.setText("0.5");
		try{for(int i=0;i<actors; i++){
			
				huoNiao[i]=Integer.parseInt(editText[0][i].getText().toString());
				tuoNiao[i]=Integer.parseInt(editText[1][i].getText().toString());
				huXi[i]=Integer.parseInt(editText[2][i].getText().toString());
			}
		
		price=Double.parseDouble(price_edit.getText().toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClick(View v) {
				
            }
	private void calculate()
	{
		result[0]=result[1]=result[2]=0;
		for(int i=0;i<actors;i++)
		{
			for(int j=0; j<actors;j++)
			{
				result[i]+=compare(huXi[i],huXi[j])*(tuoNiao[i]+tuoNiao[j]);
				result[i]+=(myInt(huXi[i])-myInt(huXi[j]))*(huoNiao[i]+1)*(huoNiao[j]+1)*price;
				
			}
		}
	}
	private void view()
	{
		for(int i=0;i<actors;i++){
			result_view[i].setText(String.valueOf(Math.round(result[i]*10/10.0)));
			
		}
	}
	class MyOncheckChangListener implements RadioGroup.OnCheckedChangeListener{
		
		@Override
		public void onCheckedChanged(RadioGroup group,int checkedId)
		{
			clear();
			boolean tag;
			if(radioButtonThree.getId()==checkedId){
			actors=3;
			tag=false;
		}else
			{
				actors=4;
				tag=true;
			}
			for (int i=0;i<3;i++)
			{
				editText[i][3].setEnabled(tag);
			}
			result_view[3].setEnabled(tag);
			ding_view.setEnabled(tag);
		}
	}
	 class MyClearOnClickListener implements View.OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			clear();
		}

	}
	 private void clear()
	 {
		 for(int i =0;i<3;i++)
		 {
			 for(int j=0;j<4;j++){
				 editText[i][j].setText("0");
			 }
		 }
		 for (int j=0;j<4;j++)
		 {
			 result_view[j].setText("0");
		 }
	 }
	 private void setEditFocusListener()
	 {
		 for(int i=0;i<3;i++)
		 {
			 for(int j=0;j<4;j++)
			 {
				 editText[i][j].setOnFocusChangeListener(myOnFocusChangListener);
			 }
		 }
		 price_edit.setOnFocusChangeListener(myOnFocusChangListener);
	 }
	 class MyCalculateOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			init();
			calculate();
			view();
		}
		 
	}
	 private int compare(int x ,int y){
		if(x==y)return 0;
		 return x>y?1:-1;
		}
	 private int myInt(int huxi)
	 {
		 int tag=1,huxi_abs;
		 huxi_abs=Math.abs(huxi);
		 if(huxi<0)tag=-1;
		 if(huxi_abs%10>=5)
			 huxi_abs=(huxi_abs/10+1)*10;
		 else
			 huxi_abs=huxi_abs/10*10;
		 return huxi_abs*tag;
	 }
	  class MyOnFocusChangListener implements View.OnFocusChangeListener{

		@Override
		public void onFocusChange(View v, boolean hanFocus) {
			// TODO Auto-generated method stub
			EditText editText=(EditText)v;
			if(hanFocus)
			{
				editText.setText("");
			}
			else {
				if(editText.getText().toString().equals(""))
				{
					if(editText.getId()==R.id.eidtTextprice)
						editText.setText("0.5");
					else
						editText.setText("0");
					
				}
			}
			
		}
		  
	  }

}
