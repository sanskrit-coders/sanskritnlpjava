package sanskritcode.sanskritdictionaryupdater;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
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
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
    private File sdcard;
    private File downloadsDir;
    private File dictDir;
    private List<String> dictUrls = new ArrayList<String>();
    private List<String> dictFiles = new ArrayList<String>();
    protected static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

    protected void downloadDict(final int index) {
        final String url = dictUrls.get(index);
        final String fileName = FilenameUtils.getName(url);
        topText.setText("Getting " + fileName);
        Log.d("downloadDict", "Getting " + fileName);
        asyncHttpClient.get(url, new FileAsyncHttpResponseHandler(new File(downloadsDir, fileName)) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, File response) {
                dictFiles.add(fileName);
                getDictionaries(index + 1);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                String message = "Failed to get " + fileName;
                topText.setText(message);
                Log.w("downloadDict", message + ":" + throwable.getStackTrace().toString());
                getDictionaries(index + 1);
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


    public void buttonPressed1(View v) {
        DictUrlGetter dictUrlGetter = new DictUrlGetter();
        dictUrlGetter.execute(DICTIONARY_LIST_URL);
    }

    protected void getDictionaries(int index) {
        if(index >= dictUrls.size()) {
            extractDicts(0);
        } else {
            downloadDict(index);
        }
    }

    protected void extractDicts(int index) {
        if(index >= dictFiles.size()) {
            topText.setText(getString(R.string.finalMessage));
            return;
        } else {
            new DictExtracter().execute(index);
        }
    }

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

    protected class DictExtracter extends AsyncTask<Integer, Integer, Integer> {

        protected void deleteTarFile(String sourceFile) {
            String message4 = "Deleting " + sourceFile + " " + new File(sourceFile).delete();
            // topText.append(message4);
            Log.d("DictExtracter", message4);

        }
        @Override
        protected void onPostExecute(Integer result) {
            String message1 = "Extracted.";
            Log.d("DictExtracter", message1);
            topText.setText(message1);
            extractDicts(result + 1);
        }
        @Override
        protected Integer doInBackground(Integer... params) {
            String fileName = dictFiles.get(params[0]);
            String sourceFile = FilenameUtils.concat(downloadsDir.toString(), fileName);
            final String baseName = FilenameUtils.getBaseName(FilenameUtils.getBaseName(fileName));
            final String destDir = FilenameUtils.concat(dictDir.toString(), baseName);
            new File(destDir).mkdirs();
            String message2 = "Destination directory " + destDir;
            Log.d("DictExtracter", message2);
            topText.append(message2);
            try {
                TarArchiveInputStream tarInput =
                        new TarArchiveInputStream(new GzipCompressorInputStream(new FileInputStream(sourceFile)));

                final byte[] buffer = new byte[50000];
                TarArchiveEntry currentEntry = null;
                while((currentEntry = (TarArchiveEntry) tarInput.getNextEntry()) != null) {
                    String destFile = FilenameUtils.concat(destDir, currentEntry.getName());
                    FileOutputStream fos = new FileOutputStream(destFile);
                    String message3 = "Destination: " + destFile;
                    Log.d("DictExtracter", message3);
                    int n = 0;
                    while (-1 != (n = tarInput.read(buffer))) {
                        fos.write(buffer, 0, n);
                    }
                    fos.close();
                }
                tarInput.close();
            } catch (Exception e) {
                Log.w("DictExtracter", "IOEx:" + e.getStackTrace());
            }
            // deleteTarFile(sourceFile);
            return params[0];
        }
    }

}
