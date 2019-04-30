package com.example.mobileproject01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    public static String DB_NAME = "database.db";
    public static String POST_TABLE_NAME = "post";
    public static String COMMENT_TABLE_NAME = "comment";


    public DatabaseManager(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + POST_TABLE_NAME + " (userId int, id int, title varchar(50), body varchar(100), primary key (id));");
        db.execSQL("create table " + COMMENT_TABLE_NAME + " (postId int, id int, name varchar(50), email varchar(50), body varchar(100), primary key (id));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertPosts(ArrayList<Post> posts) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("delete from " + POST_TABLE_NAME + ";");
        ContentValues contentValues = new ContentValues();
        for (Post p:
             posts) {
            contentValues.put("userId", p.userId);
            contentValues.put("id", p.id);
            contentValues.put("title", p.title);
            contentValues.put("body", p.body);
            database.insert(POST_TABLE_NAME, null, contentValues);
            contentValues.clear();
        }

    }

    public void insertComments(ArrayList<Comment> comments) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("delete from " + COMMENT_TABLE_NAME + ";");
        ContentValues contentValues = new ContentValues();
        for (Comment c:
             comments) {
            contentValues.put("postId", c.postId);
            contentValues.put("id", c.id);
            contentValues.put("name", c.name);
            contentValues.put("email", c.email);
            contentValues.put("body", c.body);
            database.insert(COMMENT_TABLE_NAME, null, contentValues);
            contentValues.clear();
        }
    }

    public ArrayList<Post> getPosts() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor result = database.rawQuery("select * from " + POST_TABLE_NAME + ";", null);
        ArrayList<Post> posts = new ArrayList<Post>();
        for (result.moveToFirst(); !result.isAfterLast(); result.moveToNext()) {
            Post post = new Post();
            post.setUserId(result.getInt(0));
            post.setId(result.getInt(1));
            post.setTitle(result.getString(2));
            post.setBody(result.getString(3));
            posts.add(post);
        }
        return posts;
    }

    public ArrayList<Comment> getComments(int id) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor result = database.rawQuery("select * from " + COMMENT_TABLE_NAME + "where postId = "+id+";", null);
        ArrayList<Comment> comments = new ArrayList<Comment>();
        for (result.moveToFirst(); !result.isAfterLast(); result.moveToNext()) {
            Comment comment = new Comment();
            comment.setPostId(result.getInt(0));
            comment.setId(result.getInt(1));
            comment.setName(result.getString(2));
            comment.setEmail(result.getString(3));
            comment.setBody(result.getString(4));
            comments.add(comment);
        }
        return comments;
    }


}
