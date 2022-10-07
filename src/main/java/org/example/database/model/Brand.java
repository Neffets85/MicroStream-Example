package org.example.database.model;

public class Brand {

    private String name;

    private double stars;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Brand newBrand(String name) {
        Brand result = new Brand();
        result.setName(name);
        return result;
    }

    public double getStars()
    {
        return stars;
    }

    public void setStars(double stars)
    {
        this.stars = stars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Brand)) {
            return false;
        }

        Brand brand = (Brand) o;

        return name.equals(brand.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
