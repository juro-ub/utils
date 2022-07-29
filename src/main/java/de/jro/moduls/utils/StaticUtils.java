package de.jro.moduls.utils;

import java.util.*;
import java.math.*;
import java.util.regex.*;

public class StaticUtils {

    public static Object[][] chunkArray(Object[] array, int chunkSize) {
        int numOfChunks = (int) Math.ceil((double) array.length / chunkSize);
        Object[][] output = new Object[numOfChunks][];

        for (int i = 0; i < numOfChunks; ++i) {
            int start = i * chunkSize;
            int length = Math.min(array.length - start, chunkSize);

            Object[] temp = new Object[length];
            System.arraycopy(array, start, temp, 0, length);
            output[i] = temp;
        }

        return output;
    }

    public static Object[][] combinationsOfArray(Object[] Objects) {
        List<Object[]> combinationList = new ArrayList<Object[]>();
        for (long i = 1; i < Math.pow(2, Objects.length); i++) {
            List<Object> objectList = new ArrayList<Object>();
            for (int j = 0; j < Objects.length; j++) {
                if ((i & (long) Math.pow(2, j)) > 0) {
                    objectList.add(Objects[j]);
                }
            }
            combinationList.add(objectList.toArray(new Object[0]));
        }
        return combinationList.toArray(new Object[0][0]);
    }

    public static List<Double> extractNumbersFromStr(String str) {
        str = str.replace(",", ".");
        List<Double> ret = new ArrayList<Double>();
        Pattern pattern = Pattern.compile("-?\\d+(?:\\.\\d+)?");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            ret.add((Double.valueOf(matcher.group())));
        }
        return ret;
    }

    public static String getBiggestWordFromStr(String str, String delim) {
        if (str.trim().length() == 0) {
            return str;
        }
        List<String> list = new ArrayList<String>(Arrays.asList(str.split(delim)));
        String max = Collections.max(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        return max;
    }

    public static String getOnlyStrings(String s) {
        Pattern pattern = Pattern.compile("[^a-z A-Z]");
        Matcher matcher = pattern.matcher(s);
        String number = matcher.replaceAll("");
        return number.trim();
    }

    public static String removeIfEndsWith(String text, String endsWith) {
        if (endsWith == null || text == null || text.length() <= endsWith.length()) {
            return null;
        }
        if (text.endsWith(endsWith)) {
            text = text.substring(0, text.length() - endsWith.length());
        }
        return text;
    }

    public static String removeTextBetweenRoundBrackets(String s) {
        s = s.replaceAll("\\((.*?)\\)", "");
        return s.trim();
    }

    private static String removeWordsWithTwoCharSplittedByWhitespace(String text) {
        String output = text.replaceAll("\\b\\w{1,2}\\b\\s?", "");
        return output;
    }

    private static void throwExceptionIfNullOrEmpty(Object val) throws Exception {
        if (val == null) {
            throw new Exception("NULL exception");
        }
        if (val instanceof Integer) {
            if ((Integer) val == 0) {
                throw new Exception("Integer is empty or null");
            }
        }
        if (val instanceof String) {
            if (((String) val).length() == 0) {
                throw new Exception("String is empty or null");
            }
        }
        if (val instanceof BigDecimal) {
            if (((BigDecimal) val).doubleValue() == 0) {
                throw new Exception("BigDecimal is empty or null");
            }
        }
        if (val instanceof Double) {
            if (((Double) val) == 0) {
                throw new Exception("Double is empty or null");
            }
        }
    }

    public static void throwExceptionIfNullOrEmpty(Object[] val) throws Exception {
        for (Object b : val) {
            throwExceptionIfNullOrEmpty(b);
        }
    }

    public static String roundAndFormat(final double value, final int frac) {
        final java.text.NumberFormat nf = java.text.NumberFormat.getInstance(Locale.ENGLISH);
        nf.setMaximumFractionDigits(frac);
        nf.setRoundingMode(RoundingMode.HALF_DOWN);
        return nf.format(new BigDecimal(value));
    }

    public static double getLetterPairSimilarity(String str1, String str2) {
        try {
            if (str1 != null && str2 != null && str1.length() > 0 && str2.length() > 0) {
                str1 = str1.replaceAll("[-+.^:,]", "").trim().replaceAll("\\s{2,}", " ");
                str2 = str2.replaceAll("[-+.^:,]", "").trim().replaceAll("\\s{2,}", " ");
                double z = LetterPairSimilarity.compareStrings(str1, str2);
                return z;
            } else {
                System.out.println("no valid args: str1:" + str1 + " str2:" + str2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
