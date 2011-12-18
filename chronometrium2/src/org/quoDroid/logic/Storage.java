package org.quoDroid.logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Storage {

    private static final String DATABASE_NAME = "quote.db";
    private static final int DATABASE_VERSION = 1;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
	private Context context;

    public Storage (Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    /**
     * @TODO: getActivities
     */
    public List<ActivityP> getActivities(String search_str) {
    	ArrayList<ActivityP> acts = new ArrayList<ActivityP>();
    	
    	
		return acts;
   }

    public void saveTimepoint(TimePoint point) {
    	this.open();
        ContentValues args = new ContentValues();
        args.put("uid", point.getuId() );
        args.put("catId", point.getCatId());
        args.put("startPoint", point.getPointStar());
        args.put("endPiont", point.getPointEnd());
        db.insert("quote", null, args);
        this.close();
    }

    public void storeActivities(List<ActivityP> activities) {
    	this.open();
        for (ActivityP act : activities) {
            ContentValues args = new ContentValues();
            args.put("uid", act.getUserId() );
            args.put("parentId", act.getProfileUrl() );
            args.put("name", act.getName() );
            args.put("description",act.getDescription());
            args.put("coordX", act.getCoordX() );
            args.put("coordY", act.getCoordX() );
            args.put("radius", act.getRadius() );
            db.insert("quote", null, args);
            
        }
        this.close();
    }

    

    static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
           super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE timeline ("
            		 + "id INT PRIMARY KEY,"
                     + "uid INT NOT NULL,"
                     + "catId INT NOT NULL,"
                     + "startPoint INTEGER not NULL,"
                     + "endPiont INTEGER NOT NULL,"
                     + ");");
            
            db.execSQL("CREATE TABLE activity ("
                    + "id INT PRIMARY KEY,"
                    + "uid INT NOT NULL,"
                    + "parentId INT NOT NULL,"
                    + "name TEXT not NULL,"
                    + "description TEXT not NULL,"
                    + "coordX REAL NOT NULL,"
                    + "coordY REAL NOT NULL"
                    + "radius REAL NOT NULL"
                    + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS timeline");
            db.execSQL("DROP TABLE IF EXISTS activity");
            onCreate(db);
       }
   }

   public Storage open() throws SQLException
   {
        db = DBHelper.getWritableDatabase();
        return this;
   }

   public void close()
   {
        DBHelper.close();
   }
}
