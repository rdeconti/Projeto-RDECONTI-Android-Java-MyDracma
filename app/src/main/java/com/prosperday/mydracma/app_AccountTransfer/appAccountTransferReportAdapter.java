package com.prosperday.mydracma.app_AccountTransfer;

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

public class appAccountTransferReportAdapter extends RecyclerView.Adapter<com.prosperday.mydracma.app_AccountTransfer.appAccountTransferReportAdapter.ViewHolder> {

    public static String chooseFromUser;
    private Context context;

    private final List<appAccountTransferFirebaseModel> allListItems;

    public appAccountTransferReportAdapter(Context context, List<appAccountTransferFirebaseModel> TempList) {
        this.allListItems = TempList;
        this.context = context;
    }

    @NonNull
    @Override
    public com.prosperday.mydracma.app_AccountTransfer.appAccountTransferReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_account_transfer_report_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.prosperday.mydracma.app_AccountTransfer.appAccountTransferReportAdapter.ViewHolder holder, int position) {
        appAccountTransferFirebaseModel recyclerDetails = allListItems.get(position);
        holder.itemAccountFrom.setText(recyclerDetails.getAccountTransferAccountFrom());
        holder.itemAccountTo.setText(recyclerDetails.getAccountTransferAccountTo());
        holder.itemDatePlanned.setText(recyclerDetails.getAccountTransferDatePlanned());
        holder.itemDateRealized.setText(recyclerDetails.getAccountTransferDateRealized());
        holder.itemValuePlanned.setText(recyclerDetails.getAccountTransferValuePlanned());
        holder.itemValueRealized.setText(recyclerDetails.getAccountTransferValueRealized());
        holder.itemKey.setText(recyclerDetails.getAccountTransferKey());
    }

    @Override
    public int getItemCount() {
        return allListItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView itemAccountFrom;
        public final TextView itemAccountTo;
        public final TextView itemDatePlanned;
        public final TextView itemDateRealized;
        public final TextView itemValuePlanned;
        public final TextView itemValueRealized;
        public final TextView itemKey;
        public final ImageButton btnChange;

        public ViewHolder(View itemView) {
            super(itemView);

            itemAccountFrom = itemView.findViewById(R.id.itemAccountFrom);
            itemAccountTo = itemView.findViewById(R.id.itemAccountTo);
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
                    appAccountTransferFirebaseModel recyclerDetails = allListItems.get(getAdapterPosition());
                    chooseFromUser = recyclerDetails.getAccountTransferKey();
                    Intent intent = new Intent(context, appAccountTransferScreenUpdateDelete.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
