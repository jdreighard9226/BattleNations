package map;

public enum TerrainType {
    CITY, DESERT, MOUNTAIN;

    public static Terrain create(TerrainType terrain) {
        switch (terrain) {
            case TerrainType.CITY:
                return new CityTerrain();
            case TerrainType.DESERT:
                return new DesertTerrain();
            case TerrainType.MOUNTAIN:
                return new MountainTerrain();

            default:
                throw new IllegalStateException("Unexpected value: " + terrain);
        }
    }
}


