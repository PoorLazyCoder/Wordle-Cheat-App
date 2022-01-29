public class Tester1 {


    public static void main(String[] args) {

        String[] green = {"", "", "", "", ""};   //  is correct and in the correct position
        String[] yellow1 = {"", "", "", "", ""};  // answer but not in the right position
        String[] yellow2 = {"", "", "", "", ""};
        String[] yellow3 = {"", "", "", "", ""};
        String gray = "";    //  it is not in the answer at all

        String[] results = new Solver(green, yellow1, yellow2, yellow3, gray).solve();

        System.out.println(Solver.toArrayColumn(results,10));

    }



}
