package com.prosperday.mydracma.app_AccountTransaction;

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

public class appAccountTransactionReportAdapter extends RecyclerView.Adapter<com.prosperday.mydracma.app_AccountTransaction.appAccountTransactionReportAdapter.ViewHolder> {

    public static String chooseFromUser;
    private Context context;
    private final List<appAccountTransactionFirebaseModel> allListItems;

    public appAccountTransactionReportAdapter(Context context, List<appAccountTransactionFirebaseModel> TempList) {
        this.allListItems = TempList;
        this.context = context;
    }

    @NonNull
    @Override
    public com.prosperday.mydracma.app_AccountTransaction.appAccountTransactionReportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_account_transaction_report_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.prosperday.mydracma.app_AccountTransaction.appAccountTransactionReportAdapter.ViewHolder holder, int position) {
        appAccountTransactionFirebaseModel recyclerDetails = allListItems.get(position);
        holder.itemTransaction.setText(recyclerDetails.getAccountTransactionTransaction());
        holder.itemCategory.setText(recyclerDetails.getAccountTransactionCategory());
        holder.itemSubCategory.setText(recyclerDetails.getAccountTransactionSubCategory());
        holder.itemDatePlanned.setText(recyclerDetails.getAccountTransactionDatePlanned());
        holder.itemDateRealized.setText(recyclerDetails.getAccountTransactionDateRealized());
        holder.itemValuePlanned.setText(recyclerDetails.getAccountTransactionValuePlanned());
        holder.itemValueRealized.setText(recyclerDetails.getAccountTransactionValueRealized());
        holder.itemKey.setText(recyclerDetails.getAccountTransactionKey());
    }

    @Override
    public int getItemCount() {
        return allListItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView itemTransaction;
        public final TextView itemCategory;
        public final TextView itemSubCategory;
        public final TextView itemDatePlanned;
        public final TextView itemDateRealized;
        public final TextView itemValuePlanned;
        public final TextView itemValueRealized;
        public final TextView itemKey;
        public final ImageButton btnChange;

        public ViewHolder(View itemView) {
            super(itemView);

            itemTransaction = itemView.findViewById(R.id.itemTransaction);
            itemCategory = itemView.findViewById(R.id.itemCategory);
            itemSubCategory = itemView.findViewById(R.id.itemSubCategory);
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
                    appAccountTransactionFirebaseModel recyclerDetails = allListItems.get(getAdapterPosition());
                    chooseFromUser = recyclerDetails.getAccountTransactionKey();
                    Intent intent = new Intent(context, appAccountTransactionScreenUpdateDelete.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
