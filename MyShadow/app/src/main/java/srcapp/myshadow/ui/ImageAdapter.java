/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with image adapter
 * @author  Terminator
 * @version 210504_01
 */
package srcapp.myshadow.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.ArrayList;
import srcapp.myshadow.kbs.DocumentObject;
import srcapp.myshadow.service.Logger;

/**
 * Class for displaying a gallery of task photos
 * @author  Terminator
 * @version  210504_01
 * @since    JDK 1.8
 */
public class ImageAdapter extends BaseAdapter {
    private static final Logger sysLog = new Logger(ImageAdapter.class);
    private Context context;
    public ArrayList<String> images = new ArrayList<>();

    /**
     * Constructor of this class
     * @author  Terminator
     * @param c -- context for this class
     */
    public ImageAdapter(Context c, ArrayList imagestemp){
        context = c;
        DocumentObject doc;
        for(int i = 0; i < imagestemp.size(); i++){
            doc = (DocumentObject) imagestemp.get(i);
            sysLog.Error(doc.getFileName());
            String s = doc.getFileName();
            images.add(i, s);
        }
    }

    /**
     * Returns number of references in the array
     * @author  Terminator
     * @return images.length
     */
    @Override
    public int getCount() {
        return images.size();
    }

    /**
     * Returns the element by index
     * @author  Terminator
     * @param position -- current position
     * @return images[position]
     */
    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    /**
     * Returns id
     * @author  Terminator
     * @param position -- current position
     * @return 0
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Extracts images from the folder and adds them to the gallery
     * @author  Terminator
     * @param position -- current position
     * @param convertView -- view object
     * @param parent -- viewGroup object
     * @return ImageView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView ImageView = new ImageView(context);
        ImageView.setImageDrawable(Drawable.createFromPath((String) images.get(position)));
        ImageView.setScaleType(android.widget.ImageView.ScaleType.CENTER_INSIDE);
        ImageView.setLayoutParams(new ViewGroup.LayoutParams(240,240));
        return ImageView;
    }
}
