package ke.co.locaden.shoppingcart.views;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.net.URI;
import java.util.HashMap;

public class AvaillableCarsProvider extends ContentProvider {
    static final String PROVIDER_NAME = "LocadenCars";
    static final String URL = "content://" + PROVIDER_NAME + "/cars";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "_id";
    static final String CAR_NAME = "car_name";
    static final String CARS_IN_STOCK = "in_stock";

    private static HashMap<String, String> CARS_PROJECTION_MAP;

    static final int CARS = 1;
    static final int CARS_ID = 2;

    static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "teams", CARS);
        uriMatcher.addURI(PROVIDER_NAME, "teams/#", CARS_ID);
    }

    /**
     * Database specific constant declarations
     */

    private SQLiteDatabase db;
    static final String DB_NAME = "LocadenCars";
    static final String CARS_TABLE_NAME = "cars";
    static final int DB_VERSION = 1;
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + CARS_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " team_name TEXT NOT NULL, " +
                    " team_points TEXT NOT NULL);";


    /**
     * Helper class that creates and manages provider's data repository
     */
    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DB_NAME,null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + CARS_TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }

    public AvaillableCarsProvider() {}

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        //create a writable db
        db = dbHelper.getWritableDatabase();
        return db != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(CARS_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case CARS:
                qb.setProjectionMap(CARS_PROJECTION_MAP);
                break;
            case CARS_ID:
                qb.appendWhere(_ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
                // do nothing
        }

        // sorting
        if (sortOrder == null || sortOrder.equals("")) {
            // sort using team names
            sortOrder = CAR_NAME;
        }

        // register to watch a content URI for changes
        Cursor c = qb.query(db, projection, selection, selectionArgs,
                null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch(uriMatcher.match(uri)) {
            // get all team records
            case CARS:
                return "vnd.android.cursor.dir/teams";
            // get a particular team
            case CARS_ID:
                return "vnd.android.cursor.item/teams";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        // add a new record
        long rowId = db.insert(CARS_TABLE_NAME, "", contentValues);

        // check if record was added successfully
        if (rowId > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case CARS:
                count = db.delete(CARS_TABLE_NAME, selection, selectionArgs);
                break;
            case CARS_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(CARS_TABLE_NAME, _ID + " = " + id +
                        (!TextUtils.isEmpty(selection) ?
                                " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues,
                      @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case CARS:
                count = db.update(CARS_TABLE_NAME, contentValues, selection, selectionArgs);
                break;
            case CARS_ID:
                db.update(CARS_TABLE_NAME, contentValues,
                        _ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ?
                                        " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}