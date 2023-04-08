package com.tfg.ecoapp.Fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tfg.ecoapp.Fragments.placeholder.PlaceholderContent.PlaceholderItem;
import com.tfg.ecoapp.R;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyResiduosRecyclerViewAdapter extends RecyclerView.Adapter<MyResiduosRecyclerViewAdapter.ViewHolder> {

    private final List<PlaceholderItem> mValues;

    public MyResiduosRecyclerViewAdapter(List<PlaceholderItem> items) {
        mValues = items;
    }

    /*Esta metodo usa Databinding, y como aún no sé como se usa,
lo comento de momento y lo sustituyo por otro metodo que no de error*/
/*    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentRecycleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }*/

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recycle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public PlaceholderItem mItem;

        public ViewHolder(View view) {
            super(view);
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
