package css.viewpager;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// This layout only has on imageview -- This is used to fill the ViewPager2 for each screen
public class ImageViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView textViewCatFact;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        textViewCatFact = itemView.findViewById(R.id.textViewCatFact);
    }
}
