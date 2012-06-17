package app.yzw.android;

import android.content.res.Resources;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DragImageView extends View {
	private ControlPriority cp;
	
	public DragImageView(Context context, AttributeSet attrs){
		super(context,attrs);
		setBackgroundResource(R.drawable.pitch);
		
		setFocusable(true);
		cp = new ControlPriority(context);
		//âÊëúÇÃì«Ç›çûÇ›
		Resources res = context.getResources();
		Bitmap image1 = BitmapFactory.decodeResource(res, R.drawable.beckham);
		Bitmap image2 = BitmapFactory.decodeResource(res, R.drawable.scholes);
		Bitmap image3 = BitmapFactory.decodeResource(res, R.drawable.rooney);
		cp.add(image1);
		cp.add(image2);
		cp.add(image3);
		cp.initPriority();
	}
	
	@Override
	public void onDraw(Canvas canvas){
		cp.drawBitmaps(canvas);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			cp.callClickEvent(event);
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_MOVE:
			cp.callMoveEvent(event);
			break;
		}
		invalidate();
		return true;
	}
	
	@Override
	public boolean onTrackballEvent(MotionEvent event){
		cp.callTouchEvent(event);
		invalidate();
		return true;
	}
	
	// ëIéËí«â¡
	public void addPlayer(Context context, int id){
		Resources res = context.getResources();
		Bitmap image1 = BitmapFactory.decodeResource(res, R.drawable.best);
		cp.add(image1);

		invalidate();
		
	}
}
