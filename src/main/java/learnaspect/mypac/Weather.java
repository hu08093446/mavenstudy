package learnaspect.mypac;

import java.util.Date;

public class Weather {

    private Date date;

    private boolean isSun;

    public Weather() {}

    public Weather(Date date, boolean isSun) {
        this.date = date;
        this.isSun = isSun;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSun() {
        return isSun;
    }

    public void setSun(boolean sun) {
        isSun = sun;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "date=" + date +
                ", isSun=" + isSun +
                '}';
    }
}
