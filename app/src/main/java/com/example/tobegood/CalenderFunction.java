package com.example.tobegood;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.CalendarContract;
import android.text.TextUtils;
import android.util.Log;

import java.util.Calendar;
import java.util.TimeZone;

public class CalenderFunction {
    private static String CALENDER_URL = "content://com.android.calendar/calendars";
    private static String CALENDER_EVENT_URL = "content://com.android.calendar/events";
    private static String CALENDER_REMINDER_URL = "content://com.android.calendar/reminders";

    private static int getCalendarAccount(Context context) {
        int oldId = checkCalendarAccount(context);
        if (oldId >= 0) {
            return oldId;
        } else {
            long addId = createCalendarAccount(context);
            if (addId >= 0)
                return checkCalendarAccount(context);
            else
                return -1;
        }
    }

    private static int checkCalendarAccount(Context context) {
        try (Cursor cursor = context.getContentResolver().query(Uri.parse(CALENDER_URL),
                null, null, null, null);) {
            if (cursor == null)
                return -1;
            int count = cursor.getCount();
            if (count > 0) {
                // 存在现有账户，取第一个账户的id返回
                cursor.moveToFirst();
                return cursor.getInt(cursor.getColumnIndex(CalendarContract.Calendars._ID));
            } else {
                return -1;
            }
        }
    }

    private static long createCalendarAccount(Context context) {
        String CALENDARS_NAME = "To be good";
        String CALENDARS_ACCOUNT_NAME = "It's time to eat.@Tobegood";
        String CALENDARS_DISPLAY_NAME = "It's time to eat.";

        // 账户数据
        ContentValues value = new ContentValues();
        value.put(CalendarContract.Calendars.ACCOUNT_TYPE, CalendarContract.ACCOUNT_TYPE_LOCAL);
        value.put(CalendarContract.Calendars.NAME, CALENDARS_NAME);
        value.put(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME);
        value.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, CALENDARS_DISPLAY_NAME);
        value.put(CalendarContract.Calendars.VISIBLE, 1);
        value.put(CalendarContract.Calendars.CALENDAR_COLOR, Color.BLUE);
        value.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
        // 同步此日历到设备上
        value.put(CalendarContract.Calendars.SYNC_EVENTS, 1);
        // 日历时区
        value.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, TimeZone.getDefault().getID());
        // 拥有者的账户
        value.put(CalendarContract.Calendars.OWNER_ACCOUNT, CALENDARS_ACCOUNT_NAME);
        // 可以响应事件
        value.put(CalendarContract.Calendars.CAN_ORGANIZER_RESPOND, 1);
        // 设置允许提醒的方式
        value.put(CalendarContract.Calendars.ALLOWED_REMINDERS, "0,1,2,3,4");
        // 设置日历支持的可用性类型
        value.put(CalendarContract.Calendars.ALLOWED_AVAILABILITY, "0,1,2");
        // 设置日历允许的出席者类型
        value.put(CalendarContract.Calendars.ALLOWED_ATTENDEE_TYPES, "0,1,2");

        // 系统日历表
        Uri calendarUri = Uri.parse(CALENDER_URL);
        /**
         *  TIP: 修改或添加ACCOUNT_NAME只能由SYNC_ADAPTER调用
         *  对uri设置CalendarContract.CALLER_IS_SYNCADAPTER为true,即标记当前操作为SYNC_ADAPTER操作
         * 在设置CalendarContract.CALLER_IS_SYNCADAPTER为true时,必须带上参数ACCOUNT_NAME和ACCOUNT_TYPE(任意)
         */
        calendarUri = calendarUri.buildUpon()
                .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME)
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, CalendarContract.Calendars.CALENDAR_LOCATION)
                .build();

        Uri accountUri;
        // 检查日历权限
        if (PackageManager.PERMISSION_GRANTED == context.checkSelfPermission("android.permission.WRITE_CALENDAR"))
            accountUri = context.getContentResolver().insert(calendarUri, value);
        else
            return -2;

        return accountUri == null ? -1 : ContentUris.parseId(accountUri);
    }

    /**
     * 添加日历事件
     */
    public static boolean addCalendarEvent(Context context, String title, String description, int hour, int advancedMinutes) {
        if (context == null)
            return false;

        // 获取账户id失败直接返回
        int calId = getCalendarAccount(context);
        if (calId < 0)
            return false;

        Calendar mCalendar = Calendar.getInstance();
        // 设置开始时间
        long time = System.currentTimeMillis();
        mCalendar.setTimeInMillis(time);
        mCalendar.set(Calendar.HOUR_OF_DAY, 0);
        mCalendar.set(Calendar.HOUR, 0);
        mCalendar.set(Calendar.MINUTE, 0);
        mCalendar.set(Calendar.SECOND, 0);
        mCalendar.set(Calendar.MILLISECOND, 0);
/*        TimeZone curTimeZone = TimeZone.getTimeZone("GMT+1");
        Calendar calendar = Calendar.getInstance(curTimeZone);
        long time=System.currentTimeMillis();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);*/
        Log.d("current time", mCalendar.toString());
        // 设置事件发生时间为中午十二点
        long start = mCalendar.getTime().getTime() + hour * 60 * 60 * 1000 + advancedMinutes * 60 * 1000;
        // 设置终止时间，开始时间加一个小时
        mCalendar.setTimeInMillis(start + 60 * 60 * 1000);
        long end = mCalendar.getTime().getTime();

        // 组装日历事件
        ContentValues event = new ContentValues();
        event.put(CalendarContract.Events.CALENDAR_ID, calId);
        event.put(CalendarContract.Events.TITLE, title);
        event.put(CalendarContract.Events.DESCRIPTION, description);
        event.put(CalendarContract.Events.DTSTART, start);
        event.put(CalendarContract.Events.DTEND, end);
        event.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
        // 1-confirmed 2-canceled
        event.put(CalendarContract.Events.STATUS, 1);
        event.put(CalendarContract.Events.HAS_ALARM, true);
//        // 设置事件忙
//        event.put(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);

        //添加事件
        Uri newEvent = context.getContentResolver().insert(Uri.parse(CALENDER_EVENT_URL), event);
        // 添加日历事件失败直接返回
        if (newEvent == null)
            return false;

        // 事件提醒的设定
        ContentValues reminder1 = new ContentValues();
        // 插入events表 得到eventId也就是对应在events表中自动生成的id字段
        reminder1.put(CalendarContract.Reminders.EVENT_ID, ContentUris.parseId(newEvent));
        // 提前N天提醒
        reminder1.put(CalendarContract.Reminders.MINUTES, 0);
        // 设置提醒方式
        reminder1.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        // 插入提醒
        context.getContentResolver().insert(Uri.parse(CALENDER_REMINDER_URL), reminder1);
//        // 添加事件提醒失败直接返回
//        if (context.getContentResolver().insert(Uri.parse(CALENDER_REMINDER_URL), reminder) == null)
//            return false;

        // 提醒2
       /* ContentValues reminder2 = new ContentValues();
        reminder2.put(CalendarContract.Reminders.EVENT_ID, ContentUris.parseId(newEvent));
        reminder2.put(CalendarContract.Reminders.MINUTES, advancedDays * 24 * 60 - 120);
        reminder2.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        context.getContentResolver().insert(Uri.parse(CALENDER_REMINDER_URL), reminder2);*/

        return true;
    }

    /**
     * 删除日历事件
     */
    public static boolean deleteCalendarEvent(Context context, String title) {
        if (context == null)
            return false;

        try (Cursor cursor = context.getContentResolver().query(Uri.parse(CALENDER_EVENT_URL),
                null, null, null, null)) {
            if (cursor == null)
                return false;

            if (cursor.getCount() > 0) {
                // 遍历所有事件，找到title跟需要查询的title一样的项
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    String eventTitle = cursor.getString(cursor.getColumnIndex("title"));

                    if (!TextUtils.isEmpty(title) && title.equals(eventTitle)) {
                        int id = cursor.getInt(cursor.getColumnIndex(CalendarContract.Calendars._ID));
                        Uri deleteUri = ContentUris.withAppendedId(Uri.parse(CALENDER_EVENT_URL), id);
                        int rows = context.getContentResolver().delete(deleteUri, null, null);
                        // 事件删除失败
                        if (rows <= 0)
                            return false;
                        else
                            return true;
                    }
                }
            }
        }

        return false;
    }
}
