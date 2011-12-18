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
	private Random random;
	private Context context;

    public Storage (Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
        random = new Random();
    }

    public Quote getQuote() {
    	this.open();
        Cursor c = db.query("quote", new String[] {"txt", "author", "link", "lang", "read", "fav"},
                            "NOT read", null, null, null, null);
        if (c.getCount() == 0) {
            c = db.query("quote", new String[] {"txt", "author", "link", "lang", "read", "fav"},
                         "read", null, null, null, null);
            if (c.getCount() == 0) {
            	this.close();
                return null;
            }
        }
        int offset = this.random.nextInt(c.getCount());
        c.moveToPosition(offset);
        Quote q = new Quote(c.getString(0), c.getString(1), c.getString(2), c.getString(4), c.getInt(4) > 0, c.getInt(5) > 0);
        this.close();
        return q;
    }

    public Quote getQuote(String link) {
    	this.open();
        Cursor c = db.query("quote", new String[] {"txt", "author", "link", "lang", "read", "fav"},
                            "link =" + link, null, null, null, null);
        // TODO: SQL-escape link

        if (c.getCount() == 0) {
            this.close();
            return null;
        } else {
        	c.moveToFirst();
            Quote q = new Quote(c.getString(0), c.getString(1), c.getString(2), c.getString(4), c.getInt(4) > 0, c.getInt(5) > 0);
            this.close();
            return q;
        }
   }

    public List<Quote> getFaves() {
    	this.open();
        Cursor c = db.query("quote", new String[] {"txt", "author", "link", "lang", "read", "fav"},
                            "fav", null, null, null, null);

        if (c.getCount() == 0) {
            this.close();
            return null;
        } else {
        	List<Quote> rez = new ArrayList<Quote>();
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
            	rez.add(new Quote(c.getString(0), c.getString(1), c.getString(2), c.getString(4), c.getInt(4) > 0, c.getInt(5) > 0));
            	c.moveToNext();
            }
            this.close();
            return rez;
        }
   }

    public List<Quote> findQuotes(String search_str) {
    	this.open();
        Cursor c = db.query("quote", new String[] {"txt", "author", "link", "lang", "read", "fav"},
                            "txt LIKE '%" + search_str + "%' OR author LIKE '%" + search_str + "%'", null, null, null, null);
        // TODO: SQL-escape link

        if (c.getCount() == 0) {
            this.close();
            return null;
        } else {
        	List<Quote> rez = new ArrayList<Quote>();
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
            	rez.add(new Quote(c.getString(0), c.getString(1), c.getString(2), c.getString(4), c.getInt(4) > 0, c.getInt(5) > 0));
            	c.moveToNext();
            }
            this.close();
            return rez;
        }
   }

    public int storeQuote(Quote quote) {
    	this.open();
        ContentValues args = new ContentValues();
        args.put("link", quote.getLink());
        args.put("txt", quote.getText());
        args.put("author", quote.getAuthor());
        args.put("lang", quote.getLang());
        args.put("read", quote.isRead());
        args.put("fav", quote.isFav());
        int rez = db.update("quote", args, "link = '" + quote.getLink() + "'", null);
        this.close();
        return rez;
    }

    public void storeQuotes(List<Quote> quotes) {
    	this.open();
        for (Quote quote : quotes) {
            ContentValues args = new ContentValues();
            args.put("link", quote.getLink());
            args.put("txt", quote.getText());
            args.put("author", quote.getAuthor());
            args.put("lang", quote.getLang());
            args.put("read", quote.isRead());
            args.put("fav", quote.isFav());
            db.insert("quote", null, args);
        }
        this.close();
    }

    public int unreadCount() {
    	this.open();
        //return db.rawQuery("SELECT COUNT(*) FROM quote WHERE NOT read",null).getInt(0);
      	int rez = db.query("quote", new String[] {"txt", "author", "link", "lang", "read", "fav"},
                           "NOT read", null, null, null, null).getCount();
    	this.close();
    	return rez;
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
            db.execSQL("DROP TABLE IF EXISTS quote");
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
