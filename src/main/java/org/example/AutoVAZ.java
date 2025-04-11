package org.example;

public class AutoVAZ extends Car{
    private int countYearLiveCar;     // скорость в полете
    private String whyCarLive;       // дальность полета
    public AutoVAZ(String nameModel, float speedMax, int yearCreate, int countDor,
                 int carMileage, String color, boolean belongsToElonMusk, boolean carSpoiler,
                 boolean autoTransmissionBox, boolean canFly) {
        super(nameModel, speedMax, yearCreate, countDor, carMileage, color, belongsToElonMusk, carSpoiler,
                autoTransmissionBox, canFly);

    }
    public AutoVAZ(String nameModel, float speedMax, int yearCreate, int countDor,
                   int carMileage, String color, boolean belongsToElonMusk, boolean carSpoiler,
                   boolean autoTransmissionBox, boolean canFly, int countYearLiveCar, String whyCarLive) {
        super(nameModel, speedMax, yearCreate, countDor, carMileage, color, belongsToElonMusk, carSpoiler,
                autoTransmissionBox, canFly);
        this.countYearLiveCar = countYearLiveCar;
        this.whyCarLive = whyCarLive;
    }


    @Override
    public String getFullInfo() {
        return super.getFullInfo() + String.format(
                "\n Сколько еще лет проездит ВАЗ: "  +
                countYearLiveCar + " Почему она еще ездит? " +  whyCarLive);
    }
}
