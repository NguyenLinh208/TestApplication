package jp.linhnk.myapplication.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

import jp.linhnk.myapplication.R;

public class ImageAutoScale extends ImageView {

    private float heightPerWidth;
    private float widthPerHeight;

    private boolean enableStateWhenTouch = false;

    private boolean fitWidth;

    private Bitmap scaleBitmap;

    public ImageAutoScale(Context context) {
        super(context);
    }

    public ImageAutoScale(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parseAttributes(context, attrs);
    }

    private void parseAttributes(final Context context, final AttributeSet attrs) {
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageAutoScale);
        try {
            heightPerWidth = a.getFloat(R.styleable.ImageAutoScale_heightPerWidth, -1f);
            widthPerHeight = a.getFloat(R.styleable.ImageAutoScale_widthPerHeight, -1f);
            enableStateWhenTouch = a.getBoolean(R.styleable.ImageAutoScale_enablestate_when_touch, false);
        } finally {
            a.recycle();
        }
    }

    public ImageAutoScale(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width, height;
        if (heightPerWidth > 0) {
            width = MeasureSpec.getSize(widthMeasureSpec);
            height = (int) (width * heightPerWidth);
        } else if (widthPerHeight > 0) {
            height = MeasureSpec.getSize(heightMeasureSpec);
            width = (int) (height * widthPerHeight);
        } else {
            width = widthMeasureSpec;
            height = heightMeasureSpec;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (isEnabled() && isClickable() && enableStateWhenTouch)
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    setColorFilter(Color.parseColor("#882d2d2f"), PorterDuff.Mode.MULTIPLY);
                    break;
                case MotionEvent.ACTION_UP:
                    setColorFilter(null);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    setColorFilter(null);
                    break;
                default:
                    break;
            }
        return super.onTouchEvent(event);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        if (fitWidth && drawable != null) {
            Bitmap bitmap;
            if (drawable instanceof BitmapDrawable) {
                bitmap = ((BitmapDrawable) drawable).getBitmap();
            } else if (drawable instanceof GlideBitmapDrawable) {
                bitmap = ((GlideBitmapDrawable) drawable).getBitmap();
            } else {
                super.setImageDrawable(drawable);
                return;
            }
            if (bitmap.getWidth() < getWidth() && bitmap.getHeight() < getHeight()) {
                final float scaleW = (float) getMeasuredWidth() / bitmap.getWidth();
                final float scaleH = (float) getMeasuredWidth() / bitmap.getHeight();
                final float scale = Math.min(scaleH, scaleW);
                Matrix matrix = new Matrix();
                matrix.postScale(scale, scale);
                cleanMemory();
                scaleBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                bitmap.recycle();
                super.setImageDrawable(new BitmapDrawable(getResources(), scaleBitmap));
                return;
            }
        }
        super.setImageDrawable(drawable);
    }

    public void setFitWidth(boolean fitWidth) {
        this.fitWidth = fitWidth;
    }

    /**
     * Clean scale bitmap when use fit
     */
    public void cleanMemory() {
        if (scaleBitmap != null && !scaleBitmap.isRecycled()) {
            scaleBitmap.recycle();
            scaleBitmap = null;
        }
    }
}
