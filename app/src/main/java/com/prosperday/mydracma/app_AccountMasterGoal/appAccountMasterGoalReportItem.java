package com.prosperday.mydracma.app_AccountMasterGoal;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.prosperday.mydracma.R;

public class appAccountMasterGoalReportItem extends RecyclerView.ViewHolder {

    public final TextView itemType;
    public final TextView itemName;
    public final TextView itemDatePlanned;
    public final TextView itemDateRealized;
    public final TextView itemValuePlanned;
    public final TextView itemValueRealized;
    public final TextView itemKey;
    public final ImageButton btnChange;

    public appAccountMasterGoalReportItem(View itemView) {
        super(itemView);

        itemType = itemView.findViewById(R.id.itemType);
        itemName = itemView.findViewById(R.id.itemName);
        itemDatePlanned = itemView.findViewById(R.id.itemDatePlanned);
        itemDateRealized = itemView.findViewById(R.id.itemDateRealized);
        itemValuePlanned = itemView.findViewById(R.id.itemValuePlanned);
        itemValueRealized = itemView.findViewById(R.id.itemValueRealized);
        itemKey = itemView.findViewById(R.id.itemKey);
        btnChange = itemView.findViewById(R.id.btnChange);
    }
}
