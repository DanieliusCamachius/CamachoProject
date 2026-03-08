package FruitTableDev.DTO;

public class FruitDTO {

    private final int serialNumber;
    private final String name;
    private final String color;
    private final double price;
    private final String season;

    public FruitDTO(int serialNumber, String name, String color, double price, String season) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.color = color;
        this.price = price;
        this.season = season;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public String getSeason() {
        return season;
    }

    @Override
    public String toString() {
        return "FruitDTO{" +
                "serialNumber=" + serialNumber +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", season='" + season + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FruitDTO that)) return false;

        return serialNumber == that.serialNumber &&
                Double.compare(price, that.price) == 0 &&
                name.equalsIgnoreCase(that.name) &&
                color.equalsIgnoreCase(that.color) &&
                season.equalsIgnoreCase(that.season);
    }

}

