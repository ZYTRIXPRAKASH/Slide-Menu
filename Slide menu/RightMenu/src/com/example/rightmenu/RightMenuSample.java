package com.example.rightmenu;



import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.menudrawer.MenuDrawer;
import com.example.menudrawer.Position;

public class RightMenuSample extends Activity {

    private static final String STATE_ACTIVE_POSITION = "net.simonvt.menudrawer.samples.RightMenuSample.activePosition";
    private static final String STATE_CONTENT_TEXT = "net.simonvt.menudrawer.samples.RightMenuSample.contentText";

    private static final int MENU_OVERFLOW = 1;

    private MenuDrawer mMenuDrawer;
    private int mActivePosition = -1;
    
    

 /*   private MenuAdapter mAdapter;*/
    private ListView mList;


    private String mContentText;
    private TextView mContentTextView;

    @Override
    protected void onCreate(Bundle inState) {
        super.onCreate(inState);

        if (inState != null) {
            mActivePosition = inState.getInt(STATE_ACTIVE_POSITION);
            mContentText = inState.getString(STATE_CONTENT_TEXT);
        }

        mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.MENU_DRAG_CONTENT, Position.LEFT);
    //    mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.MENU_DRAG_CONTENT, Position.RIGHT);
        mMenuDrawer.setContentView(R.layout.activity_rightmenu);
        Button bt=(Button)findViewById(R.id.right);
      
        bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				  mMenuDrawer.toggleMenu();
			}
		});

  
        //call the main layout from xml
        LinearLayout mainLayout = (LinearLayout)findViewById(R.id.main_layout_id);
 
        //create a view to inflate the layout_item (the xml with the textView created before)
        View view = getLayoutInflater().inflate(R.layout.row, mainLayout,false);
 
      mMenuDrawer.setMenuView(view);
      
      
      
 

        mContentTextView = (TextView) findViewById(R.id.contentText);
        mContentTextView.setText(mContentText);
        
        ImageView mImageView=(ImageView)mMenuDrawer.findViewById(R.id.img);
        Button nextActivityButton=(Button)mMenuDrawer.findViewById(R.id.nextactivity_btn);
        
        
        mImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				mMenuDrawer.setActiveView(v);
				mMenuDrawer.closeMenu();
				
				
			
				
				
			}
		});
        
        
        
        
        nextActivityButton.setOnClickListener(new OnClickListener() {
			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				
    				
    				mMenuDrawer.setActiveView(v);
    				mMenuDrawer.closeMenu();
    				
    				
    			startActivity(new Intent(getApplicationContext(), NextActivity.class));
    				
    				
    				
    			}
    		});
            
        
        
        
        
        
        
        
  
    
    }

    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mActivePosition = position;
            mMenuDrawer.setActiveView(view, position);
            mContentTextView.setText(((TextView) view).getText());
            mMenuDrawer.closeMenu();
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_ACTIVE_POSITION, mActivePosition);
        outState.putString(STATE_CONTENT_TEXT, mContentText);
    }

    @Override
    public void onBackPressed() {
        final int drawerState = mMenuDrawer.getDrawerState();
        if (drawerState == MenuDrawer.STATE_OPEN || drawerState == MenuDrawer.STATE_OPENING) {
            mMenuDrawer.closeMenu();
            return;
        }

        super.onBackPressed();
    }


   
}
