package app.yzw.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends Activity {
	 @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub);

        String val = "";
        Bundle extras = getIntent().getExtras();
        if(extras != null){
        	val = extras.getString("ID");
        }

        TextView txt = (TextView)findViewById(R.id.textview02_id);
        txt.setText(val);

        Button btn = (Button)findViewById(R.id.button02_id);
        btn.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Intent data = new Intent();
        		Bundle bundle = new Bundle();
        		bundle.putString("key.StringData", "Return_DetailActivity");
        		data.putExtras(bundle);
        		setResult(RESULT_OK, data);
        		finish();
        	}
        });
	 }
}
