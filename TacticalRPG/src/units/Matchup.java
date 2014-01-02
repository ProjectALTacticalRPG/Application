package units;

/* Singleton qui contiendra les coefficients de d�g�ts entre les unit�s
 * ainsi qu'une m�thode que les arm�es appeleront pour appliquer ces coefficients
 */

final class Matchup {

	 private static volatile Matchup instance = null;
	 
     private Matchup() {
         super();
     }
 
     public final static Matchup getInstance() {
         if (Matchup.instance == null) {
            synchronized(Matchup.class) {
              if (Matchup.instance == null) {
                Matchup.instance = new Matchup();
              }
            }
         }
         return Matchup.instance;
     }
     
     public int transformDamage(int dmg, String attacker, String defender){
    	 //TODO
    	 return 0;
     }
}
