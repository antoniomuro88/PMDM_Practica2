package practica2.studium.datos_personales;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Variables globales
    EditText editNombre, editApellidos, editEdad;
    TextView result, lbNombre, lbApellidos, lbEdad;
    Button buttonEnviar, buttonRestablecer;
    RadioButton r1, r2;
    Spinner lstEstadoCivil;
    Switch sHijos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recogemos los datos
        lbNombre = findViewById(R.id.lblNombre);
        editNombre = findViewById(R.id.etNombre);
        lbApellidos = findViewById(R.id.lblApellidos);
        editApellidos = findViewById(R.id.etApellidos);
        lbEdad = findViewById(R.id.lblEdad);
        editEdad = findViewById(R.id.etEdad);
        result = findViewById(R.id.lblMensaje);
        buttonEnviar = findViewById(R.id.btEnviar);
        buttonRestablecer = findViewById(R.id.btRestablecer);
        r1 = findViewById(R.id.rbHombre);
        r2 = findViewById(R.id.rbMujer);
        final ArrayAdapter EstadoCivil = ArrayAdapter.createFromResource(this, R.array.feedbacktypelist, R.layout.support_simple_spinner_dropdown_item);
        lstEstadoCivil = findViewById(R.id.spEstadoCivil);
        lstEstadoCivil.setAdapter(EstadoCivil);
        sHijos = findViewById(R.id.swHijos);

        //Evento al hacer click en el botón enviar
        buttonEnviar.setOnClickListener(new View.OnClickListener() {

                                            @Override
                                            public void onClick(View v) {
                                                //Recogemos los datos de los edits en variables

                                                String nombre = editNombre.getText().toString();
                                                String apellidos = editApellidos.getText().toString();
                                                String edad = editEdad.getText().toString();
                                                Toast toast1 = Toast.makeText(getApplicationContext(), "Debes rellenar todos los campos", Toast.LENGTH_SHORT);

                                                //Si nombre está vacío
                                                if (nombre.isEmpty()) {
                                                    lbNombre.setTextColor(getResources().getColor(R.color.rojo));
                                                    lbNombre.setText("El campo 'Nombre' es obligatorio");
                                                    toast1.show();

                                                    //Si apellidos está vacío
                                                } else if (apellidos.isEmpty()) {
                                                    lbNombre.setTextColor(getResources().getColor(R.color.negro));
                                                    lbNombre.setText("Nombre:");
                                                    lbApellidos.setTextColor(getResources().getColor(R.color.rojo));
                                                    lbApellidos.setText("El campo 'Apellidos' es obligatorio");
                                                    toast1.show();

                                                    //Si edad está vacío
                                                } else if (edad.isEmpty()) {
                                                    lbNombre.setTextColor(getResources().getColor(R.color.negro));
                                                    lbNombre.setText("Nombre:");
                                                    lbApellidos.setTextColor(getResources().getColor(R.color.negro));
                                                    lbApellidos.setText("Apellidos:");
                                                    lbEdad.setTextColor(getResources().getColor(R.color.rojo));
                                                    lbEdad.setText("El campo 'Edad' es obligatorio");
                                                    toast1.show();

                                                    //Si no
                                                } else {
                                                    lbNombre.setTextColor(getResources().getColor(R.color.negro));
                                                    lbNombre.setText("Nombre:");
                                                    lbApellidos.setTextColor(getResources().getColor(R.color.negro));
                                                    lbApellidos.setText("Apellidos:");
                                                    lbEdad.setTextColor(getResources().getColor(R.color.negro));
                                                    lbEdad.setText("Edad:");

                                                    //Creamos variable tipo String para mayor, genero e hijos
                                                    String mayor, genero, hijos;

                                                    //Obtenemos la posición del array del estado civil
                                                    int pos = lstEstadoCivil.getSelectedItemPosition();

                                                    //Obtenemos el nombre del estado civil según la posición del array
                                                    String ecivil = lstEstadoCivil.getItemAtPosition(pos).toString();

                                                    //Convertimos el valor del EditText edad a numérico para poder operar con él
                                                    int numero = Integer.parseInt(editEdad.getText().toString());

                                                    //Si es mayor o igual a 18
                                                    if (numero >= 18)
                                                        mayor = "mayor de edad";
                                                        //Si no
                                                    else
                                                        mayor = "menor de edad";

                                                    //Si el primer radio buton está marcado
                                                    if (r1.isChecked() == true) {
                                                        genero = "Es un hombre";
                                                        ecivil = ecivil.replace('@', 'o').toLowerCase();
                                                    }
                                                    //Si no
                                                    else {
                                                        genero = "Es una mujer";
                                                        ecivil = ecivil.replace('@', 'a').toLowerCase();
                                                    }

                                                    //Si el switch está marcado
                                                    if (sHijos.isChecked())
                                                        hijos = " con hijos.";
                                                        //Si no
                                                    else
                                                        hijos = " sin hijos.";

                                                    //Mostramos el resultado
                                                    result.setText(apellidos + ", " + nombre + ".\nTiene " + edad + " años (" + mayor
                                                            + ").\n" + genero + " " + ecivil + hijos);
                                                }
                                            }

                                        }
        );
        buttonRestablecer.setOnClickListener(new View.OnClickListener() {

                                                 @Override
                                                 public void onClick(View v) {
                                                     //Restablecemos todo el formulario a su origen
                                                     lbNombre.setTextColor(getResources().getColor(R.color.negro));
                                                     lbNombre.setText("Nombre:");
                                                     lbApellidos.setTextColor(getResources().getColor(R.color.negro));
                                                     lbApellidos.setText("Apellidos:");
                                                     lbEdad.setTextColor(getResources().getColor(R.color.negro));
                                                     lbEdad.setText("Edad:");
                                                     editNombre.setText("");
                                                     editApellidos.setText("");
                                                     editEdad.setText("");
                                                     r1.setChecked(true);
                                                     lstEstadoCivil.setSelection(0);
                                                     sHijos.setChecked(false);
                                                     result.setText("");
                                                 }
                                             }

        );

    }

}