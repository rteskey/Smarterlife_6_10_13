package com.smarterlife.calendar;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.smarterlife.filepicker.FilePickerActivity;

import android.view.MotionEvent;

import android.R.color;
import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.opengl.Visibility;
import android.os.Looper;
import android.os.StrictMode;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsSpinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SlidingDrawer;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.ViewFlipper;

import androidwheel.ArrayWheelAdapter;
import androidwheel.OnWheelChangedListener;
import androidwheel.OnWheelScrollListener;
import androidwheel.WheelView;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddEvent {
    private Context eContext;
    private LayoutInflater inflater, inflaterCustomReminder,
            inflater_selected_files;
    private View dialoglayout, dialogCustomReminder, selected_files_screen;
    private AutoCompleteTextView text1;
    private WheelView wheelMonth;
    Context ctx;
    private EditText txtDatePopupResultMonth;
    private EditText txtDatePopupResultDay;
    private EditText txtDatePopupResultYear;
    private AutoCompleteTextView text2;
    private AutoCompleteTextView text3;
    private boolean wheelScrolled = false;
    private WheelView wheelDays;
    private WheelView wheelYears;
    private int totalDaysInMonth;
    private AlertDialog alertDate, alertCustomReminder;
    private AlertDialog alertTime;
    private Activity eActivity;
    private TextView dateStartDisplay, dateEndDisplay, timeStartDisplay,
            timeEndDisplay, reminderDisplay, reminderMonthDisplay,
            reminderYearDisplay, txttimewheeltitle;
    private String wheelMenu3[] = new String[20];
    private String wheelHoursArray[] = new String[12];
    private String wheelMinutesArray[] = new String[60];
    private String wheelAmPmArray[] = {"AM", "PM"};
    private WheelView wheelHours;
    private WheelView wheelMinutes;
    private WheelView wheelAmPm;
    private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    private SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
    private SimpleDateFormat daysFormat = new SimpleDateFormat("d");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa");
    private SimpleDateFormat hourFormat = new SimpleDateFormat("hh");
    private SimpleDateFormat minuteFormat = new SimpleDateFormat("mm");
    private SimpleDateFormat ampmFormat = new SimpleDateFormat("aa");
    private SimpleDateFormat customReminderItemFormat = new SimpleDateFormat(
            "MMMM d, yyyy hh:mm aa");
    private Calendar cal = Calendar.getInstance();
    private Calendar cremindarCal = Calendar.getInstance();
    private int currentYear = 0;
    private Button date_ok, time_ok, savebtn, cancelbtn, customreminderbtn,
            customreminderbtn_previous, customreminderbtn_next;
    // final SlidingDrawer slide, slideMap, slideReminder, slideMore;
    private EditText lbltitle;
    private CheckBox ckballdayStart, ckballdayEnd;
    private TableRow Remindertablerow, Moretablerow;
    private Animation animationLeft;
    private LinearLayout RL;

    private View location;
    private LinearLayout lyt_location;
    private EditText et_location_name;
    private EditText et_location_address1;
    private EditText et_location_address2;
    private EditText et_location_city;
    private EditText et_location_state;
    private EditText et_location_zipcode;
    private ImageView img_location_save;
    private ImageView img_location_cancel;

    private View task_location;
    private LinearLayout lyt_task_location;
    private EditText et_task_location_name;
    private EditText et_task_location_address1;
    private EditText et_task_location_address2;
    private EditText et_task_location_city;
    private EditText et_task_location_state;
    private EditText et_task_location_zipcode;
    private ImageView img_task_location_save;
    private ImageView img_task_location_cancel;


    private View reminders;
    private LinearLayout lyt_reminders;
    private ImageView img_reminders_save;
    private ImageView img_reminders_cancel;

    private View task_reminders;
    private LinearLayout lyt_task_reminders;
    private ImageView img_task_reminders_save;
    private ImageView img_task_reminders_cancel;

    private View main;
    private View more;
    private LinearLayout lyt_more;
    private View attachments;
    private LinearLayout lyt_attachments;
    private ImageView img_attachments_save;
    private ImageView img_attachments_cancel;
    private View priority;
    private LinearLayout lyt_priority;
    private ImageView img_priority_save;
    private ImageView img_priority_cancel;
    private View privacy;
    private LinearLayout lyt_privacy;
    private ImageView img_privacy_save;
    private ImageView img_privacy_cancel;
    private View attendees;
    private LinearLayout lyt_attendees;
    private ImageView img_attendees_save;
    private ImageView img_attendees_cancel;

    private View repeat_daily;
    private LinearLayout lyt_repeat_daily;
    private ImageView img_repeat_daily_save;
    private ImageView img_repeat_daily_cancel;
    private Spinner repeat_daily_type;

    private View repeat_weekly;
    private LinearLayout lyt_repeat_weekly;
    private ImageView img_repeat_weekly_save;
    private ImageView img_repeat_weekly_cancel;
    private Spinner repeat_weekly_type;

    private View repeat_monthly;
    private LinearLayout lyt_repeat_monthly;
    private ImageView img_repeat_monthly_save;
    private ImageView img_repeat_monthly_cancel;
    private Spinner repeat_monthly_type;

    private View repeat_yearly;
    private LinearLayout lyt_repeat_yearly;
    private ImageView img_repeat_yearly_save;
    private ImageView img_repeat_yearly_cancel;
    private Spinner repeat_yearly_type;

    private View event_notes;
    private LinearLayout lyt_notes;
    private ImageView img_event_notes_save;
    private ImageView img_event_notes_cancel;

    private View task_time_duration;
    private LinearLayout lyt_task_time_duration;
    private ImageView img_task_time_duration_save;
    private ImageView img_task_time_duration_cancel;

    private View task;
    private LinearLayout lyt_task;

    private static final ScheduledExecutorService Refreshactivityworker = Executors
            .newSingleThreadScheduledExecutor();
    // file picker variables
    private static final int REQUEST_PICK_FILE = 1;

    private Button btn_start_file_picker;

    private EditText et_event_title;
    public AddEvent(Context context, Activity activity) {
        eContext = context;
        ctx = eContext;
        eActivity = activity;
        populateYearArray();

        initializeControls();

        // slide = (SlidingDrawer) eActivity
        // .findViewById(R.id.event_lyt);
        final Button eventSliderbtn = (Button) eActivity
                .findViewById(R.id.btnClose);

        // View e = (View) eActivity.findViewById(R.id.popup_window);
        // e.bringToFront();
        final Dialog dialog = new Dialog(eContext);

        setDateDialog();
        setTimeDialog();

        // slideMore.setOnDrawerScrollListener(new
        // SlidingDrawer.OnDrawerScrollListener() {
        //
        //
        // public void onScrollStarted() {
        // slideMore.startAnimation(animationLeft);
        //
        // }
        //
        //
        // public void onScrollEnded() {
        //
        // }
        // });
        // intiliazing location variables

        setCustomReminderDialog();

    }

    private void initializeControls()
    // Pre: the acivity is running
    // post: all controls will be loaded into variables
    {
        lbltitle = (EditText) eActivity.findViewById(R.id.txtTitle);
        lbltitle.addTextChangedListener(textWatcher);
        InputMethodManager inputManager = (InputMethodManager) eContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(lbltitle.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        dateStartDisplay = (TextView) eActivity
                .findViewById(R.id.dateStartDisplay);

        dateEndDisplay = (TextView) eActivity.findViewById(R.id.dateEndDisplay);

        timeStartDisplay = (TextView) eActivity
                .findViewById(R.id.timeStartDisplay);

        timeEndDisplay = (TextView) eActivity.findViewById(R.id.timeEndDisplay);

        dateStartDisplay.setOnClickListener(DateDisplayListener);
        dateEndDisplay.setOnClickListener(DateDisplayListener);
        timeStartDisplay.setOnClickListener(TimeDisplayListener);
        timeEndDisplay.setOnClickListener(TimeDisplayListener);

        savebtn = (Button) eActivity.findViewById(R.id.btnsave);
        // savebtn.setOnClickListener(SaveEventListener);

        ckballdayStart = (CheckBox) eActivity
                .findViewById(R.id.timeStartallday);
        ckballdayEnd = (CheckBox) eActivity.findViewById(R.id.timeEndallday);

        ckballdayStart.setOnCheckedChangeListener(ckballdayListener);
        ckballdayEnd.setOnCheckedChangeListener(ckballdayListener);

        // slideLoc =
        // (SlidingDrawer)eActivity.findViewById(R.id.slidingDrawerloc);
        // slideLoc.bringToFront();
        // slideMap =
        // (SlidingDrawer)eActivity.findViewById(R.id.slidingDrawermap);
        // slideMap.bringToFront();


        //controls, buttons, edit text variables for event location screen
        location = eActivity.findViewById(R.id.event_location);
        lyt_location = (LinearLayout) eActivity.findViewById(R.id.lyt_location);
        lyt_location.setOnClickListener(controlsClickListener);
        et_location_name = (EditText) eActivity
                .findViewById(R.id.et_location_name);
        et_location_address1 = (EditText) eActivity
                .findViewById(R.id.et_location_address1);
        et_location_address2 = (EditText) eActivity
                .findViewById(R.id.et_location_address2);
        et_location_city = (EditText) eActivity
                .findViewById(R.id.et_location_city);
        et_location_state = (EditText) eActivity
                .findViewById(R.id.et_location_state);
        et_location_zipcode = (EditText) eActivity
                .findViewById(R.id.et_location_zipcode);
        // making the zipcode field numric
        et_location_zipcode.setInputType(InputType.TYPE_CLASS_NUMBER);
        img_location_save = (ImageView) eActivity
                .findViewById(R.id.img_location_save);
        img_location_save.setOnClickListener(controlsClickListener);
        img_location_cancel = (ImageView) eActivity
                .findViewById(R.id.img_location_cancel);
        img_location_cancel.setOnClickListener(controlsClickListener);

        //controls, edit text variables, buttons for task location screen
        task_location = eActivity.findViewById(R.id.task_location);
        lyt_task_location = (LinearLayout) eActivity.findViewById(R.id.lyt_task_location);
        lyt_task_location.setOnClickListener(controlsClickListener);
        et_task_location_name = (EditText) eActivity
                .findViewById(R.id.et_task_location_name);
        et_task_location_address1 = (EditText) eActivity
                .findViewById(R.id.et_task_location_address1);
        et_task_location_address2 = (EditText) eActivity
                .findViewById(R.id.et_task_location_address2);
        et_task_location_city = (EditText) eActivity
                .findViewById(R.id.et_task_location_city);
        et_task_location_state = (EditText) eActivity
                .findViewById(R.id.et_task_location_state);
        et_task_location_zipcode = (EditText) eActivity
                .findViewById(R.id.et_task_location_zipcode);
        // making the zipcode field numric
        et_task_location_zipcode.setInputType(InputType.TYPE_CLASS_NUMBER);
        img_task_location_save = (ImageView) eActivity
                .findViewById(R.id.img_task_location_save);
        img_task_location_save.setOnClickListener(controlsClickListener);
        img_task_location_cancel = (ImageView) eActivity
                .findViewById(R.id.img_task_location_cancel);
        img_task_location_cancel.setOnClickListener(controlsClickListener);



        reminders = eActivity.findViewById(R.id.event_reminders);
        lyt_reminders = (LinearLayout) eActivity
                .findViewById(R.id.lyt_reminders);
        lyt_reminders.setOnClickListener(controlsClickListener);
        img_reminders_save = (ImageView) eActivity
                .findViewById(R.id.img_reminders_save);
        img_reminders_save.setOnClickListener(controlsClickListener);
        img_reminders_cancel = (ImageView) eActivity
                .findViewById(R.id.img_reminders_cancel);
        img_reminders_cancel.setOnClickListener(controlsClickListener);

        task_reminders = eActivity.findViewById(R.id.task_reminders);
        lyt_task_reminders = (LinearLayout) eActivity
                .findViewById(R.id.lyt_task_reminders);
        lyt_task_reminders.setOnClickListener(controlsClickListener);
        img_task_reminders_save = (ImageView) eActivity
                .findViewById(R.id.img_task_reminders_save);
        img_task_reminders_save.setOnClickListener(controlsClickListener);
        img_task_reminders_cancel = (ImageView) eActivity
                .findViewById(R.id.img_task_reminders_cancel);
        img_task_reminders_cancel.setOnClickListener(controlsClickListener);


        event_notes = eActivity.findViewById(R.id.event_notes);
        lyt_notes = (LinearLayout) eActivity
                .findViewById(R.id.lyt_notes);
        lyt_notes.setOnClickListener(controlsClickListener);
        img_event_notes_save = (ImageView) eActivity
                .findViewById(R.id.img_event_notes_save);
        img_event_notes_save.setOnClickListener(controlsClickListener);
        img_event_notes_cancel = (ImageView) eActivity
                .findViewById(R.id.img_event_notes_cancel);
        img_event_notes_cancel.setOnClickListener(controlsClickListener);


        attachments = eActivity.findViewById(R.id.event_attachments);
        lyt_attachments = (LinearLayout) eActivity
                .findViewById(R.id.lyt_attachments);
        lyt_attachments.setOnClickListener(controlsClickListener);
        img_attachments_save = (ImageView) eActivity
                .findViewById(R.id.img_attachments_save);
        img_attachments_save.setOnClickListener(controlsClickListener);
        img_attachments_cancel = (ImageView) eActivity
                .findViewById(R.id.img_attachments_cancel);
        img_attachments_cancel.setOnClickListener(controlsClickListener);

        main = (View) eActivity
                .findViewById(R.id.activity_cal_event_firstscreen);

        more = (View) eActivity
                .findViewById(R.id.activity_cal_event_secondscreen);
        lyt_more = (LinearLayout) eActivity.findViewById(R.id.lyt_more);
        lyt_more.setOnClickListener(controlsClickListener);

        animationLeft = AnimationUtils.loadAnimation(eActivity,
                R.anim.layout_left_animation);
        customreminderbtn = (Button) eActivity
                .findViewById(R.id.btnremindercustom);
        customreminderbtn.setOnClickListener(CustomReminderbtnListener);

        btn_start_file_picker = (Button) eActivity
                .findViewById(R.id.start_file_picker_button);

        btn_start_file_picker.setOnClickListener(controlsClickListener);
        inflater_selected_files = eActivity.getLayoutInflater();
        selected_files_screen = inflater_selected_files.inflate(
                R.layout.event_attachments, null);

        priority = eActivity.findViewById(R.id.event_priority);
        lyt_priority = (LinearLayout) eActivity.findViewById(R.id.lyt_priority);
        lyt_priority.setOnClickListener(controlsClickListener);
        img_priority_save = (ImageView) eActivity
                .findViewById(R.id.img_priority_save);
        img_priority_save.setOnClickListener(controlsClickListener);
        img_priority_cancel = (ImageView) eActivity
                .findViewById(R.id.img_priority_cancel);
        img_priority_cancel.setOnClickListener(controlsClickListener);

        privacy = eActivity.findViewById(R.id.event_privacy);
        lyt_privacy = (LinearLayout) eActivity.findViewById(R.id.lyt_privacy);
        lyt_privacy.setOnClickListener(controlsClickListener);
        img_privacy_save = (ImageView) eActivity
                .findViewById(R.id.img_privacy_save);
        img_privacy_save.setOnClickListener(controlsClickListener);
        img_privacy_cancel = (ImageView) eActivity
                .findViewById(R.id.img_privacy_cancel);
        img_privacy_cancel.setOnClickListener(controlsClickListener);

        attendees = eActivity.findViewById(R.id.event_attendees);
        lyt_attendees = (LinearLayout) eActivity
                .findViewById(R.id.lyt_attendees);
        lyt_attendees.setOnClickListener(controlsClickListener);
        img_attendees_save = (ImageView) eActivity
                .findViewById(R.id.img_attendees_save);
        img_attendees_save.setOnClickListener(controlsClickListener);
        img_attendees_cancel = (ImageView) eActivity
                .findViewById(R.id.img_attendees_cancel);
        img_attendees_cancel.setOnClickListener(controlsClickListener);

        task_time_duration = eActivity.findViewById(R.id.task_time_duration);
        lyt_task_time_duration = (LinearLayout) eActivity
                .findViewById(R.id.lyt_task_time_duration);
        lyt_task_time_duration.setOnClickListener(controlsClickListener);
        img_task_time_duration_save = (ImageView) eActivity
                .findViewById(R.id.img_task_time_duration_save);
        img_task_time_duration_save.setOnClickListener(controlsClickListener);
        img_task_time_duration_cancel = (ImageView) eActivity
                .findViewById(R.id.img_task_time_duration_cancel);
        img_task_time_duration_cancel.setOnClickListener(controlsClickListener);


        task = eActivity.findViewById(R.id.task_main);
        lyt_task = (LinearLayout) eActivity.findViewById(R.id.lyt_tasks);
        lyt_task.setOnClickListener(controlsClickListener);


        repeat_daily = eActivity.findViewById(R.id.event_repeat_daily);
        lyt_repeat_daily = (LinearLayout) eActivity.findViewById(R.id.lyt_repeat_daily);
        lyt_repeat_daily.setOnClickListener(controlsClickListener);
        img_repeat_daily_save = (ImageView) eActivity
                .findViewById(R.id.img_repeat_daily_save);
        img_repeat_daily_save.setOnClickListener(controlsClickListener);
        img_repeat_daily_cancel = (ImageView) eActivity
                .findViewById(R.id.img_repeat_daily_cancel);
        img_repeat_daily_cancel.setOnClickListener(controlsClickListener);

        repeat_daily_type = (Spinner) eActivity.findViewById(R.id.sp_repeat_daily_type);
        repeat_daily_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (parentView.getItemAtPosition(position).toString().equalsIgnoreCase("Weekly")) {

                    repeat_weekly.setVisibility(View.VISIBLE);
                    repeat_daily.setVisibility(View.INVISIBLE);
                } else if (parentView.getItemAtPosition(position).toString().equalsIgnoreCase("Monthly")) {

                    repeat_monthly.setVisibility(View.VISIBLE);
                    repeat_daily.setVisibility(View.INVISIBLE);
                } else if (parentView.getItemAtPosition(position).toString().equalsIgnoreCase("Yearly")) {

                    repeat_yearly.setVisibility(View.VISIBLE);
                    repeat_daily.setVisibility(View.INVISIBLE);
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // repeat_daily.setVisibility(View.INVISIBLE);
            }

        });

        repeat_weekly = eActivity.findViewById(R.id.event_repeat_weekly);
        lyt_repeat_weekly = (LinearLayout) eActivity.findViewById(R.id.lyt_repeat_weekly);
        lyt_repeat_weekly.setOnClickListener(controlsClickListener);
        img_repeat_weekly_save = (ImageView) eActivity
                .findViewById(R.id.img_repeat_weekly_save);
        img_repeat_weekly_save.setOnClickListener(controlsClickListener);
        img_repeat_weekly_cancel = (ImageView) eActivity
                .findViewById(R.id.img_repeat_weekly_cancel);
        img_repeat_weekly_cancel.setOnClickListener(controlsClickListener);

        repeat_weekly_type = (Spinner) eActivity.findViewById(R.id.sp_repeat_weekly_type);
        repeat_weekly_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (parentView.getItemAtPosition(position).toString().equalsIgnoreCase("Daily")) {
                    repeat_daily.setVisibility(View.VISIBLE);
                    repeat_weekly.setVisibility(View.INVISIBLE);
                } else if (parentView.getItemAtPosition(position).toString().equalsIgnoreCase("Monthly")) {
                    repeat_monthly.setVisibility(View.VISIBLE);
                    repeat_weekly.setVisibility(View.INVISIBLE);
                } else if (parentView.getItemAtPosition(position).toString().equalsIgnoreCase("Yearly")) {
                    repeat_yearly.setVisibility(View.VISIBLE);
                    repeat_weekly.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                //repeat_weekly.setVisibility(View.INVISIBLE);
            }
        });

        repeat_monthly = eActivity.findViewById(R.id.event_repeat_monthly);
        lyt_repeat_monthly = (LinearLayout) eActivity.findViewById(R.id.lyt_repeat_monthly);
        lyt_repeat_monthly.setOnClickListener(controlsClickListener);
        img_repeat_monthly_save = (ImageView) eActivity
                .findViewById(R.id.img_repeat_monthly_save);
        img_repeat_monthly_save.setOnClickListener(controlsClickListener);
        img_repeat_monthly_cancel = (ImageView) eActivity
                .findViewById(R.id.img_repeat_monthly_cancel);
        img_repeat_monthly_cancel.setOnClickListener(controlsClickListener);

        repeat_monthly_type = (Spinner) eActivity.findViewById(R.id.sp_repeat_monthly_type);
        repeat_monthly_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (parentView.getItemAtPosition(position).toString().equalsIgnoreCase("Daily")) {
                    repeat_daily.setVisibility(View.VISIBLE);
                    repeat_monthly.setVisibility(View.INVISIBLE);
                } else if (parentView.getItemAtPosition(position).toString().equalsIgnoreCase("Weekly")) {
                    repeat_weekly.setVisibility(View.VISIBLE);
                    repeat_monthly.setVisibility(View.INVISIBLE);
                } else if (parentView.getItemAtPosition(position).toString().equalsIgnoreCase("Yearly")) {
                    repeat_yearly.setVisibility(View.VISIBLE);
                    repeat_monthly.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                //repeat_monthly.setVisibility(View.INVISIBLE);
            }
        });
        repeat_yearly = eActivity.findViewById(R.id.event_repeat_yearly);
        lyt_repeat_yearly = (LinearLayout) eActivity.findViewById(R.id.lyt_repeat_yearly);
        lyt_repeat_yearly.setOnClickListener(controlsClickListener);
        img_repeat_yearly_save = (ImageView) eActivity
                .findViewById(R.id.img_repeat_yearly_save);
        img_repeat_yearly_save.setOnClickListener(controlsClickListener);
        img_repeat_yearly_cancel = (ImageView) eActivity
                .findViewById(R.id.img_repeat_yearly_cancel);
        img_repeat_yearly_cancel.setOnClickListener(controlsClickListener);

        repeat_yearly_type = (Spinner) eActivity.findViewById(R.id.sp_repeat_yearly_type);
        repeat_yearly_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (parentView.getItemAtPosition(position).toString().equalsIgnoreCase("Daily")) {
                    repeat_daily.setVisibility(View.VISIBLE);
                    repeat_yearly.setVisibility(View.INVISIBLE);
                } else if (parentView.getItemAtPosition(position).toString().equalsIgnoreCase("Weekly")) {
                    repeat_weekly.setVisibility(View.VISIBLE);
                    repeat_yearly.setVisibility(View.INVISIBLE);
                } else if (parentView.getItemAtPosition(position).toString().equalsIgnoreCase("Monthly")) {
                    repeat_monthly.setVisibility(View.VISIBLE);
                    repeat_yearly.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                //repeat_yearly.setVisibility(View.INVISIBLE);
            }
        });
        
        et_event_title = (EditText)eActivity.findViewById(R.id.txtTitle); 

    }

    private OnClickListener controlsClickListener = new OnClickListener() {

        @Override
        public void onClick(View l) {
            // TODO Auto-generated method stub
            switch (l.getId()) {

                case R.id.lyt_location:
                    location.setVisibility(View.VISIBLE);
                    break;
                case R.id.img_location_save:
                    location.setVisibility(View.INVISIBLE);
                    break;
                case R.id.img_location_cancel:
                    // clear the form then hide it
                    et_location_name.setText("");
                    et_location_address1.setText("");
                    et_location_address2.setText("");
                    et_location_city.setText("");
                    et_location_state.setText("");
                    et_location_zipcode.setText("");
                    location.setVisibility(View.INVISIBLE);
                    break;

                case R.id.lyt_task_location:
                    task_location.setVisibility(View.VISIBLE);
                    break;
                case R.id.img_task_location_save:
                    task_location.setVisibility(View.INVISIBLE);
                    break;
                case R.id.img_task_location_cancel:
                    // clear the form then hide it
                    et_task_location_name.setText("");
                    et_task_location_address1.setText("");
                    et_task_location_address2.setText("");
                    et_task_location_city.setText("");
                    et_task_location_state.setText("");
                    et_task_location_zipcode.setText("");
                    task_location.setVisibility(View.INVISIBLE);
                    break;

                case R.id.lyt_reminders:
                    reminders.setVisibility(View.VISIBLE);
                    break;
                case R.id.img_reminders_save:
                    reminders.setVisibility(View.INVISIBLE);
                    break;
                case R.id.img_reminders_cancel:
                    // more code to be added here
                    reminders.setVisibility(View.INVISIBLE);
                    break;

                case R.id.lyt_task_reminders:
                    task_reminders.setVisibility(View.VISIBLE);
                    break;
                case R.id.img_task_reminders_save:
                    task_reminders.setVisibility(View.INVISIBLE);
                    break;
                case R.id.img_task_reminders_cancel:
                    // more code to be added here
                    task_reminders.setVisibility(View.INVISIBLE);
                    break;

                case R.id.lyt_task_time_duration:
                    task_time_duration.setVisibility(View.VISIBLE);
                    break;
                case R.id.img_task_time_duration_save:
                    task_time_duration.setVisibility(View.INVISIBLE);
                    break;
                case R.id.img_task_time_duration_cancel:
                    // more code to be added here
                    task_time_duration.setVisibility(View.INVISIBLE);
                    break;

                case R.id.lyt_tasks:
                    task.setVisibility(View.VISIBLE);
                    main.setVisibility(View.INVISIBLE);
                    break;

                case R.id.lyt_more:
                    more.setVisibility(View.VISIBLE);
                    main.setVisibility(View.INVISIBLE);
                    break;

                case R.id.lyt_attachments:
                    attachments.setVisibility(View.VISIBLE);
                    break;
                case R.id.img_attachments_save:
                    attachments.setVisibility(View.INVISIBLE);
                    break;
                case R.id.img_attachments_cancel:
                    // more code to be added here
                    View insertPoint = eActivity
                            .findViewById(R.id.lyt_selected_files);
                    // make sure the returned objects from the upload activity are
                    // also removed
                    ((ViewGroup) insertPoint).removeAllViews();
                    attachments.setVisibility(View.INVISIBLE);
                    break;
                case R.id.lyt_priority:
                    priority.setVisibility(View.VISIBLE);
                    break;
                case R.id.img_priority_save:
                    priority.setVisibility(View.INVISIBLE);
                    break;
                case R.id.img_priority_cancel:
                    priority.setVisibility(View.INVISIBLE);
                    break;
                case R.id.lyt_privacy:
                    privacy.setVisibility(View.VISIBLE);
                    break;
                case R.id.img_privacy_save:
                    privacy.setVisibility(View.INVISIBLE);
                    break;
                case R.id.img_privacy_cancel:
                    privacy.setVisibility(View.INVISIBLE);
                    break;
                case R.id.lyt_attendees:
                    attendees.setVisibility(View.VISIBLE);
                    break;
                case R.id.img_attendees_save:
                    attendees.setVisibility(View.INVISIBLE);
                    break;
                case R.id.img_attendees_cancel:
                    attendees.setVisibility(View.INVISIBLE);
                    break;
                case R.id.lyt_repeat_daily:
                    repeat_daily.setVisibility(View.VISIBLE);
                    repeat_weekly.setVisibility(View.INVISIBLE);
                    break;
                case R.id.img_repeat_daily_save:
                    repeat_daily.setVisibility(View.INVISIBLE);
                    break;
                case R.id.img_repeat_daily_cancel:
                    repeat_daily.setVisibility(View.INVISIBLE);
                    break;

                case R.id.lyt_repeat_weekly:
                    repeat_weekly.setVisibility(View.VISIBLE);
                    repeat_daily.setVisibility(View.INVISIBLE);
                    break;
                case R.id.img_repeat_weekly_save:
                    repeat_weekly.setVisibility(View.INVISIBLE);
                    break;
                case R.id.img_repeat_weekly_cancel:
                    repeat_weekly.setVisibility(View.INVISIBLE);
                    break;

                case R.id.lyt_repeat_monthly:
                    repeat_monthly.setVisibility(View.VISIBLE);
                    repeat_daily.setVisibility(View.INVISIBLE);
                    break;
                case R.id.img_repeat_monthly_save:
                    repeat_monthly.setVisibility(View.INVISIBLE);
                    break;
                case R.id.img_repeat_monthly_cancel:
                    repeat_monthly.setVisibility(View.INVISIBLE);
                    break;
                case R.id.lyt_repeat_yearly:
                    repeat_yearly.setVisibility(View.VISIBLE);
                    repeat_daily.setVisibility(View.INVISIBLE);
                    break;
                case R.id.img_repeat_yearly_save:
                    repeat_yearly.setVisibility(View.INVISIBLE);
                    break;
                case R.id.img_repeat_yearly_cancel:
                    repeat_yearly.setVisibility(View.INVISIBLE);
                    break;

                case R.id.lyt_notes:
                    event_notes.setVisibility(View.VISIBLE);
                    break;
                case R.id.img_event_notes_save:
                    event_notes.setVisibility(View.INVISIBLE);
                    break;
                case R.id.img_event_notes_cancel:
                    event_notes.setVisibility(View.INVISIBLE);
                    break;
                case R.id.start_file_picker_button:
                    // Create a new Intent for the file picker activity
                    Intent intent = new Intent(eActivity, FilePickerActivity.class);
                    // accepted format
                    ArrayList<String> extensions = new ArrayList<String>();
                    extensions.add(".jpg");
                    extensions.add(".gif");
                    extensions.add(".png");
                    extensions.add(".csv");
                    extensions.add(".txt");
                    extensions.add(".doc");
                    intent.putExtra(
                            FilePickerActivity.EXTRA_ACCEPTED_FILE_EXTENSIONS,
                            extensions);

                    // Start the activity
                    eActivity.startActivityForResult(intent, REQUEST_PICK_FILE);
                    break;

            }

        }

    };

    private void injectDailyRepeat() {
        // TODO Auto-generated method stub

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        int x = 5;
        if (resultCode == eActivity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_PICK_FILE:
                    if (data.hasExtra(FilePickerActivity.EXTRA_FILE_PATH)) {
                        // Get the file path
                        File f = new File(
                                data.getStringExtra(FilePickerActivity.EXTRA_FILE_PATH));

                        String[] path = f.getPath().split("/");
                        String file_name = path[path.length - 1];

                        LayoutInflater vi = (LayoutInflater) eActivity
                                .getApplicationContext().getSystemService(
                                        Context.LAYOUT_INFLATER_SERVICE);
                        View v = vi.inflate(R.layout.lyt_selected_files, null);

                        // fill in any details dynamically here
                        TextView txt_file = (TextView) v
                                .findViewById(R.id.selected_item);
                        txt_file.setText(file_name);

                        // insert into the list of selected item
                        View insertPoint = eActivity
                                .findViewById(R.id.lyt_selected_files);
                        ((ViewGroup) insertPoint).addView(v);
                    }
            }
        }
    }

    private OnCheckedChangeListener ckballdayListener = new OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            if (isChecked) {
                Toast.makeText(ctx, buttonView.getTag() + " ",
                        Toast.LENGTH_SHORT).show();

                if (buttonView.getTag().toString().equals("alldayStart")) {
                    timeStartDisplay.setVisibility(View.GONE);

                } else
                    timeEndDisplay.setVisibility(View.GONE);
            } else {
                if (buttonView.getTag().toString().equals("alldayStart"))
                    timeStartDisplay.setVisibility(View.VISIBLE);
                else
                    timeEndDisplay.setVisibility(View.VISIBLE);

            }

        }

    };

    private OnClickListener DateDisplayListener = new OnClickListener() {
        String yearStr = "";
        int month = 0;
        int year = 0;
        int days = 0;

        public void onClick(View v) {

            try {

                if (v.getTag().toString().trim().contains("dateStartDisplay")) {
                    cal.setTime(dateFormat.parse(dateStartDisplay.getText()
                            .toString().trim()));
                    year = cal.get(Calendar.YEAR);
                    days = cal.get(Calendar.DAY_OF_MONTH);
                    month = cal.get(Calendar.MONTH);
                    date_ok.setText("Set Start Date");
                } else {
                    cal.setTime(dateFormat.parse(dateEndDisplay.getText()
                            .toString().trim()));
                    year = cal.get(Calendar.YEAR);
                    days = cal.get(Calendar.DAY_OF_MONTH);
                    month = cal.get(Calendar.MONTH);
                    date_ok.setText("Set End Date");
                }
            } catch (NumberFormatException e) {
                // year = Calendar.YEAR;
                month = 0;

            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //

            wheelMonth.setCurrentItem(month);
            wheelDays.setCurrentItem(days - 1);
            wheelYears.setCurrentItem(Math.abs(year
                    - Integer.parseInt(wheelMenu3[0])));

            alertDate.show();
        }

    };

    private OnClickListener TimeDisplayListener = new OnClickListener() {
        int hours = 0
                ,
                minutes = 0;
        String ampm = "";

        public void onClick(View v) {

            if (v.getTag().toString().trim().contains("timeStartDisplay")) {
                time_ok.setText("Set Start Time");
                hours = Integer.parseInt(timeStartDisplay.getText().toString()
                        .trim().substring(0, 2));
                minutes = Integer.parseInt(timeStartDisplay.getText()
                        .toString().trim().substring(3, 5));
                ampm = timeStartDisplay.getText().toString().trim()
                        .substring(6, 8);
            } else {
                time_ok.setText("Set End Time");
                hours = Integer.parseInt(timeEndDisplay.getText().toString()
                        .trim().substring(0, 2));
                minutes = Integer.parseInt(timeEndDisplay.getText().toString()
                        .trim().substring(3, 5));
                ampm = timeEndDisplay.getText().toString().trim()
                        .substring(6, 8);
            }

            wheelHours.setCurrentItem(hours - 1);
            wheelMinutes.setCurrentItem(minutes);
            wheelAmPm.setCurrentItem(ampm == "AM" ? 0 : 1);
            alertTime.show();
        }
    };

    public void AddNewEvent() {
        // TODO we have to set the time and date for todays date and current
        // ti2me or clicked time..
        // open the sliding drawer
        // have the user add new Event fields
        // when save we have to take event's value fields and save to to DB
        // calling json web services..
        // close sliding drawer. on save or close..
        // show event on date or time clicked on the event view..
        // alert.show();
        dateStartDisplay.setText(getCurrentDateString());
        dateEndDisplay.setText(getCurrentDateString());

        timeStartDisplay.setText(getCurrentTimeString(0));
        timeEndDisplay.setText(getCurrentTimeString(1));
        //collecting event data 
      
    }

    public void EditEvent() {
        // TODO get the event Id

        // alert.show();

    }

    private OnClickListener SaveEventListener = new OnClickListener() {
    //
    
    // SimpleDateFormat datetimeFormatnum = new
    // SimpleDateFormat("MM/dd/yyyy hh:mm aa");
    // SimpleDateFormat dateFormatnum = new
    // SimpleDateFormat("MMMM d, yyyy hh:mm aa");
    // java.util.Date dateStart = null;
    // java.util.Date dateEnd = null;
    // public void onClick(View v) {
    // _eventData.setEventid( 0);
    // //_eventData.CalendarID = 1;
    // //_eventData.EventData = "";

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Event _eventData = new Event();
		_eventData.setEventName(et_event_title.getText().toString().trim());
		
	}
    
    // "New evnt": lbltitle.getText().toString().trim());
    // try {
    // dateStart=
    // dateFormatnum.parse(dateStartDisplay.getText().toString().trim() + " "+
    // timeStartDisplay.getText().toString().trim());
    // dateEnd = dateFormatnum.parse(
    // dateEndDisplay.getText().toString().trim()+
    // " "+timeEndDisplay.getText().toString().trim());
    // _eventData.setStartDateTime( datetimeFormatnum.format(dateStart));
    // _eventData.EventEndDate= datetimeFormatnum.format(dateEnd);
    //
    // } catch (ParseException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    //
    //
    // SaveNewEvent saveNewEvent = new SaveNewEvent(eContext, eActivity,
    // _eventData);
    // saveNewEvent.execute(new String[] {
    // "http://036d4b1.netsolhost.com/smartlife/CalendarService.svc/json/SaveEvent"
    // });
    // slide.animateClose();
    // onRestart();
    //
    // }
    //
    //
     };

    OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
        public void onScrollStarts(WheelView wheel) {
            // wheelScrolled = true;
        }

        public void onScrollEnds(WheelView wheel) {
            wheelScrolled = false;
            Toast.makeText(ctx.getApplicationContext(), wheel.getId() + "",
                    Toast.LENGTH_SHORT).show();
            // updateStatus();

        }

        public void onScrollingStarted(WheelView wheel) {
            // TODO Auto-generated method stub

        }

        public void onScrollingFinished(WheelView wheel) {
            // TODO Auto-generated method stub

            if (wheel.getTag().toString().trim().contains("month")
                    || wheel.getTag().toString().trim().contains("year")) {
                updateStatusMonth();
            }
        }
    };

    // Wheel changed listener
    private final OnWheelChangedListener changedListener = new OnWheelChangedListener() {
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            if (!wheelScrolled) {
                // updateStatus();
            }
        }
    };

    /**
     * Updates entered PIN status
     */
    private void updateStatus() {

        txtDatePopupResultMonth
                .setText(wheelMenu1[wheelMonth.getCurrentItem()]);
        txtDatePopupResultDay.setText(wheelMenu2[wheelDays.getCurrentItem()]);
        txtDatePopupResultYear.setText(wheelMenu3[wheelYears.getCurrentItem()]);

        int month = wheelMonth.getCurrentItem();
        Calendar mycal = new GregorianCalendar(
                Integer.parseInt(wheelMenu3[wheelYears.getCurrentItem()]),
                month, 1);

        // Get the number of days in that month
        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

        // initWheel2(wheelMonth,daysInMonth ); // infinite Loop

        text1.setText(wheelMenu1[wheelMonth.getCurrentItem()]);
        text1.setSelection(wheelMenu1[wheelMonth.getCurrentItem()].length());
        text1.dismissDropDown();

        text2.setText(wheelMenu2[wheelDays.getCurrentItem()]);
        text2.setSelection(wheelMenu2[wheelDays.getCurrentItem()].length());

        text3.setText(wheelMenu3[wheelYears.getCurrentItem()]);
        text3.setSelection(wheelMenu3[wheelYears.getCurrentItem()].length());

    }

    private void updateStatusMonth() {

        txtDatePopupResultMonth.setText(String.valueOf(wheelMonth
                .getCurrentItem() + 1));
        txtDatePopupResultDay.setText(wheelMenu2[wheelDays.getCurrentItem()]);
        txtDatePopupResultYear.setText(wheelMenu3[wheelYears.getCurrentItem()]);

        int month = wheelMonth.getCurrentItem();
        Calendar mycal = new GregorianCalendar(
                Integer.parseInt(wheelMenu3[wheelYears.getCurrentItem()]),
                month, 1);

        // Get the number of days in that month
        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

        initWheel2(wheelDays, daysInMonth);

    }

    private void initWheel1() {

        ArrayWheelAdapter AWA = new ArrayWheelAdapter(ctx, wheelMenu1);

        wheelMonth.setViewAdapter(AWA);
        wheelMonth.setVisibleItems(2);
        wheelMonth.setCurrentItem(0);
        wheelMonth.addChangingListener(changedListener);
        wheelMonth.addScrollingListener(scrolledListener);
    }

    private void initWheel2(WheelView wheel, int daysInMonth) {
        // WheelView wheel = (WheelView) findViewById(id);
        totalDaysInMonth = daysInMonth;
        int selectedIndex = wheel.getCurrentItem();

        if (selectedIndex > daysInMonth - 1)
            selectedIndex = daysInMonth - 1;

        wheel.setViewAdapter(new ArrayWheelAdapter(ctx,
                getTotalDaysInMonth(daysInMonth)));

        wheel.setVisibleItems(2);
        wheel.setCurrentItem(0);
        wheel.addChangingListener(changedListener);
        wheel.addScrollingListener(scrolledListener);
        wheel.setCurrentItem(selectedIndex);

        ArrayAdapter<String> daysAdapter = new ArrayAdapter<String>(
                ctx.getApplicationContext(), R.layout.hintdropdown,
                getTotalDaysInMonth(daysInMonth));
        text2.setAdapter(daysAdapter);

    }

    private void initWheel3(WheelView wheel) {

        wheel.setViewAdapter(new ArrayWheelAdapter(ctx, wheelMenu3));
        wheel.setVisibleItems(2);
        wheel.setCurrentItem(0);
        wheel.addChangingListener(changedListener);
        wheel.addScrollingListener(scrolledListener);
    }

    private void populateYearArray() {
        Calendar calendar = Calendar.getInstance();
        int startYear = calendar.get(Calendar.YEAR) - 2;
        for (int i = 0; i < wheelMenu3.length; i++)
            wheelMenu3[i] = String.valueOf(i + startYear);

    }

    private void populateHoursArray() {

        for (int i = 0; i < wheelHoursArray.length; i++)
            if (i < 9)
                wheelHoursArray[i] = "0" + String.valueOf(i + 1);
            else
                wheelHoursArray[i] = String.valueOf(i + 1);

    }

    private void populateMinutesArray() {

        for (int i = 0; i < wheelMinutesArray.length; i++)
            if (i < 10)
                wheelMinutesArray[i] = "0" + String.valueOf(i);
            else
                wheelMinutesArray[i] = String.valueOf(i);
    }

    private String[] getTotalDaysInMonth(int daysInMonth) {
        if (daysInMonth == 28)
            return month28;
        if (daysInMonth == 29)
            return month29;
        if (daysInMonth == 30)
            return month30;
        else
            return month31;

    }

    private String getCurrentDateString() {
        Calendar currentCalendar = Calendar.getInstance();
        currentYear = currentCalendar.get(Calendar.YEAR);
        return dateFormat.format(currentCalendar.getTimeInMillis());
    }

    private String getCurrentTimeString(int s) {
        // s=0 for star time s=1 for end time
        Calendar currentCalendar = Calendar.getInstance();
        int hours = currentCalendar.get(Calendar.HOUR_OF_DAY);
        int minutes = currentCalendar.get(Calendar.MINUTE);

        if (minutes < 30) {
            currentCalendar.setTimeInMillis(currentCalendar.getTimeInMillis()
                    + (30 - minutes) * 60 * 1000);
            // currentCalendar.setTimeInMillis(currentCalendar.getTimeInMillis()
            // + 30*60*100);
        }
        if (minutes > 30) {
            currentCalendar.setTimeInMillis(currentCalendar.getTimeInMillis()
                    + (60 - minutes) * 60 * 1000);
            // currentCalendar.setTimeInMillis(currentCalendar.getTimeInMillis()
            // + 30*60*100);
        }
        if (s == 1)
            currentCalendar
                    .setTimeInMillis(currentCalendar.getTimeInMillis() + 30 * 60 * 1000);

        return timeFormat.format(currentCalendar.getTimeInMillis());
    }

    String wheelMenu1[] = new String[]{"January", "February", "March",
            "April", "May", "June", "July", "August", "September", "October",
            "November", "December"};
    String wheelMenu2[] = new String[]{"1", "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
            "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
            "30", "31"};

    String month28[] = new String[]{"1", "2", "3", "4", "5", "6", "7", "8",
            "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
            "20", "21", "22", "23", "24", "25", "26", "27", "28"};
    String month29[] = new String[]{"1", "2", "3", "4", "5", "6", "7", "8",
            "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
            "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"};
    String month30[] = new String[]{"1", "2", "3", "4", "5", "6", "7", "8",
            "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
            "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
    String month31[] = new String[]{"1", "2", "3", "4", "5", "6", "7", "8",
            "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
            "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31"};

    private OnClickListener CustomReminderbtnListener = new OnClickListener() {

        public void onClick(View v) {
            Calendar cCal = Calendar.getInstance();

            PopulateCalendar();
            alertCustomReminder.show();

        }

    };

    private void setCustomReminderDialog() {
        inflaterCustomReminder = eActivity.getLayoutInflater();
        dialogCustomReminder = inflaterCustomReminder.inflate(
                R.layout.customreminderlayout, null);
        final AlertDialog.Builder builderCustomReminder = new AlertDialog.Builder(
                eContext);
        builderCustomReminder.setView(dialogCustomReminder);
        alertCustomReminder = builderCustomReminder.create();

    }

    private void setDateDialog() {
        inflater = eActivity.getLayoutInflater();

        dialoglayout = inflater.inflate(R.layout.datewheellayout, null);// ,
        // (ViewGroup) eActivity.getCurrentFocus(), false);

        final AlertDialog.Builder builder = new AlertDialog.Builder(eContext);
        builder.setView(dialoglayout);
        alertDate = builder.create();

        if (dialoglayout.getParent() != null)
            dialoglayout = null;

        text1 = (AutoCompleteTextView) dialoglayout.findViewById(R.id.r1);
        wheelMonth = (WheelView) dialoglayout.findViewById(R.id.p1);
        initWheel1();

        if (dialoglayout.getParent() != null)
            dialoglayout = null;

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(
                ctx.getApplicationContext(), R.layout.hintdropdown, wheelMenu1);
        text1.setAdapter(monthAdapter);
        text1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                v.setActivated(true);
            }

        });
        text1.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub
                for (int i = 0; i < wheelMenu1.length; i++) {
                    if (wheelMenu1[i].toString().startsWith(s.toString())
                            && count > 2) {
                        wheelMonth.setCurrentItem(i);

                    }

                }

            }
        });
        text2 = (AutoCompleteTextView) dialoglayout.findViewById(R.id.r2);
        text2.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub
                if (count > 0)
                    if (Integer.parseInt(s.toString()) > 0
                            && Integer.parseInt(s.toString()) < totalDaysInMonth + 1) {
                        wheelDays.setCurrentItem(Integer.parseInt(s.toString()) - 1);

                    }
                //

            }
        });

        text3 = (AutoCompleteTextView) dialoglayout.findViewById(R.id.r3);
        text3.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

                //

            }
        });

        txtDatePopupResultMonth = (EditText) dialoglayout
                .findViewById(R.id.resultMonth);
        txtDatePopupResultDay = (EditText) dialoglayout
                .findViewById(R.id.resultDay);
        txtDatePopupResultYear = (EditText) dialoglayout
                .findViewById(R.id.resultYear);
        wheelMonth = (WheelView) dialoglayout.findViewById(R.id.p1);
        wheelDays = (WheelView) dialoglayout.findViewById(R.id.p2);
        wheelYears = (WheelView) dialoglayout.findViewById(R.id.p3);
        initWheel1();
        initWheel2(wheelDays, 31);
        initWheel3(wheelYears);

        if (dialoglayout.getParent() == null)
            builder.setView(dialoglayout);
        else {
            dialoglayout = null;
            builder.setView(dialoglayout);
        }

        ArrayWheelAdapter AWAMonth = new ArrayWheelAdapter(ctx, wheelMenu1);
        wheelMonth.setViewAdapter(AWAMonth);

        date_ok = (Button) dialoglayout.findViewById(R.id.date_ok_btn);

        date_ok.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (date_ok.getText().toString().trim()
                        .contains("Set Start Date")) {
                    dateStartDisplay.setText(wheelMenu1[wheelMonth
                            .getCurrentItem()]
                            + " "
                            + wheelMenu2[wheelDays.getCurrentItem()]
                            + ", "
                            + wheelMenu3[wheelYears.getCurrentItem()]);
                }

                {
                    dateEndDisplay.setText(wheelMenu1[wheelMonth
                            .getCurrentItem()]
                            + " "
                            + wheelMenu2[wheelDays.getCurrentItem()]
                            + ", "
                            + wheelMenu3[wheelYears.getCurrentItem()]);
                }
                alertDate.dismiss();
            }

        });

    }

    private void setTimeDialog() {
        inflater = eActivity.getLayoutInflater();

        dialoglayout = inflater.inflate(R.layout.timewheellayout, null);
        // (ViewGroup) eActivity.getCurrentFocus(), false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(eContext);
        builder.setView(dialoglayout);
        alertTime = builder.create();

        if (dialoglayout.getParent() != null)
            dialoglayout = null;

        populateHoursArray();
        populateMinutesArray();
        wheelHours = (WheelView) dialoglayout.findViewById(R.id.t1);
        wheelMinutes = (WheelView) dialoglayout.findViewById(R.id.t2);
        wheelAmPm = (WheelView) dialoglayout.findViewById(R.id.t3);
        initHoursWheel(wheelHours);
        initMinutesWheel(wheelMinutes);
        initAmPmWheel(wheelAmPm);

        txttimewheeltitle = (TextView) dialoglayout
                .findViewById(R.id.txttimewheeltitle);
        time_ok = (Button) dialoglayout.findViewById(R.id.time_ok_btn);

        time_ok.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (time_ok.getText().toString().trim()
                        .contains("Set Start Time")) {
                    timeStartDisplay.setText(wheelHoursArray[wheelHours
                            .getCurrentItem()]
                            + ":"
                            + wheelMinutesArray[wheelMinutes.getCurrentItem()]
                            + " " + wheelAmPmArray[wheelAmPm.getCurrentItem()]);
                }

                if (time_ok.getText().toString().equals("Set End Time")) {
                    timeEndDisplay.setText(wheelHoursArray[wheelHours
                            .getCurrentItem()]
                            + ":"
                            + wheelMinutesArray[wheelMinutes.getCurrentItem()]
                            + " " + wheelAmPmArray[wheelAmPm.getCurrentItem()]);
                }

                if (time_ok.getText().toString().trim()
                        .equals("Set reminder time")) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis((Long) txttimewheeltitle.getTag());
                    cal.set(Calendar.HOUR, Integer
                            .parseInt(wheelHoursArray[wheelHours
                                    .getCurrentItem()]));
                    cal.set(Calendar.MINUTE, Integer
                            .parseInt(wheelMinutesArray[wheelMinutes
                                    .getCurrentItem()]));
                    cal.set(Calendar.AM_PM, wheelAmPmArray[wheelAmPm
                            .getCurrentItem()] == "AM" ? 0 : 1);

                    BuildCustomReminderItem(cal.getTimeInMillis());
                }

                alertTime.dismiss();
            }

        });

    }

    private void initHoursWheel(WheelView wheelHours) {
        // TODO Auto-generated method stub
        wheelHours.setViewAdapter(new ArrayWheelAdapter(ctx, wheelHoursArray));
        wheelHours.setVisibleItems(2);
        wheelHours.setCurrentItem(0);

    }

    private void initMinutesWheel(WheelView wheelMunites) {
        // TODO Auto-generated method stub

        wheelMunites.setViewAdapter(new ArrayWheelAdapter(ctx,
                wheelMinutesArray));
        wheelMunites.setVisibleItems(2);
        wheelMunites.setCurrentItem(0);
        wheelMunites.addChangingListener(changedListener);
        wheelMunites.addScrollingListener(scrolledListener);

    }

    private void initAmPmWheel(WheelView wheelAmPM) {
        // TODO Auto-generated method stub
        wheelAmPM.setViewAdapter(new ArrayWheelAdapter(ctx, wheelAmPmArray));
        wheelAmPM.setVisibleItems(2);
        wheelAmPM.setCurrentItem(0);
        wheelAmPM.addChangingListener(changedListener);
        wheelAmPM.addScrollingListener(scrolledListener);

    }

    public void onRestart() {
        // TODO Auto-generated method stub

        Runnable task = new Runnable() {
            public void run() {
                SharedPreferences daysettings = eContext.getSharedPreferences(
                        "month", 0);
                String month = daysettings.getString("month", "month");
                ;
                SharedPreferences monthsettings = eContext
                        .getSharedPreferences("day", 0);
                int day = monthsettings.getInt("day", 0);
                SharedPreferences yearsettings = eContext.getSharedPreferences(
                        "year", 0);
                int year = yearsettings.getInt("year", 0);
                Toast.makeText(eContext, day + "" + month + "" + year,
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(); // your class
                intent.setClassName(eContext, eActivity.getClass().getName());
                intent.putExtra("day", day);
                intent.putExtra("month", month); // wDays[0] //
                // gCal.get(Calendar.MONTH)
                intent.putExtra("year", year);

                eActivity.startActivity(intent);
                eActivity.finish();
            }
        };

        Refreshactivityworker.schedule(task, 3, TimeUnit.SECONDS);
    }

    private TextWatcher textWatcher = new TextWatcher() {

        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // after text changed
            int spaceindex = 0;
            spaceindex = s.toString().trim().lastIndexOf(" ");

            String text = s.toString().trim();
            ArrayList arlistText = new ArrayList();
            String[] StArText;// = text.split("\\s+");
            Calendar cal = null;
            Calendar calCurrrent = Calendar.getInstance();
            if (spaceindex > -1) // if spaceindex >0 => multiple words in phrase
            {
                StArText = text.split("\\s+");
                for (int i = 0; i < StArText.length; i++) {
                    String word = StArText[i];
                    int wordInt = 0;
                    if (word.equalsIgnoreCase("at")) {
                        if (StArText.length > i + 1)

                            try {
                                cal = praseNext(StArText[i + 1]);
                                if (cal.getTimeInMillis() < calCurrrent
                                        .getTimeInMillis()) //
                                { // this mean time is in past so add 12 hrs to
                                    // flip it to next 12 hrs peroid
                                    cal.add(Calendar.HOUR, 12);

                                }

                                if (cal != null) {
                                    int hourint = cal.get(Calendar.HOUR);

                                    if (hourint == 0)
                                        hourint = 12;

                                    String hours = hourint < 10 ? "0"
                                            + String.valueOf(hourint) : String
                                            .valueOf(hourint);
                                    String minute = cal.get(Calendar.MINUTE) < 10 ? "0"
                                            + String.valueOf(cal
                                            .get(Calendar.MINUTE))
                                            : String.valueOf(cal
                                            .get(Calendar.MINUTE));
                                    String ampm = cal.get(Calendar.AM_PM) == 0 ? "AM"
                                            : "PM";
                                    timeStartDisplay.setText(hours + ":"
                                            + minute + " " + ampm);

                                    cal.add(Calendar.HOUR, 1); // set the end
                                    // time, add 1
                                    // hour to
                                    // current
                                    // calendar
                                    hourint = cal.get(Calendar.HOUR);

                                    if (hourint == 0)
                                        hourint = 12;
                                    hours = hourint < 10 ? "0"
                                            + String.valueOf(hourint) : String
                                            .valueOf(hourint);
                                    ampm = cal.get(Calendar.AM_PM) == 0 ? "AM"
                                            : "PM";
                                    timeEndDisplay.setText(hours + ":" + minute
                                            + " " + ampm);
                                    // Toast.makeText(ctx,
                                    // "cal.get(Calendar.HOUR)   "+
                                    // cal.get(Calendar.HOUR)
                                    // +"cal.get(Calendar.minute) "+
                                    // cal.get(Calendar.MINUTE),
                                    // Toast.LENGTH_LONG).show();
                                }
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                Toast.makeText(
                                        ctx,
                                        "onTextChanged   "
                                                + e.getMessage().toString(),
                                        Toast.LENGTH_LONG).show();

                            }

                        // TODO Auto-generated catch block

                    }
                }

                arlistText.add(text.split("\\s+"));

                text = text.substring(text.lastIndexOf(" "), text.length());

                Scanner titlescanner = new Scanner(s.toString());
            } else // one word sentence
            {
                if (text.toLowerCase() == "at") {

                }

            }

            // Toast.makeText(ctx, "onTextChanged "+ s.toString() + " start "+
            // start + " before "+ before + "count "+ count,
            // Toast.LENGTH_LONG).show();

        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        public void afterTextChanged(Editable s) {
            // Toast.makeText(ctx, "afterTextChanged "+ s.toString(),
            // Toast.LENGTH_LONG).show();
        }
    };

    protected Calendar praseNext(String nextWord) throws ParseException {
        // first we have to prase for time

        Calendar cal = Calendar.getInstance();
        int timeInt = -1;
        int length = nextWord.length();
        int hours = -1, minutes = -1;

        try {
            timeInt = Integer.parseInt(nextWord);

        } catch (NumberFormatException e) {

        }
        if (timeInt > -1 && timeInt < 25) {
            hours = timeInt;
            minutes = 0;

        }
        if (timeInt > -1 && length == 3) {
            hours = Integer.parseInt(nextWord.substring(0, 1));
            minutes = Integer.parseInt(nextWord.substring(1, 3));

        }

        if (timeInt > -1 && length == 4) {
            hours = Integer.parseInt(nextWord.substring(0, 2));
            minutes = Integer.parseInt(nextWord.substring(2, 4));

        }

        if (hours > 23 || hours < 0 || minutes > 60 || minutes < 0) {
            cal = null;
        } else
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH), hours, minutes, 0);

        // if(cal.get(Calendar.HOUR_OF_DAY)<7 && cal.get(Calendar.HOUR_OF_DAY)
        // >=0 )
        // cal.set(Calendar.AM_PM, 1);
        // else cal.set(Calendar.AM_PM, 0);

        return cal;
    }

    private View.OnClickListener CReminderPrevoiusNextMouthClick = new OnClickListener() {

        public void onClick(View v) {
            if (v.getTag().equals("creminderbtn_previous")) {
                cremindarCal.add(Calendar.MONTH, -1);
            } else
                cremindarCal.add(Calendar.MONTH, +1);

            PopulateCalendar();
        }

    };
    private String[] monthslist = {"January", "February", "March", "April",
            "May", "June", "July", "August", "September", "October",
            "November", "December"};

    private ArrayList<Long> customReminderSelectedDate = new ArrayList<Long>();

    private void PopulateCalendar() {

        Calendar calendar = Calendar.getInstance();

        int cMonth = 0;
        calendar.add(Calendar.MONTH, cMonth);
        Calendar currentCalendar = Calendar.getInstance();

        Calendar.getInstance();
        final Calendar gCal = new GregorianCalendar(
                cremindarCal.get(Calendar.YEAR),
                cremindarCal.get(Calendar.MONTH), 1);// (calendar.get(Calendar.DAY_OF_MONTH))+(cWeek)
        // //(2013,
        // Calendar.JANUARY, 1);
        // gCal =cremindarCal ;
        int currentDay = gCal.get(Calendar.DAY_OF_WEEK);
        // backtracks to the beginning of current week (Sunday)
        final int backtrackdays = Calendar.SUNDAY - currentDay;
        gCal.add(Calendar.DAY_OF_YEAR, Calendar.SUNDAY - currentDay);

        // Toast.makeText(c.getApplicationContext(),"backtrackdays: "+
        // backtrackdays + "currentDay:  "+ currentDay,
        // Toast.LENGTH_SHORT).show();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMMMMMMMM");
        SimpleDateFormat year_date = new SimpleDateFormat("yyyy");
        // wDays[0] = month_date.format(gCal.getTime());
        String weekEndingMth = "";
        try {
            // wDate[0] = Integer.parseInt(year_date.format(gCal.getTime())) ;
        } catch (NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        // String month=month_date.format(calendar.getTime())+"  " +
        // calendar.get(Calendar.MONTH) +" "+ calendar.get(Calendar.YEAR);
        int yearindex = calendar.get(Calendar.YEAR);

        // EditText edtext = (EditText) v.findViewById(R.id.editText1);
        // /edtext.setText(month);
        RL = (LinearLayout) dialogCustomReminder
                .findViewById(R.id.cremindermonthcalendarLayout);

        dialogCustomReminder.destroyDrawingCache();
        LinearLayout.LayoutParams rlParams = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        // RL.setGravity(Gravity.CENTER_HORIZONTAL);
        // rl.alignWithParent= true;
        reminderMonthDisplay = (TextView) dialogCustomReminder
                .findViewById(R.id.txtcremindermonth);
        reminderYearDisplay = (TextView) dialogCustomReminder
                .findViewById(R.id.txtcreminderyear);
        customreminderbtn_previous = (Button) dialogCustomReminder
                .findViewById(R.id.creminderbtn_previous);
        customreminderbtn_next = (Button) dialogCustomReminder
                .findViewById(R.id.creminderbtn_next);
        reminderMonthDisplay.setText(monthslist[cremindarCal
                .get(Calendar.MONTH)].toString());
        reminderMonthDisplay.setTag(String.valueOf(cremindarCal
                .get(Calendar.MONTH)));
        reminderYearDisplay.setText(String.valueOf(cremindarCal
                .get(Calendar.YEAR)));
        customreminderbtn_previous
                .setOnClickListener(CReminderPrevoiusNextMouthClick);
        customreminderbtn_next
                .setOnClickListener(CReminderPrevoiusNextMouthClick);
        // vf = (ViewFlipper)
        // dialogCustomReminder.findViewById(R.id.view_flipper);
        // vf.setOnTouchListener(onTouchEvent);

        TableLayout table = new TableLayout(ctx);
        table.setStretchAllColumns(true);
        table.setShrinkAllColumns(true);

        TableRow rowTitle = new TableRow(ctx);
        // rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        TableRow rowDayLabels = new TableRow(ctx);
        // title column/row
        TableRow.LayoutParams paramsMonth = new TableRow.LayoutParams();
        paramsMonth.span = 3;
        TableRow.LayoutParams paramsYear = new TableRow.LayoutParams();
        paramsYear.span = 2;
        Spinner title_month = new Spinner(ctx);

        title_month.setAdapter(new ArrayAdapter<String>(ctx,
                android.R.layout.simple_list_item_checked, monthslist));
        // title_month.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title_month.setGravity(Gravity.LEFT);
        boolean monthSelected = false;

        title_month.setSelection(calendar.get(Calendar.MONTH));

        // title_month.setOnItemSelectedListener(this);
        String spinnerMonth = title_month.getSelectedItem().toString();

        // Toast.makeText(c.getApplicationContext(),title_month.getChildCount()+"v.toString()",
        // Toast.LENGTH_SHORT).show();
        // title_month.setBackgroundResource(drawable.btn_blue_states);

        Integer[] yearlist = {2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018,
                2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028,
                2029, 2030};
        Spinner title_year = new Spinner(ctx);
        title_year.setAdapter(new ArrayAdapter<Integer>(ctx,
                android.R.layout.simple_list_item_checked, yearlist));
        // title_year.setSelection(yearindex);
        title_year.setGravity(Gravity.RIGHT);
        // title_year.setBackgroundResource(drawable.btn_blue_states);
        Integer spinnerYear = (Integer) title_year.getSelectedItem();

        Button submitbtn = new Button(ctx);
        submitbtn.setText("G0");
        // submitbtn.setBackgroundResource(drawable.btn_green_glossy);
        submitbtn.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                // Intent intent = new
                // Intent().setClass(ctx.getApplicationContext(),
                // Monthview.class);

                // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //
                //
            }

        });

        table.addView(rowTitle);

        // Days Row
        TableRow daysNameRow = new TableRow(ctx);
        String[] dayslist = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i = 0; i < dayslist.length; i++) {// add Days Name Row..
            TextView daysTxt = new TextView(ctx);
            // daysTxt.setBackgroundResource(drawable.day_bkg);
            daysTxt.setTypeface(Typeface.DEFAULT_BOLD);
            // daysTxt.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            daysTxt.setTextColor(Color.BLACK);
            daysTxt.setText(dayslist[i]);
            // daysTxt.setGravity(Gravity.CENTER_HORIZONTAL);
            daysTxt.setGravity(Gravity.CENTER);
            daysNameRow.addView(daysTxt);

        }
        table.addView(daysNameRow);

        int monthBegin = 0;
        final int monthEnd = gCal.getActualMaximum(Calendar.DAY_OF_MONTH);

        boolean before = true;

        RelativeLayout.LayoutParams dayParams = new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, 60);

        int gridSizeX = 7, gridSizeY = 6;
        for (int i = 0; i < gridSizeY; i++) {
            TableRow rowDays = new TableRow(ctx);
            // TableRow.LayoutParams TblRowParams = new TableRow.LayoutParams();
            final int ii = i;
            for (int j = 0; j < gridSizeX; j++) {

                final int jj = j;

                RelativeLayout RL_day = new RelativeLayout(ctx);

                final Button daybtn = new Button(ctx);
                daybtn.setGravity(Gravity.CENTER);
                daybtn.setPadding(0, 20, 0, 0);
                Button daybtn_event = new Button(ctx);
                daybtn_event.setText("12");
                daybtn_event.setTextSize(15);

                daybtn.setText(gCal.get(Calendar.DAY_OF_MONTH) + "");
                daybtn.setId(gCal.get(Calendar.MONTH));
                daybtn.setTag(gCal.getTimeInMillis());
                // Toast.makeText(c.getApplicationContext(), "M "+
                // gCal.get(Calendar.MONTH) + " Y "+ gCal.get(Calendar.YEAR)
                // ,Toast.LENGTH_SHORT ).show();
                daybtn.setOnLongClickListener(new OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        Calendar rCal = Calendar.getInstance();
                        // rCal.setTimeInMillis((Long)v.getTag());
                        int hours = 0;
                        int minutes = 0;
                        int ampm = 0;

                        if (ItemSelectedExist((Long) v.getTag())) {
                            // daybtn.setBackgroundResource(drawable.btn_day_states);
                            RemoveCustomReminderItem((Long) v.getTag());
                        } else {
                            hours = rCal.get(Calendar.HOUR);
                            minutes = rCal.get(Calendar.MINUTE);
                            ampm = rCal.get(Calendar.AM_PM);

                            daybtn.setBackgroundColor(Color.GREEN);
                            time_ok.setText("Set reminder time");
                            txttimewheeltitle.setText(String.valueOf(dateFormat
                                    .format((Long) v.getTag())));
                            txttimewheeltitle.setTag((Long) v.getTag());
                            // BuildCustomReminderItem( (Long)v.getTag());
                            wheelHours.setCurrentItem(hours - 1);
                            wheelMinutes.setCurrentItem(minutes);
                            wheelAmPm.setCurrentItem(ampm);
                            alertTime.show();
                        }
                        return true;
                    }
                });

                daybtn.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        Calendar rCal = Calendar.getInstance();
                        // rCal.setTimeInMillis((Long)v.getTag());

                        int hours = 0;
                        int minutes = 0;
                        int ampm = 0;

                        if (ItemSelectedExist((Long) v.getTag())) {
                            // daybtn.setBackgroundResource(drawable.btn_day_states);
                            RemoveCustomReminderItem((Long) v.getTag());
                        } else {
                            hours = rCal.get(Calendar.HOUR_OF_DAY);
                            minutes = rCal.get(Calendar.MINUTE);
                            ampm = rCal.get(Calendar.AM_PM);

                            daybtn.setBackgroundColor(Color.GREEN);
                            BuildCustomReminderItem((Long) v.getTag());

                        }

                    }

                });

                // daybtn.setBackgroundResource(drawable.btn_day_states);
                daybtn.setTextColor(Color.BLACK);

                if (currentCalendar.get(Calendar.DAY_OF_MONTH) == gCal
                        .get(Calendar.DAY_OF_MONTH) && cMonth == 1)
                    // daybtn.setBackgroundResource(drawable.current_day);

                    // if(currentCalendar.get(Calendar.DAY_OF_MONTH) >
                    // gCal.get(Calendar.DAY_OF_MONTH) && cMonth ==0 )
                    // daybtn.setBackgroundResource(drawable.btn_army_glossy);
                    // daybtn.setLayoutParams(rl);
                    daybtn.bringToFront();
                RelativeLayout.LayoutParams event_params = new RelativeLayout.LayoutParams(
                        30, 30);
                event_params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                event_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                // event_params.addRule(RelativeLayout.ABOVE);
                event_params.addRule(Gravity.CENTER_HORIZONTAL);
                daybtn_event.setTextSize(8);
                // daybtn_event.setBackgroundResource(drawable.btn_green_glossy);
                event_params.setMargins(0, 0, 0, 75);

                RL_day.addView(daybtn, dayParams);
                // RL_day.addView(daybtn_event,event_params ) ;
                rowDays.addView(RL_day);
                daybtn_event.bringToFront();
                // rowDays.addView(daybtn_event,event_params );

                // fill in your cell with this value
                // System.out.print("Day of month:"+gCal.get(Calendar.DAY_OF_MONTH)
                // + " position: "+position );
                // wDate.add(j, gCal.get(Calendar.DAY_OF_MONTH)); // wDate[j]

                // System.out.print(" ");

                // add one to the day and keep going
                gCal.add(Calendar.DAY_OF_YEAR, 1);
                weekEndingMth = month_date.format(gCal.getTime());
            }
            table.addView(rowDays);
            // wDate[i]= gCal.get(Calendar.DAY_OF_MONTH);
            System.out.println();
        }

        if (RL.getChildCount() == 2)
            RL.removeViewAt(1);
        RL.addView(table, rlParams);

        // Toast.makeText(ctx,RL.getChildCount()+" ",Toast.LENGTH_SHORT).show();

    }

    private void BuildCustomReminderItems() {
        ScrollView SV = (ScrollView) dialogCustomReminder
                .findViewById(R.id.customselecteditemsv);
        // SV.setLayoutParams(new
        // LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        LinearLayout LL = (LinearLayout) dialogCustomReminder
                .findViewById(R.id.customreminderitemsll);
        // LL.setOrientation(LinearLayout.VERTICAL);
        // LL.setLayoutParams(new
        // LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        if (LL.getChildCount() > 0)
            LL.removeAllViews();

        ArrangeArrayDates();
        for (int i = 0; i < customReminderSelectedDate.size(); i++) {
            final ViewFlipper rVF = new ViewFlipper(ctx);
            rVF.setOnLongClickListener(new OnLongClickListener() {

                public boolean onLongClick(View v) {
                    // TODO Auto-generated method stub

                    if (rVF.getDisplayedChild() == 0) {
                        rVF.setOutAnimation(ctx, R.anim.out_to_left);
                        rVF.setInAnimation(ctx, R.anim.in_from_right);

                        rVF.showPrevious();
                        // rVF.setBackgroundResource(R.drawable.btn_day_states);
                    }

                    // if///(rVF.getDisplayedChild()==1)
                    else {

                        rVF.setInAnimation(ctx, R.anim.in_from_left);
                        rVF.setOutAnimation(ctx, R.anim.out_to_right);
                        rVF.showNext();
                        // rVF.setBackgroundResource(R.drawable.btn_darkgray_states);
                    }

                    return false;
                }

            });

            // rVF.setBackgroundResource(R.drawable.btn_darkgray_states);

            LinearLayout customReminderItemlayout = new LinearLayout(ctx);
            LinearLayout customReminderItemlayoutEdit = new LinearLayout(ctx);
            LinearLayout.LayoutParams rll = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams rItem = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            rItem.gravity = Gravity.CENTER_HORIZONTAL;

            rll.gravity = Gravity.CENTER_HORIZONTAL;
            rll.leftMargin = 6;
            rll.rightMargin = 6;
            customReminderItemlayout.setLayoutParams(rll);
            customReminderItemlayout.setGravity(Gravity.CENTER_HORIZONTAL);

            customReminderItemlayoutEdit.setLayoutParams(rll);
            rVF.setLayoutParams(rll);

            customReminderItemlayout.setOrientation(LinearLayout.HORIZONTAL);
            customReminderItemlayoutEdit
                    .setOrientation(LinearLayout.HORIZONTAL);
            TextView txtCustomReminderItem = new TextView(ctx);
            txtCustomReminderItem.setTextSize(20);
            txtCustomReminderItem.setGravity(Gravity.CENTER_HORIZONTAL);

            txtCustomReminderItem.setText(customReminderItemFormat
                    .format(customReminderSelectedDate.get(i)) + "");

            TextView txtCustomReminderItemEdit = new TextView(ctx);
            txtCustomReminderItemEdit.setText("Edit");
            customReminderItemlayoutEdit.addView(txtCustomReminderItemEdit,
                    rItem);

            customReminderItemlayout.addView(txtCustomReminderItem, rItem);

            rVF.addView(customReminderItemlayout);
            rVF.addView(customReminderItemlayoutEdit);

            LL.addView(rVF);
            LL.refreshDrawableState();

            SV.refreshDrawableState();
            // Toast.makeText(ctx, "Added on control "+ i,
            // Toast.LENGTH_SHORT).show();
        }
        SV.invalidate();
        // SV.addView(LL);
        // RL.addView(SV);
    }

    private void RemoveCustomReminderItem(long dateinMills) {
        Calendar Caldate = Calendar.getInstance();
        Caldate.setTimeInMillis(dateinMills);

        for (int i = 0; i < customReminderSelectedDate.size(); i++) {
            Calendar CaldateAry = Calendar.getInstance();
            CaldateAry.setTimeInMillis(customReminderSelectedDate.get(i));
            boolean sameDay = Caldate.get(Calendar.YEAR) == CaldateAry
                    .get(Calendar.YEAR)
                    && Caldate.get(Calendar.DAY_OF_YEAR) == CaldateAry
                    .get(Calendar.DAY_OF_YEAR);
            if (sameDay) {
                customReminderSelectedDate.remove(CaldateAry.getTimeInMillis());
            }

        }
        BuildCustomReminderItems();

    }

    private void BuildCustomReminderItem(long dateinMills) {

        Calendar Caldate = Calendar.getInstance();
        Caldate.setTimeInMillis(dateinMills);
        customReminderSelectedDate.add(Caldate.getTimeInMillis());
        // Toast.makeText(ctx, "Added toi Array "+ Caldate.getTimeInMillis()+
        // " Count Ar "+customReminderSelectedDate.size() ,
        // Toast.LENGTH_SHORT).show();
        // ArrangeArrayDates();
        BuildCustomReminderItems();

    }

    private boolean ItemSelectedExist(long dateinMills) {
        boolean IsExist = false;
        Calendar Caldate = Calendar.getInstance();
        Caldate.setTimeInMillis(dateinMills);

        for (int i = 0; i < customReminderSelectedDate.size(); i++) {
            Calendar CaldateAry = Calendar.getInstance();
            CaldateAry.setTimeInMillis(customReminderSelectedDate.get(i));
            boolean sameDay = Caldate.get(Calendar.YEAR) == CaldateAry
                    .get(Calendar.YEAR)
                    && Caldate.get(Calendar.DAY_OF_YEAR) == CaldateAry
                    .get(Calendar.DAY_OF_YEAR);
            if (sameDay) {
                IsExist = true;
            }
        }

        return IsExist;

    }

    private void ArrangeArrayDates() {

        Long[] customReminderSelectedDateArranged;
        customReminderSelectedDateArranged = new Long[customReminderSelectedDate
                .size()];
        for (int i = 0; i < customReminderSelectedDate.size(); i++) {
            customReminderSelectedDateArranged[i] = customReminderSelectedDate
                    .get(i);

        }

        Arrays.sort(customReminderSelectedDateArranged);
        customReminderSelectedDate.clear();

        for (int i = 0; i < customReminderSelectedDateArranged.length; i++) {
            customReminderSelectedDate
                    .add(customReminderSelectedDateArranged[i]);

        }

    }
    // private ViewFlipper rVF;
    // private float lastX;
    //
    // private OnTouchListener onTouchEvent = new OnTouchListener(){
    //
    // public boolean onTouch(View v, MotionEvent touchevent) {
    // // TODO Auto-generated method stub
    //
    //
    // rVF.setInAnimation(ctx, R.anim.in_from_left);
    // rVF.setOutAnimation(ctx, R.anim.out_to_right);
    // rVF.showNext();
    // switch (touchevent.getAction())
    // {
    // case MotionEvent.ACTION_DOWN:
    // {
    // lastX = touchevent.getX();
    // break;
    // }
    // case MotionEvent.ACTION_UP:
    // {
    // float currentX = touchevent.getX();
    // if (lastX < currentX)
    // {
    // if (rVF.getDisplayedChild()==0) break;
    // rVF.setInAnimation(ctx, R.anim.in_from_left);
    // rVF.setOutAnimation(ctx, R.anim.out_to_right);
    // rVF.showNext();
    // }
    // if (lastX > currentX)
    // {
    // if (rVF.getDisplayedChild()==1) break;
    // rVF.setInAnimation(ctx, R.anim.in_from_right);
    // rVF.setOutAnimation(ctx, R.anim.out_to_left);
    // rVF.showPrevious();
    // }
    // break;
    // }
    // }
    // return false;
    // }
    //
    //
    //
    //
    //
    // };

}
