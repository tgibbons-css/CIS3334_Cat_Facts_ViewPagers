package css.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

// https://www.geeksforgeeks.org/image-slider-in-android-using-viewpager/
public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the ImagePageAdaoter similar to other RecycleView Adapters
        viewPage = (ViewPager2) findViewById(R.id.viewPagerMain);
        ImagePagerAdapter adapter = new ImagePagerAdapter(this.getApplicationContext());
        viewPage.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}