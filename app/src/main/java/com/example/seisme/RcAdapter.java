package com.example.seisme;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seisme.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RcAdapter extends RecyclerView.Adapter<RcAdapter.ViewHolder> {
    JSONArray data;
    Context context;

    public RcAdapter(Context ct, JSONArray dt) {
        context = ct;
        data = dt;
    }

    public void setData(JSONArray newData) {
        data = newData;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rc_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pos) {

        try {
            JSONObject jo = data.getJSONObject(pos);
            holder.tv_name.setText(jo.getString("Location"));
            holder.tv_city.setText(jo.getString("City"));
            holder.tv_date.setText("Date : "+ jo.getString("Date"));
            holder.tv_depth.setText(jo.getString("Depth") + " km");
            holder.tv_ml.setText(jo.getString("Magnitude"));
            double temp = jo.getDouble("Magnitude");
            holder.tv_ml.setBackground(DetermineBgColor(temp));
            final String url = "https://www.google.com/maps/@"+ jo.getString("x") + ","+ jo.getString("y") +",12.29z?entry=ttu";

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    context.startActivity(browserIntent);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        Log.e("data size", "" + data.length());
        return data.length();
    }



    public Drawable DetermineBgColor(double temp)
    {
        if(temp < 1)
        {
            return context.getResources().getDrawable(R.drawable.ml_bg);
        }
        else if(temp < 2)
        {
            return context.getResources().getDrawable(R.drawable.ml_bg2);
        }
        else if(temp < 3)
        {
            return context.getResources().getDrawable(R.drawable.ml_bg3);
        }
        else if(temp < 4)
        {
            return context.getResources().getDrawable(R.drawable.ml_bg4);
        }
        else if(temp < 5)
        {
            return context.getResources().getDrawable(R.drawable.ml_bg5);
        }
        else if(temp < 6)
        {
            return context.getResources().getDrawable(R.drawable.ml_bg6);
        }
        else if(temp < 7)
        {
            return context.getResources().getDrawable(R.drawable.ml_bg7);
        }
        else if(temp < 8)
        {
            return context.getResources().getDrawable(R.drawable.ml_bg8);
        }
        else if(temp < 9)
        {
            return context.getResources().getDrawable(R.drawable.ml_bg9);
        }
        else
        {
            return context.getResources().getDrawable(R.drawable.ml_bg10);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_name;
        TextView tv_date;
        TextView tv_city;
        TextView tv_depth;
        TextView tv_ml;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.location);
            tv_date = itemView.findViewById(R.id.date);
            tv_depth = itemView.findViewById(R.id.depth);
            tv_ml = itemView.findViewById(R.id.ml);
           tv_city = itemView.findViewById(R.id.city);
        }
    }
}
