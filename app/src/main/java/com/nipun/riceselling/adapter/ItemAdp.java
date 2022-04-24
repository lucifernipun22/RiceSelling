package com.nipun.riceselling.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nipun.riceselling.DatabaseHelper;
import com.nipun.riceselling.R;
import com.nipun.riceselling.SessionManager;
import com.nipun.riceselling.activity.CartActivity;
import com.nipun.riceselling.model.MyCart;
import com.nipun.riceselling.utils.Constants;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ItemAdp extends RecyclerView.Adapter<ItemAdp.ViewHolder> {
    final int[] count = {0};
    double[] totalAmount = {0};
    DatabaseHelper helper ;
    private List<MyCart> mData;
    private LayoutInflater mInflater;
    Context mContext;
    SessionManager sessionManager;

    public ItemAdp(Context context, ArrayList<MyCart> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mContext = context;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        sessionManager = new SessionManager(mContext);
        helper = new DatabaseHelper(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custome_mycard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        MyCart cart = mData.get(i);
        Glide.with(mContext).load(Constants.API_BASE_URL + "admin/storage/app/public/product/" + cart.getImage()).thumbnail(Glide.with(mContext).load(R.drawable.lodingimage)).into(holder.imgIcon);
        double res = (Double.parseDouble(cart.getCost()) * mData.get(i).getDiscount()) / 100;
        res = Double.parseDouble(cart.getCost()) - res;
        holder.txtGram.setText("  " + cart.getWeight() + "  ");
        holder.txtPrice.setText("₹ " + new DecimalFormat("##.##").format(res));
        holder.txtTitle.setText("" + cart.getTitle());

        MyCart myCart = new MyCart();
        myCart.setPid(cart.getPid());
        myCart.setImage(cart.getImage());
        myCart.setTitle(cart.getTitle());
        myCart.setWeight(cart.getWeight());
        myCart.setCost(cart.getCost());
        myCart.setDiscount(cart.getDiscount());
        int qrt = helper.getCard(myCart.getPid(), myCart.getCost());
        if (qrt != -1) {
            count[0] = qrt;
            holder.txtcount.setText("" + count[0]);
            holder.txtcount.setVisibility(View.VISIBLE);
            holder.imgMins.setVisibility(View.VISIBLE);

        } else {
            holder.txtcount.setVisibility(View.INVISIBLE);
            holder.imgMins.setVisibility(View.INVISIBLE);
        }
        double ress = (Double.parseDouble(myCart.getCost()) / 100.0f) * myCart.getDiscount();
        ress = Double.parseDouble(myCart.getCost()) - ress;
        double temp = ress * qrt;
        totalAmount[0] = totalAmount[0] + temp;
        holder.imgMins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count[0] = Integer.parseInt(holder.txtcount.getText().toString());
                count[0] = count[0] - 1;
                if (count[0] <= 0) {
                    holder.txtcount.setVisibility(View.INVISIBLE);
                    holder.imgMins.setVisibility(View.INVISIBLE);
                    holder.txtcount.setText("" + count[0]);
                    helper.deleteRData(myCart.getPid(), myCart.getCost());
                    mData.remove(cart);

                    totalAmount[0] = totalAmount[0] - Double.parseDouble(myCart.getCost());
                    Toast.makeText(mContext, "" + myCart.getTitle() + " " + myCart.getWeight() + " is Remove", Toast.LENGTH_LONG).show();
                    if (totalAmount[0] == 0) {
                        if(mContext instanceof CartActivity){
                            ((CartActivity)mContext).findViewById(R.id.nested).setVisibility(View.GONE);
                        }
                    }
                    notifyDataSetChanged();
                    if(mContext instanceof CartActivity){
                        ((CartActivity)mContext).updateItem();
                    }
                    if(mContext instanceof CartActivity){
                        ((CartActivity)mContext).updateItem();
                    }

                } else {
                    holder.txtcount.setVisibility(View.VISIBLE);
                    holder.txtcount.setText("" + count[0]);
                    myCart.setQty(String.valueOf(count[0]));
                    totalAmount[0] = totalAmount[0] - Double.parseDouble(myCart.getCost());
                    helper.insertData(myCart);
                    notifyDataSetChanged();
                    if(mContext instanceof CartActivity){
                        ((CartActivity)mContext).updateItem();
                    }


                }
            }
        });
        holder.imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.txtcount.setVisibility(View.VISIBLE);
                holder.imgMins.setVisibility(View.VISIBLE);
                count[0] = Integer.parseInt(holder.txtcount.getText().toString());
                totalAmount[0] = totalAmount[0] + Double.parseDouble(myCart.getCost());
                count[0] = count[0] + 1;
                holder.txtcount.setText("" + count[0]);
                myCart.setQty(String.valueOf(count[0]));
                helper.insertData(myCart);
                if(mContext instanceof CartActivity){
                    ((CartActivity)mContext).updateItem();
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon = itemView.findViewById(R.id.img_icon);
        TextView txtTitle = itemView.findViewById(R.id.txt_title);
        TextView txtPrice = itemView.findViewById(R.id.txt_price);
        TextView txtGram = itemView.findViewById(R.id.txt_gram);
        TextView txtcount = itemView.findViewById(R.id.txtcount);
        LinearLayout imgMins = itemView.findViewById(R.id.img_mins);
        LinearLayout imgPlus = itemView.findViewById(R.id.img_plus);
        LinearLayout lvlAddremove = itemView.findViewById(R.id.lvl_addremove);

        ViewHolder(View itemView) {
            super(itemView);
        }

    }


}
