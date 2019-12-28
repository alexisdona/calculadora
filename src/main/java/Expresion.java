public class Expresion {
    private String expresionMatematica;
    public Expresion(String expresion) {
        this.expresionMatematica = expresion;
    }

    public static boolean esNumerico(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }

    public void getItemByItem() {
         for (int i=0;i<expresionMatematica.length();i++){
             System.out.println(expresionMatematica.charAt(i));
         }

    }
}
