package com.example.alex.p0411_layoutinflaterlist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends ActionBarActivity {

    ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] name = {"Оля", "Анна", "Коля", "Андрей", "Николая", "Максим", "Алексей", "Федор", "Олег", "Петр", "Ира", "Жучка", "Молли"};

        String[] position = {"Программер 1", "Программер 2", "Программер 3", "Программер 4", "Программер 5", "Программер 6",
                "Программер 7", "Программер 8", "Программер 9", "Программер 10",
                "Программер 11", "Программер 12", "Программер 113"};

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linLayout);

        LayoutInflater layoutInflater = getLayoutInflater();

        imageView = (ImageView) findViewById(R.id.imageView);

        ImageURL imageURL = new ImageURL();
        imageURL.execute();

        for (int i = 0; i < name.length; i++) {

            View item = layoutInflater.inflate(R.layout.item, linearLayout, false);

            TextView tvName = (TextView) item.findViewById(R.id.tvName);
            tvName.setText(name[i]);


            TextView tvPosition = (TextView) item.findViewById(R.id.tvPosition);
            tvPosition.setText(position[i]);

            //item.getLayoutParams().width= ViewGroup.LayoutParams.MATCH_PARENT;

            linearLayout.addView(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class ImageURL extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            //Тут пишем основной код
            try {
                URL url = new URL("http://developer.alexanderklimov.ru/android/images/android_cat.jpg");


                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(bitmap);
            //Тут выводим итоговые данные
        }
    }
}
