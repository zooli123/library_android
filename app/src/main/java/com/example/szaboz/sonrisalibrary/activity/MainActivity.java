package com.example.szaboz.sonrisalibrary.activity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.szaboz.sonrisalibrary.R;
import com.example.szaboz.sonrisalibrary.fragment.BorrowBookFragment;
import com.example.szaboz.sonrisalibrary.fragment.ChangePasswordFragment;
import com.example.szaboz.sonrisalibrary.fragment.ManageBooksFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private static final String MANAGE_FRAGMENT = "MANAGE_FRAGMENT";
    private static final String BORROW_FRAGMENT = "BORROW_FRAGMENT";
    private static final String CHANGE_PASSWORD_FRAGMENT = "CHANGE_PASSWORD_FRAGMENT";

    private enum fragments { //order matters
        MANAGE, BORROW, CHANGE
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.appear, R.anim.disappear);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (findViewById(R.id.fragment_container_main) != null) {
            if (savedInstanceState != null) {
                return;
            }
            ManageBooksFragment manageBooksFragment = ManageBooksFragment.newInstance();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.animator.appear, R.animator.disappear);
            ft.add(R.id.fragment_container_main, manageBooksFragment, MANAGE_FRAGMENT).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                if(isFragmentActive(MANAGE_FRAGMENT))
                    navigationView.getMenu().getItem(fragments.MANAGE.ordinal()).setChecked(true);
                else if(isFragmentActive(BORROW_FRAGMENT))
                    navigationView.getMenu().getItem(fragments.BORROW.ordinal()).setChecked(true);
                else if(isFragmentActive(CHANGE_PASSWORD_FRAGMENT))
                    navigationView.getMenu().getItem(fragments.CHANGE.ordinal()).setChecked(true);
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage_books) {
            if (findViewById(R.id.fragment_container_main) != null) {
                if (!isFragmentActive(MANAGE_FRAGMENT)) {
                    ManageBooksFragment manageBooksFragment = ManageBooksFragment.newInstance();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.animator.appear, R.animator.disappear);
                    ft.replace(R.id.fragment_container_main, manageBooksFragment, MANAGE_FRAGMENT).addToBackStack(null).commit();
                }
            }
        } else if (id == R.id.nav_borrow_books) {
            if (findViewById(R.id.fragment_container_main) != null) {
                if(!isFragmentActive(BORROW_FRAGMENT)){
                    BorrowBookFragment borrowBookFragment = BorrowBookFragment.newInstance();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.animator.appear, R.animator.disappear);
                    ft.replace(R.id.fragment_container_main, borrowBookFragment, BORROW_FRAGMENT).addToBackStack(null).commit();
                }
            }
        } else if (id == R.id.nav_change_password) {
            if(findViewById(R.id.fragment_container_main) != null) {
                if(!isFragmentActive(CHANGE_PASSWORD_FRAGMENT)) {
                    ChangePasswordFragment changePasswordFragment = ChangePasswordFragment.newInstance();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.animator.appear, R.animator.disappear);
                    ft.replace(R.id.fragment_container_main, changePasswordFragment, CHANGE_PASSWORD_FRAGMENT).addToBackStack(null).commit();
                }
            }

        } else if (id == R.id.nav_logout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to quit?")
                    .setPositiveButton("Yes", confirmationBeforeQuitListener)
                    .setNegativeButton("No", confirmationBeforeQuitListener).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean isFragmentActive(String tagName) {
        Fragment fragment = getFragmentManager().findFragmentByTag(tagName);
        return (fragment != null && fragment.isVisible());
    }

    DialogInterface.OnClickListener confirmationBeforeQuitListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    Intent mainIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(mainIntent);
                    MainActivity.this.finish();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        }
    };


}
