package com.afrobaskets.App.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afrobaskets.App.adapter.ViewPagerAdapter;
import com.afrobaskets.App.bean.CategoriesBean;
import com.afrobaskets.App.bean.ExpandedMenuModel;
import com.afrobaskets.App.fragments.CollectionFragments;
import com.afrobaskets.App.fragments.DeliveredFragments;
import com.afrobaskets.App.interfaces.SavePref;
import com.google.firebase.messaging.FirebaseMessaging;
import com.webistrasoft.org.ecommerce.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity
 {

    private List<CategoriesBean> foodList = new ArrayList<>();
    private RecyclerView new_arrival_recyclerView,hot_deal_recyclerView,offer_recyclerView;
    Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
     private DrawerLayout cDrawerLayout;
    com.afrobaskets.App.adapter.ExpandableListAdapter mMenuAdapter;
    ExpandableListView expandableList;
    List<ExpandedMenuModel> listDataHeader;
    HashMap<ExpandedMenuModel, List<String>> listDataChild;
    TabLayout tabLayout;
public static Activity activity;

     private static final String TAG =
             "dscfd";
     private BroadcastReceiver mRegistrationBroadcastReceiver;
     private TextView txtRegId, txtMessage;



    private void prepareListData()

    {

        listDataHeader = new ArrayList<ExpandedMenuModel>();
        listDataChild = new HashMap<ExpandedMenuModel, List<String>>();

      /*  ExpandedMenuModel item1 = new ExpandedMenuModel();
        item1.setIconName("Notification");
        item1.setIconImg(R.drawable.ic_logo);
        // Adding data header
        listDataHeader.add(item1);*/

        ExpandedMenuModel item2 = new ExpandedMenuModel();
        item2.setIconName("Order History");

        item2.setIconImg(android.R.drawable.ic_delete);
        listDataHeader.add(item2);

       ExpandedMenuModel item3 = new ExpandedMenuModel();
        item3.setIconName("Notification");
        item3.setIconImg(android.R.drawable.ic_delete);
        listDataHeader.add(item3);

       /* ExpandedMenuModel item4 = new ExpandedMenuModel();
        item4.setIconName("Upcoming Delivery List");
        item4.setIconImg(android.R.drawable.ic_delete);
        listDataHeader.add(item4);*/



        ExpandedMenuModel item6 = new ExpandedMenuModel();
        item6.setIconName("Change Password");
        item6.setIconImg(android.R.drawable.ic_delete);
        listDataHeader.add(item6);

        //

        ExpandedMenuModel item7 = new ExpandedMenuModel();
        item7.setIconName("Logout");
        item7.setIconImg(android.R.drawable.ic_delete);
        listDataHeader.add(item7);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        //revision: this don't works, use setOnChildClickListener() and setOnGroupClickListener() above instead
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }



    private void createTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Order Collection");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.categories_select, 0, 0);

        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Order Delivery");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.categories_select, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        activity=this;
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        expandableList = (ExpandableListView) findViewById(R.id.navigationmenu);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        prepareListData();
        mMenuAdapter = new com.afrobaskets.App.adapter.ExpandableListAdapter(this, listDataHeader, listDataChild, expandableList);
        // setting list adapter
        expandableList.setAdapter(mMenuAdapter);
        setViewpager();
        ImageView slider=(ImageView) findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);
        slider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDrawerLayout.openDrawer(Gravity.LEFT);

            }
        });
        expandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                //Log.d("DEBUG", "heading clicked");
                /*if(i==0 ) {
                    mDrawerLayout.closeDrawers();
                }*/
                if(i==0 ) {
                    startActivity(new Intent(HomeActivity.this,CollectionHistoryActivity.class));
                    mDrawerLayout.closeDrawers();
                }

                if(i==1 ) {
                    startActivity(new Intent(HomeActivity.this,NotificationActivity.class));
                    mDrawerLayout.closeDrawers();
                }

                /*if(i==3 ) {
                    startActivity(new Intent(HomeActivity.this,UpcomingDeliveryActivity.class));
                    mDrawerLayout.closeDrawers();
                }*/

                if(i==2) {
                    startActivity(new Intent(HomeActivity.this,SettingActivity.class));

                    mDrawerLayout.closeDrawers();
                }

                if(i==3) {

                    mDrawerLayout.closeDrawers();
                    SavePref.removePref(HomeActivity.this);
                    Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }


                return false;
            }
        });


        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                   // displayFirebaseRegId();
                }

                else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received
                    recreate();
                    Toast.makeText(getApplicationContext(), "Push notification: ", Toast.LENGTH_LONG).show();
                }
            }
        };

    }


     @Override
     protected void onResume() {
         super.onResume();
         // register GCM registration complete receiver
         LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                 new IntentFilter(Config.REGISTRATION_COMPLETE));
         // register new push message receiver
         // by doing this, the activity will be notified each time a new message arrives
         LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                 new IntentFilter(Config.PUSH_NOTIFICATION));
         // clear the notification area when the app is opened
         NotificationUtils.clearNotifications(getApplicationContext());
     }

      @Override
      protected void onPause() {
          LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
          super.onPause();
      }
void setViewpager()
{
    ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    // Add Fragments to adapter one by one
    adapter.addFragment(new CollectionFragments(), "");

    adapter.addFragment(new DeliveredFragments(), "");

    viewPager.setAdapter(adapter);
     tabLayout = (TabLayout)findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);
    createTabIcons();
    for(int i=0; i < tabLayout.getTabCount()-1; i++)
    {
        View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
        ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
        p.setMargins(0, 0, 3, 0);
        tab.requestLayout();
    }
}
    @Override
    public void onBackPressed() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.dashboard, menu);
        return false;
    }
}
