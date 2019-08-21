package com.example.android.booklist;

public class Books {

  private String mbookTitle;
  private String mAuthor;
  private String mUrl;

 public Books(String title,String author,String url){

      mbookTitle = title;
      mAuthor = author;
      mUrl = url;
 }

  public String getTitle(){ return  mbookTitle; }
  public String getAuthor() { return mAuthor; }
  public String getUrl() { return mUrl; }

}