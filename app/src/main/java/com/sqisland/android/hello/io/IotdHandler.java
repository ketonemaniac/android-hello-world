package com.sqisland.android.hello.io;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Handler of XMLReader (from SAXParser). DefaultHandler is just an empty container.
 * Implement the methods to deal with tags and contents one by one.
 */

public class IotdHandler extends DefaultHandler {

    private String url = "http://rthk.hk/rthk/news/rss/e_expressnews_elocal.xml";
    private boolean inUrl = false;
    private boolean inItem = false;
    private boolean inTitle = false;
    private boolean inDescription = false;
    private boolean inDate = false;
    // Final contents. Should actually store somewhere else.
    private Bitmap image;
    private String title;
    private java.lang.StringBuffer description = new StringBuffer();
    private String date;


    public void processFeed() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            InputStream inputStream = new URL(url).openStream();
            reader.setContentHandler(this);             // important! missed in book
            reader.parse(new InputSource(inputStream));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap getBitmap(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            input.close();
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        inUrl = localName.equals("image");
        if(localName.startsWith("item")) {
            inItem = true;
        } else if(inItem) {
            inTitle = localName.startsWith("title");
            inDescription = localName.startsWith("description");
            inDate = localName.startsWith("pubDate");
        }
    }

    @Override
    public void characters(char ch[],  int start, int length) {
        String chars = new String(ch).substring(start, start + length);
        if(inUrl && image == null) image = getBitmap(chars);
        if(inTitle && title == null) title = chars;
        if(inDescription) description.append(chars);
        if(inDate && date == null) date = chars;
    }

    public Bitmap getImage() {
        return image;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description.toString();
    }
    public String getDate() {
        return date;
    }

}
