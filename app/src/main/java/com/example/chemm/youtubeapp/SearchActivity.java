package com.example.chemm.youtubeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity {

    private EditText searchInput;
    private ListView videosFound;
    private List<VideoItem> searchResults;
    private Handler handler;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchInput = (EditText) findViewById(R.id.search_input);
        videosFound = (ListView) findViewById(R.id.videos_found);

        handler = new Handler();

        searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    searchOnYoutube(v.getText().toString());
                    return false;
                }
                return true;
            }
        });
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mGestureDetector = new GestureDetector(this, new SimpleGestureListener());
        initializeDrawer();
        addClickListener();
    }

    private void searchOnYoutube(final String keywords){
        new Thread(){
            public void run(){
                YoutubeConnector yc = new YoutubeConnector(SearchActivity.this);
                searchResults = yc.search(keywords);
                handler.post(new Runnable(){
                    public void run(){
                        updateVideosFound();
                    }
                });
            }
        }.start();
    }

    private void updateVideosFound(){
        ArrayAdapter<VideoItem> adapter = new ArrayAdapter<VideoItem>(getApplicationContext(), R.layout.video_item, searchResults){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.video_item, parent, false);
                }
                ImageView thumbnail = (ImageView)convertView.findViewById(R.id.video_thumbnail);
                TextView title = (TextView)convertView.findViewById(R.id.video_title);
                TextView description = (TextView)convertView.findViewById(R.id.video_description);

                VideoItem searchResult = searchResults.get(position);

                Picasso.with(getApplicationContext()).load(searchResult.getThumbnailURL()).into(thumbnail);
                title.setText(searchResult.getTitle());
                description.setText(searchResult.getDescription());
                return convertView;
            }
        };

        videosFound.setAdapter(adapter);
    }

    private void addClickListener(){
        videosFound.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,
                                    long id) {
                Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
                intent.putExtra("VIDEO_ID", searchResults.get(pos).getId());
                startActivity(intent);
            }

        });
    }

    private void customDialog(){
        final CustomDialog dialog = new CustomDialog(this,new CustomDialog.ICustomDialogEventListener(){
            @Override
            public void onClickListener(){
                Intent intent = new Intent();
                intent.putExtra("message", "ViewPager");
                setResult(RESULT_OK, intent);
            }
        });
        dialog.show();

    }

    private void initializeDrawer() {
        drawerList = (ListView) findViewById(R.id.left_drawer);
        ArrayList<String> sample = new ArrayList<>();
        sample.add("About");
        DrawerAdapter navDrawerAdapter = new DrawerAdapter(this, sample);
        drawerList.setAdapter(navDrawerAdapter);
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent i;
            switch (position) {
                case 0:
                    customDialog();
                    break;
                default:
                    break;
            }
        }
    }
        private class SimpleGestureListener extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onDown(MotionEvent e) {
    //            toastShort("onDown");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                //toastShort("onShowPress");
            }

            @Override
            public void onLongPress(MotionEvent e) {
    //            toastShort("onLongPress");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
    //            toastShort("onSingleTapUp");
                return true;
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
    //            toastShort("onSingleTapConfirmed");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
    //            toastShort("onScroll");
                return true;
                //
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    //            toastShort("onFling");
                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
    //            toastShort("onDoubleTap");
                return true;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
    //            toastShort("onDoubleTapEvent");
                return true;
            }
    }
}



