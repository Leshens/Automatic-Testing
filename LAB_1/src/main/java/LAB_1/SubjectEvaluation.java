package LAB_1;

import org.apache.commons.validator.routines.DoubleValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;

public class SubjectEvaluation {
    public static void main(String[] args) {
        if (args.length<=0){
            System.out.println("Brak ocen cząstkowych, zatem ocena finalna to 2.0");
            return;
        }
        double[] results = new double[args.length];
        for (int i=0; i<args.length; i++){
            results[i] = Double.parseDouble(args[i]);
        }
        double finalMark = evaluate(results);
        System.out.println(String.format("Finalna ocena to %.1f", finalMark));
    }

    public static double evaluate(double ... percentages){
            double overallPercentage = sum(percentages)/percentages.length;

            for (double percentage:percentages){
                if (percentage < 0){
                    return 0;
                }
            }
            if (percentages.length == 0){
                return 0;
            }
            if (overallPercentage<0.5){
                return 2.0;
            }else {
                return 3.0 + 0.5 * (int)((overallPercentage - 0.5)/0.1);
            }
        }
    public static boolean isValidEmail(String emailAddress){
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(emailAddress);
    }
    public static String isValidUrl(String url){
        UrlValidator urlValidator = UrlValidator.getInstance();
        if (urlValidator.isValid(url)) {
            return "url is valid";
        } else {
            return "url is invalid";
        }
    }
    public static String isUrlHttps(String url){
        String[] schemes = {"http","https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        if (urlValidator.isValid(url)) {
            return "url is valid";
        } else {
            return "url is invalid";
        }
    }
    public static String isDouble(String value){
        DoubleValidator doubleValidator = DoubleValidator.getInstance();
        if (doubleValidator.isValid(value)){
            return value;
        }else
            return null;
    }
        private static double sum(double[] percentages) {
            double sum=0;
            for (double percentage:percentages){
                sum+= percentage;
            }
            return sum;
        }
}
