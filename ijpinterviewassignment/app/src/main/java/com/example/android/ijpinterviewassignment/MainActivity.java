package com.example.android.ijpinterviewassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<seekModel> list;
    seekAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createList();
        createRecyclerView();
    }

    public void createList(){
        list=new ArrayList<>();
        list.add(new seekModel("1","100"));
    }

    public void createRecyclerView(){
        recyclerView=findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         adapter=new seekAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new seekAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                list.get(position);
            }


            @Override
            public void onSeekBarChanged(int position, String prog) {
                if(prog.charAt(0)!='0')
                {
                    String newend=list.get(position).getEnd();
                   int nextprog=Integer.parseInt(prog);
                    list.add(new seekModel(Integer.toString(nextprog),newend));
                    adapter.notifyItemInserted(position+1);
                    Log.d("ved", ""+position);
                }
                else if(position !=0 )
                {
                    list.remove(position);
                    adapter.notifyItemRemoved(position);
                }
                else
                {
                   list.clear();
                   adapter.notifyItemRangeRemoved(0,list.size());
                }

            }
        });
    }

    public void insertItem(int position)
    {
        adapter.notifyItemInserted(position);
    }

    public void removeItem(int position)
    {
        list.remove(position);
        adapter.notifyItemRemoved(position);

    }
}