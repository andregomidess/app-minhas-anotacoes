package com.app.minhasanotaes;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.app.minhasanotaes.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private AnotacaoPreferencias preferencias;
    private EditText editAnotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        editAnotacao = findViewById(R.id.editAnotacaoo);

        preferencias = new AnotacaoPreferencias(getApplicationContext());

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        //appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text =  editAnotacao.getText().toString();
                if (text.equals("")){
                    Snackbar.make(view, "Preencha a anotação!", Snackbar.LENGTH_LONG).show();
                }else {
                    preferencias.salvarAnotacao(text);
                    Snackbar.make(view, "Anotação salva com sucesso!", Snackbar.LENGTH_LONG).show();

                }
            }
        });

        String anotacao = preferencias.recuperarAnotacao();
        if(!anotacao.equals("")){
            editAnotacao.setText(anotacao);
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

//    @Override
//    public boolean onSupportNavigateUp() {
//        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        //return NavigationUI.navigateUp(navController, appBarConfiguration)
//     //           || super.onSupportNavigateUp();
//    }
}