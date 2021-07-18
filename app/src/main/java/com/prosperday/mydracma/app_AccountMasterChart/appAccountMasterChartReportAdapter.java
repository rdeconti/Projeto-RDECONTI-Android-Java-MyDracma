package com.prosperday.mydracma.app_AccountMasterChart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.prosperday.mydracma.R;

import java.util.List;

import static com.prosperday.mydracma.app_Authorization.appAuthorizationLogIn.userLanguage;

public class appAccountMasterChartReportAdapter extends RecyclerView.Adapter<appAccountMasterChartReportAdapter.ViewHolder> {

    public static String chooseFromUser;

    private Context context;

    private final List<appAccountMasterChartFirebaseModel> allListItems;

    public appAccountMasterChartReportAdapter(Context context, List<appAccountMasterChartFirebaseModel> TempList) {
        this.allListItems = TempList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_account_master_chart_report_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        appAccountMasterChartFirebaseModel recyclerDetails = allListItems.get(position);

        switch (userLanguage) {
            case "EN":
                holder.itemTransaction.setText(recyclerDetails.getAccountChartTransactionEN());
                holder.itemClass.setText(recyclerDetails.getAccountChartClassEN());
                holder.itemType.setText(recyclerDetails.getAccountChartTypeEN());
                holder.itemCategory.setText(recyclerDetails.getAccountChartCategoryEN());
                holder.itemSubCategory.setText(recyclerDetails.getAccountChartSubCategoryEN());
            case "SP":
                holder.itemTransaction.setText(recyclerDetails.getAccountChartTransactionSP());
                holder.itemClass.setText(recyclerDetails.getAccountChartClassSP());
                holder.itemType.setText(recyclerDetails.getAccountChartTypeSP());
                holder.itemCategory.setText(recyclerDetails.getAccountChartCategorySP());
                holder.itemSubCategory.setText(recyclerDetails.getAccountChartSubCategorySP());
            case "PT":
                holder.itemTransaction.setText(recyclerDetails.getAccountChartTransactionPT());
                holder.itemClass.setText(recyclerDetails.getAccountChartClassPT());
                holder.itemType.setText(recyclerDetails.getAccountChartTypePT());
                holder.itemCategory.setText(recyclerDetails.getAccountChartCategoryPT());
                holder.itemSubCategory.setText(recyclerDetails.getAccountChartSubCategoryPT());
            default:
                holder.itemTransaction.setText(recyclerDetails.getAccountChartTransactionEN());
                holder.itemClass.setText(recyclerDetails.getAccountChartClassEN());
                holder.itemType.setText(recyclerDetails.getAccountChartTypeEN());
                holder.itemCategory.setText(recyclerDetails.getAccountChartCategoryEN());
                holder.itemSubCategory.setText(recyclerDetails.getAccountChartSubCategoryEN());
        }

        holder.itemKey.setText(recyclerDetails.getAccountChartKey());

        switch (recyclerDetails.getAccountChartCategoryEN()){
            case "Asset":
                holder.btnChange.setBackgroundColor(Color.CYAN);
                break;
            case "Charity / Gifts":
                holder.btnChange.setBackgroundColor(Color.GRAY);
                break;
            case "Education":
                holder.btnChange.setBackgroundColor(Color.YELLOW);
                break;
            case "Entertainment":
                holder.btnChange.setBackgroundColor(Color.MAGENTA);
                break;
            case "Feeding":
                holder.btnChange.setBackgroundColor(Color.GREEN);
                break;
            case "Financial":
                holder.btnChange.setBackgroundColor(Color.RED);
                break;
            case "Health":
                holder.btnChange.setBackgroundColor(Color.DKGRAY);
                break;
            case "Housing":
                holder.btnChange.setBackgroundColor(Color.BLUE);
                break;
            case "Income":
                holder.btnChange.setBackgroundColor(Color.MAGENTA);
                break;
            case "Investment":
                holder.btnChange.setBackgroundColor(Color.BLUE);
                break;
            case "Mobility":
                holder.btnChange.setBackgroundColor(Color.RED);
                break;
            case "Personal Care":
                holder.btnChange.setBackgroundColor(Color.MAGENTA);
                break;
            case "Pet":
                holder.btnChange.setBackgroundColor(Color.GRAY);
                break;
            case "Subscription":
                holder.btnChange.setBackgroundColor(Color.GREEN);
                break;
            case "Transportation":
                holder.btnChange.setBackgroundColor(Color.YELLOW);
                break;
            default:
                holder.btnChange.setBackgroundColor(Color.WHITE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return allListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView itemTransaction;
        public final TextView itemClass;
        public final TextView itemType;
        public final TextView itemCategory;
        public final TextView itemSubCategory;
        public final TextView itemKey;
        public final ImageButton btnChange;

        public ViewHolder(final View itemView) {
            super(itemView);

            itemTransaction = itemView.findViewById(R.id.itemTransaction);
            itemClass = itemView.findViewById(R.id.itemClass);
            itemType = itemView.findViewById(R.id.itemType);
            itemCategory = itemView.findViewById(R.id.itemCategory);
            itemSubCategory = itemView.findViewById(R.id.itemSubCategory);
            itemKey = itemView.findViewById(R.id.itemKey);
            btnChange = itemView.findViewById(R.id.btnChange);

            context = itemView.getContext();

            btnChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appAccountMasterChartFirebaseModel recyclerDetails = allListItems.get(getAdapterPosition());
                    chooseFromUser = recyclerDetails.getAccountChartKey();
                    Intent intent = new Intent(context, appAccountMasterChartScreenUpdateDelete.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}