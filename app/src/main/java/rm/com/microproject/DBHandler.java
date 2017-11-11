package rm.com.microproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JOSEPH on 11/9/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static  final int dbVersion = 1;
    private static final String dbName = "microproject";
    private static final String tableName = "images";
    private static final String userId = "user_Id";
    private static final String imageId = "image_Id";
    private static final String imageUrl = "image_Url";



    public DBHandler(Context context) {super(context, dbName, null, dbVersion);}

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String createImageTable = "CREATE TABLE IF NOT EXISTS " + tableName + "(" +userId + " TEXT, " + imageId + " TEXT," + imageUrl + " TEXT" + ")";
//        db.execSQL(createImageTable);

    }

    public void createTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String createImageTable = "CREATE TABLE IF NOT EXISTS " + tableName + "(" +userId + " TEXT, " + imageId + " TEXT," + imageUrl + " TEXT" + ")";
        db.execSQL(createImageTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS "+tableName);
    }

    public void onDrop(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+tableName);
    }

    public void addImage(ImageDetails imageDetails,String n){
        SQLiteDatabase db = this.getWritableDatabase();
        //check this
        ContentValues values = new ContentValues();
        values.put(userId,n);
        values.put(imageId,imageDetails.getImageId());
        values.put(imageUrl,imageDetails.getImageUrl());

        //inserting to table
        db.insert(tableName,null,values);
        //close the connection to db
        db.close();
    }

    public int getImagesCount(){
        String query = "SELECT * FROM " + tableName;
        SQLiteDatabase db = this.getReadableDatabase();
        //define cursor
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }

    public List<ImageDetails> getAllImages(){
        List<ImageDetails> imagesqlList = new ArrayList<ImageDetails>();
        String selectQuery = "SELECT  * FROM " + tableName;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ImageDetails imageDetails = new ImageDetails();
                imageDetails.setImageId(cursor.getString(1));
                imageDetails.setImageUrl(cursor.getString(2));

                imagesqlList.add(imageDetails);
            } while (cursor.moveToNext());
        }

        return imagesqlList;
    }

    /*public int updateStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(nameTable,student.getName());
        values.put(roll_noTable,student.getRoll_no());
        values.put(ageTable,student.getAge());
        return db.update(tableName, values, roll_noTable + " = ?",
                new String[] { String.valueOf(student.getRoll_no()) });
    }

    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, roll_noTable + " = ?",
                new String[] { String.valueOf(student.getRoll_no()) });
        db.close();
    }*/

}
