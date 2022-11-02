package org.example;

public class Asset implements Entity  {
    private int serialNumber;
    private String owner;
    private double rating;

    public Asset() {
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
