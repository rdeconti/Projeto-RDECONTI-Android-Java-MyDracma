package com.prosperday.mydracma.app_User;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.prosperday.mydracma.R;

public class appUserReportItem extends RecyclerView.ViewHolder {

    public final TextView itemName;
    public final TextView itemCode;
    public final TextView itemKey;
    public final ImageButton btnChange;

    public appUserReportItem(View itemView) {
        super(itemView);

        itemName = itemView.findViewById(R.id.itemName);
        itemCode = itemView.findViewById(R.id.itemCode);
        itemKey = itemView.findViewById(R.id.itemKey);
        btnChange = itemView.findViewById(R.id.btnChange);
    }
}
