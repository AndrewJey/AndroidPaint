package paintdroid.ace.so2.com.paintdroid;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton negro;
    ImageButton blanco;
    ImageButton rojo;
    ImageButton verde;
    ImageButton azul;
    private static lienzo lienzo;
    float ppequeno;
    float pmediano;
    float pgrande;
    float pdefecto;
    ImageButton trazo;
    ImageButton anadir;
    ImageButton borrar;
    ImageButton guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        negro = (ImageButton)findViewById(R.id.colornegro);
        blanco = (ImageButton)findViewById(R.id.colorblanco);
        rojo = (ImageButton)findViewById(R.id.colorrojo);
        verde = (ImageButton)findViewById(R.id.colorverde);
        azul = (ImageButton)findViewById(R.id.colorazul);
        trazo = (ImageButton)findViewById(R.id.trazo);
        anadir = (ImageButton)findViewById(R.id.anadir);
        borrar = (ImageButton)findViewById(R.id.borrar);
        guardar = (ImageButton)findViewById(R.id.guardar);

        negro.setOnClickListener(this);
        blanco.setOnClickListener(this);
        rojo.setOnClickListener(this);
        verde.setOnClickListener(this);
        azul.setOnClickListener(this);
        trazo.setOnClickListener(this);
        anadir.setOnClickListener(this);
        borrar.setOnClickListener(this);
        guardar.setOnClickListener(this);

        lienzo = (lienzo)findViewById(R.id.lienzo);

        ppequeno= 10;
        pmediano= 20;
        pgrande= 30;

        pdefecto= pmediano;

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

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        String color = null;


        switch (v.getId()){
            case R.id.colornegro:
                color = v.getTag().toString();
                lienzo.setColor(color);
                break;
            case R.id.colorblanco:
                color = v.getTag().toString();
                lienzo.setColor(color);
                break;
            case R.id.colorazul:
                color = v.getTag().toString();
                lienzo.setColor(color);
                break;
            case R.id.colorverde:
                color = v.getTag().toString();
                lienzo.setColor(color);
                break;
            case R.id.colorrojo:
                color = v.getTag().toString();
                lienzo.setColor(color);
                break;
            case R.id.trazo:
                final Dialog tamanopunto = new Dialog(this);
                tamanopunto.setTitle("Tamaño del punto:");
                tamanopunto.setContentView(R.layout.tamano_punto);
                //listen for clicks on tamaños de los botones
                TextView smallBtn = (TextView)tamanopunto.findViewById(R.id.tpequeno);
                smallBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        lienzo.setBorrado(false);
                        lienzo.setTamanyoPunto(ppequeno);

                        tamanopunto.dismiss();
                    }
                });
                TextView mediumBtn = (TextView)tamanopunto.findViewById(R.id.tmediano);
                mediumBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        lienzo.setBorrado(false);
                        lienzo.setTamanyoPunto(pmediano);

                        tamanopunto.dismiss();
                    }
                });
                TextView largeBtn = (TextView)tamanopunto.findViewById(R.id.tgrande);
                largeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lienzo.setBorrado(false);
                        lienzo.setTamanyoPunto(pgrande);

                        tamanopunto.dismiss();
                    }
                });
                //show and wait for user interaction
                tamanopunto.show();


                break;
            case R.id.anadir:


                AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
                newDialog.setTitle("Nuevo Dibujo");
                newDialog.setMessage("¿Comenzar nuevo dibujo (perderás el dibujo actual)?");
                newDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){

                        lienzo.NuevoDibujo();
                        dialog.dismiss();
                    }
                });
                newDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                newDialog.show();


                break;
            case R.id.borrar:

                final Dialog borrarpunto = new Dialog(this);
                borrarpunto.setTitle("Tamaño de borrado:");
                borrarpunto.setContentView(R.layout.tamano_punto);
                //listen for clicks on tamaños de los botones
                TextView smallBtnBorrar = (TextView)borrarpunto.findViewById(R.id.tpequeno);
                smallBtnBorrar.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        lienzo.setBorrado(true);
                        lienzo.setTamanyoPunto(ppequeno);

                        borrarpunto.dismiss();
                    }
                });
                TextView mediumBtnBorrar = (TextView)borrarpunto.findViewById(R.id.tmediano);
                mediumBtnBorrar.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        lienzo.setBorrado(true);
                        lienzo.setTamanyoPunto(pmediano);

                        borrarpunto.dismiss();
                    }
                });
                TextView largeBtnBorrar = (TextView)borrarpunto.findViewById(R.id.tgrande);
                largeBtnBorrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lienzo.setBorrado(true);
                        lienzo.setTamanyoPunto(pgrande);

                        borrarpunto.dismiss();
                    }
                });
                //show and wait for user interaction
                borrarpunto.show();


                break;
            case R.id.guardar:

                AlertDialog.Builder salvarDibujo = new AlertDialog.Builder(this);
                salvarDibujo.setTitle("Salvar dibujo");
                salvarDibujo.setMessage("¿Salvar Dibujo a la galeria?");
                salvarDibujo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){

                        //Salvar dibujo
                        lienzo.setDrawingCacheEnabled(true);
                        //attempt to save
                        String imgSaved = MediaStore.Images.Media.insertImage(
                                getContentResolver(), lienzo.getDrawingCache(),
                                UUID.randomUUID().toString()+".png", "drawing");
                        //Mensaje de todo correcto
                        if(imgSaved!=null){
                            Toast savedToast = Toast.makeText(getApplicationContext(),
                                    "¡Dibujo salvado en la galeria!", Toast.LENGTH_SHORT);
                            savedToast.show();
                        }
                        else{
                            Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                    "¡Error! La imagen no ha podido ser salvada.", Toast.LENGTH_SHORT);
                            unsavedToast.show();
                        }
                        lienzo.destroyDrawingCache();


                    }
                });
                salvarDibujo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
                salvarDibujo.show();

                break;
            default:

                break;
        }
    }
}
