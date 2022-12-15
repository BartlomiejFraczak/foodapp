/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bartlomiejfraczak.foodapp.encje;

/**
 *
 * @author thefr
 */
public class Miara {

    private float amount;
    private String unitLong;
    private String unitShort;

    public Miara(float amount, String unitLong, String unitShort) {
        this.amount = amount;
        this.unitLong = unitLong;
        this.unitShort = unitShort;
    }

    @Override
    public String toString() {
        return "Miara{" + "amount=" + amount + ", unitLong=" + unitLong + ", unitShort=" + unitShort + '}';
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getUnitLong() {
        return unitLong;
    }

    public void setUnitLong(String unitLong) {
        this.unitLong = unitLong;
    }

    public String getUnitShort() {
        return unitShort;
    }

    public void setUnitShort(String unitShort) {
        this.unitShort = unitShort;
    }

}
