package map;

import java.util.List;

public class MapLoaderData {
    Territory[][] territories;
    List<Region> regions;
    public MapLoaderData(Territory[][] territories, List<Region> regions) {
        this.territories = territories;
        this.regions = regions;
    }

    public Territory[][] getTerritories(){
        return territories;
    }

    public List<Region> getRegions(){
        return regions;
    }
}
