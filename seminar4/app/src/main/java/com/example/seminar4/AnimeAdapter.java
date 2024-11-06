package com.example.seminar4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class AnimeAdapter extends BaseAdapter {
    private List<Anime> anime = null;
    private Context ctx;
    private int resursaLayout;

    public AnimeAdapter(List<Anime> anime, Context ctx, int resursaLayout) {
        this.anime = anime;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return anime.size();
    }

    @Override
    public Object getItem(int i) {
        return anime.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, viewGroup, false);

        TextView denumireTV = v.findViewById(R.id.denumireTV);
        TextView anTV = v.findViewById(R.id.anTV);
        TextView genreTV = v.findViewById(R.id.genreTV);
        CheckBox finishedCB = v.findViewById(R.id.finishedCB);
        TextView epTV = v.findViewById(R.id.epTV);

        Anime anime = (Anime)getItem(i);

        denumireTV.setText(anime.getDenumire());
        anTV.setText(""+anime.getAnAparitie());
        genreTV.setText(anime.getGenre());
        finishedCB.setChecked(anime.isFinished());
        epTV.setText(""+anime.getNrEpisoade());

        return v;
    }
}
