package com.jc.android.component.pullrefresh;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jc.android.component.pullrefresh.databinding.RowDemoBinding;

import java.util.List;

/**
 * Created by tconan on 16/7/27.
 */
public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoHolder> {


    private final LayoutInflater layoutInflater;
    private final List<DemoModel> list;

    public DemoAdapter(@NonNull List<DemoModel> list, @NonNull Context context) {
        this.list = list;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public DemoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowDemoBinding rowUserBinding = DataBindingUtil.inflate(layoutInflater, R.layout.row_demo, parent, false);
        return new DemoHolder(rowUserBinding);
    }

    @Override
    public void onBindViewHolder(DemoHolder holder, int position) {
        if (0<=position && position<list.size()) {
            holder.binding(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static public class DemoHolder extends RecyclerView.ViewHolder {
        private RowDemoBinding binding;

        public DemoHolder(RowDemoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void binding(DemoModel model) {
            this.binding.setModel(model);
        }

    }
}
