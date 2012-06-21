package app.yzw.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DragImage extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//setContentView(new DragImageView(this));
		setContentView(R.layout.main);

		Button btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Intent data = new Intent(DragImage.this, app.yzw.android.digsearch.class);
        		startActivityForResult(data, 1);
        	}
        });
	}

   	@Override
   	protected void onActivityResult(int requestCode, int resultCode, Intent data){
   		super.onActivityResult(requestCode, resultCode, data);

    	switch(requestCode)
    	{
	    	case 1:
	    		if (resultCode == RESULT_OK)
	    		{
	    			if (data != null)
	    			{
	    				// ŒŸõ‰æ–Ê‚©‚ç‘I‘ğ‘ÎÛ‚Ìî•ñ‚ğæ“¾
	    				Bundle extras = data.getExtras();
						@SuppressWarnings("unchecked")
						ArrayList<Integer> selectItemList = (ArrayList<Integer>)extras.get("value");

	    				// æ“¾‚µ‚½Œ‹‰Ê‚ğ‰æ–Ê‚É”½‰f
	    				DragImageView div = ((DragImageView)findViewById(R.id.dragImageView1));
	    				for (Integer item : selectItemList) {
	    					div.addPlayer(this, item);
						}
	    			}
	    		}
	    		break;
	    	default:
	    		break;
    	}

   	}

}
