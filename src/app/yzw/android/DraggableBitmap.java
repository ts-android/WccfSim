package app.yzw.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class DraggableBitmap extends View implements GestureDetector.OnGestureListener,  GestureDetector.OnDoubleTapListener {
	private Bitmap mBitmap;
	private int mImageWitdh = 0;
	private int mImageHeight = 0;
	private Rect mSrc;
	private Rect mDst;
	private int mTouchAction;
	private int mBallAction;
	private float mScaleX = 1.0f;
	private float mScaleY = 1.0f;
	private boolean mIsDrabable = false;
	private Point mPreXY;
	private Point mTouchXY;
	private int mPriority = -1;
	private Paint mPaint;

	private GestureDetector gestureDetector;

	public DraggableBitmap(Context context){
		super(context);
		mPaint = new Paint();
		mPreXY = new Point(0,0);
		mTouchXY = new Point(0,0);
		mPaint.setAntiAlias(true);
		setFocusable(true);

        this.gestureDetector = new GestureDetector(this);
	}

	public void setBitmap(Bitmap bmp){
		if(bmp == null) return;
		mBitmap = bmp;
		mImageWitdh = mBitmap.getWidth();
		mImageHeight = mBitmap.getHeight();
		mSrc = new Rect(0,0,mImageWitdh,mImageHeight);
		mDst = new Rect(mSrc);
	}

	public void move(int x, int y){
		mDst.left = x;
		mDst.top = y;
		mDst.right = x + mImageWitdh;
		mDst.bottom = y + mImageHeight;
		invalidate();
	}

	public int getImageWidth(){
		return mImageWitdh;
	}

	public int getImageHeight(){
		return mImageHeight;
	}

	public void scale(){
		mDst.right = (int)(mSrc.right * mScaleX);
		mDst.bottom = (int)(mSrc.bottom * mScaleY);
		mImageWitdh = mDst.width();
		mImageHeight = mDst.height();
		invalidate();
	}

	@Override
	public void onDraw(Canvas canvas){
		if(mBitmap == null) return;
		canvas.drawBitmap(mBitmap, mSrc, mDst, mPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event){
        if (gestureDetector.onTouchEvent(event)) {
            return true;
        }
        
		mTouchAction = event.getAction();
		int x = (int)event.getX();
		int y = (int)event.getY();

		if((mTouchAction == MotionEvent.ACTION_MOVE) && mIsDrabable){
			mTouchXY.set(x, y);
			int newX = mDst.left + (mTouchXY.x - mPreXY.x);
			int newY = mDst.top + (mTouchXY.y - mPreXY.y);
			move(newX,newY);
			mPreXY.set(mTouchXY.x, mTouchXY.y);
		}

		if(isTouchImage(x,y)){
			if(mTouchAction == MotionEvent.ACTION_DOWN){
				mPreXY.set(x, y);
				mIsDrabable = true;
			}
			else if(mTouchAction == MotionEvent.ACTION_UP){
				mIsDrabable = false;
			}
		}
		else {
			return false;
		}
		return true;
	}

	@Override
	public boolean onTrackballEvent(MotionEvent event){
		mBallAction = event.getAction();
		int x = (int)event.getX();
		int y = (int)event.getY();
		if(mBallAction == MotionEvent.ACTION_MOVE){
			float newScaleX = mScaleX + (float)(x/20.0f);
			float newScaleY = mScaleY + (float)(y/20.0f);
			if(newScaleX > 0.4f && newScaleY > 0.4f){
				setScale(newScaleX, newScaleY);
			}
		}
		return true;
	}

	private boolean isTouchImage(int x, int y){
		int imgX = mDst.left;
		int imgY = mDst.top;
		return x - imgX >= 0 && x - imgX < mDst.width() &&
			   y - imgY >= 0 && y - imgY < mDst.height();
	}

	public void setPriority(int priority){
		this.mPriority = priority;
	}

	public int getPriority(){
		return mPriority;
	}

	public void downPriority(){
		--mPriority;
	}

	public void setScale(float scaleX, float scaleY){
		this.mScaleX = scaleX;
		this.mScaleY = scaleY;
		scale();
	}

	public Rect getRect(){
		return mDst;
	}

	public boolean onDoubleTap(MotionEvent e) {
		Log.v("test", "onDoubleTap");
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	public boolean onDoubleTapEvent(MotionEvent e) {
		Log.v("test", "onDoubleTapEvent");
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	public boolean onSingleTapConfirmed(MotionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	public boolean onDown(MotionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	public void onLongPress(MotionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	public void onShowPress(MotionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public boolean onSingleTapUp(MotionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}
