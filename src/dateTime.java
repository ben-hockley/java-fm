public class dateTime {
    //contains functions for updating the in-game calendar.
    private Integer dateNumber;
    private Integer monthNumber;

    private Integer monthLength;
    private String monthName;

    public dateTime(){
        this.dateNumber = 1;
        this.monthNumber = 1;
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
        } else {
            monthName = "December";
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
    }

    public Integer getDateNumber(){
        return dateNumber;
    }
    public Integer getMonthNumber(){
        return monthNumber;
    }
    public Integer getMonthLength(){
        return monthLength;
    }
    public String getMonthName(){
        return monthName;
    }
}
