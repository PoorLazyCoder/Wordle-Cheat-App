import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Solver {


    static List<String[]> dicWords;

    String[] g, y1, y2, y3;
    String gray;

    // g green:  is correct and in the correct position
    // y yellow:  answer but not in the right position
    //  gray:   it is not in the answer at all


    public Solver(String[] green, String[] yellow1, String[] yellow2, String[] yellow3, String gray) {
        this.g = green;
        this.y1 = yellow1;
        this.y2 = yellow2;
        this.y3 = yellow3;
        this.gray = gray;
    }


    public String[] solve() {

        if (String.join("", g).trim().length() == 0) g = null;
        if (String.join("", y1).trim().length() == 0) y1 = null;
        if (String.join("", y2).trim().length() == 0) y2 = null;
        if (String.join("", y3).trim().length() == 0) y3 = null;
        gray = gray.trim();

        if(g==null && y1==null && y2==null && y3==null &&  gray.isEmpty() )
            return null;


        List<String[]> words = new LinkedList<>(Arrays.asList());

        words.addAll(getAllDicWords());


        if (g != null) {

            words = words.stream()
                    .filter(w ->
                            (w[0].equals(g[0]) || g[0].equals("")) &&
                                    (w[1].equals(g[1]) || g[1].equals("")) &&
                                    (w[2].equals(g[2]) || g[2].equals("")) &&
                                    (w[3].equals(g[3]) || g[3].equals("")) &&
                                    (w[4].equals(g[4]) || g[4].equals(""))
                    )
                    .collect(Collectors.toList());
        }

        if (y1 != null) {
            words = filterOutYellow(y1, words);
        }

        if (y2 != null) {
            words = filterOutYellow(y2, words);
        }

        if (y3 != null) {
            words = filterOutYellow(y3, words);
        }


        if (!gray.isEmpty()) {

            if (g != null) for (String c : g) gray = gray.replace(c, "");
            if (y1 != null) for (String c : y1) gray = gray.replace(c, "");
            if (y2 != null) for (String c : y2) gray = gray.replace(c, "");
            if (y3 != null) for (String c : y3) gray = gray.replace(c, "");

            words = words.stream()
                    .filter(w -> {
                        return Arrays.stream(w).noneMatch(s ->
                                gray.contains(s)
                        );
                    }).collect(Collectors.toList());
        }


        List<String> list = new LinkedList<>(Arrays.asList());

        words.stream().forEach(w -> {
                    list.add(String.join("", w));
                }
        );


        return list.toArray(new String[0]);
    }


    public List<String[]> getAllDicWords() {

        if (dicWords == null) {

            dicWords = new LinkedList<>(Arrays.asList());

            for (String w : Words1.dicWordsArr1) {
                dicWords.add(w.split(""));
            }
            for (String w : Words2.dicWordsArr2) {
                dicWords.add(w.split(""));
            }
            for (String w : Words3.dicWordsArr3) {
                dicWords.add(w.split(""));
            }

        }


        return dicWords;
    }


    private List<String[]> filterOutYellow(String[] y, List<String[]> words) {

        words = words.stream()
                .filter(w ->
                        ((!w[0].equals(y[0])) &&
                                (!w[1].equals(y[1])) &&
                                (!w[2].equals(y[2])) &&
                                (!w[3].equals(y[3])) &&
                                (!w[4].equals(y[4]))
                        )
                ).filter(w -> {

                            String wordSt = String.join("", w);
                            boolean isMatchAll = Arrays.stream(y).allMatch(s ->
                                    wordSt.contains(s)
                            );

                            return isMatchAll;
                        }
                )
                .collect(Collectors.toList());

        return words;
    }


    public void printArrary(String[] arr) {
        for (String a : arr) {
            System.out.print("'" + a + "'");
        }

        System.out.println();
    }



    public static String toArrayColumn(String[] arr, int numOfColumn) {
        StringBuffer sb = new StringBuffer();
        int counter = 1;
        for (String w : arr) {

            sb.append(String.join("", w) + " ");
            if (counter++ % numOfColumn== 0)
                sb.append("\n");
        }

        return sb.toString();
    }


}
