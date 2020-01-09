package avd.itics.tesoem.edu.datossqlite.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatosConexion {

    private SQLiteDatabase basedatos;
    private DatosHelper datosHelper;

    public DatosConexion(Context context){
        datosHelper = DatosHelper.getInstance(context);
    }

    public void open(){
        basedatos = datosHelper.getWritableDatabase();
    }

    public void close(){
        basedatos.close();
    }

    public String[] llenagridview(){
        String[] datos;
        int fila=5;
        Cursor cursor = basedatos.rawQuery("select * from " + DatosHelper.tabladatos.TABLA, null);
        if(cursor.getCount() <= 0 ){
            datos = new String[5];
            datos[0] = DatosHelper.tabladatos.COLUMNA_ID;
            datos[1] = DatosHelper.tabladatos.COLUMNA_NOMBRE;
            datos[2] = DatosHelper.tabladatos.COLUMNA_CORREO;
            datos[3] = DatosHelper.tabladatos.COLUMNA_EDAD;
            datos[4] = DatosHelper.tabladatos.COLUMNA_CURP;
        } else {
            datos = new String[(cursor.getCount()*5)+5];
            datos[0] = DatosHelper.tabladatos.COLUMNA_ID;
            datos[1] = DatosHelper.tabladatos.COLUMNA_NOMBRE;
            datos[2] = DatosHelper.tabladatos.COLUMNA_CORREO;
            datos[3] = DatosHelper.tabladatos.COLUMNA_EDAD;
            datos[4] = DatosHelper.tabladatos.COLUMNA_CURP;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                datos[fila] = String.valueOf(cursor.getInt(0));
                datos[fila+1] = cursor.getString(1);
                datos[fila+2] = cursor.getString(2);
                datos[fila+3] = String.valueOf(cursor.getInt(3));
                datos[fila+4] = cursor.getString(4);
                fila+=5;
                cursor.moveToNext();
            }
        }
        return datos;
    }

    public boolean insertar(ContentValues parametros){
        boolean estado = true;
        basedatos.isOpen();
        Long resulta = basedatos.insert(DatosHelper.tabladatos.TABLA,null,parametros);
        if (resulta <0 ) estado = false;
        return estado;
    }

    public boolean actualiza(ContentValues parametros,String[] Condicion){
        boolean estado = true;
        basedatos.isOpen();
        long resulta = basedatos.update(DatosHelper.tabladatos.TABLA,parametros,DatosHelper.tabladatos.COLUMNA_ID + "=?", Condicion);
        if (resulta<0) estado = false;
        return estado;
    }

    public boolean eliminar(String[] Condicion){
        boolean estado = true;
        basedatos.isOpen();
        long resulta = basedatos.delete(DatosHelper.tabladatos.TABLA, DatosHelper.tabladatos.COLUMNA_ID + "=?",Condicion);
        if (resulta<0) estado = false;
        return estado;
    }

}