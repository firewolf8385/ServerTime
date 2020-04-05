package org.firewolf8385.servertime;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.firewolf8385.servertime.objects.Time;
import org.firewolf8385.servertime.objects.TimeSlot;

import java.util.Arrays;
import java.util.List;

public class ServerTime extends JavaPlugin
{
    Settings settings = Settings.getInstance();
    private Time time;

    @Override
    public void onEnable()
    {
        // Setup Settings
        settings.setup(this);

        // Setup Time
        time = new Time();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> time.update(), 0, 12);

        // Register time slots
        registerTimeSlots();
    }

    @Override
    public void onDisable()
    {
        settings.getConfig().set("Time.Month", time.getMonth().getNumber());
        settings.getConfig().set("Time.Day", time.getDay());
        settings.getConfig().set("Time.WeekDay", time.getWeekDay());
        settings.getConfig().set("Time.Hour", time.getHour());
        settings.getConfig().set("Time.Minute", time.getMinute());
        settings.saveConfig();
    }

    /**
     * Get the current time.
     * @return Time
     */
    public Time getTime()
    {
        return time;
    }

    /**
     * Register the slots.
     */
    private void registerTimeSlots()
    {
        ConfigurationSection section = settings.getConfig()
                .getConfigurationSection("TimeSlot");

        for(String str : section.getKeys(false))
        {
            String name = settings.getConfig().getString("TimeSlot." + str + ".name");
            int start = (settings.getConfig().getInt("TimeSlot." + str + ".start.hour") * 60) + settings.getConfig().getInt("TimeSlots." + str + ".start.minute");
            int end = (settings.getConfig().getInt("TimeSlot." + str + ".end.hour") * 60) + settings.getConfig().getInt("TimeSlots." + str + ".end.minute");
            List<Integer> days = settings.getConfig().getIntegerList("TimeSlot." + str + ".weekdays");

            new TimeSlot(name, start, end, days);
        }

        new TimeSlot("No School", 0, 1440, Arrays.asList(1,2,3,4,5,6,7));
    }

}
