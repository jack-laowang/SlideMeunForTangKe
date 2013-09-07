package com.aretha.slidemenudemo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.aretha.slidemenu.SlideMenu;
import com.aretha.slidemenudemo.R;

public class SlideMenuAttribute extends FragmentActivity implements
		OnSeekBarChangeListener, OnCheckedChangeListener,
		android.widget.RadioGroup.OnCheckedChangeListener, OnClickListener,
		OnItemSelectedListener {
	private SlideMenu mSlideMenu;
	private SeekBar mPrimaryShadowWidth;
	private SeekBar mSecondaryShadowWidth;
	private RadioGroup mSlideMode;
	private ToggleButton mSlideLeft;
	private ToggleButton mSlideRight;
	private Spinner mInterpolator;
	public void setSlideRole(int res) {
		if (null == mSlideMenu) {
			return;
		}

		getLayoutInflater().inflate(res, mSlideMenu, true);
	}

	public SlideMenu getSlideMenu() {
		return mSlideMenu;
	}
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_slidemenu);
		mSlideMenu = (SlideMenu) findViewById(R.id.slideMenu);
		setSlideRole(R.layout.layout_slidemenu_attribute);
		setSlideRole(R.layout.layout_primary_menu);
		setSlideRole(R.layout.layout_secondary_menu);
//		mSlideMenu = getSlideMenu();

		mPrimaryShadowWidth = (SeekBar) findViewById(R.id.primaryShadowWidth);
		mSecondaryShadowWidth = (SeekBar) findViewById(R.id.secondaryShadowWidth);
		mSlideMode = (RadioGroup) findViewById(R.id.slideMode);
		mSlideLeft = (ToggleButton) findViewById(R.id.slideLeft);
		mSlideRight = (ToggleButton) findViewById(R.id.slideRight);
		mInterpolator = (Spinner) findViewById(R.id.interpolator);

		mPrimaryShadowWidth.setOnSeekBarChangeListener(this);
		mSecondaryShadowWidth.setOnSeekBarChangeListener(this);
		mSlideMode.setOnCheckedChangeListener(this);
		mSlideLeft.setOnCheckedChangeListener(this);
		mSlideRight.setOnCheckedChangeListener(this);
		mInterpolator.setOnItemSelectedListener(this);

		mInterpolator.setAdapter(ArrayAdapter.createFromResource(this,
				R.array.interpolator, android.R.layout.simple_list_item_1));
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		if (mPrimaryShadowWidth == seekBar) {
			mSlideMenu.setPrimaryShadowWidth(progress);
		} else if (mSecondaryShadowWidth == seekBar) {
			mSlideMenu.setSecondaryShadowWidth(progress);
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		int slideDirectionFlag = mSlideMenu.getSlideDirection();
		if (mSlideLeft == buttonView) {
			if (isChecked) {
				slideDirectionFlag |= SlideMenu.FLAG_DIRECTION_LEFT;
			} else {
				slideDirectionFlag &= ~SlideMenu.FLAG_DIRECTION_LEFT;
			}
		} else if (mSlideRight == buttonView) {
			if (isChecked) {
				slideDirectionFlag |= SlideMenu.FLAG_DIRECTION_RIGHT;
			} else {
				slideDirectionFlag &= ~SlideMenu.FLAG_DIRECTION_RIGHT;
			}
		}
		mSlideMenu.setSlideDirection(slideDirectionFlag);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.slideContent:
			mSlideMenu.setSlideMode(SlideMenu.MODE_SLIDE_CONTENT);
			break;
		case R.id.slideWindow:
			mSlideMenu.setSlideMode(SlideMenu.MODE_SLIDE_WINDOW);
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.open:
			mSlideMenu.open(true, true);
			break;
		case R.id.close:
			mSlideMenu.close(true);
			break;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> adapterView, View view,
			int position, long id) {
		Interpolator interpolator;
		switch (position) {
		default:
		case 0:
			interpolator = SlideMenu.DEFAULT_INTERPOLATOR;
			break;
		case 1:
			interpolator = new AccelerateDecelerateInterpolator();
		case 2:
			interpolator = new AccelerateInterpolator();
			break;
		case 3:
			interpolator = new AnticipateInterpolator();
			break;
		case 4:
			interpolator = new AnticipateOvershootInterpolator();
		case 5:
			interpolator = new BounceInterpolator();
			break;
		case 6:
			interpolator = new DecelerateInterpolator();
			break;
		case 7:
			interpolator = new LinearInterpolator();
			break;
		case 8:
			interpolator = new OvershootInterpolator();
			break;
		}
		mSlideMenu.setInterpolator(interpolator);
	}

	@Override
	public void onNothingSelected(AdapterView<?> adapterView) {

	}
}
