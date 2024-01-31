import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Geohash {

    public  static final double[] latRange = {-90,90};
    public  static final double[] longRange = {-180,180};
    public static boolean[] hash1D(double coor, double[] range, int precision){
        double mid = (range[0]+range[1])/2;
        if(precision==1){
            boolean[] hash = new boolean[1];
            hash[0] = coor>=mid ? true : false;
            return hash;
        }
        boolean[] hash = new boolean[precision];
        if(coor>=mid){
            hash[0]=true;
            range[0]=mid;
            boolean[] temp = hash1D(coor, range, precision-1);
            for(int i=0; i<temp.length; i++){
                hash[i+1]=temp[i];
            }
        }
        else{
            hash[0]=false;
            range[1]=mid;
            boolean[] temp = hash1D(coor, range, precision-1);
            for(int i=0; i<temp.length; i++){
                hash[i+1]=temp[i];
            }
        }


        return hash;
    }

    public static boolean[] hash2D(double lat, double[] latRange, double longi, double[] longRange, int precision){
        int longPrec = (precision/2)+precision%2;
        int latPrec = precision/2;
        boolean[] hashArray = new boolean[precision];
        boolean[] latArray = hash1D(lat, latRange,latPrec);
        boolean[] longArray = hash1D(longi, longRange, longPrec);
        for(int i=0; i<precision/2; i++){
           hashArray[i*2] = longArray[i];
           hashArray[(i*2)+1] = latArray[i];
        }
        if(precision%2==1){
            hashArray[hashArray.length-1] = longArray[precision/2];
        }
        return hashArray;
    }

    public static String encode(double lat, double longi, int precision){

        boolean[] hash = hash2D(lat, Arrays.copyOf(latRange,2), longi, Arrays.copyOf(longRange,2), precision);
        StringBuilder result = new StringBuilder();
        for(int i=0; i<hash.length; i++){
            result.append(hash[i]?'1' : '0');
        }
        return result.toString();
    }

    public static double[] decode(String hash){
        double[] result = new double[2];
        double[] longBnds = Arrays.copyOf(longRange,2);
        double[] latBnds = Arrays.copyOf(latRange,2);
        for(int i=0; i<hash.length(); i+=2){
            double mid = (longBnds[0]+longBnds[1])/2;
            if(hash.charAt(i)=='1'){
                longBnds[0]=mid;
            }
            else{
                longBnds[1]=mid;
            }
        }
        for(int i=1; i<hash.length(); i+=2){
            double mid = (latBnds[0]+latBnds[1])/2;
            if(hash.charAt(i)=='1'){
                latBnds[0]=mid;
            }
            else{
                latBnds[1]=mid;
            }
        }
        result[0]=(longBnds[0]+longBnds[1])/2;
        result[1]=(latBnds[0]+latBnds[1])/2;
        return result;
    }



}
