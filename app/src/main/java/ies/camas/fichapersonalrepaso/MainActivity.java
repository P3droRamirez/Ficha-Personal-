package ies.camas.fichapersonalrepaso;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;




public class MainActivity extends AppCompatActivity {

    Button btnNombre, btnCurso, btnLenguaje, btnNuevoRegistro;
    TextView lblNombre, lblCurso, lblLenguaje;
    boolean[] elementoElegido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentes();

        if(savedInstanceState != null){
            lblNombre.setText(savedInstanceState.getString("nombre"));
            lblCurso.setText(savedInstanceState.getString("curso"));
            lblLenguaje.setText(savedInstanceState.getString("lenguaje"));
        }

        elementoElegido = new boolean[5];

    }
    public void inicializarComponentes(){
        btnNombre = findViewById(R.id.btnNombre);
        lblNombre = findViewById(R.id.lblNombre);
        btnCurso = findViewById(R.id.btnCurso);
        lblCurso = findViewById(R.id.lblCurso);
        btnLenguaje = findViewById(R.id.btnLenguajes);
        lblLenguaje = findViewById(R.id.lblLenguaje);

        btnNuevoRegistro = findViewById(R.id.btnNuevoRegistro);



        btnNombre.setOnClickListener(view -> crearAlertaNombre());
        btnNuevoRegistro.setOnClickListener(view -> nuevoRegistro());
        btnCurso.setOnClickListener(view -> crearAlertaCurso());
        btnLenguaje.setOnClickListener(view -> crearAlertaLenguajes());
    }
    public void crearAlertaNombre(){
        final View layout = getLayoutInflater().inflate(R.layout.custom_layout,null);
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(R.string.tituloMensajeRegistro)
                .setView(layout)
                .setNegativeButton(R.string.negativeMensaje,null)
                .setPositiveButton(R.string.positiveMensaje,(dialog,which) -> {
                    EditText editText = layout.findViewById(R.id.txtNombre);
                    String nombreIntroducido = editText.getText().toString();
                    lblNombre.setText(nombreIntroducido);
                })
                .create()
                .show();
    }
    public void nuevoRegistro(){
        new AlertDialog.Builder(this)
                .setTitle(R.string.tituloMensajeRegistro)
                .setMessage(R.string.mensajeRegistro)
                .setNegativeButton(R.string.negativeMensaje,null)
                .setPositiveButton(R.string.positiveMensaje,(dialog,which) ->{
                    lblNombre.setText("");
                    lblCurso.setText("");
                    lblLenguaje.setText("");
                })
                .create()
                .show();
    }
    public void crearAlertaCurso(){
        String[] miLista = {"DAM","DAW","ASIR"};
        int[] checkLista = {-1};

        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(R.string.tituloCurso)
                .setSingleChoiceItems(miLista,checkLista[0],(dialog,which) -> {
                    checkLista[0] = which;
                })
                .setNegativeButton(R.string.negativeMensaje,null)
                .setPositiveButton(R.string.positiveMensaje,(dialog,which) ->{
                    if(checkLista[0] != -1){
                        lblCurso.setText(miLista[checkLista[0]]);
                    }
                })
                .create()
                .show();
    }
    public void crearAlertaLenguajes(){
        String[] miLista = new String[]{"Java","JavaScript","C#","Kotlin","Phyton"};


        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(R.string.tituloLenguaje)
                .setMultiChoiceItems(miLista,elementoElegido,(dialog,which,isChecked) ->{
                    elementoElegido[which] = isChecked;
                })
                .setNegativeButton(R.string.negativeMensaje,null)
                .setPositiveButton(R.string.mensajePoositivoLenguaje,(dialog,which) ->{
                    StringBuilder lenguajesSeleccionados = new StringBuilder();
                    for (int i = 0; i < elementoElegido.length; i++) {
                        if(elementoElegido[i]){
                            if(lenguajesSeleccionados.length() > 0){
                                lenguajesSeleccionados.append("\n");
                            }
                            lenguajesSeleccionados.append(miLista[i]);
                        }
                    }
                    lblLenguaje.setText(lenguajesSeleccionados.toString());
                })
                .create()
                .show();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putString("nombre", lblNombre.getText().toString());
        outState.putString("curso", lblCurso.getText().toString());
        outState.putString("lenguaje", lblLenguaje.getText().toString());
    }
}