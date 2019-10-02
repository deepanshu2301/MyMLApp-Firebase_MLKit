package com.example.mlkit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Album> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(final View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();

                    if(pos==0){
//                        Toast.makeText(mContext,"Click"+pos,Toast.LENGTH_LONG).show();
                        Intent i= new Intent(mContext,TextActivity.class);
                        mContext.startActivity(i);
                    }
                    if(pos==1){
//                        Toast.makeText(mContext,"Click"+pos,Toast.LENGTH_LONG).show();
                        Intent i= new Intent(mContext,BarcodeActivity.class);
                        mContext.startActivity(i);
                    }

                    if(pos==2){
//                        Toast.makeText(mContext,"Click"+pos,Toast.LENGTH_LONG).show();
                        Intent i= new Intent(mContext,FaceActivity.class);
                        mContext.startActivity(i);
                    }
                    if(pos==3){
//                        Toast.makeText(mContext,"Click"+pos,Toast.LENGTH_LONG).show();
                        Intent i= new Intent(mContext,ImageActivity.class);
                        mContext.startActivity(i);
                    }
                    if(pos==4){
//                        Toast.makeText(mContext,"Click"+pos,Toast.LENGTH_LONG).show();
                        Intent i= new Intent(mContext,LandmarkActivity.class);
                        mContext.startActivity(i);
                    }
                    if(pos==5){
//                        Toast.makeText(mContext,"Click"+pos,Toast.LENGTH_LONG).show();
                        Intent i= new Intent(mContext,CustomActivity.class);
                        mContext.startActivity(i);
                    }
                    if(pos==6){
//                        Toast.makeText(mContext,"Click"+pos,Toast.LENGTH_LONG).show();
                        Intent i= new Intent(mContext,LanguageActivity.class);
                        mContext.startActivity(i);
                    }
                    if(pos==7){
//                        Toast.makeText(mContext,"Click"+pos,Toast.LENGTH_LONG).show();
                        Intent i= new Intent(mContext,SmartReplyActivity.class);
                        mContext.startActivity(i);
                    }
                    if(pos==8){
//                        Toast.makeText(mContext,"Click"+pos,Toast.LENGTH_LONG).show();
                        Intent i= new Intent(mContext,TranslateActivity.class);
                        mContext.startActivity(i);
                    }
                    if(pos==9){
//                        Toast.makeText(mContext,"Click"+pos,Toast.LENGTH_LONG).show();
                        Intent i= new Intent(mContext,AutoMLActivity.class);
                        mContext.startActivity(i);
                    }
                }
            });
        }
    }


    public AlbumsAdapter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.title.setText(album.getName());

        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

//        holder.overflow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(holder.overflow);
//            }
//        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
