

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println(Geohash.encode(-90, 180,10));
        double[] coor = Geohash.decode("0110111111110000010000010");
        System.out.println("Longitude: " + coor[0]);
        System.out.println(("Latitude: " + coor[1]));
    }
}