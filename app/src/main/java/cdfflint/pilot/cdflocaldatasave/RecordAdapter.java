package cdfflint.pilot.cdflocaldatasave;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class RecordAdapter extends ListAdapter<SQLRecord, RecordAdapter.RecordHolder> {
    private OnItemClickListener listener;

    public RecordAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<SQLRecord> DIFF_CALLBACK = new DiffUtil.ItemCallback<SQLRecord>() {
        @Override
        public boolean areItemsTheSame(@NonNull SQLRecord oldItem, @NonNull SQLRecord newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull SQLRecord oldItem, @NonNull SQLRecord newItem) {
            return oldItem.getCollectionDate().equals(newItem.getCollectionDate())
                    && oldItem.getCollectionTime().equals(newItem.getCollectionTime()) &&
                    oldItem.getTabletNumber() == newItem.getTabletNumber() &&
                    oldItem.getTimeRunning().equals(newItem.getTimeRunning()) &&
                    oldItem.getWaterTemp().equals(newItem.getWaterTemp()) &&
                    oldItem.getNormalUse().equals(newItem.getNormalUse()) &&
                    oldItem.getWaterColor().equals(newItem.getWaterColor()) &&
                    oldItem.getWaterSmell().equals(newItem.getWaterSmell()) &&
                    oldItem.getWaterTaste().equals(newItem.getWaterTaste()) &&
                    oldItem.getRottenEgg().equals(newItem.getRottenEgg()) &&
                    oldItem.getSedimentPresent().equals(newItem.getSedimentPresent()) &&
                    oldItem.getSedimentFeathery().equals(newItem.getSedimentFeathery()) &&
                    oldItem.getBacteriaResult().equals(newItem.getBacteriaResult()) &&
                    oldItem.getHardnessPpm().equals(newItem.getHardnessPpm()) &&
                    oldItem.getChlorinePpm().equals(newItem.getChlorinePpm()) &&
                    oldItem.getAlkalinityPpm().equals(newItem.getAlkalinityPpm()) &&
                    oldItem.getCopperPpm().equals(newItem.getCopperPpm()) &&
                    oldItem.getIronPpm().equals(newItem.getIronPpm()) &&
                    oldItem.getPhValue().equals(newItem.getPhValue()) &&
                    oldItem.getPesticideResult().equals(newItem.getPesticideResult()) &&
                    oldItem.getLeadResult().equals(newItem.getLeadResult()) &&
                    oldItem.getNitriteResult().equals(newItem.getNitriteResult()) &&
                    oldItem.getNitrateResult().equals(newItem.getNitrateResult());
        }
    };

    @NonNull
    @Override
    public RecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.record_item, parent, false);
        return new RecordHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordHolder holder, int position) {
        SQLRecord currentRecord = getItem(position);
        holder.textViewTablet.setText(String.valueOf(currentRecord.getTabletNumber()));
        holder.textViewDate.setText(currentRecord.getCollectionDate());
        holder.textViewTime.setText(currentRecord.getCollectionTime());
    }

    public SQLRecord getRecordAt(int position) {
        return getItem(position);
    }

    class RecordHolder extends RecyclerView.ViewHolder {
        private TextView textViewTablet;
        private TextView textViewDate;
        private TextView textViewTime;

        public RecordHolder(View itemView) {
            super(itemView);
            textViewTablet = itemView.findViewById(R.id.text_view_tablet);
            textViewDate = itemView.findViewById(R.id.text_view_date);
            textViewTime = itemView.findViewById(R.id.text_view_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(SQLRecord record);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
