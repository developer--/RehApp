package geolab.myo.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "garage48";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_GRAPHITE_DB_TABLE =
            "CREATE TABLE " + TableGraphite.TABLE_NAME + "("
            + TableGraphite.id + " integer,"
            + TableGraphite.title + " text,"
            + TableGraphite.videoPath + " text"
            + ");";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase DB){
        DB.execSQL(CREATE_GRAPHITE_DB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        onUpgrade(sqLiteDatabase,i,i2);
    }
}
