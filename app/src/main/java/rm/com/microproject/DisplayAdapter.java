package rm.com.microproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Joseph on 13/11/17.
 */

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.ViewHolder>{


    private List<ImageDetails> imageDetails;
    private Context context;


    public DisplayAdapter(Context context, List<ImageDetails> imageDetails) {
        this.imageDetails = imageDetails;
        this.context = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView imageId;

        public View layout;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;


            imageView = itemView.findViewById(R.id.imageView);
            imageId = itemView.findViewById(R.id.imageId);
        }
    }


    @Override
    public DisplayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.list_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return  vh;


    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

        ImageDetails data = imageDetails.get(position);

        holder.imageId.setText(data.getImageId());

    }
}