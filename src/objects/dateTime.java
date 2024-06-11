package objects;

public class dateTime {
    //contains functions for updating the in-game calendar.
    /**
     * The day of the month, increments by 1 each day, and resets to one
     * at the end of the month.
     */
    private Integer dateNumber;
    /**
     * The month of the year, increments by 1 each month, and resets to one
     * at the end of the year.
     */
    private Integer monthNumber;
    /**
     * The year of the simulation, increments by 1 each year.
     */
    private Integer yearNumber;
    /**
     * The number of days in the current month, dependant by monthNumber.
     */
    private Integer monthLength;
    /**
     * The name of the current month, dependant by monthNumber.
     */
    private String monthName;

    public dateTime(Integer[] startingDate){
        this.dateNumber = startingDate[0];
        this.monthNumber = startingDate[1];
        this.yearNumber = startingDate[2];

        this.monthLength = setMonthLength(monthNumber);
        this.monthName = setMonthName(monthNumber);
    }

    private Integer setMonthLength(Integer monthNumber){
        if (monthNumber == 1 || monthNumber == 3 || monthNumber == 5 || monthNumber == 7 || monthNumber == 8 || monthNumber == 10 || monthNumber == 12){
            monthLength = 31;
        } else if (monthNumber == 2){
            monthLength = 28;
        } else {
            monthLength = 30;
        }
        return monthLength;
    }
    private String setMonthName(Integer monthNumber){
        if (monthNumber == 1){
            monthName = "January";
        } else if (monthNumber == 2){
            monthName = "February";
        } else if (monthNumber == 3){
            monthName = "March";
        } else if (monthNumber == 4){
            monthName = "April";
        } else if (monthNumber == 5){
            monthName = "May";
        } else if (monthNumber == 6){
            monthName = "June";
        } else if (monthNumber == 7){
            monthName = "July";
        } else if (monthNumber == 8){
            monthName = "August";
        } else if (monthNumber == 9){
            monthName = "September";
        } else if (monthNumber == 10){
            monthName = "October";
        } else if (monthNumber == 11){
            monthName = "November";
        } else if (monthNumber == 12){
            monthName = "December";
        } else {
            monthName = "January";
        }
        return monthName;
    }

    public void progressDate(){
        this.dateNumber += 1;
        if (dateNumber > monthLength){
            this.dateNumber = 1;
            this.monthNumber += 1;
            setMonthLength(monthNumber);
            setMonthName(monthNumber);
        }
        if (monthNumber > 12){
            this.monthNumber = 1;
            this.yearNumber += 1;
        }
    }

    public Integer getDateNumber(){
        return dateNumber;
    }
    public Integer getMonthNumber(){
        return monthNumber;
    }
    public Integer getYearNumber(){
        return yearNumber;
    }

    public Integer getMonthLength(){
        return monthLength;
    }
    public String getMonthName(){
        return monthName;
    }
}
