package com.prosperday.mydracma.app_User;

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

public class appUserReportAdapter extends RecyclerView.Adapter<com.prosperday.mydracma.app_User.appUserReportAdapter.ViewHolder> {

    public static String chooseFromUser;
    private Context context;
    private final List<appUserFirebaseModel> allListItems;

    public appUserReportAdapter(Context context, List<appUserFirebaseModel> TempList) {
        this.allListItems = TempList;
        this.context = context;
    }

    @NonNull
    @Override
    public com.prosperday.mydracma.app_User.appUserReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_user_report_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.prosperday.mydracma.app_User.appUserReportAdapter.ViewHolder holder, int position) {
        appUserFirebaseModel MasterDetails = allListItems.get(position);
        holder.itemName.setText(MasterDetails.getUserDataName());
        holder.itemCode.setText(MasterDetails.getUserDataCode());
        holder.itemKey.setText(MasterDetails.getUserDataKey());
    }

    @Override
    public int getItemCount() {
        return allListItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView itemName;
        public final TextView itemCode;
        public final TextView itemKey;
        public final ImageButton btnChange;

        public ViewHolder(View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.itemName);
            itemCode = itemView.findViewById(R.id.itemCode);
            itemKey = itemView.findViewById(R.id.itemKey);
            btnChange = itemView.findViewById(R.id.btnChange);
            context = itemView.getContext();

            btnChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appUserFirebaseModel recyclerDetails = allListItems.get(getAdapterPosition());
                    chooseFromUser = recyclerDetails.getUserDataKey();
                    Intent intent = new Intent(context, appUserScreenUpdateDelete.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
