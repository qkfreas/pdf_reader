package text_processor;

/**
 * Created by Quinn Freas on 10/26/2016.
 *
 * Used to find invoice numbers.
 */
public abstract class KeywordFinder {
    private String invoice = null;

    void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getInvoice() {
        return invoice;
    }

    public KeywordFinder(){}


    public void processText(String[] s, int index) {
        int current = index;
        boolean flag = true;
        String out = "";
        while (flag) {
//            System.out.println(s[index]);
//            flag = true;
            try {
                if (current >= s.length - 1) {
                    out = "-1";
                    flag = !flag;
                } else if (current > index + 3) {
                    out = "null";
                    flag = !flag;
                } else if (s[current].matches(".*[0-9]+.*") && s[current].length() >= 3) {
                    out = s[current];
                    flag = !flag;
                }

                if (out.equals("null") && current <= s.length) {

                    processText(s, getNextIndex(s, current));
                }
                else {
                    flag = !flag;
                    out = null;
                }
                current++;
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }

        setInvoice(out);
    }

    public String[] toArray(String s) {
        return s.split(" |\n");
    }

    public int getNextIndex(String[] words) {

        for (int i = 0; i < words.length; i++) {
            System.out.println("i @" + i + ": " + words[i]);
        }
        boolean flag = true;
        int index = 0;
        int i = index;

        while(flag) {
            System.out.println("@" + i +": does equal: " + words[i].toUpperCase().equals("INVOICE") + "; word: " + words[i]);
            if (words[i].toUpperCase().equals("INVOICE") || words[i].toUpperCase().matches(".*INVOICE.*")) {
                flag = !flag;
                index = i;
            }

            if (i == words.length -1) {
                flag = !flag;
            }

            else
                index = getNextIndex(words, index);

            i++;
        }
        return index;
    }

    public int getNextIndex(String[] words, int index) {
        boolean flag = true;
        if (index != 0) {
            index += 3;
        }

        int i = index;
        while(flag) {
            if (words[i].toUpperCase().matches("INVOICE") || words[i].toUpperCase().matches(".*INVOICE.*")) {
                flag = !flag;
//                System.out.println ("word = " + words[i]);
            }

            if (i == words.length -1) {
                flag = !flag;
            }

            i++;
        }
//        System.out.println ("index = " + index);
        return index;
    }
}