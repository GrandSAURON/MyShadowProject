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
public class ListContentFragment extends Fragment {
    private static final Logger sysLog = new Logger(MainActivity.class);
    private FragmentPagerAdapter mainManager;

    /**
     * Class constructor
     * @param manager
     */
    public ListContentFragment(FragmentPagerAdapter manager){
        mainManager = manager;
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
        public ImageView image;
        public TextView name;
        public TextView description;

        /**
         * Class constructor
         * @author  Terminator
         * @param inflater -- layoutInflater object
         * @param parent -- viewGroup object
         */
        public MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_list, parent, false));
            image = (ImageView) itemView.findViewById(R.id.list_avatar);
            name = (TextView) itemView.findViewById(R.id.helper_name);
            description = (TextView) itemView.findViewById(R.id.description);
            itemView.setOnClickListener(new View.OnClickListener() {

                /**
                 * Opening new Detail Activity
                 * @author  Terminator
                 * @param v -- view object
                 */
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    int iPos = getAdapterPosition();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("position", iPos );
                    context.startActivity(intent);
                    sysLog.Error("TGHFHHCHSDJHCVJHSDUHVJUVDU");
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
        private List<Integer> mImageResources = new ArrayList<>();
        private Random mRandom = new Random();
        private ArrayList data;
        private ArrayList images = null;

        /**
         * Class constructor
         * @author  Terminator
         */
        public MyContentAdapter() {
            mImageResources.add(R.drawable.i1);
            mImageResources.add(R.drawable.i3);
            mImageResources.add(R.drawable.i2);
            mImageResources.add(R.drawable.i5);
            setHasStableIds(true);
            DataStoreSQLite ds = DataStoreSQLite.getCurDataStore();
            this.data = ds.getData(DataDefine.I_TASK_OBJ);
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
            //int[] images1 = {R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4,
            // R.drawable.i5, R.drawable.i6};
           // Random rand = new Random();
            //holder.image.setImageResource([rand.nextInt(images1.length)]);
            TaskObject task = (TaskObject) data.get(position);

            holder.name.setText(task.getName());
            holder.description.setText(task.getNote());
            images = task.getTaskDocList();
            if (images != null && images.size() > 0) {
                DocumentObject doc;
                doc = (DocumentObject) images.get(0);
                holder.image.setImageDrawable(Drawable.createFromPath((String) doc.getFileName()));
               // holder.image.setImageResource(R.drawable.i1);
            }
        }

        /**
         * Count size of data array
         * @author  Terminator
         * @return data.size()
         */
        @Override
        public int getItemCount() {
            return data.size();
        }

        /**
         * Getting item id
         * @param position -- current position
         * @return task.getId()
         */
        @Override
        public long getItemId(int position) {
            TaskObject task = (TaskObject) data.get(position);
            return task.getId();
        }
    }
}
