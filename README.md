#### 前言
Android文字向上滚动效果没有自带的控件，这就需要开发者根据算法精确按照时间和坐标来实现TextView控件的向上移动，从网上找过几个框架，基本都会有bug，于是自己写了一个自定义控件，来实现TextView平滑向上滚动的文字效果
首先，自定义控件：ScrollBanner.java
```java
public class ScrollBanner extends LinearLayout {
    private TextView mBannerTV1;
    private TextView mBannerTV2;
    private Handler handler;
    private boolean isShow=false;
    private int startY1, endY1, startY2, endY2;
    private Runnable runnable;
    private List<String> list;
    private int position = 0;
    private int offsetY = 100;
    public ScrollBanner(Context context) {
        this(context, null);
    }

    public ScrollBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.view_scroll_banner, this);
        mBannerTV1 = (TextView) view.findViewById(R.id.tv_banner1);
        mBannerTV2 = (TextView) view.findViewById(R.id.tv_banner2);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                isShow = !isShow;
                if (position == list.size()-1) {
                    position = 0;
                }
                if (isShow) {
                    mBannerTV1.setText(list.get(position++));
                    mBannerTV2.setText(list.get(position));
                } else {
                    mBannerTV2.setText(list.get(position++));
                    mBannerTV1.setText(list.get(position));
                }
                startY1 = isShow ? 0 : offsetY;
                endY1 = isShow ? -offsetY : 0;
                ObjectAnimator.ofFloat(mBannerTV1, "translationY", startY1, endY1).setDuration(300).start();
                startY2 = isShow ? offsetY : 0;
                endY2 = isShow ? 0 : -offsetY;
                ObjectAnimator.ofFloat(mBannerTV2, "translationY", startY2, endY2).setDuration(300).start();
                handler.postDelayed(runnable, 3000);
            }
        };
    }
    public List<String> getList() {
        return list;
    }
    public void setList(List<String> list) {
        this.list = list;
    }
    public void startScroll() {
        handler.post(runnable);
    }
    public void stopScroll() {
        handler.removeCallbacks(runnable);
    }
}
```
布局文件 view_scroll_banner.xml

```java
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent" android:layout_height="match_parent" android:orientation="horizontal" android:paddingLeft="10dp" android:paddingTop="5dp" android:paddingBottom="5dp">

    <TextView  android:id="@+id/tv_banner1" android:layout_width="match_parent" android:layout_height="wrap_content" android:layout_centerVertical="true" android:ellipsize="end" android:singleLine="true" android:textColor="#FFFF7B00" android:textSize="11sp" />



    <TextView  android:id="@+id/tv_banner2" android:layout_width="match_parent" android:layout_height="wrap_content" android:layout_centerVertical="true" android:ellipsize="end" android:singleLine="true" android:textColor="#FFFF7B00" android:textSize="11sp" />
</RelativeLayout>
```
主界面布局引用如下控件：

```java
<com.example.view.ScrollBanner
    android:id="@+id/sb_demographic"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
   />
```
在将要实现该效果的页面调用以下方法即可

```java
ScrollBanner sb_demographic;
 sb_demographic = (ScrollBanner) rootView.findViewById(R.id.sb_demographic);
 List<String> demographicsList=new ArrayList<String>();
 for(int i=0;i<20;i++){
     demographicsList.add("第"+i+"条内容");
 }
 sb_demographic.setList(demographicsList);
 sb_demographic.startScroll();
```
