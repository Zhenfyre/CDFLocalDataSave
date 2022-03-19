package cdfflint.pilot.cdflocaldatasave;

import java.util.ArrayList;

public class TabletNumbers {

    private static ArrayList<TabletNumbers> TabletArrayList;

    private int id;
    private String tabNum;

    public TabletNumbers(int id, String tabNum) {
        this.id = id;
        this.tabNum = tabNum;
    }

    public static void initNumbers() {

        TabletArrayList = new ArrayList<>();

        TabletNumbers zero = new TabletNumbers(0,"000");
        TabletArrayList.add(zero);

        TabletNumbers one = new TabletNumbers(1,"001");
        TabletArrayList.add(one);

        TabletNumbers two = new TabletNumbers(2,"002");
        TabletArrayList.add(two);

        TabletNumbers three = new TabletNumbers(3,"003");
        TabletArrayList.add(three);

        TabletNumbers four = new TabletNumbers(4,"004");
        TabletArrayList.add(four);

        TabletNumbers five = new TabletNumbers(5,"005");
        TabletArrayList.add(five);

        TabletNumbers six = new TabletNumbers(6,"006");
        TabletArrayList.add(six);

        TabletNumbers seven = new TabletNumbers(7,"007");
        TabletArrayList.add(seven);

        TabletNumbers eight = new TabletNumbers(8,"008");
        TabletArrayList.add(eight);

        TabletNumbers nine = new TabletNumbers(9,"009");
        TabletArrayList.add(nine);

        TabletNumbers ten = new TabletNumbers(10,"010");
        TabletArrayList.add(ten);

        TabletNumbers eleven = new TabletNumbers(11,"011");
        TabletArrayList.add(eleven);

        TabletNumbers twelve = new TabletNumbers(12,"012");
        TabletArrayList.add(twelve);

        TabletNumbers thirteen = new TabletNumbers(13,"013");
        TabletArrayList.add(thirteen);

        TabletNumbers fourteen = new TabletNumbers(14,"014");
        TabletArrayList.add(fourteen);

        TabletNumbers fifteen = new TabletNumbers(15,"015");
        TabletArrayList.add(fifteen);

        TabletNumbers sixteen = new TabletNumbers(16,"016");
        TabletArrayList.add(sixteen);

        TabletNumbers seventeen = new TabletNumbers(17,"017");
        TabletArrayList.add(seventeen);

        TabletNumbers eighteen = new TabletNumbers(18,"018");
        TabletArrayList.add(eighteen);

        TabletNumbers nineteen = new TabletNumbers(19,"019");
        TabletArrayList.add(nineteen);

        TabletNumbers twenty = new TabletNumbers(20,"020");
        TabletArrayList.add(twenty);

        TabletNumbers twentyone = new TabletNumbers(21,"021");
        TabletArrayList.add(twentyone);

        TabletNumbers twentytwo = new TabletNumbers(22,"022");
        TabletArrayList.add(twentytwo);

        TabletNumbers twentythree = new TabletNumbers(23,"023");
        TabletArrayList.add(twentythree);

        TabletNumbers twentyfour = new TabletNumbers(24,"024");
        TabletArrayList.add(twentyfour);

        TabletNumbers twentyfive = new TabletNumbers(25,"025");
        TabletArrayList.add(twentyfive);

        TabletNumbers twentysix = new TabletNumbers(26,"026");
        TabletArrayList.add(twentysix);

        TabletNumbers twentyseven = new TabletNumbers(27,"027");
        TabletArrayList.add(twentyseven);

        TabletNumbers twentyeight = new TabletNumbers(28,"028");
        TabletArrayList.add(twentyeight);

        TabletNumbers twentynine = new TabletNumbers(29,"029");
        TabletArrayList.add(twentynine);

        TabletNumbers thirty = new TabletNumbers(30,"030");
        TabletArrayList.add(thirty);

        TabletNumbers thirtyone = new TabletNumbers(31,"031");
        TabletArrayList.add(thirtyone);

        TabletNumbers thirtytwo = new TabletNumbers(32,"032");
        TabletArrayList.add(thirtytwo);
    }

    public static ArrayList<TabletNumbers> getTabletArrayList() {
        return TabletArrayList;
    }

    public static String[] numberList()
    {
        String[] numbers = new String[TabletArrayList.size()];
        for(int i = 0; i< TabletArrayList.size(); i++){
            numbers[i] = TabletArrayList.get(i).tabNum;
        }
        return numbers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTabNum() {
        return tabNum;
    }

    public void setTabNum(String tabNum) {
        this.tabNum = tabNum;
    }
}
