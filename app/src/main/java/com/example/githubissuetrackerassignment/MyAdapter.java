package com.example.githubissuetrackerassignment;
// MyAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<DataModel> dataList;

    public MyAdapter(List<DataModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataModel data = dataList.get(position);
        holder.titleTextView.setText(data.getTitle());
        holder.createdDateTextView.setText(data.getCreatedDate());
        holder.closedDateTextView.setText(data.getClosedDate());
        holder.userNameTextView.setText(data.getUserName());

        // Use Picasso library to load user images asynchronously
        Picasso.get().load(data.getUserImage()).into(holder.userImageView);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView createdDateTextView;
        TextView closedDateTextView;
        TextView userNameTextView;
        ImageView userImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            createdDateTextView = itemView.findViewById(R.id.createdDateTextView);
            closedDateTextView = itemView.findViewById(R.id.closedDateTextView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            userImageView = itemView.findViewById(R.id.userImageView);
        }
    }
}
