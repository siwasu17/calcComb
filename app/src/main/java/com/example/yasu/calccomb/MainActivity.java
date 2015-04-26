package com.example.yasu.calccomb;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.btnClac);

        btn.setOnClickListener(new View.OnClickListener(){
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

            public void onClick(View v){
                EditText etxtAllNum = (EditText)findViewById(R.id.etxtAllNum);
                EditText etxtSelectNum = (EditText)findViewById(R.id.etxtSelectNum);
                TextView txtResult = (TextView)findViewById(R.id.txtResult);

                int allNum = Integer.parseInt(etxtAllNum.getText().toString());
                int selectNum = Integer.parseInt(etxtSelectNum.getText().toString());

                //nCr = nPr / r!
                long result = this.permutation(allNum,selectNum) / this.permutation(selectNum,selectNum);
                txtResult.setText(Long.toString(result));

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
