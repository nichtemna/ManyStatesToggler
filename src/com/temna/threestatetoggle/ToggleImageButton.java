/*
 * Copyright (C) {year} nichtemna
 * https://github.com/nichtemna
 * nichtemna@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.temna.threestatetoggle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.nichtemna.manystatestoggler.R;

public class ToggleImageButton extends ImageView implements View.OnClickListener {
	private boolean isChecked = false;
	private OnCheckedChangeListener mChangeListener;
	private Drawable mCheckedDrawable, mUncheckedDrawable;

	public ToggleImageButton(Context context) {
		super(context);
		init();
	}

	public ToggleImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttrs(context, attrs);
		init();
	}

	public ToggleImageButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		initAttrs(context, attrs);
		init();
	}

	private void initAttrs(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ToggleImageButton);
		try {
			int checkedImage = a.getResourceId(R.styleable.ToggleImageButton_checkedImage, R.drawable.toggle_checked_center);
			int uncheckedImage = a.getResourceId(R.styleable.ToggleImageButton_uncheckedImage, R.drawable.toggle_unchecked_center);

			mCheckedDrawable = context.getResources().getDrawable(checkedImage);
			mUncheckedDrawable = context.getResources().getDrawable(uncheckedImage);

			isChecked = a.getBoolean(R.styleable.ToggleImageButton_checked, false);
		} finally {
			a.recycle();
		}
	}

	public void init() {
		changeBackgroundImage();
		setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		if (!isChecked) {
			setChecked(true);
			if (mChangeListener != null) {
				mChangeListener.onCheckedChanged(this);
			}
		}
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean checked) {
		isChecked = checked;
		changeBackgroundImage();
	}

	/**
	 * change background image for one from xml parameters
	 */
	private void changeBackgroundImage() {
		setBackground(isChecked ? mCheckedDrawable : mUncheckedDrawable);
	}

	public void setOnCheckedChangeListener(OnCheckedChangeListener changeListener) {
		mChangeListener = changeListener;
	}

	/**
	 * Listen button check
	 */
	public static interface OnCheckedChangeListener {
		void onCheckedChanged(ToggleImageButton toggleImageButton);
	}
}
