package units;

/* Singleton qui contiendra les coefficients de dégâts entre les unités
 * ainsi qu'une méthode que les armées appeleront pour appliquer ces coefficients
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
