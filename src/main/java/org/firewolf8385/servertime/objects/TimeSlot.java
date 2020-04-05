package org.firewolf8385.servertime.objects;

import java.util.ArrayList;
import java.util.List;

public class TimeSlot
{
    private static ArrayList<TimeSlot> slots = new ArrayList<>();

    private String name;
    private int start;
    private int end;
    private List<Integer> days;

    /**
     * Create a new TimeSlot object.
     * @param name Name
     * @param start Time the slot starts.
     * @param end Time the slot ends.
     * @param days Days the slot runs.
     */
    public TimeSlot(String name, int start, int end, List<Integer> days)
    {
        this.name = name;
        this.start = start;
        this.end = end;
        this.days = days;

        slots.add(this);
    }

    /**
     * Get the days the timeslot applies to
     * @return days
     */
    public List<Integer> getDays()
    {
        return days;
    }

    /**
     * Get the end time of the slot.
     * @return End time.
     */
    public int getEnd()
    {
        return end;
    }

    /**
     * Get the name of the slot.
     * @return Name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the name of the start.
     * @return Start time.
     */
    public int getStart()
    {
        return start;
    }

    /**
     * Get the current time slot.
     * @param t Time
     * @return TimeSlot
     */
    public static TimeSlot getCurrentTimeSlot(Time t)
    {
        TimeSlot s = getTimeSlots().get(0);
        int time = (t.getHour() * 60) + t.getMinute();

        for(TimeSlot slot : slots)
        {
            if(time >= slot.getStart() && time <= slot.getEnd())
            {
                if(slot.getDays().contains(t.getDay()))
                {
                    s = slot;
                    break;
                }
            }
        }

        return s;
    }

    /**
     * Get an ArrayList of time slots.
     * @return Time slots.
     */
    public static ArrayList<TimeSlot> getTimeSlots()
    {
        return slots;
    }
}
