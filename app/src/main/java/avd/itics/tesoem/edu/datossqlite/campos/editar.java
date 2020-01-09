package avd.itics.tesoem.edu.datossqlite.campos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import avd.itics.tesoem.edu.datossqlite.R;

public class editar extends AppCompatActivity {

    private EditText et_codigo, et_nombre, et_correo, et_edad, et_curp;
    private EditText bienvenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        et_codigo = (EditText)findViewById(R.id.c);

        et_nombre = (EditText)findViewById(R.id.nom);
        et_correo = (EditText)findViewById(R.id.mail);
        et_edad = (EditText)findViewById(R.id.ano);
        et_curp = (EditText)findViewById(R.id.curpp);

        Bundle parametro = (Bundle) this.getIntent().getExtras();
        if(parametro !=null){
            String datos = parametro.getString("Id");
            et_codigo.setText(datos);


        }

    }

    public void Modificar(View view){
        avd.itics.tesoem.edu.datossqlite.campos.AdminSQLiteOpenHelper admin = new avd.itics.tesoem.edu.datossqlite.campos.AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDatabase = admin.getWritableDatabase();

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

            int cantidad = BaseDatabase.update("articulos", registro, "codigo=" + codigo, null);
            BaseDatabase.close();

            if(cantidad == 1){
                Toast.makeText(this, "Contacto modificado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El contacto no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void Buscar(View view){
        avd.itics.tesoem.edu.datossqlite.campos.AdminSQLiteOpenHelper admin = new avd.itics.tesoem.edu.datossqlite.campos.AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String nombre = et_nombre.getText().toString();
        String correo = et_correo.getText().toString();
        String edad = et_edad.getText().toString();
        String curp = et_curp.getText().toString();

        if(!codigo.isEmpty()){
            Cursor fila = BaseDeDatabase.rawQuery
                    ("select nombre, correo , edad, curp  from articulos where codigo =" + codigo, null);

            if(fila.moveToFirst()){
                et_nombre.setText(fila.getString(0));
                et_correo.setText(fila.getString(1));
                et_edad.setText(fila.getString(2));
                et_curp.setText(fila.getString(3));
                BaseDeDatabase.close();
            } else {
                Toast.makeText(this,"No existe el contacto", Toast.LENGTH_SHORT).show();
                BaseDeDatabase.close();
            }

        } else {
            Toast.makeText(this, "Debes introducir el código del contacto", Toast.LENGTH_SHORT).show();
        }
    }



    public void regresar(View v){
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
        this.finish();
    }

}

