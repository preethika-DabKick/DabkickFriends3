package com.dabkick.choosefriends;

import java.util.ArrayList;

import com.dabkick.R;
import com.dabkick.utils.Constants;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FriendsFragment extends Fragment{
	
	ArrayList<String> names_array;
	CustomListViewAdapter adapter;
	ListView friendsList; 
	ImageView tick,displayPic;
	TextView friendName,friendNameList;
	RelativeLayout blueBar,chooseFriends,searchBar;
	boolean clicked[]= new boolean[10];
	int count=0;
	final String separator = ", ";
	String namesSelected[]=new String[10],chosenFriends="";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		View view = inflater.inflate(R.layout.friends_fragment, container, false);
		friendsList = (ListView) view.findViewById(R.id.contacts_list);
		friendNameList = (TextView) view.findViewById(R.id.friend_name_list);
		blueBar = (RelativeLayout) view.findViewById(R.id.blue_bar);
		chooseFriends = (RelativeLayout) view.findViewById(R.id.choose_layout);
		searchBar = (RelativeLayout) view.findViewById(R.id.search_bar);
		blueBar.setVisibility(View.INVISIBLE);
		adapter = new CustomListViewAdapter(getActivity());
		friendsList.setAdapter(adapter);
		if (adapter.getCount()>=7){
			searchBar.setVisibility(View.VISIBLE);
			chooseFriends.setVisibility(View.INVISIBLE);
		}
		else{
			searchBar.setVisibility(View.INVISIBLE);
			chooseFriends.setVisibility(View.VISIBLE);
		}
/*	InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		
		im.hideSoftInputFromInputMethod(searchBar, InputMethodManager.HIDE_IMPLICIT_ONLY);*/
		Log.d("DabKick", "tag "+adapter.getCount());
		for (int i = 0;i<adapter.getCount();i++){
			clicked[i]=false;
			namesSelected[i]="";
		}
		
		chooseFriends.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//TODO
			}
		});
		
		friendsList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				View v = friendsList.getChildAt(position);
				tick = (ImageView) v.findViewById(R.id.radio_icon);
				displayPic = (ImageView) v.findViewById(R.id.icon);
				friendName = (TextView) v.findViewById(R.id.friend_name);
				Log.d("DabKick", "init done ");
				Log.d("DabKick", "clicked[position] "+clicked[position]);
				
				if (clicked[position]){
					tick.setImageResource(R.drawable.friends_not_selected);
					friendName.setTextColor(getResources().getColor(android.R.color.black));
					namesSelected[position]= "";
					clicked[position]=false;
					count--;
					if (count==0){
						blueBar.setVisibility(View.INVISIBLE);
					}
				}
				else {
					tick.setImageResource(R.drawable.friends_selected);
					friendName.setTextColor(getResources().getColor(R.color.blue));
					 namesSelected[position]= adapter.getItem(position);
					clicked[position]=true;
					count++;
					if (count>0){
						blueBar.setVisibility(View.VISIBLE);
					}
				}
				Log.d("DabKick", "namesSelected.length "+namesSelected.length);
				chosenFriends="";
				for (int i = 0;i<namesSelected.length;i++){
					if (!namesSelected[i].equals(""))
						if (chosenFriends.equals(""))
							chosenFriends = namesSelected[i];
						else
						chosenFriends=chosenFriends+separator+namesSelected[i];
				}
				Log.d("DabKick","chosenFriends "+ chosenFriends);
				friendNameList.setText(chosenFriends);
			}
		});
		
		return view;
	}
	
}
