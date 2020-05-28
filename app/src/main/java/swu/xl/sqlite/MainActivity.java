package swu.xl.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        //insert(database);

        //find(database);

        //delete(database);

        //update(database);

        //原生执行一个SQL语句
        //database.execSQL();

        //原生查询
        //database.rawQuery()

        //事务
        //transactionTest(database);
    }

    /**
     * 插入记录
     * @param database
     */
    public void insert(SQLiteDatabase database) {
        //ContentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_NAME,"文小酒");
        contentValues.put(DatabaseHelper.COLUMN_PASSWORD,"123456");

        //插入成功：返回插入的id
        //插入失败：返回-1
        long insert_id = database.insert(
                DatabaseHelper.TABLE_NAME,
                null,
                contentValues
        );
        Toast.makeText(this, "插入数据的id："+insert_id, Toast.LENGTH_SHORT).show();
    }

    /**
     * 查询记录
     */
    public void find(SQLiteDatabase database){
        //Cursor 游标 可以理解为查询的结果集合
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);

        //将游标移动到最开始的位置
        if (cursor.moveToFirst()){
            for (int i = 0; i < cursor.getCount(); i++) {
                //查询数据
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
                String password = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PASSWORD));
                //移动到下一个位置
                cursor.moveToNext();

                Toast.makeText(this, "用户："+name+" 密码："+password, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 修改记录
     */
    public void update(SQLiteDatabase database){
        //ContentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_PASSWORD,"45678");

        String whereClaus = DatabaseHelper.COLUMN_NAME+"=?";
        String[] whereArgs = {"文小酒"};

        database.update(DatabaseHelper.TABLE_NAME,contentValues,whereClaus,whereArgs);
    }

    /**
     * 删除记录
     */
    public void delete(SQLiteDatabase database){
        String whereClaus = DatabaseHelper.COLUMN_PASSWORD+"=?";
        String[] whereArgs = {"123456"};

        database.delete(DatabaseHelper.TABLE_NAME,whereClaus,whereArgs);
    }

    /**
     * 事务
     * @param database
     */
    private void transactionTest(SQLiteDatabase database) {
        //开始事务，此时db会被锁定
        database.beginTransaction();

        try {
            //做你的事情，例如循环插入数据

            //设置事务成功，必须要设置
            database.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //结束事务
            database.endTransaction();
        }
    }
}
