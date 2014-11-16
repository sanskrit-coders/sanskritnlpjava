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

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import org.apache.commons.io.FilenameUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
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
    // private final TarGZipUnArchiver ua = new TarGZipUnArchiver();


    private TextView topText;
    private File sdcard;
    private File downloadsDir;
    private File dictDir;
    public List<String> dictUrls;
    protected static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

    protected class DictUrlGetter extends AsyncTask<String, Integer, Integer> {
        private final String DICT_URL_GETTER = DictUrlGetter.class.getName();

        @Override
        public Integer doInBackground(String... dictionaryListUrls) {
            Log.i(DICT_URL_GETTER, getString(R.string.use_n_dictionary_indexes) + dictionaryListUrls.length);
            dictUrls = new ArrayList();
            for (String url : dictionaryListUrls) {
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
                        Log.d(DICT_URL_GETTER, getString(R.string.added_dictionary_url) + dictUrl);
                        publishProgress(dictUrls.size());
                    }
                } catch (IOException e) {
                    Log.e(DICT_URL_GETTER, "Failed " + e.getStackTrace());
                }
            }
            Log.i(DICT_URL_GETTER, getString(R.string.added_n_dictionary_urls) + dictUrls.size());
            return dictUrls.size();
        }
        @Override
        protected void onPostExecute(Integer result) {
            String message = R.string.added_n_dictionary_urls + dictUrls.size() + " " +
                    getString(R.string.download_dictionaries);
            topText.setText(message);
            getDictionaries(0);
        }

    }

    protected void getDictionaries(int index) {
        if(index >= dictUrls.size()) return;
        downloadDict(index);
    }

    protected void extractDict(final String fileName) {
        Log.d("extractDict", "Extracting " + fileName);
        topText.setText("Extracting " + fileName);
    }

    protected void downloadDict(final int index) {
        final String url = dictUrls.get(index);
        final String fileName = FilenameUtils.getName(url);
        topText.setText("Getting " + fileName);
        Log.d("downloadDict", "Getting " + fileName);
        asyncHttpClient.get(url, new FileAsyncHttpResponseHandler(new File(downloadsDir, fileName)) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, File response) {
                extractDict(fileName);
                getDictionaries(index + 1);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                String message = "Failed to get " + fileName;
                topText.setText(message);
                Log.w(MAIN_ACTIVITY, message + ":" + throwable.getStackTrace());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        asyncHttpClient.getHttpClient().getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
        setContentView(R.layout.activity_main);
        topText = (TextView) findViewById(R.id.textView);
        sdcard = Environment.getExternalStorageDirectory();
        downloadsDir = new File (sdcard.getAbsolutePath() + "/Download/dicttars");
        if(downloadsDir.exists()==false) {
            downloadsDir.mkdirs();
        }
        dictDir = new File (sdcard.getAbsolutePath() + "/dictdata");
        if(dictDir.exists()==false) {
            dictDir.mkdirs();
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


    public void putDictionaries(View v) {
        DictUrlGetter dictUrlGetter = new DictUrlGetter();
        dictUrlGetter.execute(DICTIONARY_LIST_URL);
    }
}
