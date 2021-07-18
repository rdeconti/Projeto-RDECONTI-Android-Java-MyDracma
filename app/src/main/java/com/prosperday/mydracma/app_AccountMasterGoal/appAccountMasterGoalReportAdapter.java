package com.prosperday.mydracma.app_AccountMasterGoal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.prosperday.mydracma.R;

import java.util.List;

public class appAccountMasterGoalReportAdapter extends RecyclerView.Adapter<appAccountMasterGoalReportAdapter.ViewHolder> {

    public static String chooseFromUser;
    private Context context;
    private final List<appAccountMasterGoalFirebaseModel> allListItems;

    public appAccountMasterGoalReportAdapter(Context context, List<appAccountMasterGoalFirebaseModel> TempList) {
        this.allListItems = TempList;
        this.context = context;
    }

    @NonNull
    @Override
    public appAccountMasterGoalReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_account_master_goal_report_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull appAccountMasterGoalReportAdapter.ViewHolder holder, int position) {
        appAccountMasterGoalFirebaseModel recyclerDetails = allListItems.get(position);

        holder.itemType.setText(recyclerDetails.getGoalType());
        holder.itemName.setText(recyclerDetails.getGoalName());
        holder.itemDatePlanned.setText(recyclerDetails.getGoalDatePlanned());
        holder.itemDateRealized.setText(recyclerDetails.getGoalDateRealized());
        holder.itemValuePlanned.setText(recyclerDetails.getGoalValuePlanned());
        holder.itemValueRealized.setText(recyclerDetails.getGoalValueRealized());
        holder.itemKey.setText(recyclerDetails.getGoalKey());
    }

    @Override
    public int getItemCount() {
        return allListItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView itemType;
        public final TextView itemName;
        public final TextView itemDatePlanned;
        public final TextView itemDateRealized;
        public final TextView itemValuePlanned;
        public final TextView itemValueRealized;
        public final TextView itemKey;
        public final ImageButton btnChange;

        public ViewHolder(View itemView) {
            super(itemView);

            itemType = itemView.findViewById(R.id.itemType);
            itemName = itemView.findViewById(R.id.itemName);
            itemDatePlanned = itemView.findViewById(R.id.itemDatePlanned);
            itemDateRealized = itemView.findViewById(R.id.itemDateRealized);
            itemValuePlanned = itemView.findViewById(R.id.itemValuePlanned);
            itemValueRealized = itemView.findViewById(R.id.itemValueRealized);
            itemKey = itemView.findViewById(R.id.itemKey);
            btnChange = itemView.findViewById(R.id.btnChange);
            
            context = itemView.getContext();

            btnChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appAccountMasterGoalFirebaseModel recyclerDetails = allListItems.get(getAdapterPosition());
                    chooseFromUser = recyclerDetails.getGoalKey();
                    Intent intent = new Intent(context, appAccountMasterGoalScreenUpdateDelete.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
