package com.prosperday.mydracma.app_AccountMasterBudget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.prosperday.mydracma.R;

public class appAccountMasterBudgetReportItem extends RecyclerView.ViewHolder {

    public final TextView itemYear;
    public final TextView itemMonth;
    public final TextView itemTransaction;
    public final TextView itemCategory;
    public final TextView itemSubCategory;
    public final TextView itemValuePlanned;
    public final TextView itemValueRealized;
    public final TextView itemKey;
    public final ImageButton btnChange;

    public appAccountMasterBudgetReportItem(View itemView) {
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
    }
}
