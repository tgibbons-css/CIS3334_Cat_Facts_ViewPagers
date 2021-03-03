package css.viewpager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/***
 *  This is a RecycleView Adapater used to manage the images in the viewpager
 */
public class ImagePagerAdapter extends RecyclerView.Adapter<ImageViewHolder>{

    // This stores the resource ids for all the images from res->drawable
    private List<Integer> imageList = new ArrayList<>();

    ImagePagerAdapter () {
        Log.d("CIS 3334", "In ImagePagerAdapter constructor -- Initialize array");
        imageList.add(R.drawable.cat1);
        imageList.add(R.drawable.cat2);
        imageList.add(R.drawable.cat3);
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_layout, parent, false);
        ImageViewHolder holder = new ImageViewHolder(view);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Log.d("CIS 3334", "In ImagePagerAdapter -- onBindViewHolder");
        Integer currentImage = imageList.get(position);
        holder.imageView.setImageResource(currentImage);
        //holder.imageView.setImageResource(R.drawable.cat1);

    }

    @Override
    public int getItemCount() {
        Log.d("CIS 3334", "In ImagePagerAdapter -- getItemCount = "+imageList.size());
        return imageList.size();
    }
}
