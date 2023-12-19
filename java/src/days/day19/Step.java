package days.day19;


import java.util.TreeSet;

public class Step {
    private boolean conditional;
    private String charToCheck;
    private String op;
    private long value;
    private String result;

    public Step(String str) {
        if (str.indexOf(':') > 0) {
            conditional = true;
            charToCheck = "" + str.charAt(0);
            op = "" + str.charAt(1);

            value = Long.parseLong( str.split(":")[0].substring(2) );
            result = str.split(":")[1];
        }
        else {
            conditional=false;
            result = str.trim();
        }
    }

    public void applyLimit(TreeSet<Integer> opt_set) {
        // m > 10
        if (op.equals(">")) {
            for(int i=1; i<=value; i++) opt_set.remove(i);
        }
        // m < 10
        if (op.equals("<")) {
            for(int i=(int)value; i<=4000; i++) opt_set.remove(i);
        }
    }

    public boolean isConditional() {
        return this.conditional;
    }

    public String getResult() {
        return this.result;
    }

    public String getCharToCheck() {
        return this.charToCheck;
    }
}
