package cdfflint.pilot.cdflocaldatasave;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordHolder> {

    private List<SQLRecord> records = new ArrayList<>();

    @NonNull
    @Override
    public RecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.record_item, parent, false);
        return new RecordHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordHolder holder, int position) {
        SQLRecord currentRecord = records.get(position);
        holder.textViewTablet.setText(String.valueOf(currentRecord.getTabletNumber()));
        holder.textViewDate.setText(currentRecord.getCollectionDate());
        holder.textViewTime.setText(currentRecord.getCollectionTime());
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public void setRecords(List<SQLRecord> records) {
        this.records = records;
        notifyDataSetChanged();
    }

    class RecordHolder extends RecyclerView.ViewHolder{
        private TextView textViewTablet;
        private TextView textViewDate;
        private TextView textViewTime;

        public RecordHolder(View itemView) {
            super(itemView);
            textViewTablet = itemView.findViewById(R.id.text_view_tablet);
            textViewDate = itemView.findViewById(R.id.text_view_date);
            textViewTime = itemView.findViewById(R.id.text_view_time);
        }
    }
}
