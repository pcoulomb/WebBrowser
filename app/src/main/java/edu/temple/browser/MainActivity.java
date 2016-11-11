package edu.temple.browser;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    int currentIndex = 0;
    EditText url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Uri data = getIntent().getData();

        url = (EditText) findViewById(R.id.urlEditText);

        Button go = (Button) findViewById(R.id.go_button);
        View.OnClickListener ocl = new View.OnClickListener(){
            public void onClick(View v) {

                if(fragmentArrayList.size()!=0)
                {
                    WebBrowser browser = (WebBrowser)fragmentArrayList.get(currentIndex);
                    browser.loadWebPage(url.getText().toString());
                }
            }
        };

        go.setOnClickListener(ocl);

        if(data != null) {
            String url_data = data.toString();

            WebBrowser browser_fragment = new WebBrowser();
            fragmentArrayList.add(browser_fragment);
            currentIndex = fragmentArrayList.size()-1;

            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.webBrowserFragment,browser_fragment)
                    .commit();
            getFragmentManager().executePendingTransactions();

            browser_fragment.loadWebPage(url_data);

        }

    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem menuItem = menu.findItem(R.id.back_menu_item);
        View menuView =
                (View) MenuItemCompat.getActionView(menuItem);

        // Configure the search info and add any event listeners...

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back_menu_item:
                if (fragmentArrayList.size() == 0) {
                    return false;
                }
                if (currentIndex == 0) {
                    currentIndex = fragmentArrayList.size()-1;
                } else
                {
                    currentIndex--;
                }

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.webBrowserFragment,fragmentArrayList.get(currentIndex))
                        .commit();

                return true;

            case R.id.forward_menu_item:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                if(fragmentArrayList.size()==0)
                {
                    return false;
                }
                 if(currentIndex == fragmentArrayList.size()-1)
                {
                    currentIndex = 0;
                }
                else
                {
                    currentIndex++;
                }

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.webBrowserFragment,fragmentArrayList.get(currentIndex))
                        .commit();

                return true;

            case R.id.new_menu_item:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...


                fragmentArrayList.add(new WebBrowser());
                currentIndex = fragmentArrayList.size()-1;

                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.webBrowserFragment,fragmentArrayList.get(currentIndex))
                        .commit();

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
