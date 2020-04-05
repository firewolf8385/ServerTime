package org.firewolf.servertime.enums;

public enum Month
{
    JANUARY("January"),
    FEBRUARY("February"),
    MARCH("March"),
    APRIL("April"),
    MAY("May"),
    JUNE("June"),
    JULY("July"),
    AUGUST("August"),
    SEPTEMBER("September"),
    OCTOBER("October"),
    NOVEMBER("November"),
    DECEMBER("December");

    private String name;

    /**
     * Create a new Month.
     * @param name
     */
    Month(String name)
    {
        this.name = name;
    }

    /**
     * Get the name of the month.
     * @return Name of the month.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the number of the month.
     * @return Number of the month.
     */
    public int getNumber()
    {
        int num = 0;

        for(int i = 0; i < Month.values().length; i++)
        {
            if(Month.values()[i].equals(this))
            {
                num = i + 1;
                break;
            }
        }

        return num;
    }

    /**
     * Get a month from an integer.
     * @param num Integer
     * @return Month
     */
    public static Month getFromInt(int num)
    {
        return Month.values()[num - 1];
    }
}
