package com.direction.investor.farmsprayer;

import android.annotation.SuppressLint;


import android.app.LoaderManager.LoaderCallbacks;
import ioio.lib.api.AnalogInput;

import ioio.lib.api.DigitalOutput;
import ioio.lib.api.IOIO;
import ioio.lib.api.PulseInput;
import ioio.lib.api.DigitalInput.Spec.Mode;
import ioio.lib.api.PulseInput.ClockRate;
import ioio.lib.api.PulseInput.PulseMode;
import ioio.lib.api.PwmOutput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOActivity;




import ioio.lib.api.DigitalInput;
import ioio.lib.api.IOIO;
import ioio.lib.api.PwmOutput;



import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.AbstractIOIOActivity;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOLooper;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.location.GpsStatus;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CameraPosition.Builder;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;
import ioio.lib.api.DigitalInput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.AbstractIOIOActivity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

//LoaderCallbacks needed if you store and retrieve location data on background thread

@SuppressLint("DefaultLocale")
public class PopulationMonitor extends IOIOActivity implements LocationListener, OnClickListener, /*LoaderCallbacks<Cursor>,*/ OnItemSelectedListener {
	  private GoogleMap mMap;
	    public static float  freqHz1;
	    public static float  freqHz11;
	    public static EditText numberOfRows;
	    public static EditText rowWidth;
	    public static EditText populationNumber;
	    public static double seedCount;
	    public  static double frequencyScaling;
	    public  static int floFrequencyScaling;
	    public static String rows;
	    public static String width;
	    public static double pubroundedacres;
	
	// For an 8 row planter you will need 8 digital input channels
	
	    private ProgressBar mProgress;
	    private ProgressBar mProgress2;
	    private ProgressBar mProgress3;
	    private ProgressBar mProgress4;
	    private ProgressBar mProgress5;
	    private ProgressBar mProgress6;
	    private ProgressBar mProgress7;
	    private ProgressBar mProgress8;
	    private float mProgressStatus = 0;
	    private float mProgressStatus2 = 0;
	    private float mProgressStatus3 = 0;
	    private float mProgressStatus4 = 0;
	    private float mProgressStatus5 = 0;
	    private float mProgressStatus6 = 0;
	    private float mProgressStatus7 = 0;
	    private float mProgressStatus8 = 0;
	    private Handler mHandler = new Handler();
	    private static final int PROGRESS = 0x1;
	   public static double mph;
  
	private TextView mTextView;
    private TextView mTextView1;
    private TextView mSpeedView;
    private TextView lineDistanceView;
    public static Marker mMarkerA;
    public static Marker mMarkerB;
   
    private Spinner spinner;
    public static LatLng previousPoint = null;
    public SQLiteDatabase mDB; 
    public static  String DATABASE_TABLE = "default a";
    public EditText length = null;
    public String lengthString =null;
    public PopupWindow popupWindowOpener;
    public static boolean heightMagnitude;
    public static boolean mapFlipper = false;
    public static double height;
    public static double boomWidth =0;
    public static int boomWidthMultiplier;
    public static LatLng lastLocation;
    public static LatLng currentLocation;
    
   public static int locationCount = 0;
	public static double lat=0;
	public static double lng=0;
	public static float zoom=0;
	public static double bear = 0;
	public static double boomwidth = 0;
	public static double rowsinteger;
    public static double rowwidth;
   
   
   public RadioGroup radioGroup;  
   public RadioButton standard;  
    public RadioButton metricRadio;  
  
	
	public static TextView currentpopulation;
	
    public static LocationManager locationmanager;
    public static LatLng point1, point2, point3 ;
    public static double heading;
    public static double headingline;
    public static double boomconversionfactor = 1;
    public static double tometersconversion = 1;
    public static double kilometersconversion =1;
    public static double hectareconversion = 1;
    public static boolean metric = false;
    public static float bearingorientation;
    public static Location location;
     public static LatLng previous;
     public static LatLng current;
    public static double widthInMeters = 0;
    
    public LatLng locationEngine;
    
    public static String fieldName = "default b";
    public static String fieldComments = "default c";
    public static String name;
    public static String lacomments;
    public static String label; 
    
    float[] results;
    boolean setIt = false;
    boolean centerControl = true;
   public static LatLng navOrigin;
   public static LatLng navSecPoint;
    public static Polyline navigationalLine;
   public static double distancesph;
    public static boolean orientation = false;
    public static double rowInteger;
    
    public static TextView row1;
	public static TextView row2;
	public static TextView row3;
	public static TextView row4;
	public static TextView row5;
	public static TextView row6;
	public static TextView row7;
	public static TextView row8;
    
    public double semiPerimeter;
  
    public double sideA;
    public double sideB;
    public double sideC;
    public double distToAB;
    private final int BUTTON1_PIN = 6;
	private final int BUTTON2_PIN = 7;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setUpMapIfNeeded();
        
       //VISUAL REPRESENTATION OF PLANTER POPULATION	
        mProgress = (ProgressBar) findViewById(R.id.vertical_progressbar1);
        mProgress2 = (ProgressBar) findViewById(R.id.vertical_progressbar2);
        mProgress3 = (ProgressBar) findViewById(R.id.vertical_progressbar3);
        mProgress4 = (ProgressBar) findViewById(R.id.vertical_progressbar4);
        mProgress5 = (ProgressBar) findViewById(R.id.vertical_progressbar5);
        mProgress6 = (ProgressBar) findViewById(R.id.vertical_progressbar6);
        mProgress7 = (ProgressBar) findViewById(R.id.vertical_progressbar7);
        mProgress8 = (ProgressBar) findViewById(R.id.vertical_progressbar8);
        
currentpopulation = (TextView) findViewById(R.id.currentpopulation);
		
		
    
        
      
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
    
    private void setUpMapIfNeeded() {
        if (mMap != null) {
            return;
        }
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();
        if (mMap != null) {
            startDemo();
        }
    }
    
    // function to quickly call GOOGLE Map Fragment 
    
    protected GoogleMap getMap() {
        setUpMapIfNeeded();
        return mMap;
    }
    protected int getLayoutId() {
        return R.layout.populationmonitor;
    }
    
    // MENU CONTROLS
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.populationtrackingmenu, menu);
	    return true;
	}
    
	 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		
		int id = item.getItemId();
		if (id == R.id.item1) {
			Toast.makeText(this, "Standard Units Selected. Feet,Ac,mph", Toast.LENGTH_LONG).show();
			boomconversionfactor = 1;
            tometersconversion = 1;
            kilometersconversion = 1;
            hectareconversion = 1;
            metric = false;
			
			
			
		}
		
		if (id == R.id.item2) {
			Toast.makeText(this, "Metric Units. Meters, Ha, kph", Toast.LENGTH_LONG).show();
			
			boomconversionfactor = 3.2808;
            tometersconversion = 0.3048;
            kilometersconversion = 1.60934;
            hectareconversion = 0.404686;
            metric = true;
			
			
			
		}
		
		if (id == R.id.centerhold) {
			
			 if (centerControl == true) {
		        	
		        	centerControl = false;
		        	Toast.makeText(this, "Center Hold is OFF", Toast.LENGTH_LONG).show();
		        	
		        	
		            // The toggle is enabled
		        } else {
		        	
		        	centerControl = true;
		        	Toast.makeText(this, "Center Hold is ON", Toast.LENGTH_LONG).show();
		            // The toggle is disabled
		        }
			
			
			
		}
		
		if (id == R.id.clearmap) {
			
			
			getMap().clear();
			startDemo();
			
		}
		
		if (id == R.id.imageexport) {
			
		 	Toast.makeText(getApplicationContext(), "ScreenShot in Downloads",
                    Toast.LENGTH_LONG).show();
         	
         	
         	getMap().setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
         	    public void onMapLoaded() {
         	        getMap().snapshot(new GoogleMap.SnapshotReadyCallback() {
         	            public void onSnapshotReady(Bitmap bitmap) {
         	                // Write image to disk
         	                FileOutputStream fos = null;
								try {
									
                           // UNIQUE FILE NAME
									Date date = new Date();
					            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hhmmss");
					            	String formattedDate = sdf.format(date);
					            	String filename = "MapShot_" + formattedDate + ".png";
									
							        File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename);
							        fos = new FileOutputStream(f);
									
									
									
								} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
         	                bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
         	            }
         	        });
         	    }
         	});
			
			
		}
		
		
		if (id == R.id.resetcounter) {
			
			
			
			
			distancesph = 0;
			
		}
	
		
		
		if (id == R.id.screentrack){
			
			if (orientation == false) {
			
			orientation = true;
			Toast.makeText(this, "Screen Tracking On", Toast.LENGTH_LONG).show();
			
        	
            // The toggle is enabled
        } else {
        	
        	orientation = false;
        	
        	Toast.makeText(this, "Screen Tracking Off", Toast.LENGTH_LONG).show();
        	
            // The toggle is disabled
        }
			
		}
		
		
		return super.onOptionsItemSelected(item);
	}
    
    // END OF MENU CONTROLS
	

    protected void startDemo()  {
    	
		currentpopulation = (TextView) findViewById(R.id.currentpopulation);
	
      // Makes map with road & earth appearance
        getMap().setMapType(GoogleMap.MAP_TYPE_HYBRID);
       getMap().setMyLocationEnabled(true);
		
       
       //popup screen controls
       
     final Button btnOpenPopup = (Button)findViewById(R.id.buttonStart);
       btnOpenPopup.setOnClickListener(new Button.OnClickListener(){

  @Override
  public void onClick(View arg0) {
   LayoutInflater layoutInflater 
    = (LayoutInflater)getBaseContext()
     .getSystemService(LAYOUT_INFLATER_SERVICE);  
   final View popupView = layoutInflater.inflate(R.layout.populationpopup, null);  
            final PopupWindow popupWindow = new PopupWindow(
              popupView, 
              LayoutParams.MATCH_PARENT,  
                    LayoutParams.MATCH_PARENT);  
            
            popupWindow.setFocusable(true);
            
          
            
            OnClickListener standardlistener = new OnClickListener (){
            	 public void onClick(View v) {
            		
            		 standard.setChecked(true);  
                     
                   	
          			boomconversionfactor = 1;
                      tometersconversion = 1;
                      kilometersconversion = 1;
                      hectareconversion = 1;
                      metric = false;
                      Toast.makeText(getApplicationContext(), "STANDARD UNITS",
	                           Toast.LENGTH_LONG).show();
            		 
            		 
            	   
            	 }
            	};
            	
            	OnClickListener metriclistenerlistener = new OnClickListener (){
               	 public void onClick(View v) {
               	   metricRadio.setChecked(true);  
                   boomconversionfactor = 3.2808;
                   tometersconversion = 0.3048;
                   kilometersconversion = 1.60934;
                   hectareconversion = 0.404686;
                   metric = true;
                   Toast.makeText(getApplicationContext(), "METRIC UNITS",
                           Toast.LENGTH_LONG).show();
               		 
               	   
               	 }
               	};
            
            
           radioGroup = (RadioGroup) popupView.findViewById(R.id.radiogroup);  
          standard = (RadioButton) popupView.findViewById(R.id.standard);  
           metricRadio = (RadioButton) popupView.findViewById(R.id.metric);  
       	numberOfRows = (EditText) popupView.findViewById(R.id.numberofrows);
        rowWidth = (EditText) popupView.findViewById(R.id.rowwidth);
        populationNumber = (EditText) popupView.findViewById(R.id.population);
           standard.setOnClickListener(standardlistener);
           metricRadio.setOnClickListener(metriclistenerlistener);
           
           Button cancel = (Button)popupView.findViewById(R.id.button1);
	        
	        cancel.setOnClickListener(new Button.OnClickListener(){

	            @Override
	            public void onClick(View v) {
	             // TODO Auto-generated method stub
	            	
	            
	            
	                
	                
	                
	            	
	             popupWindow.dismiss();
	             
	             
	             
	            }});
            
            Button btnDismiss = (Button)popupView.findViewById(R.id.startbutton);
            
            btnDismiss.setOnClickListener(new Button.OnClickListener(){

    @Override
    public void onClick(View v) {
     // TODO Auto-generated method stub
    	
    	 //length = (EditText) popupView.findViewById(R.id.numberofrows);
     	
    	// lengthString = length.getText().toString();
    	
    	 
    	rows = numberOfRows.getText().toString();
        width = rowWidth.getText().toString();
       String seeds = populationNumber.getText().toString();
    	
        if(rows != null && width != null && seeds != null) {	
        	
        	rowInteger = Double.parseDouble(rows);
        	
        	rowwidth = Double.parseDouble(width);
        	
        	seedCount = Double.parseDouble(seeds);
        
        	widthInMeters = Double.parseDouble(width) * Double.parseDouble(rows); 
        	
        	
			
        	boomWidth = widthInMeters  * boomconversionfactor * 0.3048;
        	
        	
        
        
        }
        
        else { Toast.makeText(getApplicationContext(), "FILL IN ROW & WIDTH INFO",
                Toast.LENGTH_LONG).show(); }
             
        	
        
    	//feet
    	   
    	
    	
    	EditText fieldname = (EditText) popupView.findViewById(R.id.fieldname);
    	fieldName = fieldname.getText().toString();
    	
    	EditText comments = (EditText) popupView.findViewById(R.id.commentlines);
    	fieldComments = comments.getText().toString();
    	
    	
    	DATABASE_TABLE = fieldName;
    	
    	
    	
    	
     popupWindow.dismiss();
     
     
     
    }});
              
            popupWindow.showAsDropDown(btnOpenPopup, 50, -30);
        
  }});
       
       //end of popup screen controls.
       
        
        
        locationmanager = (LocationManager) getSystemService(LOCATION_SERVICE);
    	
    		
    		locationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 3, this);
           
    		location = locationmanager
                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
        
    	
    		
    		ToggleButton togglePause = (ToggleButton) findViewById(R.id.toggleButton2);
    		togglePause.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    		        if (isChecked) {
    		        	
    		        	setIt = false;
    		        	
    		        	
    		        	
    		            // The toggle is enabled
    		        } else {
    		        	
    		        	setIt = true;
    		        	lastLocation = null;
    		            // The toggle is disabled
    		        }
    		    }
    		});
    		
    		
        
        if (location!=null) {
        	
        	getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 16));
		
    
       
	
     
		}
		else { Toast.makeText(this, "NO GPS SIGNAL!", Toast.LENGTH_LONG).show(); }
    
      
        
        
        Button startMoving = (Button)findViewById(R.id.storebutton);
        
        startMoving.setOnClickListener(new Button.OnClickListener(){

@Override
public void onClick(View v) {
        
        setIt=true;
	mapStarter();
	
	
        
}});
        
        
        
    }

     

	@Override
	public void  onLocationChanged(Location location) {
		
		float speed = location.getSpeed();
		// m per sec equals 2.23694 mph
		//for sprayer speed
		double mphspeed = speed * 2.23694 * kilometersconversion;
		//for planter speed
		mph = speed * 2.23694;
		
		double roundedmph = Math.round(mphspeed * 100.0)/100.0 ;
		
		 mSpeedView = (TextView) findViewById(R.id.mphindicator);
		 
			
		 if(metric == false) {
		 
		 mSpeedView.setText(roundedmph + " mph"); }
		 
		 else {
			 mSpeedView.setText(roundedmph + " kph");
			 
		 }
		
		if (widthInMeters != 0  ) {
			
			if(centerControl == true){ getMap().moveCamera(CameraUpdateFactory.newLatLng((new LatLng(location.getLatitude(), location.getLongitude()))));}
		
	
			
			point1 = (new LatLng(location.getLatitude()   , location.getLongitude()));
		
		
		
		 point2 = (new LatLng(location.getLatitude()   , location.getLongitude()));
		 current = (new LatLng(location.getLatitude()   , location.getLongitude()));
		 
		 
		 if (location.hasAccuracy() && location.getAccuracy() <= 10)
	        {
		 
			 	if (previous != null) {
	     
			 
			 
			 			if (setIt == true){
	        distancesph +=  SphericalUtil.computeDistanceBetween(previous, current);
			 }
	         
	         
	    }
		 
		 previous = current;
	        
		double actualfeet = widthInMeters ;
		 double distancetraveledconversion = distancesph * 3.28084;
		
		 double acres = distancetraveledconversion * actualfeet/43560 * hectareconversion;
		
		 double roundedacres = Math.round(acres*100.0)/100.0;
		 pubroundedacres = roundedacres;
	
		 mTextView1 = (TextView) findViewById(R.id.textView2);
		 
		if(metric == false) {
		 mTextView1.setText(roundedacres + " ac");}
		else{mTextView1.setText(roundedacres + " ha");}
	        }

		float bearing = location.getBearing();
		//version three zoom commented out to fix zoom fight
		double bear = bearing;
		if(orientation == true) { 
			
			float zoom = getMap().getCameraPosition().zoom;
			
			CameraPosition cameraPosition =
		        new CameraPosition.Builder()
        .target(new LatLng(location.getLatitude(), location.getLongitude()))
                       .bearing(bearing)
                       .zoom(zoom)
                       .build();
		
		getMap().animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		
		}
		
		
		 

	 
		if (setIt == true){
	
			
		        currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
		       
		        
		        if (location.hasAccuracy() && location.getAccuracy() <= 25)
		        {
		        
		        if (lastLocation != null && setIt == true)
		        {
		            PolygonFactory();
		        }
		        
		        lastLocation = currentLocation;
			storageEngine();
		        }
		
		}
		else {}

		}
		
		else { }
		
		
	}

	
	
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}


	


	




public void PolygonFactory() {
	
	
		
		
       LatLng polypoint1 = SphericalUtil.computeOffset(lastLocation, (0.30480000000000002D * boomWidth * boomconversionfactor) / 2, 90 + location.getBearing());
       LatLng polypoint2 = SphericalUtil.computeOffset(lastLocation, (0.30480000000000002D * boomWidth * boomconversionfactor) / 2, location.getBearing() - 90F);
       LatLng polypoint3 = SphericalUtil.computeOffset(currentLocation, (0.30480000000000002D * boomWidth * boomconversionfactor) / 2, 90 + location.getBearing());
       LatLng polypoint4 = SphericalUtil.computeOffset(currentLocation, (0.30480000000000002D * boomWidth * boomconversionfactor) / 2, location.getBearing() - 90F);
        
        Polygon polygon = getMap().addPolygon(new PolygonOptions()
        .add(polypoint1, polypoint2, polypoint4, polypoint3)
        .strokeColor(Color.RED)
        .fillColor(Color.BLUE));
        
    
	
	
}





public void storageEngine() { 
	
	// Set up something like this to store your values so you can pull the map and progress back up later...
	
	// Creating an instance of ContentValues
			ContentValues contentValues = new ContentValues();
			/*
			location = locationmanager
                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
			
		
			
			contentValues.put(LocationsDB.FIELD_NAME, fieldName );
			
			// Setting latitude in ContentValues
	        contentValues.put(LocationsDB.FIELD_LAT, location.getLatitude() );
	        
	        
	        
	        // Setting longitude in ContentValues
	        contentValues.put(LocationsDB.FIELD_LNG, location.getLongitude());
	        
	      //  contentValues.put(LocationsDB.FIELD_NAME, textView.getasdlkfjaslfkj);
	        
	        
	        contentValues.put(LocationsDB.FIELD_BEAR, location.getBearing());
	        
	        contentValues.put(LocationsDB.FIELD_BOOMWIDTH, widthInMeters);
	        
	        contentValues.put(LocationsDB.FIELD_COMMENTS, fieldComments);
	        
	        contentValues.put(LocationsDB.TODAYS_DATE, getDateTime());
	        
	        contentValues.put(LocationsDB.ACRES, pubroundedacres);
	        
	        // Setting zoom in ContentValues
	        contentValues.put(LocationsDB.FIELD_ZOOM, getMap().getCameraPosition().zoom);
	        
	        // Creating an instance of LocationInsertTask
			LocationInsertTask insertTask = new LocationInsertTask();
			
			// Storing the latitude, longitude and zoom level to SQLite database
			insertTask.execute(contentValues);      
	
			
	
	//gonna need bearing and latlng values getBearing and getlocation
	
	*/
	
}


//Pull up saved location data in the background thread... 
/*
private class LocationInsertTask extends AsyncTask<ContentValues, Void, Void>{
	
	
	
	
	
	@Override
	protected Void doInBackground(ContentValues... contentValues) {
		
		// Setting up values to insert the clicked location into SQLite database            
        getContentResolver().insert(LocationsContentProvider.CONTENT_URI, contentValues[0]);	
        
      //  getSupportLoaderManager().initLoader(0, null, this);    
        
		return null;
	}		
	
}
*/

// DISPLAYS saved info after processing in background.


public void onLoadFinished(Loader<Cursor> arg0,
		Cursor arg1) {
	
	/*
	// Number of locations available in the SQLite database table
	locationCount = arg1.getCount();
	// int loadingMessage = arg1.getCount();
	
	// Move the current record pointer to the first row of the table
	arg1.moveToFirst();
	
	//TextView  mLoadView = (TextView) findViewById(R.id.textView1);
	
	for(int i=0;i<locationCount;i++){
		
		
		// Get the latitude
		
		
		name = arg1.getString(arg1.getColumnIndex(LocationsDB.FIELD_NAME));
		
		lacomments = arg1.getString(arg1.getColumnIndex(LocationsDB.FIELD_COMMENTS));
		
		lat = arg1.getDouble(arg1.getColumnIndex(LocationsDB.FIELD_LAT));
		
		// Get the longitude
		lng = arg1.getDouble(arg1.getColumnIndex(LocationsDB.FIELD_LNG));
		
		bear = arg1.getDouble(arg1.getColumnIndex(LocationsDB.FIELD_BEAR));
		
		boomwidth = arg1.getDouble(arg1.getColumnIndex(LocationsDB.FIELD_BOOMWIDTH));
		

		
		// Get the zoom level
	
		
		// Creating an instance of LatLng to plot the location in Google Maps
		 locationEngine = new LatLng(lat, lng);
		 
	
		 
		
		// Drawing the marker in the Google Maps
		drawPolygon(locationEngine, previousPoint, boomwidth, bear);
		
		
		// Traverse the pointer to the next row
		arg1.moveToNext();
		
		 previousPoint = locationEngine;
		 
		 //display map loading
		 
		// mLoadView.setText("POINTS LOADING");
		 
		
		
		
		 
		 
	}
	
	
	
	if(locationCount>0){
		// Moving CameraPosition to last clicked position
        getMap().moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat,lng)));
        
       
        
	}	
	
	
	
	
	*/
	
}


private void drawPolygon(LatLng point,LatLng previousPoint, double boomwidth, double bearing){
	
	//make polygons here
	//do something about this lastLocation and currentLocation
	
	if(previousPoint != null){
	
		LatLng polypoint1 = SphericalUtil.computeOffset(previousPoint, (0.30480000000000002D * boomwidth * boomconversionfactor) / 2, 90 + bear);
        LatLng polypoint2 = SphericalUtil.computeOffset(previousPoint, (0.30480000000000002D * boomwidth * boomconversionfactor) / 2, bear - 90F);
        LatLng polypoint3 = SphericalUtil.computeOffset(locationEngine, (0.30480000000000002D * boomwidth * boomconversionfactor) / 2, 90 + bear);
        LatLng polypoint4 = SphericalUtil.computeOffset(locationEngine, (0.30480000000000002D * boomwidth * boomconversionfactor) / 2, bear - 90F);
    	
         Polygon polygon = getMap().addPolygon(new PolygonOptions()
         .add(polypoint1, polypoint2, polypoint4, polypoint3)
         .strokeColor(Color.RED)
         .fillColor(Color.BLUE));
	
	
	// Creating an instance of MarkerOptions
	  		} // end of doesn't equal null
}





public void onLoaderReset(Loader<Cursor> arg0) {
	// TODO Auto-generated method stub		
}	






	
	public void mapStarter() {
		// TODO Auto-generated method stub

		//Make all of this happen after hitting start
		  
		       
		        	  
		          if(location!=null) {
		              
		            
		             
		        	  
		             
		            
		        	CameraPosition cameraPosition = new CameraPosition(new LatLng(location.getLatitude(), location.getLongitude()), 17, 0, bearingorientation);
		            
		            getMap().animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		            
		            
		        	
		        	
		        	  }
		        	  
		        	  else{ 
		        	
		        	
		        		  Toast.makeText(getApplicationContext(), "No GPS Signal",
		                           Toast.LENGTH_LONG).show(); }
		
		
		
		
		
		
	}



//Location Provider storage
/*
	@Override
	public Loader<Cursor> onCreateLoader(int arg0,
			Bundle arg1) {
		
		// Uri to the content provider LocationsContentProvider
		Uri uri = LocationsContentProvider.CONTENT_URI;
		
		// Fetches all the rows from locations table
        return new CursorLoader(this, uri, null, null, null, null);

	}
*/



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	 private void loadSpinnerData() {
	        // database handler
	      
	    }


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
            long id) {
		
		 label = parent.getItemAtPosition(position).toString();
		 
      
		
		
		//fieldLoader();
		// popupWindowOpener.dismiss();
		// TODO Auto-generated method stub
		
	}
	
	 public void setLabel(String label) {
	       this.label = label;
	    }

	 public static String getLabel() {
	       return label;
	    }
	 
	 
	public void fieldLoader() {
		
		  Toast.makeText(getApplicationContext(), "LOADING " + label,
                  Toast.LENGTH_LONG).show();
		
		  //DO QUERY FOR ALL STUFF WITH THAT FIELD NAME
		//  getLoaderManager().initLoader(0, null,this); 
		  
		 //PUT A BUTTON IN POP UP VIEW THAT WILL ENGAGE THE LOADERMANAGER
		  
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}	
		
	private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
}


	class  PulseInputThread extends Thread {
		 private final PulseInput input_;
		  private final Timer timer_;
		  public float last_;
		
		
	public PulseInputThread(PulseInput input,Timer timer) {
		 input_ = input;
		    timer_ = timer;
	}

	 
	 private synchronized void read() throws ConnectionLostException {
		    TimerTask task = new TimerTask() {public void run() {
			     interrupt();
			  }};
			  
		    timer_.schedule(task, 1000);
		    try {
		      last_ = input_.getFrequencySync();
		      task.cancel();
		    } catch (InterruptedException e) {
		      last_ = 0;
		     // Log.d("BACON", "BACON");
		     // Toast.makeText(getApplicationContext(), "UNHOOKED",
	              //      Toast.LENGTH_SHORT).show();
		    }
		  }
		 
		
		  public void run() {
		    try {
		      while (true) {
		        read();
		      
		        try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		    } catch (ConnectionLostException e) {
		    	e.printStackTrace();
		    }
		  }
		

	public synchronized float getLastReading() {
	    return last_;
	  }
	
	}
	
	
	
	class Looper extends BaseIOIOLooper {
		
		PulseInputThread thread1_;
		PulseInputThread thread2_;
		PulseInputThread thread3_;
		PulseInputThread thread4_;
		PulseInputThread thread5_;
		PulseInputThread thread6_;
		PulseInputThread thread7_;
		PulseInputThread thread8_;
		
		
		@Override
		protected void setup() throws ConnectionLostException {
		
			//PulseInput	  input1_ = ioio_.openPulseInput(new DigitalInput.Spec(1,Mode.PULL_DOWN), ClockRate.RATE_62KHz,  PulseMode.FREQ, false);
			PulseInput	  input1_ = ioio_.openPulseInput(new DigitalInput.Spec(1,Mode.PULL_DOWN), ClockRate.RATE_62KHz,  PulseMode.FREQ, false);
			PulseInput  input2_ = ioio_.openPulseInput( new DigitalInput.Spec(2,Mode.PULL_DOWN ), ClockRate.RATE_62KHz,  PulseMode.FREQ, false);
			PulseInput	  input3_ = ioio_.openPulseInput(new DigitalInput.Spec(3,Mode.PULL_DOWN), ClockRate.RATE_62KHz,  PulseMode.FREQ, false);
			PulseInput  input4_ = ioio_.openPulseInput( new DigitalInput.Spec(4,Mode.PULL_DOWN ), ClockRate.RATE_62KHz,  PulseMode.FREQ, false);
			PulseInput	  input5_ = ioio_.openPulseInput(new DigitalInput.Spec(6,Mode.PULL_DOWN), ClockRate.RATE_62KHz,  PulseMode.FREQ, false);
			PulseInput  input6_ = ioio_.openPulseInput( new DigitalInput.Spec(7,Mode.PULL_DOWN ), ClockRate.RATE_62KHz,  PulseMode.FREQ, false);
			PulseInput	  input7_ = ioio_.openPulseInput(new DigitalInput.Spec(10,Mode.PULL_DOWN), ClockRate.RATE_62KHz,  PulseMode.FREQ, false);
			PulseInput  input8_ = ioio_.openPulseInput( new DigitalInput.Spec(12,Mode.PULL_DOWN ), ClockRate.RATE_62KHz,  PulseMode.FREQ, false);

			 Timer t = new Timer();
			   thread1_ = new PulseInputThread(input1_, t);
			   thread2_ = new PulseInputThread(input2_, t);
			   thread3_ = new PulseInputThread(input3_, t);
			   thread4_ = new PulseInputThread(input4_, t);
			   thread5_ = new PulseInputThread(input5_, t);
			   thread6_ = new PulseInputThread(input6_, t);
			   thread7_ = new PulseInputThread(input7_, t);
			   thread8_ = new PulseInputThread(input8_, t);
			   
			   
			   
			   
			   
			  thread1_.start();
			  thread2_.start();
			  thread3_.start();
			  thread4_.start();
			  thread5_.start();
			  thread6_.start();
			  thread7_.start();
			  thread8_.start();
			
			
		}

		
		@Override
		public void loop() throws ConnectionLostException {
			
			 setText1(thread1_.getLastReading());
			  setText2(thread2_.getLastReading());
			  setText3(thread3_.getLastReading());
			  setText4(thread4_.getLastReading());
			  setText5(thread5_.getLastReading());
			  setText6(thread6_.getLastReading());
			  setText7(thread7_.getLastReading());
			  setText8(thread8_.getLastReading());
			  
			  setTextPopulation(thread1_.getLastReading(),thread2_.getLastReading(),thread3_.getLastReading(),thread4_.getLastReading(),thread5_.getLastReading(),thread6_.getLastReading(),thread7_.getLastReading(),thread8_.getLastReading());
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		
		public void disconnected() {
			   try {
				 
			     thread1_.join();
			     thread2_.join();
			     thread3_.join();
			     thread4_.join();
			     thread5_.join();
			     thread6_.join();
			     thread7_.join();
			     thread8_.join();
			   } catch (InterruptedException e) {
			   }
			}
		
		
		
	}



@Override
protected IOIOLooper createIOIOLooper() {
	return new Looper();
}

	private void setText1(final float freqHZ1) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				
				double secondsperfoot = 1/14.6667; //14.67 is 10 mph in feed per second... No one is going to plant that fast.
				//frequencyScaling = (secondsperfoot *  (1/(rowwidth/12)) * 43560)/ (1/seedCount);
				
				
				
				double firstx = (43560/(rowInteger * (rowwidth/12)))/14.667;
				float secondx = (float) ((seedCount/firstx)/rowInteger);
				
				 mProgressStatus = freqHZ1/secondx*100;
				 mProgress.setProgress((int) mProgressStatus);
				
				
				
			}
		});
	}
	
	private void setText2( final float freqHZ2) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
			
				double firstx = (43560/(rowInteger * (rowwidth/12)))/14.667;
				float secondx = (float) ((seedCount/firstx)/rowInteger);
				
				 mProgressStatus2 = freqHZ2/secondx*100; 
				 mProgress2.setProgress((int) mProgressStatus2);
				 
				
				
				
			}
		});
	}
	private void setText3( final float freqHZ3) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				
				double firstx = (43560/(rowInteger * (rowwidth/12)))/14.667;
				float secondx = (float) ((seedCount/firstx)/rowInteger);
			
				mProgressStatus3 = freqHZ3/secondx*100; 
				 mProgress3.setProgress((int) mProgressStatus3);
				
				
				
				
			}
		});
	}
	private void setText4( final float freqHZ4) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
			
				double firstx = (43560/(rowInteger * (rowwidth/12)))/14.667;
				float secondx = (float) ((seedCount/firstx)/rowInteger);
				
				 mProgressStatus4 = freqHZ4/secondx*100; 
				 mProgress4.setProgress((int) mProgressStatus4);
				
				
				
			}
		});
	}
	private void setText5( final float freqHZ5) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
			
				double firstx = (43560/(rowInteger * (rowwidth/12)))/14.667;
				float secondx = (float) ((seedCount/firstx)/rowInteger);
				
				mProgressStatus5 = freqHZ5/secondx*100; 
				mProgress5.setProgress((int) mProgressStatus5);
				
				
				
			}
		});
	}
	private void setText6( final float freqHZ6) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
			
				double firstx = (43560/(rowInteger * (rowwidth/12)))/14.667;
				float secondx = (float) ((seedCount/firstx)/rowInteger);
				
				mProgressStatus6 = freqHZ6/secondx*100; 
				 mProgress6.setProgress((int) mProgressStatus6);
				
				
				
			}
		});
	}
	private void setText7( final float freqHZ7) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
			
				double firstx = (43560/(rowInteger * (rowwidth/12)))/14.667;
				float secondx = (float) ((seedCount/firstx)/rowInteger);
				
				 mProgressStatus7 = freqHZ7/secondx*100; 
				 mProgress7.setProgress((int) mProgressStatus7);
				
				
				
			}
		});
	}
	private void setText8( final float freqHZ8) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				
				double firstx = (43560/(rowInteger * (rowwidth/12)))/14.667;
				float secondx = (float) ((seedCount/firstx)/rowInteger);
				
				mProgressStatus8 = freqHZ8/secondx*100; 
                   mProgress8.setProgress((int) mProgressStatus8);
				
				
				
				
			}
		});
	}

	private void setTextPopulation( final float freqHZ1, final float freqHZ2,final float freqHZ3,final float freqHZ4,final float freqHZ5,final float freqHZ6,final float freqHZ7, final float freqHZ8) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				
				// population count
				if(rowInteger == 1){
					 
					// population = seeds/sec * number of rows * speed / acres traveled
					
					
					
					
					double feetpersecond = mph * 1.46667;
					double secondsperfoot = 1/feetpersecond;
					
					double populationCalculation = secondsperfoot * (1/(rowwidth/12)) * freqHZ1 * 43560;
					
					int roundpopulation = (int) Math.round(populationCalculation);
					if(metric==false){
					currentpopulation.setText("Population " + roundpopulation + "  per ac");
					}else {
						int roundmetricpopulation = (int) Math.round(populationCalculation* 2.47105);
						currentpopulation.setText("Population " + roundmetricpopulation  + " per ha");}
				}
				
if(rowInteger == 2){
					
	double feetpersecond = mph * 1.46667;
	double secondsperfoot = 1/feetpersecond;
	
	double populationCalculation = secondsperfoot * (1/(rowwidth/12))  * ((freqHZ1 + freqHZ2)/2) * 43560;
	
	int roundpopulation = (int) Math.round(populationCalculation);
	
	if(metric==false){
		currentpopulation.setText("Population " + roundpopulation + "  per ac");
		}else {
			int roundmetricpopulation = (int) Math.round(populationCalculation* 2.47105);
			currentpopulation.setText("Population " + roundmetricpopulation  + " per ha");}
					
					
					
				}
if(rowInteger == 3){
	
	
	
	double feetpersecond = mph * 1.46667;
	double secondsperfoot = 1/feetpersecond;
	
	double populationCalculation = secondsperfoot * (1/(rowwidth/12))  * ((freqHZ1 + freqHZ2 + freqHZ3)/3) * 43560;
	
	int roundpopulation = (int) Math.round(populationCalculation);
	
	if(metric==false){
		
		if(roundpopulation<=0){
			currentpopulation.setText("Population " + 0 + "  per ac");
		}
		else{
		
		currentpopulation.setText("Population " + roundpopulation + "  per ac");}
		}else {
			int roundmetricpopulation = (int) Math.round(populationCalculation* 2.47105);
			currentpopulation.setText("Population " + roundmetricpopulation  + " per ha");}
	
}
if(rowInteger == 4){
	
	
	
	double feetpersecond = mph * 1.46667;
	double secondsperfoot = 1/feetpersecond;
	
	double populationCalculation = secondsperfoot * (1/(rowwidth/12))  * ((freqHZ1 + freqHZ2 + freqHZ3 + freqHZ4)/4) * 43560;
	
	int roundpopulation = (int) Math.round(populationCalculation);
	
	if(metric==false){
		currentpopulation.setText("Population " + roundpopulation + "  per ac");
		}else {
			int roundmetricpopulation = (int) Math.round(populationCalculation* 2.47105);
			currentpopulation.setText("Population " + roundmetricpopulation  + " per ha");}
	
}
if(rowInteger == 5){
	
	
	
	double feetpersecond = mph * 1.46667;
	double secondsperfoot = 1/feetpersecond;
	
	double populationCalculation = secondsperfoot * (1/(rowwidth/12))  * ((freqHZ1 + freqHZ2 + freqHZ3 + freqHZ4 + freqHZ5)/5) * 43560;
	
	int roundpopulation = (int) Math.round(populationCalculation);
	
	if(metric==false){
		currentpopulation.setText("Population " + roundpopulation + "  per ac");
		}else {
			int roundmetricpopulation = (int) Math.round(populationCalculation* 2.47105);
			currentpopulation.setText("Population " + roundmetricpopulation  + " per ha");}
}
if(rowInteger == 6){
	
	
	
	double feetpersecond = mph * 1.46667;
	double secondsperfoot = 1/feetpersecond;
	
	double populationCalculation = secondsperfoot * (1/(rowwidth/12))  * ((freqHZ1 + freqHZ2 + freqHZ3 + freqHZ4 + freqHZ5 + freqHZ6)/6) * 43560;
	
	int roundpopulation = (int) Math.round(populationCalculation);
	
	if(metric==false){
		currentpopulation.setText("Population " + roundpopulation + "  per ac");
		}else {
			int roundmetricpopulation = (int) Math.round(populationCalculation* 2.47105);
			currentpopulation.setText("Population " + roundmetricpopulation  + " per ha");}
	
}
if(rowInteger == 7){
	
	
	
	double feetpersecond = mph * 1.46667;
	double secondsperfoot = 1/feetpersecond;
	
	double populationCalculation = secondsperfoot * (1/(rowwidth/12))  * ((freqHZ1 + freqHZ2 + freqHZ3 + freqHZ4 + freqHZ5 + freqHZ6 + freqHZ7)/7) * 43560;
	
	int roundpopulation = (int) Math.round(populationCalculation);
	
	if(metric==false){
		currentpopulation.setText("Population " + roundpopulation + "  per ac");
		}else {
			int roundmetricpopulation = (int) Math.round(populationCalculation* 2.47105);
			currentpopulation.setText("Population " + roundmetricpopulation  + " per ha");}
	
}
if(rowInteger == 8){
	
	
	
	double feetpersecond = mph * 1.46667;
	double secondsperfoot = 1/feetpersecond;
	
	double populationCalculation = secondsperfoot * (1/(rowwidth/12))  * ((freqHZ1 + freqHZ2 + freqHZ3 + freqHZ4 + freqHZ5 + freqHZ6 + freqHZ7 + freqHZ8)/8) * 43560;
	
	int roundpopulation = (int) Math.round(populationCalculation);
	if(metric==false){
		currentpopulation.setText("Population " + roundpopulation + "  per ac");
		}else {
			int roundmetricpopulation = (int) Math.round(populationCalculation* 2.47105);
			currentpopulation.setText("Population " + roundmetricpopulation  + " per ha");}
	
	
	
}
				
				
			}
		});
	}

	
	
	
	
	
	
	
}









