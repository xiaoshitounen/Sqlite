package swu.xl.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    //数据库名称
    public static final String DATABASE_NAME = "user";
    //数据库版本
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "user";
    public static final String COLUMN_NAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    /**
     * 构造方法
     * @param context
     */
    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * 创建数据库
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //执行创建语句
        db.execSQL("create table " + TABLE_NAME + "(" + COLUMN_NAME + " varchar(20) not null, " + COLUMN_PASSWORD + " varchar(20) not null);");
    }

    /**
     * 更新数据库
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //数据库更新操作 oldVersion > newVersion 的时候会调用这个方法
    }
}
