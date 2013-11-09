package com.ShoutApp.shout;

/**
 * Created by vishnu on 11/8/2013.
 */
        //import com.ShoutApp.shout.R;
        import android.app.FragmentManager;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import javax.xml.parsers.DocumentBuilder;
        import javax.xml.parsers.DocumentBuilderFactory;
        import org.w3c.dom.Document;
        import org.w3c.dom.Element;
        import org.w3c.dom.Node;
        import org.w3c.dom.NodeList;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

        import android.content.Intent;
        import android.util.Log;
        import android.widget.AdapterView;
        import android.widget.ListView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.app.DialogFragment;
        import android.app.Dialog;
        import android.app.AlertDialog;
        import android.content.DialogInterface;
import com.ShoutApp.shout.ShoutRespond;


public class WallFragment extends Fragment {

    // XML node keys
    static final String KEY_TAG = "weatherdata"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_CITY = "city";
    static final String KEY_TEMP_C = "tempc";
    static final String KEY_TEMP_F = "tempf";
    static final String KEY_CONDN = "condition";
    static final String KEY_SPEED = "windspeed";
    static final String KEY_ICON = "icon";



    // List items
    ListView list;
    BinderData adapter = null;
    List<HashMap<String,String>> hashMapList;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_wall, container, false);

        try {


            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc;
            doc = docBuilder.parse (getActivity().getAssets().open("data.xml"));

            hashMapList = new ArrayList<HashMap<String,String>>();

            // normalize text representation
            doc.getDocumentElement ().normalize ();

            NodeList weatherList = doc.getElementsByTagName("weatherdata");

            HashMap<String,String> map = null;

            for (int i = 0; i < weatherList.getLength(); i++) {

                map = new HashMap<String,String>();

                Node firstWeatherNode = weatherList.item(i);

                if(firstWeatherNode.getNodeType() == Node.ELEMENT_NODE){

                    Element firstWeatherElement = (Element)firstWeatherNode;
                    //-------
                    NodeList idList = firstWeatherElement.getElementsByTagName(KEY_ID);
                    Element firstIdElement = (Element)idList.item(0);
                    NodeList textIdList = firstIdElement.getChildNodes();
                    //--id
                    map.put(KEY_ID, ((Node)textIdList.item(0)).getNodeValue().trim());

                    //2.-------
                    NodeList cityList = firstWeatherElement.getElementsByTagName(KEY_CITY);
                    Element firstCityElement = (Element)cityList.item(0);
                    NodeList textCityList = firstCityElement.getChildNodes();
                    //--city
                    map.put(KEY_CITY, ((Node)textCityList.item(0)).getNodeValue().trim());

                    //3.-------
                    NodeList tempList = firstWeatherElement.getElementsByTagName(KEY_TEMP_C);
                    Element firstTempElement = (Element)tempList.item(0);
                    NodeList textTempList = firstTempElement.getChildNodes();
                    //--city
                    map.put(KEY_TEMP_C, ((Node)textTempList.item(0)).getNodeValue().trim());

                    //4.-------
                    NodeList condList = firstWeatherElement.getElementsByTagName(KEY_CONDN);
                    Element firstCondElement = (Element)condList.item(0);
                    NodeList textCondList = firstCondElement.getChildNodes();
                    //--city
                    map.put(KEY_CONDN, ((Node)textCondList.item(0)).getNodeValue().trim());

                    //5.-------
                    NodeList speedList = firstWeatherElement.getElementsByTagName(KEY_SPEED);
                    Element firstSpeedElement = (Element)speedList.item(0);
                    NodeList textSpeedList = firstSpeedElement.getChildNodes();
                    //--city
                    map.put(KEY_SPEED, ((Node)textSpeedList.item(0)).getNodeValue().trim());

                    //6.-------
                    NodeList iconList = firstWeatherElement.getElementsByTagName(KEY_ICON);
                    Element firstIconElement = (Element)iconList.item(0);
                    NodeList textIconList = firstIconElement.getChildNodes();
                    //--city
                    map.put(KEY_ICON, ((Node)textIconList.item(0)).getNodeValue().trim());

                    //Add to the Arraylist
                    hashMapList.add(map);
                }
            }


            BinderData bindingData = new BinderData(this.getActivity(), hashMapList);

            //View V = inflater.inflate(R.layout.activity_main,null);
            list = (ListView)rootView.findViewById(R.id.listView);

            Log.i("BEFORE", "<<------------- Before SetAdapter-------------->>");

            list.setAdapter(bindingData);

            Log.i("AFTER", "<<------------- After SetAdapter-------------->>");



            // Click event for single list row
            list.setOnItemClickListener(new OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {



                    DialogFragment newFragment = new ShoutRespond();

                    //FragmentManager fm=(android.app.FragmentManager) getFragmentManager();
                    //newFragment.show(fm, "XYXXX");



                    //sex.show

                    Intent intent = new Intent(getActivity(), DisplayMessageActivity.class);

                    // parameters
                    intent.putExtra("Compose", "Compose");
                    startActivity(intent);

                    // start the sample activity
                    //startActivity(i); */

                }
            });

        }

        catch (IOException ex) {
            Log.e("Error", ex.getMessage());
        }
        catch (Exception ex) {
            Log.e("Error", "Loading exception");
        }



        return rootView;
    }
}
