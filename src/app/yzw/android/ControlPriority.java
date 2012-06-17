package app.yzw.android;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
// add by hotake -start-
import android.graphics.Rect;
import android.graphics.Paint;
// add by hotake -end-

public class ControlPriority {
	// 各ビットマップをリスト管理
	private ArrayList<DraggableBitmap> mBitmaps;
	private int draggingNum;
	private Context context;
	
	public ControlPriority(Context context){
		this.context = context;
		mBitmaps = new ArrayList<DraggableBitmap>();
	}
	
	// ビットマップの優先順位を初期化
	public void initPriority(){
		int i = 0;
		for(DraggableBitmap dBmp : mBitmaps){
			dBmp.setPriority(i++);
		}
	}
	
	// 管理するビットマップを追加
	public void add(DraggableBitmap dBitmap){
		mBitmaps.add(dBitmap);
	}
	
	// 管理するビットマップを追加
	public void add(Bitmap bitmap){
		DraggableBitmap dbitmap = new DraggableBitmap(context);
		dbitmap.setBitmap(bitmap);
		mBitmaps.add(dbitmap);
	}
	
	// 管理ビットマップ描画(優先度低から順に)
	public void drawBitmaps(Canvas canvas){
		for(int i = mBitmaps.size() - 1; i >= 0; --i){
			mBitmaps.get(i).draw(canvas);
// add by hotake -start-
			Rect rectSrc = mBitmaps.get(i).getRect();
			for(int j = mBitmaps.size() - 1; j >= 0; --j){
				Rect rectDst = mBitmaps.get(j).getRect();
				Paint paint = new Paint();
				canvas.drawLine(rectSrc.centerX(), rectSrc.centerY(), rectDst.centerX(), rectDst.centerY(), paint);
			}
// add by hotake -end-
		}
	}
	
	public void callClickEvent(MotionEvent event){
		int size = mBitmaps.size();
		for(int i = 0; i < size; ++i){
			if(mBitmaps.get(i).onTouchEvent(event)){
				draggingNum = i;
				break;
			}
		}
		
		int dPriority = mBitmaps.get(draggingNum).getPriority();
		for(DraggableBitmap dBmp : mBitmaps){
			if(dBmp.getPriority() > dPriority){
				dBmp.downPriority();
			}
		}
		
		mBitmaps.get(draggingNum).setPriority(size);
		
		Collections.sort(mBitmaps, new Comparator<DraggableBitmap>(){
			public int compare(DraggableBitmap db1, DraggableBitmap db2){
				return db2.getPriority() - db1.getPriority();
			}
		});
	}
	
	public void callMoveEvent(MotionEvent event){
		mBitmaps.get(0).onTouchEvent(event);
	}
	
	public void callTouchEvent(MotionEvent event){
		mBitmaps.get(0).onTrackballEvent(event);
	}
}
