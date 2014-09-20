package com.marlosirapuan.exemploactionbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends Activity {
    FragmentManager fm = getFragmentManager();

    // instancia essa classe extra de menu pra usar na troca do icone
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            Toast.makeText(this, R.string.message_welcome, Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // associa no ciclo de criacao das opcoes do menu
        this.menu = menu;

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_left) {
            // troca o icone depois que clicar
            changeIcon(0);

            // metodo padrao de controle de um fragment
            ContentLeft cl = new ContentLeft();
            FragmentTransaction ft = fm.beginTransaction();
            // carrega no fragment left o content left
            ft.add(R.id.left_fragment, cl);
            ft.addToBackStack("memory"); // pra nao dar crozópe qdo voltar
            ft.commit();

            return true;
        }
        if (id == R.id.action_right) {
            changeIcon(1);

            // modo diferente de aplicacao do fragment manager com transaction
            getFragmentManager().beginTransaction()
                    // carrega no fragment right o content right
                    .add(R.id.right_fragment, new ContentRight())
                    .addToBackStack("memory")
                    .commit();
            return true;
        }

        // sair do app apos confirmar
        if (id == R.id.action_settings_exit) {
            new AlertDialog.Builder(this)
                    .setTitle("Confirmação")
                    .setMessage("Que fechar mesmo??")
                    .setPositiveButton("Yep", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton("Não", null)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void changeIcon(int i) {
        menu.getItem(i).setIcon(getResources().getDrawable(R.drawable.ic_launcher));
    }
}
