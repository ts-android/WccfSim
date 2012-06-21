package app.yzw.android;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.graphics.Rect;
import android.graphics.Paint;

public class ControlPriority {
	// �e�r�b�g�}�b�v�����X�g�Ǘ�
	private ArrayList<DraggableBitmap> mBitmaps;
	private int draggingNum;
	private Context context;

	public ControlPriority(Context context){
		this.context = context;
		mBitmaps = new ArrayList<DraggableBitmap>();
	}

	// �r�b�g�}�b�v�̗D�揇�ʂ�������
	public void initPriority(){
		int i = 0;
		for(DraggableBitmap dBmp : mBitmaps){
			dBmp.setPriority(i++);
		}
	}

	// �Ǘ�����r�b�g�}�b�v��ǉ�
	public void add(DraggableBitmap dBitmap){
		mBitmaps.add(dBitmap);
	}

	// �Ǘ�����r�b�g�}�b�v��ǉ�
	public void add(Bitmap bitmap){
		DraggableBitmap dbitmap = new DraggableBitmap(context);
		dbitmap.setBitmap(bitmap);
		mBitmaps.add(dbitmap);
	}

	// �Ǘ��r�b�g�}�b�v�`��(�D��x�Ⴉ�珇��)
	public void drawBitmaps(Canvas canvas){
		for(int i = mBitmaps.size() - 1; i >= 0; --i){
			mBitmaps.get(i).draw(canvas);
	// �I��Ԃ̐�������
	// �A�g�ɉ����āu���̑����v��u���̐F�v��ύX����K�v�����邪
	// ����͌Œ�
			Rect rectSrc = mBitmaps.get(i).getRect();
			for(int j = mBitmaps.size() - 1; j >= 0; --j){
				Rect rectDst = mBitmaps.get(j).getRect();
				Paint paint = new Paint();
				paint.setColor(Color.argb(255, 255, 255, 255));
				paint.setStrokeWidth(4);
				canvas.drawLine(rectSrc.centerX(), rectSrc.centerY(), rectDst.centerX(), rectDst.centerY(), paint);
			}
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
