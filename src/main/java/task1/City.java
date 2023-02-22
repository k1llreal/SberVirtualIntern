package task1;

public class City {
    // наименование города
    private String name;
    // регион
    private String region;
    // федеральный округ
    private String district;
    // население
    private int population;
    // дата основания или первое упоминание
    private String foundation;


    public City(String name, String region, String district, int population, String foundation) {
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                ", foundation='" + foundation + '\'' +
                '}';
    }
}