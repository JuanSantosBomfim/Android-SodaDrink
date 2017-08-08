package com.sodadrink.br.mobile;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Amongsk on 06/05/2017.
 */

public class ViewPagerAdapter extends PagerAdapter{

    //String UrlMostrarBanner = Enderecos.UrlMostrarBanner();

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer []imagens = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5};

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imagens.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_home_layout, null);

        /*RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, UrlMostrarBanner, new Response.Listener<JSONObject>() {
            @Override

            public void onResponse(JSONObject response) {

                try {

                    JSONArray jsonArray = response.getJSONArray("PRODUTOS");

                    for(int i = 0; i < jsonArray.length(); i++){

                        JSONObject dadosProduto = jsonArray.getJSONObject(i);

                        int id_banner = dadosProduto.getInt("id_banner");
                        String imagem = dadosProduto.getString("imagem");

                        //listaImagens.add(imagem);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("VOLLEY","ERROR");

            }

        });
        requestQueue.add(jsonObjectRequest);*/

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        imageView.setImageResource(imagens[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == 0) {

                    //Toast.makeText(context,"Slider 1",Toast.LENGTH_SHORT).show();

                }else if (position == 1) {

                    //Toast.makeText(context,"Slider 2",Toast.LENGTH_SHORT).show();

                }else if (position == 2) {

                    //Toast.makeText(context,"Slider 3",Toast.LENGTH_SHORT).show();

                }else if (position == 3) {

                    //Toast.makeText(context,"Slider 3",Toast.LENGTH_SHORT).show();

                }else if (position == 4) {

                    //Toast.makeText(context,"Slider 3",Toast.LENGTH_SHORT).show();

                }

            }
        });

        ViewPager vp = (ViewPager) container;
        vp.addView(view,0);

        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);

    }

}
