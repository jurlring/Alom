package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    private ArrayList<MainData> arrayList;

    public MainAdapter(ArrayList<MainData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//리스트뷰가 생성될 때 생명주기

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        CustomViewHolder holder=new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.CustomViewHolder holder, int position) {//실제 추가될 때 생명주기
        holder.profile.setImageResource(arrayList.get(position).getProfile());//그 위치에서 profile을 가져와라
        holder.name.setText(arrayList.get(position).getName());
        holder.content.setText(arrayList.get(position).getContent());

        holder.itemView.setTag(position);//포지션 값 가져오기
        holder.content.setOnClickListener(new View.OnClickListener() {//클릭이 됐을 때
            @Override
            public void onClick(View v) {
                String curName=holder.name.getText().toString();//리사이클러뷰 중 클릭한 것의 이름을 가져옴
                Toast.makeText(v.getContext(),curName,Toast.LENGTH_SHORT).show();
                //어떤 데이터를 띄울건지->curName,몇초동안 ->Toast.LENGTH_SHORT
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(holder.getAdapterPosition());
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null!=arrayList ? arrayList.size():0);
    }

    public void remove(int position){
        try{
            arrayList.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView profile;
        protected TextView name;
        protected TextView content;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.profile=(ImageView) itemView.findViewById(R.id.profile);
            this.name=(TextView) itemView.findViewById(R.id.name);
            this.content=(TextView) itemView.findViewById(R.id.content);
        }
    }
}
