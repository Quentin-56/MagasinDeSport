package controlleur;

public class BoiteAOutil {



        /**
         * @brief appelle estUnNombreValable et verifie si il ny a pas de point => est un entier
         * @param saisieUtilisateur la chaine en entrée
         * @return true si saisieUtilisateur est un nombre entier positif et false sinon
         */
        public static boolean estUnNombreValableEntier(String saisieUtilisateur)
        {
            if(estUnNombreValable(saisieUtilisateur) == false)
            {
                return false;
            }

            float res = Float.valueOf(saisieUtilisateur);
            int i;
            i = (int)res;

            //cas entier
            if (i == res)
            {
                return true;
            }
            //cas non entier
            else
            {
                return false;
            }
        }

        /**
         * @brief verifie si cest un nombre et quil est positif
         * @param saisieUtilisateur la chaine en entree
         * @return true si saisieUtilisateur est un nombre positif
         */
        public static boolean estUnNombreValable(String saisieUtilisateur)
        {
            float res;

            //test si saisieUtilisateur est un nombre
            try
            {
                res = Float.valueOf(saisieUtilisateur);
            }
            catch(java.lang.NumberFormatException ex)
            {
                return false;
            }

            //test si nombre positif
            if(res >= 0)
            {
                return true;
            }
            else
            {
                return false;
            }

        }



        /**
         * Enlever les espaces avant le 1er caractere et apres le dernier caractere
         * @param saisieUtilisateur chaine a analyser
         * @return chaine nettoye
         */
        public static String supprimerEspace(String saisieUtilisateur)
        {
            String newStr ="";
            int compteur = 0;
            int compteur2 =saisieUtilisateur.length()-1;

            if(saisieUtilisateur.isEmpty()){
                return "";
            }

            while(saisieUtilisateur.charAt(compteur) == ' ' && compteur<saisieUtilisateur.length()-1){
                compteur++;

            }
            while(saisieUtilisateur.charAt(compteur2)==' '&& compteur2>0){
                compteur2--;

            }
            for(int i = compteur; i<=compteur2; i++)
            {
                newStr += saisieUtilisateur.charAt(i);

            }
            return newStr;
        }

        /**
         * @brief put a upper letter for the first letter of the saisieUtilisateur and lower letter for other letters
         * @param saisieUtilisateur the String to convert
         */
        public static String formaterCasse(String saisieUtilisateur)
        {
            String res = "";

            //cas ou la chaine en entree a une longueur superieure a 1
            if (saisieUtilisateur.length() > 1)
            {
                saisieUtilisateur = saisieUtilisateur.substring(0, 1).toUpperCase() + saisieUtilisateur.substring(1).toLowerCase();
                res = saisieUtilisateur;
                return res;
            }

            //cas ou chaine en entree a un seul caractere
            if (saisieUtilisateur.length() == 1)
            {
                saisieUtilisateur = saisieUtilisateur.substring(0, 1).toUpperCase();
                res = saisieUtilisateur;
                return res;
            }

            //cas ou la chaine en entree est une chaine vide
            return res;
        }

    /**
     *
     * @param input le mot de passe en entree
     * @return true si le mot de passe est correct, false sinon
     */
    public static boolean checkString(String input) {
        char currentCharacter;
        boolean numberPresent = false;
        boolean upperCasePresent = false;
        boolean lowerCasePresent = false;

        for (int i = 0; i < input.length(); i++) {
            currentCharacter = input.charAt(i);
            if (Character.isDigit(currentCharacter)) {
                numberPresent = true;
            } else if (Character.isUpperCase(currentCharacter)) {
                upperCasePresent = true;
            } else if (Character.isLowerCase(currentCharacter)) {
                lowerCasePresent = true;
            }
        }

        return
                numberPresent && upperCasePresent && lowerCasePresent;
    }

    /**
     *
     * @param input la chaine de caractere en entree
     * @return true si la input contient un caractere special, false sinon
     */
    public static boolean checkCaractereSpeciaux(String input) {
        String specialChars = "~`[email protected]#$%^&** ()-__=+\\|[{]};:'\",<.>/?";
        char currentCharacter;
        boolean specialCharacterPresent = false;

        for (int i = 0; i < input.length(); i++) {
            currentCharacter = input.charAt(i);
            if (specialChars.contains(String.valueOf(currentCharacter))) {
                specialCharacterPresent = true;
            }
        }

        return specialCharacterPresent;
    }


}
