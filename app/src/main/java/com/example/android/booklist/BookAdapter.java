package com.example.android.booklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Books> {


    public BookAdapter(Context context, List<Books> books) {
        super(context, 0,books);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.book_list_item,parent,false);
        }

        Books bookPosition = getItem(position);
        String title = bookPosition.getTitle();
        String author = bookPosition.getAuthor();

        TextView bookTitle = listItemView.findViewById(R.id.book_name);
        bookTitle.setText(title);

        TextView bookAuthor = listItemView.findViewById(R.id.book_author);
        bookAuthor.setText(author);

        Animation animation = null;
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.push_left_in);
        animation.setDuration(400);
        listItemView.startAnimation(animation);


        return listItemView;
    }
}
