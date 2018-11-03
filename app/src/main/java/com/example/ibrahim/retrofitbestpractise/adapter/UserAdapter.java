package com.example.ibrahim.retrofitbestpractise.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ibrahim.retrofitbestpractise.R;
import com.example.ibrahim.retrofitbestpractise.arch.rest.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> users;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from
                (viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        User user = users.get(i);
        if (user != null) {
            viewHolder.id.setText("ID: " + String.valueOf(user.getId()));
            viewHolder.firstName.setText("FirstName: " + user.getFirstName());
            viewHolder.lastName.setText("LastName: " + user.getLastName());
            viewHolder.email.setText("Email: " + user.getEmail());
            viewHolder.age.setText("Age: " + String.valueOf(user.getAge()));
            viewHolder.companyId.setText("CompanyId: " + String.valueOf(user.getCompanyId()));
        }
    }

    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }

    public void setItems(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView firstName;
        TextView lastName;
        TextView email;
        TextView age;
        TextView companyId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.list_item_id);
            firstName = itemView.findViewById(R.id.list_item_first_name);
            lastName = itemView.findViewById(R.id.list_item_last_name);
            email = itemView.findViewById(R.id.list_item_email);
            age = itemView.findViewById(R.id.list_item_age);
            companyId = itemView.findViewById(R.id.list_item_company_id);

        }
    }
}
