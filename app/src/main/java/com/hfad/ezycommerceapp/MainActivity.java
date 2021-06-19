package com.hfad.ezycommerceapp;

    import  androidx.appcompat.app.AppCompatActivity;
    import androidx.appcompat.widget.Toolbar;
    import androidx.fragment.app.FragmentActivity;
    import androidx.fragment.app.FragmentTransaction;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.ProgressBar;

    import com.hfad.ezycommerceapp.Fragment.business;
    import com.hfad.ezycommerceapp.Fragment.cookbook;
    import com.hfad.ezycommerceapp.Fragment.mystery;
    import com.hfad.ezycommerceapp.Fragment.scifi;
    import com.hfad.ezycommerceapp.Retrofit.ApiService;

    import java.util.ArrayList;
    import java.util.List;

    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MainAdapter mainAdapter;
    private List<ProductModel.Product> products = new ArrayList<>();
    private Button button,button2,button3,button4;

    business FragmentBussiness;
    cookbook Fragmentcookbook;
    mystery Fragmentmystery;
    scifi Fragmentscifi;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);


        setupView();
        setupRecyclerView();
        getDataFromApi();
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    void bussinessFrag(){
        FragmentBussiness = new business();
        FragmentTransaction ft =getSupportFragmentManager()
                .beginTransaction().replace(R.id.container, FragmentBussiness);
        ft.commit();
    }
    void cookbookFrag(){
        Fragmentcookbook = new cookbook();
        FragmentTransaction ft =getSupportFragmentManager()
                .beginTransaction().replace(R.id.container, Fragmentcookbook);
        ft.commit();
    }
    void mysteryFrag(){
        Fragmentmystery = new mystery();
        FragmentTransaction ft =getSupportFragmentManager()
                .beginTransaction().replace(R.id.container, Fragmentmystery);
        ft.commit();
    }
    void scifiFrag(){
        Fragmentscifi = new scifi();
        FragmentTransaction ft =getSupportFragmentManager()
                .beginTransaction().replace(R.id.container, Fragmentscifi);
        ft.commit();
    }



private void filtered (List<ProductModel.Product> data ){


        final List<ProductModel.Product> category = new ArrayList<>();

        for (int i = 0; i <data.size(); i ++){
            if (data.get(i).getCategory().equals("business")){
                category.add(data.get(i));
            }
        }
}

    private void setupView() {
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

    }

    private void setupRecyclerView() {
        mainAdapter = new MainAdapter(this,products, new MainAdapter.AdapterListener() {
            @Override
            public void onClick(ProductModel.Product products) {
                Intent intent = new Intent(MainActivity.this, Productdetail.class);
                intent.putExtra("intent_id",products.getId());
                intent.putExtra("intent_name",products.getName());
                intent.putExtra("intent_desc",products.getDescription());
                intent.putExtra("intent_price",products.getPrice());
                intent.putExtra("intent_author",products.getAuthor());
                intent.putExtra("intent_type",products.getType());
                intent.putExtra("intent_img",products.getImg());
                intent.putExtra("intent_category",products.getCategory());
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);
    }

    private void getDataFromApi() {
        showLoad(true);
        ApiService.endpoint().getList().enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    List<ProductModel.Product> products = response.body().getProducts();
                    Log.d(TAG, products.toString());
                    mainAdapter.setData(products);
                }
            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
               showLoad(false);
                Log.d(TAG, t.toString());
            }
        });

    }

    private void showLoad(boolean load){
        if (load){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == button){
            mysteryFrag();

        }else if (v ==button2){
            scifiFrag();

        }else if(v == button3){
            bussinessFrag();
        }
        else if(v == button4){
            cookbookFrag();
        }
    }
}
