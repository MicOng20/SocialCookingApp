package my.edu.utar.socialcookingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import my.edu.utar.socialcookingapp.FoodDesc;
import my.edu.utar.socialcookingapp.Model.FoodData;
import my.edu.utar.socialcookingapp.R;

public class FoodAdapter extends RecyclerView.Adapter<FoodViewHolder>{

    private Context mContext;
    private List<FoodData> myFoodList;
    private int lastPosition = -1;

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

        Glide.with(mContext).load(myFoodList.get(i).getImage()).into(foodViewHolder.imageView);

        //foodViewHolder.imageView.setImageResource(myFoodList.get(i).getImage());
        foodViewHolder.mTitle.setText(myFoodList.get(i).getFoodName());
        foodViewHolder.mDesc.setText(myFoodList.get(i).getFoodDesc());

        foodViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FoodDesc.class);
                intent.putExtra("Image",myFoodList.get(foodViewHolder.getAdapterPosition()).getImage());
                intent.putExtra("FoodName",myFoodList.get(foodViewHolder.getAdapterPosition()).getFoodName());
                intent.putExtra("Description",myFoodList.get(foodViewHolder.getAdapterPosition()).getFoodDesc());
                intent.putExtra("Ingredient1",myFoodList.get(foodViewHolder.getAdapterPosition()).getIngredient1());
                intent.putExtra("Amount1",myFoodList.get(foodViewHolder.getAdapterPosition()).getAmount1());
                intent.putExtra("Ingredient2",myFoodList.get(foodViewHolder.getAdapterPosition()).getIngredient2());
                intent.putExtra("Amount2",myFoodList.get(foodViewHolder.getAdapterPosition()).getAmount2());
                intent.putExtra("Ingredient3",myFoodList.get(foodViewHolder.getAdapterPosition()).getIngredient3());
                intent.putExtra("Amount3",myFoodList.get(foodViewHolder.getAdapterPosition()).getAmount3());
                intent.putExtra("Ingredient4",myFoodList.get(foodViewHolder.getAdapterPosition()).getIngredient4());
                intent.putExtra("Amount4",myFoodList.get(foodViewHolder.getAdapterPosition()).getAmount4());
                intent.putExtra("Ingredient5",myFoodList.get(foodViewHolder.getAdapterPosition()).getIngredient5());
                intent.putExtra("Amount5",myFoodList.get(foodViewHolder.getAdapterPosition()).getAmount5());
                intent.putExtra("Ingredient6",myFoodList.get(foodViewHolder.getAdapterPosition()).getIngredient6());
                intent.putExtra("Amount6",myFoodList.get(foodViewHolder.getAdapterPosition()).getAmount6());
                intent.putExtra("Ingredient7",myFoodList.get(foodViewHolder.getAdapterPosition()).getIngredient7());
                intent.putExtra("Amount7",myFoodList.get(foodViewHolder.getAdapterPosition()).getAmount7());
                intent.putExtra("Method",myFoodList.get(foodViewHolder.getAdapterPosition()).getFoodMethod());
                mContext.startActivity(intent);
            }
        });

        setAnimation(foodViewHolder.itemView,i);

    }

    public void setAnimation(View viewToAnimate, int position){
        if(position>lastPosition)
        {
            ScaleAnimation animation = new ScaleAnimation(0.0f,1.0f,0.0f,1.0f
            , Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);

            animation.setDuration(1500);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }


    @Override
    public int getItemCount() {
        return myFoodList.size();
    }

    public void filteredList(ArrayList<FoodData> filterList) {
        myFoodList = filterList;
        notifyDataSetChanged();

    }
}

class FoodViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mTitle, mDesc, mStep, mIngredient1,mIngredient2,mIngredient3,mIngredient1Amount
            ,mIngredient2Amount, mIngredient3Amount;
    CardView mCardView;

    public FoodViewHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mDesc = itemView.findViewById(R.id.tvDescription);
       /* mStep = itemView.findViewById(R.id.tvRecipeStep);
        mIngredient1 = itemView.findViewById(R.id.tvIngredient1);
        mIngredient2 = itemView.findViewById(R.id.tvIngredient2);
        mIngredient3 = itemView.findViewById(R.id.tvIngredient2);
        mIngredient1Amount = itemView.findViewById(R.id.tvIngredient1Amount);
        mIngredient2Amount = itemView.findViewById(R.id.tvIngredient2Amount);
        mIngredient3Amount = itemView.findViewById(R.id.tvIngredient3Amount);*/
        mCardView = itemView.findViewById(R.id.cardView);
    }
}
