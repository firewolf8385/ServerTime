package org.firewolf8385.servertime.objects;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.firewolf8385.servertime.Settings;
import org.firewolf8385.servertime.enums.Month;
import org.firewolf8385.servertime.enums.WeekDay;
import org.firewolf8385.servertime.utils.StringUtils;

public class Time
{
    Settings settings = Settings.getInstance();

    private int month;
    private int day;
    private int weekDay;
    private int hour;
    private int minute;
    private int counter;

    /**
     * Create a Time object.
     */
    public Time()
    {
        for(String str : settings.getConfig().getStringList("Worlds"))
        {
            World w = Bukkit.getWorld(str);

            if(w != null)
            {
                w.setGameRuleValue("doDaylightCycle", "false");
            }
        }

        counter = 0;

        month = settings.getConfig().getInt("Time.Month");
        day = settings.getConfig().getInt("Time.Day");
        weekDay = settings.getConfig().getInt("Time.WeekDay");
        hour = settings.getConfig().getInt("Time.Hour");
        minute = settings.getConfig().getInt("Time.Minute");;
    }

    /**
     * Add a day.
     */
    public void addDay()
    {
        day++;

        if(day > getMonth().getMaxDays())
        {
            day = 1;
            addMonth();
        }
    }

    /**
     * Add an hour.
     */
    public void addHour()
    {
        hour++;

        if(hour >= 24)
        {
            hour = 0;
            addDay();
        }
    }

    /**
     * Add a minute.
     */
    public void addMinute()
    {
        minute++;

        if(minute >= 60)
        {
            minute = 0;
            addHour();
        }
    }

    /**
     * Add a month.
     */
    public void addMonth()
    {
        month++;

        if(month > 12)
        {
            month = 1;
        }
    }

    /**
     * Display the time
     * @param p Player to display time for.
     */
    public void display(Player p)
    {
        String time = "";

        switch(hour)
        {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                time+= "0";
                break;
        }

        time += hour + ":";

        switch(minute)
        {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                time+= "0";
                break;
        }

        time += minute + "";

        String format = Month.getFromInt(month) + " " + day;

        switch(day)
        {
            case 1:
            case 21:
            case 31:
                format += "st";
                break;

            case 2:
            case 22:
                format += "nd";

            case 3:
            case 23:
                format += "rd";
                break;

            default:
                format += "th";
                break;
        }

        String message = StringUtils.translate(time + " &7|&f " + WeekDay.getFromInt(weekDay).toString() + " &7|&f " + format + " &7|&f " + TimeSlot.getCurrentTimeSlot(this).getName());
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }

    /**
     * Get the day.
     * @return day
     */
    public int getDay()
    {
        return day;
    }

    /**
     * Get the hour.
     * @return Hour
     */
    public int getHour()
    {
        return hour;
    }

    /**
     * Get a minute
     * @return Minute
     */
    public int getMinute()
    {
        return minute;
    }

    /**
     * Get the month.
     * @return Month
     */
    public Month getMonth()
    {
        return Month.getFromInt(month);
    }

    /**
     * Update the time.
     */
    public void update()
    {

        for(String str : settings.getConfig().getStringList("Worlds"))
        {
            World w = Bukkit.getWorld(str);

            if(w != null)
            {
                w.setFullTime(w.getFullTime() + 1);
            }
        }

        counter++;

        if(counter == 16)
        {
            counter = 0;
            addMinute();
        }

        for(Player p : Bukkit.getOnlinePlayers())
        {
            display(p);
        }
    }

    /**
     * Get the week day.
     * @return Week day.
     */
    public int getWeekDay()
    {
        return weekDay;
    }
}
