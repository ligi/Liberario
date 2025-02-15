/*    Liberario
 *    Copyright (C) 2013-2014 Torsten Grote
 *
 *    This program is Free Software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as
 *    published by the Free Software Foundation, either version 3 of the
 *    License, or (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.grobox.liberario.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	public static final String DB_NAME = "liberario.db";
	private static final int DB_VERSION = 1;

	public static final String TABLE_FAV_LOCS  = "fav_locations";
	public static final String TABLE_FAV_TRIPS = "fav_trips";
	public static final String TABLE_HOME_LOCS = "home_locations";

	private static final String NETWORK = "network STRING NOT NULL";

	private static final String LOCATION =
		"type STRING NOT NULL, " +
		"id STRING, " +
		"lat INTEGER NOT NULL DEFAULT 0, " +
		"lon INTEGER NOT NULL DEFAULT 0, " +
		"place TEXT, " +
		"name TEXT";

	private static final String CREATE_TABLE_FAV_LOCS =
		"CREATE TABLE "	+ TABLE_FAV_LOCS + " (" +
			"_id INTEGER PRIMARY KEY, " +
			NETWORK + ", " +
			LOCATION + ", " +
			"from_count INTEGER NOT NULL DEFAULT 0, " +
			"to_count INTEGER NOT NULL DEFAULT 0" +
		" )";

	private static final String CREATE_TABLE_FAV_TRIPS =
		"CREATE TABLE "	+ TABLE_FAV_TRIPS + " (" +
			"_id INTEGER PRIMARY KEY, " +
			NETWORK + ", " +
			"from_loc INTEGER NOT NULL, " +
			"to_loc INTEGER NOT NULL, " +
			"count INTEGER NOT NULL DEFAULT 0" +
		" )";

	private static final String CREATE_TABLE_HOME_LOCS =
		"CREATE TABLE "	+ TABLE_HOME_LOCS + " (" +
			"_id INTEGER PRIMARY KEY, " +
			NETWORK + ", " +
			LOCATION + ", " +
			"UNIQUE(network)" +
		" )";

	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_FAV_LOCS);
		db.execSQL(CREATE_TABLE_FAV_TRIPS);
		db.execSQL(CREATE_TABLE_HOME_LOCS);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// NOOP
	}


}
