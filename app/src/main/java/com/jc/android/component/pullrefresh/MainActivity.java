package com.jc.android.component.pullrefresh;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jc.android.component.pullrefresh.databinding.ActivityMainBinding;
import com.jc.android.component.pulltorefresh.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //ObservableField

    private int page = 0;
    private int pageRow = 20;
    private List<DemoModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.recycler.setLayoutManager(new LinearLayoutManager(this));

        binding.refresh.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        page = 0;
                        list.clear();
                        list.addAll(getList());
                        binding.refresh.refreshFinish(PullToRefreshLayout.SUCCEED);
                    }
                };
                handler.sendEmptyMessageDelayed(1, 1500L);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        page = page + 1;
                        list.addAll(getList());
                        if (getList().size()==0) {
                            binding.refresh.loadmoreFinish(PullToRefreshLayout.NO_MORE);
                        } else {
                            binding.refresh.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                        }

                    }
                };
                handler.sendEmptyMessageDelayed(1, 1500L);
            }
        });

        binding.recycler.addItemDecoration(new SpaceItemDecoration(32));

        // 列表
        list = getList();
        // 适配器
        DemoAdapter adapter = new DemoAdapter(list, this);
        // 绑定
        binding.setAdapter(adapter);
    }

    private List<DemoModel> getList() {
        List<DemoModel> list = new ArrayList<>();

        int start = Math.max(0, page*pageRow);
        int end = Math.min(55, (page+1)*pageRow);

        for (int i=start; i<end; ++i) {
            DemoModel model = new DemoModel();
            model.setTitle(String.format(Locale.US, "%d:%s", i, "标题"));
            model.setTime("2016.06.22 12:32:44");
            list.add(model);
        }

        return list;
    }
}
