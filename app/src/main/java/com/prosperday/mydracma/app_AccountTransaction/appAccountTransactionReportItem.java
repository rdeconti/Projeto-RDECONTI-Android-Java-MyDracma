package com.prosperday.mydracma.app_AccountTransaction;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.prosperday.mydracma.R;

public class appAccountTransactionReportItem extends RecyclerView.ViewHolder {

    public final TextView itemTransaction;
    public final TextView itemCategory;
    public final TextView itemSubCategory;
    public final TextView itemDatePlanned;
    public final TextView itemDateRealized;
    public final TextView itemValuePlanned;
    public final TextView itemValueRealized;
    public final TextView itemKey;
    public final ImageButton btnChange;

    public appAccountTransactionReportItem(View itemView) {
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
    }
}
