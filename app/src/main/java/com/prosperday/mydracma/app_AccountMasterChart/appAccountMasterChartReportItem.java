package com.prosperday.mydracma.app_AccountMasterChart;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.prosperday.mydracma.R;

import java.util.List;

public class appAccountMasterChartReportItem extends RecyclerView.ViewHolder {

    public final TextView itemTransaction;
    public final TextView itemClass;
    public final TextView itemType;
    public final TextView itemCategory;
    public final TextView itemSubCategory;
    public final TextView itemKey;
    public final ImageButton btnChange;

    public appAccountMasterChartReportItem(View itemView, List<Task> task) {
        super(itemView);

        itemTransaction = itemView.findViewById(R.id.itemTransaction);
        itemClass = itemView.findViewById(R.id.itemClass);
        itemType = itemView.findViewById(R.id.itemType);
        itemCategory = itemView.findViewById(R.id.itemCategory);
        itemSubCategory = itemView.findViewById(R.id.itemSubCategory);
        itemKey = itemView.findViewById(R.id.itemKey);
        btnChange = itemView.findViewById(R.id.btnChange);
    }
}
