package liu.myapplication.ui;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import liu.myapplication.R;
import liu.myapplication.pulltorefreshlistview.PullToRefreshBase;
import liu.myapplication.pulltorefreshlistview.PullToRefreshListView;

/**
 * @PackageName: liu.myapplication.ui
 * @Description: PullToRefreshListActivity
 * @author: mifm
 * @date: 2016/8/5 16:36
 */
public class PullToRefreshListActivity extends AppCompatActivity {
    @BindView(R.id.ListView)
    PullToRefreshListView pullToRefreshListView;
    private android.widget.ListView listView;
//    String[] array={"aaa","bbb","ccc","ddd","eee","eee","eee","eee","eee","eee","eee"};
    String[] array={"aaa","bbb","ccc","ddd","eee"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulltorefresh);
        ButterKnife.bind(this);
        initListView();
    }

    private void initListView() {
        listView = pullToRefreshListView.getRefreshableView();
        pullToRefreshListView.setPullLoadEnabled(false);
        pullToRefreshListView.setScrollLoadEnabled(true);
        ArrayAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,array);
        listView.setAdapter(adapter);
//        pullToRefreshListView.setHasMoreData(false);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Logger.d("下拉刷新");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pullToRefreshListView.onPullDownRefreshComplete();
                                Logger.d("下拉刷新完成");
                            }
                        });
                    }
                }).start();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Logger.d("上拉加载;");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pullToRefreshListView.onPullUpRefreshComplete();
                                Logger.d("上拉加载完成完成");
                            }
                        });
                    }
                }).start();
            }
        });
    }
}