package com.sodadrink.br.mobile;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapaFragment extends Fragment implements OnMapReadyCallback{

    /*LatLng Endereco = new LatLng( -22.9052556, -43.1775701);
    LatLng Endereco1 = new LatLng( -23.9052556, -44.1775701);*/

    GoogleMap mGoogle;
    MapView mapView;

    public MapaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_mapa, container, false);



        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView)view.findViewById(R.id.mapaGoogle);

        if(mapView != null){

            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);

        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());

        mGoogle = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        /*googleMap.addMarker(new MarkerOptions().position(Endereco).title("Nome da Loja").snippet("Breve descricao"));
        googleMap.addMarker(new MarkerOptions().position(Endereco1).title("Nome da Loja").snippet("Breve descricao"));*/

        //String location = "london";

        Geocoder gc = new Geocoder(getContext());
        List<Address> list = null;

        Bundle bundle = getArguments();
        ArrayList<String> listNomes = bundle.getStringArrayList("nomes");
        ArrayList<String> listEnderecos = bundle.getStringArrayList("enderecos");

        //Toast.makeText(getContext(),"aqui = "+listEnderecos.get(0),Toast.LENGTH_SHORT).show();

        try {

            for(int i = 0; i < listEnderecos.size(); i++){

                String location = listEnderecos.get(i);

                //Toast.makeText(getContext(),"aqui = "+gc.getFromLocationName(location,1),Toast.LENGTH_SHORT).show();

                if(gc.getFromLocationName(location,1) != null && !gc.getFromLocationName(location,1).isEmpty()){

                    list = gc.getFromLocationName(location,1);

                    Address address = list.get(0);

                    double lat = address.getLatitude();
                    double lng = address.getLongitude();

                    LatLng Endereco = new LatLng( lat, lng);

                    String nomeFantasia = listNomes.get(i);
                    googleMap.addMarker(new MarkerOptions().position(Endereco).title(""+nomeFantasia).snippet(""));

                    CameraPosition cameraPosition = CameraPosition.builder().target(Endereco).zoom(10).bearing(0).tilt(45).build();
                    googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                }

            }

            /*String locality = add.getLocality();
            Toast.makeText(getContext(),locality,Toast.LENGTH_SHORT).show();*/


        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*public void gotoLocation(double lat, double lng, float zoom){

        LatLng ll = new LatLng(lat,lng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(ll,zoom);
        mGoogle.moveCamera(cameraUpdate);

    }

    public void geoLocate(View v) throws IOException {

        String location = "london";

        Geocoder gc = new Geocoder(getContext());
        List<Address> list = gc.getFromLocationName(location,1);
        Address add = list.get(0);
        String locality = add.getLocality();

        Toast.makeText(getContext(),locality,Toast.LENGTH_SHORT).show();

        double lat = add.getLatitude();
        double lng = add.getLongitude();

        gotoLocation(lat, lng , 16);

    }*/

}
