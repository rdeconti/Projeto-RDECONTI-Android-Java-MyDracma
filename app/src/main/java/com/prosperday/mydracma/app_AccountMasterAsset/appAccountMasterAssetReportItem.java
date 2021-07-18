package com.prosperday.mydracma.app_AccountMasterAsset;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.prosperday.mydracma.R;

public class appAccountMasterAssetReportItem extends RecyclerView.ViewHolder {

    public final TextView itemTransaction;
    public final TextView itemCategory;
    public final TextView itemSubCategory;
    public final TextView itemName;
    public final TextView itemInitialValue;
    public final TextView itemKey;
    public final ImageButton btnChange;

    public appAccountMasterAssetReportItem(View itemView) {
        super(itemView);

        itemTransaction = itemView.findViewById(R.id.itemTransaction);
        itemCategory = itemView.findViewById(R.id.itemCategory);
        itemSubCategory = itemView.findViewById(R.id.itemSubCategory);
        itemName = itemView.findViewById(R.id.itemName);
        itemInitialValue = itemView.findViewById(R.id.itemInitialValue);
        itemKey = itemView.findViewById(R.id.itemKey);
        btnChange = itemView.findViewById(R.id.btnChange);
    }
}
