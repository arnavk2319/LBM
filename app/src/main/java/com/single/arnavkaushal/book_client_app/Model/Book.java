package com.single.arnavkaushal.book_client_app.Model;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Book
{
    private String openLibraryID;
    private String author;
    private String title;

    public String getOpenLibraryID()
    {
        return openLibraryID;
    }

    public String getAuthor()
    {
        return author;
    }
    public String getTitle()
    {
        return title;
    }

    public String getCoverURL()
    {
        return "http://covers.openlibrary.org/b/olid/" + openLibraryID + "-M.jpg?default=false";
    }

    public String getLargeCoverURL()
    {
        return "http://covers.openlibrary.org/b/olid/" + openLibraryID + "-L.jpg?default=false";
    }

    public static Book fromJSON(JSONObject jsonObject)
    {
        Book book = new Book();

        try {

            if(jsonObject.has("cover_edition_key"))
            {
                book.openLibraryID = jsonObject.getString("cover_edition_key");
            }
            else if(jsonObject.has("edition_key"))
            {
                final JSONArray ids = jsonObject.getJSONArray("edition_key");
                book.openLibraryID = ids.getString(0);
            }

            if(jsonObject.has("title_suggest"))
                book.title = jsonObject.getString("title_suggest");

            book.author = getAuthor(jsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return book;
    }

    private static String getAuthor(final JSONObject jsonObject)
    {
        try {
            final JSONArray authors = jsonObject.getJSONArray("author_name");
            int numAuthors = authors.length();
            final String[] authorNames = new String[numAuthors];
            for (int i = 0; i < numAuthors; i++)
                authorNames[i] = authors.getString(i);
            return TextUtils.join(",",authorNames);
        }catch(JSONException e)
        {
            return "";
        }
    }

    public static ArrayList<Book> fromJSONArray(JSONArray jsonArray)
    {
        ArrayList<Book> arrayList = new ArrayList<>(jsonArray.length());

        for(int i=0;i<jsonArray.length();i++)
        {
            JSONObject jsonObject = null;

            try
            {
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
            Book book = Book.fromJSON(jsonObject);
            if(book!=null)
                arrayList.add(book);
        }

        return arrayList;
    }

}
