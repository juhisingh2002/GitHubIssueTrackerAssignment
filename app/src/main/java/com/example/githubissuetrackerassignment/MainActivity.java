package com.example.githubissuetrackerassignment;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Execute AsyncTask to fetch data
        new FetchDataAsyncTask().execute();
    }

    private class FetchDataAsyncTask extends AsyncTask<Void, Void, List<DataModel>> {

        @Override
        protected List<DataModel> doInBackground(Void... voids) {
            return fetchData();
        }

        @Override
        protected void onPostExecute(List<DataModel> data) {
            adapter = new MyAdapter(data);
            recyclerView.setAdapter(adapter);
        }
    }

    private List<DataModel> fetchData() {
        List<DataModel> dataList = new ArrayList<>();

        try {
            URL url = new URL("https://api.github.com/repos/microsoft/vscode/issues?state=closed");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            JSONArray jsonArray = new JSONArray(response.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject issue = jsonArray.getJSONObject(i);
                String title = issue.getString("title");
                String createdDate = issue.getString("created_at");
                String closedDate = issue.getString("closed_at");

                JSONObject user = issue.getJSONObject("user");
                String userName = user.getString("login");
                String userImage = user.getString("avatar_url");

                DataModel dataModel = new DataModel(title, createdDate, closedDate, userName, userImage);
                dataList.add(dataModel);
            }

            reader.close();
            connection.disconnect();

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return dataList;
    }
}
