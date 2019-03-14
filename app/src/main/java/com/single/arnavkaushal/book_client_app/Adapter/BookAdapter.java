package com.single.arnavkaushal.book_client_app.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.single.arnavkaushal.book_client_app.Model.Book;
import com.single.arnavkaushal.book_client_app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {
    private static class ViewHolder
    {
        public ImageView ivBookCover;
        public TextView tvAuthor;
        public TextView tvTitle;
    }
    public BookAdapter(Context context, ArrayList<Book> aBooks)
    {
        super(context,0,aBooks);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final Book book = getItem(position);

        ViewHolder viewHolder;
//        if(convertView == null)
//        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_book,parent,false);
            viewHolder.ivBookCover = (ImageView)convertView.findViewById(R.id.ivBookCover);
            viewHolder.tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
            viewHolder.tvAuthor = (TextView)convertView.findViewById(R.id.tvAuthor);
            convertView.setTag(convertView);
//        }
//        else
//        {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }

        viewHolder.tvTitle.setText(book.getTitle());
        viewHolder.tvAuthor.setText(book.getAuthor());
        Picasso.with(getContext()).load(Uri.parse(book.getCoverURL())).error(R.drawable.no_image).into(viewHolder.ivBookCover);
        return convertView;
    }
}
