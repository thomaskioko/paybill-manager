package com.thomaskioko.paybillmanager.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.thomaskioko.paybillmanager.R;
import com.thomaskioko.paybillmanager.models.PaybillCategory;
import com.thomaskioko.paybillmanager.ui.AddPaybillActivity;
import com.thomaskioko.paybillmanager.ui.MainActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adapter to display categories
 *
 * @author Thomas Kioko
 */
public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.PaybillHolder> {

    private List<PaybillCategory> mPaybillCategoryList;
    private Context mContext;

    /**
     * Constructor
     *
     * @param objectList List of Objects
     */
    public CategoryRecyclerViewAdapter(Context context, List<PaybillCategory> objectList) {
        mPaybillCategoryList = objectList;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mPaybillCategoryList.size();
    }

    @Override
    public CategoryRecyclerViewAdapter.PaybillHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new PaybillHolder(view);
    }

    @Override
    public void onBindViewHolder(final PaybillHolder holder, final int position) {

        final PaybillCategory paybillCategory = mPaybillCategoryList.get(position);
        holder.tvCategoryName.setText(paybillCategory.getCategoryName());
        int resourceId = 0;
        //TODO:: Load the drawable using the drawable id. This will do away with the switch case

        switch (paybillCategory.getCategoryName()) {
            case "Utilities":
                resourceId = R.mipmap.ic_utilities;
                break;
            case "Entertainment":
                resourceId = R.mipmap.ic_entertainment;
                break;
            case "Internet":
                resourceId = R.mipmap.ic_internet;
                break;
            case "Others":
                resourceId = R.mipmap.ic_others;
                break;
            default:
                break;
        }

        Glide.with(mContext)
                .load(resourceId)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageViewCategory);

        final int finalResourceId = resourceId;
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddPaybillActivity.setPaybillCategory(paybillCategory);
                /**
                 * When a view is clicked, we check it has been selected and we determine whether
                 * to make the background to reset the color or get the colot of the image and set it
                 * to the recyclerView.
                 */
                if (!holder.isSelected) {

                    holder.isSelected = true;
                    Glide.with(holder.imageViewCategory.getContext())
                            .load(finalResourceId)
                            .asBitmap()
                            .into(new BitmapImageViewTarget(holder.imageViewCategory) {
                                @Override
                                public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                                    super.onResourceReady(bitmap, anim);
                                    Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                                        @Override
                                        public void onGenerated(Palette palette) {
                                            if (palette.getDarkVibrantSwatch() != null) {
                                                holder.relativeLayout.setBackgroundColor(palette.getDarkVibrantSwatch().getRgb());
                                                holder.tvCategoryName.setTextColor(mContext.getResources().getColor(R.color.white));
                                            } else if (palette.getMutedSwatch() != null) {
                                                holder.relativeLayout.setBackgroundColor(palette.getMutedSwatch().getRgb());
                                                holder.tvCategoryName.setTextColor(mContext.getResources().getColor(R.color.white));
                                            }
                                        }
                                    });
                                }
                            });
                } else { //Reset the Background color and the text color
                    holder.isSelected = false;
                    holder.relativeLayout.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                    holder.tvCategoryName.setTextColor(mContext.getResources().getColor(R.color.black));
                }

            }
        });
    }

    /**
     * This class uses A ViewHolder object stores to each of the component views inside the tag field
     * of the Layout, so you can immediately access them without the need to look them up repeatedly.
     * <p/>
     * {@see <a href="http://developer.android.com/training/improving-layouts/smooth-scrolling.html</a>}
     */
    class PaybillHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.ivCategoryIcon)
        ImageView imageViewCategory;
        @Bind(R.id.tvCategoryName)
        TextView tvCategoryName;
        @Bind(R.id.relativeLayout)
        RelativeLayout relativeLayout;
        View view;
        boolean isSelected = false;

        private PaybillHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            view = itemView;
        }
    }


}