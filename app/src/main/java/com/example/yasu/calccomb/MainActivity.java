package com.example.yasu.calccomb;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spnBillStyle);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.bill_style_array,android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        /*
                        System.out.println("SELECTED " + parent.toString());
                        System.out.println(view.toString());
                        System.out.println("POS " + Integer.toString(position));
                        System.out.println("ID " + Long.toString(id));
                        */

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        System.out.println("NOTHING SELECTED " + parent.toString());

                    }
                });

        Button btn = (Button)findViewById(R.id.btnClac);
        btn.setOnClickListener(new View.OnClickListener(){

            //順列
            public long permutation(int n,int r){
                // nPr
                if(n < 0){
                    return 0;
                }
                long ret = 1;
                for(int i = n;i > n - r;i--){
                    ret *= i;
                }

                return ret;
            }

            //組み合わせ
            public long factrial(int n,int r){
                //nCr = nPr / r!
                return this.permutation(n,r) / this.permutation(r,r);
            }

            public void onClick(View v){
                EditText etxtAllNum = (EditText)findViewById(R.id.etxtAllNum);
                //EditText etxtSelectNum = (EditText)findViewById(R.id.etxtSelectNum);
                TextView txtResult = (TextView)findViewById(R.id.txtResult);
                TextView txtResultPercent = (TextView)findViewById(R.id.txtResultPercent);

                int allNum = Integer.parseInt(etxtAllNum.getText().toString());
                //int selectNum = Integer.parseInt(etxtSelectNum.getText().toString());

                Spinner sp = (Spinner)findViewById(R.id.spnBillStyle);
                int idx = sp.getSelectedItemPosition();

                long result = 0;
                double per = 0.0;
                switch(idx){
                    case 0:
                        //単勝
                        result = this.factrial(allNum,1);
                        per = 1 / (double)result;
                        break;
                    case 1:
                        //複勝
                        result = this.factrial(allNum,1);
                        per = 3 / (double)result;
                        break;
                    case 2:
                        //枠複(枠連)
                        //TODO: 後で実装
                        break;
                    case 3:
                        //枠単
                        //TODO: 後で実装
                        break;
                    case 4:
                        //馬複(馬連)
                        result = this.factrial(allNum,2);
                        per = 1 / (double)result;
                        break;
                    case 5:
                        //馬単
                        result = this.permutation(allNum,2);
                        per = 1 / (double)result;
                        break;
                    case 6:
                        //ワイド
                        result = this.factrial(allNum,2);
                        per = 3 / (double)result;
                        break;
                    case 7:
                        //三連複
                        result = this.factrial(allNum,3);
                        per = 1 / (double)result;
                        break;
                    case 8:
                        //三連単
                        result = this.permutation(allNum,3);
                        per = 1 / (double)result;
                        break;
                    default:
                        result = 0;
                        break;
                }

                DecimalFormat df = new DecimalFormat("#0.00");

                txtResult.setText(Long.toString(result));
                txtResultPercent.setText(df.format(per * 100));

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
