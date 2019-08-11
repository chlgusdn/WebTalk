package com.example.webtalk.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webtalk.Adapter.Friend_Tab_Adapter;
import com.example.webtalk.Adapter.ViewPagerAdapter;
import com.example.webtalk.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("WebTalk");
        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        final TabLayout main_tab = (TabLayout)findViewById(R.id.main_tab);
        main_tab.addTab(main_tab.newTab().setText("친 구"));
        main_tab.addTab(main_tab.newTab().setText("채 팅"));
        main_tab.addTab(main_tab.newTab().setText("내 일정"));
        main_tab.addTab(main_tab.newTab().setText("내 정보"));
        main_tab.setTabGravity(main_tab.GRAVITY_FILL);

        final PagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),main_tab.getTabCount());
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(main_tab)); // view Pager가 페이지 변경이 되었을때 Tablayout에게 알려주는 리스너
        main_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // 페이지선택이 되었음을 알려주는 리스너
            @Override
            public void onTabSelected(TabLayout.Tab tab) { // 탭이 선택되었을때 작용하는 메소드
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_search :
                return true;
            case R.id.tool_bar_add_friend :
                final EditText add_friend_text_field = new EditText(this);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("친구 추가");
                builder.setMessage("추가할 친구의 이름을 입력해주세요");
                builder.setView(add_friend_text_field);
                builder.setPositiveButton("입력", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_SHORT);
                    }
                });
                builder.show();
                return true;
            case R.id.group_code_input :
                final EditText add_friend_text_fieldEditText = new EditText(this);
                AlertDialog.Builder alert_view = new AlertDialog.Builder(MainActivity.this);
                alert_view.setTitle("그룹코드를 이용한 채팅방");
                alert_view.setMessage("그룹코드를 이용하여 채팅방에 들어갑니다. 만약 그룹코드가 없으면 채팅방을 만듭니다.");
                alert_view.setView(add_friend_text_fieldEditText);
                alert_view.setPositiveButton("입력", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert_view.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_SHORT);
                    }
                });
                alert_view.show();
            default :
                return super.onOptionsItemSelected(item) ;
        }
    }

}
