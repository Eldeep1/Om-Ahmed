package com.depogramming.omahmed.presentation.search.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.meals.model.countries.CountryModel;

import java.util.List;

public class CountriesDropDownAdapter extends RecyclerView.Adapter<CountriesDropDownAdapter.ViewHolder>{
    List<CountryModel> countryModels;
    private OnDropDownItemClickListener listener;

    public CountriesDropDownAdapter(OnDropDownItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drop_down_list_item, parent, false);
        return new CountriesDropDownAdapter.ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CountryModel countries = countryModels.get(position);
        holder.bind(countries);
    }

    @Override
    public int getItemCount() {
        return countryModels==null?0:countryModels.size();
    }
    public void setCountries(List<CountryModel> categories){
        this.countryModels=categories;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView countryImage;
        private final TextView countryString;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryImage=itemView.findViewById(R.id.dropDownImage);
            countryString=itemView.findViewById(R.id.dropDownText);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                listener.onDropdownItemClickListener(countryModels.get(position));
            });

        }
        public void bind(CountryModel country) {
            countryString.setText(country.getCountryName());
            Glide.with(itemView).load(country.getFlagLink()).into(countryImage);
        }
    }
}
