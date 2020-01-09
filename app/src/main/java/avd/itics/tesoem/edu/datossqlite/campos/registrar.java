package avd.itics.tesoem.edu.datossqlite.campos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import avd.itics.tesoem.edu.datossqlite.R;

public class registrar extends AppCompatActivity {
    private EditText et_codigo, et_nombre,  et_correo, et_edad, et_curp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        et_codigo = (EditText)findViewById(R.id.codigo);
        et_nombre = (EditText)findViewById(R.id.nombre);
        et_correo = (EditText)findViewById(R.id.correo);
        et_edad = (EditText)findViewById(R.id.edad);
        et_curp = (EditText)findViewById(R.id.curp);
    }

    public void Registrar(View view){
        avd.itics.tesoem.edu.datossqlite.campos.AdminSQLiteOpenHelper admin = new avd.itics.tesoem.edu.datossqlite.campos.AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String nombre = et_nombre.getText().toString();
        String correo = et_correo.getText().toString();
        String edad = et_edad.getText().toString();
        String curp = et_curp.getText().toString();

        if(!codigo.isEmpty() && !nombre.isEmpty() && !curp.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("codigo", codigo);
            registro.put("nombre", nombre);
            registro.put("correo", correo);
            registro.put("edad", edad);
            registro.put("curp", curp);

            BaseDeDatos.insert("articulos", null, registro);

            BaseDeDatos.close();
            et_codigo.setText("");
            et_nombre.setText("");
            et_correo.setText("");
            et_edad.setText("");
            et_curp.setText("");

            Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void regreso(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        this.finish();
    }



}

