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

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ToggleLayout extends LinearLayout implements ToggleImageButton.OnCheckedChangeListener {
	private ToggleImageButton mToggleButton;
	private List<ToggleImageButton> buttons;
	private Context mContext;

	public ToggleLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public void init(Context context) {
		mContext = context;
		setGravity(HORIZONTAL);
		buttons = new ArrayList<ToggleImageButton>();
	}

	@Override
	public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) {
		if (child instanceof ToggleImageButton) {
			final ToggleImageButton button = (ToggleImageButton) child;
			button.setOnCheckedChangeListener(this);
			buttons.add(button);
			if (button.isChecked()) {
				mToggleButton = button;
			}
		}
		super.addView(child, index, params);
	}

	/**
	 * if some button was checked, we uncheck the previous one
	 */
	@Override
	public void onCheckedChanged(ToggleImageButton toggleButton) {
		mToggleButton.setChecked(false);
		mToggleButton = toggleButton;
		getCheckedPosition();
	}

	/**
	 * @return the position of checked item
	 */
	public int getCheckedPosition() {
		int position = 0;
		for (int i = 0; i < buttons.size(); i++) {
			if (buttons.get(i).equals(mToggleButton)) {
				position = i + 1;
			}
		}
		Toast.makeText(mContext, "position " + position, Toast.LENGTH_SHORT).show();
		return position;
	}
}
