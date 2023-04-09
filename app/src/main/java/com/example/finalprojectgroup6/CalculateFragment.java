package com.example.finalprojectgroup6;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Cache;
import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.List;

public class CalculateFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinner1;
    Spinner spinner2;
    List<String> currencyList;
    View v;
//

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v = inflater.inflate(R.layout.fragment_calculate, container, false);
        spinner1 = v.findViewById(R.id.spinner1);
        spinner2 = v.findViewById(R.id.spinner2);
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        currencyList = new ArrayList<String>();
        // list of Dropdown items
        getCurrencyData();
        //String[] listOfCoin = (String[]) currencyList.keySet().toArray();
        String[] listOfCoin2 = {"BTC","ETH","TTH","DOG","MAT","DOT","WRX","BNB","USDT","TRT"};
        Log.e("Array",currencyList.toArray().toString());
        ArrayAdapter adapter;
        ArrayAdapter adapter1;
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,listOfCoin2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter1 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,listOfCoin2);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter1);

         return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
   private  void  getCurrencyData(){
        String api_key = "6d71da36-bd1d-4795-bb93-8790d10c7e28";
        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";



   }
}