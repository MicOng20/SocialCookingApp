package my.edu.utar.socialcookingapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import my.edu.utar.socialcookingapp.Model.FoodData;
import my.edu.utar.socialcookingapp.R;

public class FoodAdapter extends RecyclerView.Adapter<FoodViewHolder>{

    private Context mContext;
    private List<FoodData> myFoodList;

    public FoodAdapter(Context mContext, List<FoodData> myFoodList) {
        this.mContext = mContext;
        this.myFoodList = myFoodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_row_food,viewGroup,false);

        return new FoodViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder foodViewHolder, int i) {

        foodViewHolder.imageView.setImageResource(myFoodList.get(i).getImage());
        foodViewHolder.mTitle.setText(myFoodList.get(i).getFoodName());
        foodViewHolder.mDesc.setText(myFoodList.get(i).getFoodDesc());

    }

    @Override
    public int getItemCount() {
        return myFoodList.size();
    }
}

class FoodViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mTitle, mDesc;
    CardView mCardView;

    public FoodViewHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mDesc = itemView.findViewById(R.id.tvDescription);
        mCardView = itemView.findViewById(R.id.cardView);
    }
}
