# Android Calendar Library

## Developer Instructions

In order to use this library in your project add the below dependencies is your project's
 **build.gradle** file

```
dependencies {
    compile 'com.github.ik024:calendar-lib:+'
}
```
**Add the following in your xml file:**
```
<com.github.ik024.calendar_lib.MonthCalendarView 
        android:id="@+id/calendar_month_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"/>
```

**Implement the CalendarClickListener in your Activity:**
```
public class MonthViewDemo extends AppCompatActivity implements CalendarClickListeners{
...

 @Override
    public void dateClicked(Date dateClicked) {
        Toast.makeText(this, dateClicked.toString(), Toast.LENGTH_LONG).show();
    }
    
...    
}
```

**For more refer __MonthViewDemo.java__ file.**

## Customizations

Following attributes can be customized:


```
 <attr name="currentDayTextColor" format="color"/>
 <attr name="monthNameTextColor" format="color"/>
 <attr name="daysOfMonthTextColor" format="color"/>
 <attr name="daysOfWeekTextColor" format="color"/>
 <attr name="eventDayBackgroundColor" format="color"/>
 <attr name="eventDayTextColor" format="color"/>
 <attr name="calendarBackgroundColor" format="color" />
 <attr name="MonthNameBackgroundColor" format="color" />
 <attr name="prevButtonBackgroundResource" format="reference" />
 <attr name="nextButtonBackgroundResource" format="reference" />
```
