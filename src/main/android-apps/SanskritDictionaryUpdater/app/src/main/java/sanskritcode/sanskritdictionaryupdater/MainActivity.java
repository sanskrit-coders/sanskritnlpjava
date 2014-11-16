package sanskritcode.sanskritdictionaryupdater;

import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private static final String MAIN_ACTIVITY = "MainActivity";
    private static final String DICTIONARY_LIST_URL = "https://raw.githubusercontent.com/vvasuki/stardict-sanskrit/master/sa-head/tars/tars.MD";
    private static final String DICTIONARY_LOCATION = "dict";
    private static final String DOWNLOAD_LOCATION = "dict";

    private TextView topText;
    public List<String> dictUrls;

    public class DictUrlGetter extends AsyncTask<String, Integer, Integer> {
        private final String DICT_URL_GETTER = DictUrlGetter.class.getName();

        @Override
        public Integer doInBackground(String... dictionaryListUrls) {
            Log.i(DICT_URL_GETTER, "Will get dictionaries from " + dictionaryListUrls.length + " urls:");
            dictUrls = new ArrayList();
            for(String url: dictionaryListUrls) {
                Log.i(DICT_URL_GETTER, url);
                try {
                    DefaultHttpClient httpclient = new DefaultHttpClient();
                    HttpGet httppost = new HttpGet(url);
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity ht = response.getEntity();

                    BufferedHttpEntity buf = new BufferedHttpEntity(ht);

                    InputStream is = buf.getContent();
                    BufferedReader r = new BufferedReader(new InputStreamReader(is));

                    String line;
                    while ((line = r.readLine()) != null) {
                        String dictUrl = line.replace("<", "").replace(">", "");
                        dictUrls.add(dictUrl);
                        Log.d(DICT_URL_GETTER, "Added dictionary: " + dictUrl);
                        publishProgress(dictUrls.size());
                    }
                } catch (IOException e) {
                    Log.e(DICT_URL_GETTER, "Failed " + e.getStackTrace());
                }
            }
            Log.i(DICT_URL_GETTER, "Added so many dictionary urls: " + dictUrls.size());
            return dictUrls.size();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            topText.setText("Got " + dictUrls.size() + " urls so far.");
        }

        @Override
        protected void onPostExecute(Integer result) {
            topText.setText("Got " + dictUrls.size() + " urls. Now we download the dictionaries.");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topText = (TextView) findViewById(R.id.textView);
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


    public void putDictionaries(View v) {
        DictUrlGetter dictUrlGetter = new DictUrlGetter();
        dictUrlGetter.execute(DICTIONARY_LIST_URL);
        File sdcard = Environment.getExternalStorageDirectory();
    }
}
