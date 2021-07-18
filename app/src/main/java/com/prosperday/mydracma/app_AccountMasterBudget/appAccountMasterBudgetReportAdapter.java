package com.prosperday.mydracma.app_AccountMasterBudget;

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

public class appAccountMasterBudgetReportAdapter extends RecyclerView.Adapter<appAccountMasterBudgetReportAdapter.ViewHolder> {

    public static String chooseFromUser;
    private Context context;
    private final List<appAccountMasterBudgetFirebaseModel> allListItems;

    public appAccountMasterBudgetReportAdapter(Context context, List<appAccountMasterBudgetFirebaseModel> TempList) {
        this.allListItems = TempList;
        this.context = context;
    }

    @NonNull
    @Override
    public appAccountMasterBudgetReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_account_master_budget_report_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull appAccountMasterBudgetReportAdapter.ViewHolder holder, int position) {
        appAccountMasterBudgetFirebaseModel recyclerDetails = allListItems.get(position);

        holder.itemYear.setText(recyclerDetails.getBudgetYear());
        holder.itemMonth.setText(recyclerDetails.getBudgetMonth());
        holder.itemTransaction.setText(recyclerDetails.getBudgetTransaction());
        holder.itemCategory.setText(recyclerDetails.getBudgetCategory());
        holder.itemSubCategory.setText(recyclerDetails.getBudgetSubCategory());
        holder.itemValuePlanned.setText(recyclerDetails.getBudgetValuePlanned());
        holder.itemValueRealized.setText(recyclerDetails.getBudgetValueRealized());
        holder.itemKey.setText(recyclerDetails.getBudgetKey());
    }

    @Override
    public int getItemCount() {
        return allListItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView itemYear;
        public final TextView itemMonth;
        public final TextView itemTransaction;
        public final TextView itemCategory;
        public final TextView itemSubCategory;
        public final TextView itemValuePlanned;
        public final TextView itemValueRealized;
        public final TextView itemKey;
        public final ImageButton btnChange;

        public ViewHolder(View itemView) {
            super(itemView);

            itemYear = itemView.findViewById(R.id.itemYear);
            itemMonth = itemView.findViewById(R.id.itemMonth);
            itemTransaction = itemView.findViewById(R.id.itemTransaction);
            itemCategory = itemView.findViewById(R.id.itemCategory);
            itemSubCategory = itemView.findViewById(R.id.itemSubCategory);
            itemValuePlanned = itemView.findViewById(R.id.itemValuePlanned);
            itemValueRealized = itemView.findViewById(R.id.itemValueRealized);
            itemKey = itemView.findViewById(R.id.itemKey);
            btnChange = itemView.findViewById(R.id.btnChange);
            
            context = itemView.getContext();

            btnChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appAccountMasterBudgetFirebaseModel recyclerDetails = allListItems.get(getAdapterPosition());
                    chooseFromUser = recyclerDetails.getBudgetKey();
                    Intent intent = new Intent(context, appAccountMasterBudgetScreenUpdateDelete.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
