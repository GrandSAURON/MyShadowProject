/**
 * Copyright © 2020 - 2021 by RAS Technologies  All rights reserved.
 * Classes for working with list fragment
 * @author  Terminator
 * @version 210425_01
 */
package srcapp.myshadow.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import srcapp.myshadow.kbs.DataDefine;
import srcapp.myshadow.kbs.DocumentObject;
import srcapp.myshadow.kbs.TaskObject;
import srcapp.myshadow.kbs.store.DataStoreSQLite;
import srcapp.myshadow.service.Logger;

/**
 * Class for making list content fragment
 * @author  Terminator
 * @version  210425_01
 * @since    JDK 1.8
 */
public class RAPListHelpersFragment extends Fragment {
    private static final Logger sysLog = new Logger(MainActivity.class);
    private FragmentPagerAdapter mainManager;

    /**
     * Class constructor
     */
    public RAPListHelpersFragment(){

    }

    /**
     * Creating recyclerView and adapters for them
     * @author  Terminator
     * @param inflater -- layoutInflater object
     * @param container -- viewGroup object
     * @param savedInstanceState -- bundle object
     * @return recyclerView
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        MyContentAdapter adapter = new MyContentAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    /**
     * Class for making holder and items for it
     * @author  Terminator
     * @version  210425_01
     * @since    JDK 1.8
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView image;

        /**
         * Class constructor
         * @author  Terminator
         * @param inflater -- layoutInflater object
         * @param parent -- viewGroup object
         */
        public MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.rap_helpers_list, parent, false));
            name = (TextView) itemView.findViewById(R.id.helper_name);
            image = (ImageView) itemView.findViewById(R.id.imageView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                /**
                 * Opening new Detail Activity
                 * @author  Terminator
                 * @param v -- view object
                 */
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, GatesOpenActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

    /**
     * Class for creating adapter to add information to layout
     * @author  Terminator
     * @version  210425_01
     * @since    JDK 1.8
     */
    public class MyContentAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private final Logger sysLog = new Logger(ListContentFragment.class);

        /**
         * Class constructor
         * @author  Terminator
         */
        public MyContentAdapter() {

        }

        /**
         * Method for creating view holder
         * @author  Terminator
         * @param parent -- viewGroup object
         * @param viewType -- int var
         * @return
         */
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        /**
         * Adding information to the fields on layout
         * @author  Terminator
         * @param holder -- myViewHolder object
         * @param position -- int var
         */
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.name.setText("Привратник");
            holder.image.setImageResource(R.drawable.getesman);
        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }
}
