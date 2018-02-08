package me.matthewe.atherial.api.modules.plotsquared;

import com.intellectualcrafters.plot.object.Location;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.Rating;
import me.matthewe.atherial.api.module.ApiWrapperModule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew E on 2/7/2018.
 */
public class PlotSquaredApiWrapperModule implements ApiWrapperModule {

    public List<Rating> getPlotRatingsByPlotLocation(org.bukkit.Location location) {
        Plot plotByLocation = getPlotByLocation(location);
        if (plotByLocation != null) {
            return new ArrayList<>(plotByLocation.getRatings().values());
        }
        return new ArrayList<>();
    }

    public Plot getPlotByLocation(org.bukkit.Location location) {
        return Plot.getPlot(new Location(location.getWorld().getName(), (int) location.getX(), (int) location.getY(), (int) location.getZ(), location.getYaw(), location.getPitch()));
    }

    @Override
    public String getPluginName() {
        return "PlotSquared";
    }

    @Override
    public String getName() {
        return "PlotSquaredApiWrapperModule";
    }
}
