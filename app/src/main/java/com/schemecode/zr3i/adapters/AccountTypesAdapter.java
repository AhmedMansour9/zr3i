package com.schemecode.zr3i.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.schemecode.zr3i.R;
import com.schemecode.zr3i.data.models.account_types.Datum;

import java.util.List;

public class AccountTypesAdapter extends RecyclerView.Adapter<AccountTypesAdapter.AccountsTypesViewHolder> {

    private Context context;
    private List<Datum> accountsTypesList;
    private OnItemClickListener mListener;


    public AccountTypesAdapter(List<Datum> accountsTypesList) {
        this.accountsTypesList = accountsTypesList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    public class AccountsTypesViewHolder extends RecyclerView.ViewHolder {
        private CheckBox validateItem;
        private TextView tvTypeOfAccount;
        private int indexOfAccountType ;
        public AccountsTypesViewHolder(@NonNull View itemView  , final OnItemClickListener listiner) {
            super(itemView);
            validateItem = itemView.findViewById(R.id.account_type_check_box);
            tvTypeOfAccount = itemView.findViewById(R.id.account_type_text_view);
            validateItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listiner != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listiner.onItemClick(position);
                            for (int i = 0; i < accountsTypesList.size(); i++) {
                                if (position == i) {
                                    accountsTypesList.get(i).setSelected(true);
                                } else {
                                    accountsTypesList.get(i).setSelected(false);
                                }
                            }
                        }
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }

    @NonNull
    @Override
    public AccountsTypesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_type_layout, parent, false);
        AccountsTypesViewHolder accountsTypesViewHolder = new AccountsTypesViewHolder(view , mListener);
        this.context = parent.getContext();
        return accountsTypesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AccountsTypesViewHolder holder, int position) {
        Datum accountType = accountsTypesList.get(position);
        holder.tvTypeOfAccount.setText(accountType.getTitle());
        if (accountType.getSelected() == true) {
            holder.validateItem.setChecked(true);
        } else {
            holder.validateItem.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return accountsTypesList.size();
    }

}
