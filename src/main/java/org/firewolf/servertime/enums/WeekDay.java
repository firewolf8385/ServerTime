package org.firewolf.servertime.enums;

public enum WeekDay
{
    SUNDAY("Sunday"),
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday");

    private String name;

    /**
     * Create a new WeekDay
     * @param name Name
     */
    WeekDay(String name)
    {
        this.name = name;
    }

    /**
     * Get the number of the week day.
     * @return Number of the week day.
     */
    public int getNumber()
    {
        int num = 0;

        for(int i = 0; i < WeekDay.values().length; i++)
        {
            if(WeekDay.values()[i].equals(this))
            {
                num = i + 1;
                break;
            }
        }

        return num;
    }

    /**
     * Get a week day from an integer.
     * @param num Integer
     * @return Week Day
     */
    public static WeekDay getFromInt(int num)
    {
        return WeekDay.values()[num - 1];
    }

    /**
     * Get a string of the enum.
     * @return Enum
     */
    public String toString()
    {
        return name;
    }
}
