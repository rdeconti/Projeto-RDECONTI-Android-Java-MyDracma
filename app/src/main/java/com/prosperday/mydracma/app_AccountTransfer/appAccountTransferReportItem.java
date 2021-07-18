package com.prosperday.mydracma.app_AccountTransfer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.prosperday.mydracma.R;

public class appAccountTransferReportItem extends RecyclerView.ViewHolder {

    public final TextView itemAccountFrom;
    public final TextView itemAccountTo;
    public final TextView itemDatePlanned;
    public final TextView itemDateRealized;
    public final TextView itemValuePlanned;
    public final TextView itemValueRealized;
    public final TextView itemKey;
    public final ImageButton btnChange;

    public appAccountTransferReportItem(View itemView) {
        super(itemView);

        itemAccountFrom = itemView.findViewById(R.id.itemAccountFrom);
        itemAccountTo = itemView.findViewById(R.id.itemAccountTo);
        itemDatePlanned = itemView.findViewById(R.id.itemDatePlanned);
        itemDateRealized = itemView.findViewById(R.id.itemDateRealized);
        itemValuePlanned = itemView.findViewById(R.id.itemValuePlanned);
        itemValueRealized = itemView.findViewById(R.id.itemValueRealized);
        itemKey = itemView.findViewById(R.id.itemKey);
        btnChange = itemView.findViewById(R.id.btnChange);
    }
}
