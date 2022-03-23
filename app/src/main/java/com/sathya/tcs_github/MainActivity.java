package com.sathya.tcs_github;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sathya.tcs_github.REST.GitHubClientRepo;
import com.sathya.tcs_github.REST.GithubClient;
import com.sathya.tcs_github.REST.OKhttp_ServiceGenerator;
import com.sathya.tcs_github.REST.ServiceGenerator;
import com.sathya.tcs_github.model.GithubUser;
import com.sathya.tcs_github.model.Repo;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/*

link for OkHttp page : https://square.github.io/okhttp/

Assignment
   Display the repo's in a listView By clicking on it download the zip file.
 */
public class MainActivity extends AppCompatActivity {


    ImageView imageView1;
    Button searchBtn, listRepoButton;
    TextView responseText, textViewreporesults;
    EditText editText;
    ProgressBar progressBar;
    ListView listView;

    // interface
    GithubClient gitHubClient;
    GitHubClientRepo gitHubClientRepo ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        searchBtn     = (Button) findViewById(R.id.main_btn_lookup);
        responseText  = (TextView) findViewById(R.id.main_text_response);
        editText      = (EditText) findViewById(R.id.main_edit_username);
        progressBar   = (ProgressBar) findViewById(R.id.main_progress);

        imageView1     =(ImageView)findViewById(R.id.avatar) ;
        textViewreporesults  = (TextView) findViewById(R.id.text_view_result);

        progressBar.setVisibility(View.INVISIBLE);

        listRepoButton       = (Button) findViewById(R.id.btn_repo) ;

        // Phase I
//           ServiceGenerator provides the retrofitHandle.getClient().create( GitHubClient.class);
       // gitHubClient = ServiceGenerator.getClient().create(GithubClient.class);

        // Phase II
        gitHubClient = OKhttp_ServiceGenerator.createService(GithubClient.class);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serchForUser();
            }
        });

        listRepoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listRepo();
            }
        });


    }

      String content = "" ;
    private void listRepo() {
        String User = editText.getText().toString();
        progressBar.setVisibility(View.VISIBLE);

        gitHubClientRepo = ServiceGenerator.getClient().create(GitHubClientRepo.class);

        final Call<List<Repo>> call = gitHubClientRepo.listRepos(User);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {

                if( !response.isSuccessful()){
                    textViewreporesults.setText("Code : "+response.code());
                    return;
                }

                List<Repo> posts = response.body();
                for( Repo post : posts){
                    content +=  post.getName() + " \n ";
                    content +=  post.getUrl()  + " \n ";
                    textViewreporesults.append(content);
                }

                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                responseText.setText("Error loading "+t.fillInStackTrace().toString());
                responseText.setTextColor(Color.RED);
                responseText.setTextSize(23);

                progressBar.setVisibility(View.INVISIBLE);
            }
        });


    }

    private void serchForUser() {


        String User = editText.getText().toString();
        progressBar.setVisibility(View.VISIBLE);

        // we are bringing up the interface part now...
        final Call<GithubUser> call = gitHubClient.getFeed( User);

        call.enqueue(new Callback<GithubUser>() {
            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {

                GithubUser gitModel = response.body(); // Thsi can be sent to recycler Adapter directly
                if( gitModel != null){
                    responseText.setText(getString( R.string.main_response_text,
                            gitModel.getName(),
                            gitModel.getBlog(),
                            gitModel.getBio(),
                            gitModel.getCompany()
                            ));

                    responseText.setTextSize(18);
                    responseText.setTextColor(Color.RED);

                    // Lets Display Users  Image using PICASSO lib

                    Picasso.get()
                            .load(gitModel.getAvatarUrl())
                            .resize(200,150)
                           // .centerCrop()
                            .into(imageView1);


                }else {
                    responseText.setText("User Not Found.");
                    Toast.makeText(MainActivity.this, "User Not Found!!!", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<GithubUser> call, Throwable t) {

                // Lets take care of this tomorrow...
                responseText.setText("Error loading "+t.fillInStackTrace().toString());
                responseText.setTextColor(Color.RED);
                responseText.setTextSize(23);

                progressBar.setVisibility(View.INVISIBLE);
            }
        });



    }
}