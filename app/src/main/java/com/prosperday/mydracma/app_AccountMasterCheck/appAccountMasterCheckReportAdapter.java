package com.prosperday.mydracma.app_AccountMasterCheck;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.prosperday.mydracma.R;

import java.util.List;

public class appAccountMasterCheckReportAdapter extends RecyclerView.Adapter<appAccountMasterCheckReportAdapter.ViewHolder> {

    public static String chooseFromUser;
    private Context context;
    private final List<appAccountMasterCheckFirebaseModel> allListItems;

    public appAccountMasterCheckReportAdapter(Context context, List<appAccountMasterCheckFirebaseModel> TempList) {
        this.allListItems = TempList;
        this.context = context;
    }

    @NonNull
    @Override
    public appAccountMasterCheckReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_account_master_report_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull appAccountMasterCheckReportAdapter.ViewHolder holder, int position) {
        appAccountMasterCheckFirebaseModel recyclerDetails = allListItems.get(position);
        holder.itemTransaction.setText(recyclerDetails.getAccountMasterTransaction());
        holder.itemCategory.setText(recyclerDetails.getAccountMasterCategory());
        holder.itemSubCategory.setText(recyclerDetails.getAccountMasterSubCategory());
        holder.itemName.setText(recyclerDetails.getAccountMasterName());
        holder.itemInitialValue.setText(recyclerDetails.getAccountMasterInitialValue());
        holder.itemKey.setText(recyclerDetails.getAccountMasterKey());

        switch (recyclerDetails.getAccountMasterCategory()){
            case "Asset":
                holder.btnChange.setBackgroundColor(Color.CYAN);
                break;
            case "Charity / Gifts":
                holder.btnChange.setBackgroundColor(Color.GRAY);
                break;
            case "Education":
                holder.btnChange.setBackgroundColor(Color.YELLOW);
                break;
            case "Entertainment":
                holder.btnChange.setBackgroundColor(Color.MAGENTA);
                break;
            case "Feeding":
                holder.btnChange.setBackgroundColor(Color.GREEN);
                break;
            case "Financial":
                holder.btnChange.setBackgroundColor(Color.RED);
                break;
            case "Health":
                holder.btnChange.setBackgroundColor(Color.DKGRAY);
                break;
            case "Housing":
                holder.btnChange.setBackgroundColor(Color.BLUE);
                break;
            case "Income":
                holder.btnChange.setBackgroundColor(Color.MAGENTA);
                break;
            case "Investment":
                holder.btnChange.setBackgroundColor(Color.BLUE);
                break;
            case "Mobility":
                holder.btnChange.setBackgroundColor(Color.RED);
                break;
            case "Personal Care":
                holder.btnChange.setBackgroundColor(Color.MAGENTA);
                break;
            case "Pet":
                holder.btnChange.setBackgroundColor(Color.GRAY);
                break;
            case "Subscription":
                holder.btnChange.setBackgroundColor(Color.GREEN);
                break;
            case "Transportation":
                holder.btnChange.setBackgroundColor(Color.YELLOW);
                break;
            default:
                holder.btnChange.setBackgroundColor(Color.WHITE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return allListItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView itemTransaction;
        public final TextView itemClass;
        public final TextView itemType;
        public final TextView itemCategory;
        public final TextView itemSubCategory;
        public final TextView itemName;
        public final TextView itemInitialValue;
        public final TextView itemKey;
        public final ImageButton btnChange;

        public ViewHolder(final View itemView) {
            super(itemView);

            itemTransaction = itemView.findViewById(R.id.itemTransaction);
            itemClass = itemView.findViewById(R.id.itemClass);
            itemType = itemView.findViewById(R.id.itemType);
            itemCategory = itemView.findViewById(R.id.itemCategory);
            itemSubCategory = itemView.findViewById(R.id.itemSubCategory);
            itemName = itemView.findViewById(R.id.itemName);
            itemInitialValue = itemView.findViewById(R.id.itemInitialValue);
            itemKey = itemView.findViewById(R.id.itemKey);
            btnChange = itemView.findViewById(R.id.btnChange);

            context = itemView.getContext();

            btnChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appAccountMasterCheckFirebaseModel recyclerDetails = allListItems.get(getAdapterPosition());
                    chooseFromUser = recyclerDetails.getAccountMasterKey();
                    Intent intent = new Intent(context, appAccountMasterCheckScreenUpdateDelete.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
