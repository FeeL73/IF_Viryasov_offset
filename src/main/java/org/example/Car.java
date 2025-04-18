package org.example;

public abstract class Car {
    private String nameModel;
    private float speedMax;
    private int yearCreate;
    private int countDor;
    private int carMileage;
    private String color;
    private boolean belongsToElonMusk;
    private boolean carSpoiler;
    private boolean autoTransmissionBox;
    protected boolean canFly;

    public Car(String nameModel, float speedMax, int yearCreate, int countDor,
               int carMileage, String color, boolean belongsToElonMusk, boolean carSpoiler,
               boolean autoTransmissionBox, boolean canFly){
        this.nameModel = nameModel;
        this.speedMax = speedMax;
        this.yearCreate = yearCreate;
        this.countDor = countDor;
        this.carMileage = carMileage;
        this.color = color;
        this.belongsToElonMusk = belongsToElonMusk;
        this.carSpoiler = carSpoiler;
        this.autoTransmissionBox = autoTransmissionBox;
        this.canFly = canFly;
    }

    public String getFullInfo() {
        return "Название модели " + nameModel +
                " Максимальная сокрость " + speedMax + " км/ч" +
                " Год выпуска " + yearCreate +
                " Количество дверей " + countDor +
                " Пробег " + carMileage +
                " Цвет " + color +
                ", Принадлежит Илону Маску? " + (belongsToElonMusk ? " Да " : " Нет ") +
                ", Есть ли спойлер? " + (carSpoiler ? " Да " : " Нет ") +
                ", Автоматическая коробка? " + (autoTransmissionBox ? " Да " : " Нет ") +
                ", Умеет летать? " + (canFly ? " Да " : " Нет ")  ;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String newColor) {
        this.color = newColor;
    }

    public int getYearCreate() {
        return yearCreate;
    }

    public String getBrand() {
        return nameModel;
    }

    public boolean canFly() {
        return canFly;
    }
}
