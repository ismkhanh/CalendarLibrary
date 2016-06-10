# Android Calendar Library
MonthView           |  YearView
:-------------------------:|:-------------------------:
![alt tab](https://cloud.githubusercontent.com/assets/4861930/15537040/ee3cfa7a-2291-11e6-99b5-bc48bc7db484.png)  |  ![alt tab](https://cloud.githubusercontent.com/assets/4861930/15888021/e055587a-2d82-11e6-9329-4da8b8d8dad8.png)

## Usage

In order to use this library in your project add the below dependencies is your project's
 **build.gradle** file

```
...
dependencies {
    compile 'com.github.ik024:calendar-lib:2.0.1'
}
...
```

## MonthView Example

**Add the following in your xml file:**
```
<com.github.ik024.calendar_lib.MonthView 
        android:id="@+id/calendar_month_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"/>
```

**Implement the MonthViewClickListener in your Activity:**

```
public class MonthViewDemo extends AppCompatActivity implements MonthViewClickListeners{
...

 @Override
    public void dateClicked(Date dateClicked) {
        Toast.makeText(this, dateClicked.toString(), Toast.LENGTH_LONG).show();
    }
    
...    
}
```

**For more refer __[MonthViewDemo.java](https://github.com/ik024/CalendarLibrary/blob/master/app/src/main/java/com/github/ik024/MonthViewDemo.java)__ file.**

## Customizations

Following attributes can be customized:

```
 <attr name="currentDayTextColorMV" format="color"/>
 <attr name="monthNameTextColorMV" format="color"/>
 <attr name="daysOfMonthTextColorMV" format="color"/>
 <attr name="daysOfWeekTextColorMV" format="color"/>
 <attr name="eventDayBackgroundColorMV" format="color"/>
 <attr name="eventDayTextColorMV" format="color"/>
 <attr name="calendarBackgroundColorMV" format="color" />
 <attr name="monthNameBackgroundColorMV" format="color" />
 <attr name="prevButtonBackgroundResourceMV" format="reference" />
 <attr name="nextButtonBackgroundResourceMV" format="reference" />
```

## YearView Example

**Add the following in your xml file:**

```
<com.github.ik024.calendar_lib.YearView 
        android:id="@+id/calendar_year_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"/>
```

**Implement the YearViewClickListener in your Activity:**
```
public class MonthViewDemo extends AppCompatActivity implements YearViewClickListeners{
...

@Override
    public void dateClicked(int year, int month, int day) {
        Toast.makeText(this, "year: "+year+";\nmonth: "+month+";\nday: "+day, Toast.LENGTH_LONG).show();
        //TODO: Perform your actions based on year or month or day clicked
    }
    
...    
}
```

**For more refer __[YearViewDemo.java](https://github.com/ik024/CalendarLibrary/blob/master/app/src/main/java/com/github/ik024/YearViewDemo.java)__ file.**

## Customizations

Following attributes can be customized:

```
 <attr name="currentDayTextColorYV" format="color"/>
 <attr name="monthNameTextColorYV" format="color"/>
 <attr name="daysOfMonthTextColorYV" format="color"/>
 <attr name="daysOfWeekTextColorYV" format="color"/>
 <attr name="eventDayBackgroundColorYV" format="color"/>
 <attr name="eventDayTextColorYV" format="color"/>
 <attr name="calendarBackgroundColorYV" format="color" />
 <attr name="monthNameBackgroundColorYV" format="color" />
 <attr name="displayYearTextColorYV" format="color" />
 <attr name="headerBackgroundColorYV" format="color" />
 <attr name="prevButtonBackgroundResourceYV" format="reference" />
 <attr name="nextButtonBackgroundResourceYV" format="reference" />
```
