package com.example.mido.sqlitetutorials;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewTransactions extends AppCompatActivity {
   ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transactions);
        listView= (ListView) findViewById(R.id.list_view);
        DBHandler dbHandler=new DBHandler(this);
         List<Transaction> transactions=dbHandler.getAllContacts();
        CustomAdapter adapter=new CustomAdapter(this,android.R.layout.simple_list_item_1,transactions);
        listView.setAdapter(adapter);

    }

    public class CustomAdapter extends ArrayAdapter<Transaction>
    {
         List<Transaction> transactions;
        public CustomAdapter(Context context, int resource, List<Transaction> objects) {
            super(context, resource, objects);
            this.transactions=objects;
        }
        class ViewHolder
        {
TextView name,product,sell_price,buy_price;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = new ViewHolder();

            try {

                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.custom_item, parent, false);
                   // holder.name = (TextView) convertView.findViewById(R.id.name_tv);
                    holder.product =(TextView) convertView.findViewById(R.id.product_tv);
                    holder.sell_price=(TextView) convertView.findViewById(R.id.sell_price_tv);
                    holder.buy_price=(TextView) convertView.findViewById(R.id.buy_price_tv);

                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                final Transaction transaction = transactions.get(position);
               // holder.name.setText(transaction.getName());
                holder.product.setText(transaction.getProduct());
                holder.buy_price.setText(transaction.getBuy_price());
                holder.sell_price.setText(transaction.getSell_price());
            return convertView;
            } catch (Exception e) {
                return null;
            }

        }
    }

}
