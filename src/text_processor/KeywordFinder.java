package text_processor;

/**
 * Created by Quinn Freas on 10/26/2016.
 *
 * Used to find invoice numbers.
 */
public abstract class KeywordFinder {
    private static String invoice = null;

    public void run(String name) {
        String[] wordArr = toArray(name);
        processText(wordArr, getNextIndex(wordArr));
    }

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
            if (++current >= s.length - 1) {
                out = "-1";
                flag = !flag;
            }
            else if (current > index + 3) {
                out = "null";
                flag = !flag;
            }
            else if (s[current].matches("[0-9]+") && s[current].length() >= 3) {
                out = s[current];
                flag = !flag;
            }
//            System.out.println("Current: " + s[current]);
            current++;
        }

        setInvoice(out);
    }

    public String[] toArray(String s) {
        return s.split(" ");
    }

    public int getNextIndex(String[] words) {

      /*  for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }*/
        boolean flag = true;
        int index = 0;
        int i = index;


        while(flag) {
            if (words[i].toUpperCase().equals("INVOICE")) {
                flag = !flag;
                index = i;
//                System.out.println ("word = " + words[i]);
            }
//            else getNextIndex(words, index);

            if (i == words.length -1) {
                flag = !flag;
            }

            i++;
        }
//        System.out.println ("index = " + index);
        return index;
    }

    public int getNextIndex(String[] words, int index) {
        boolean flag = true;
        int i = index;
        if (index != 0) {
            index += 3;
            i += 3;
        }
        while(flag) {
            if (words[i].toUpperCase().matches("INVOICE") && words[i].toUpperCase().matches(".*INVOICE.*")) {
                flag = !flag;
                index = i;
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