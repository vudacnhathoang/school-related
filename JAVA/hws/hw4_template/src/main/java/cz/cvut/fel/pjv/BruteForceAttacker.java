package cz.cvut.fel.pjv;


public class BruteForceAttacker extends Thief {
    boolean opened = false;


    public boolean tryrecursively(char[] charactersArray, char[] passwordString, int index) {
        if (index >= passwordString.length ) {
            return false;
        }
        else{
            for (int i = 0; i < charactersArray.length ; i++)
            {
                passwordString[index] = charactersArray[i];

                if (tryOpen(passwordString)) {
                    opened = true;
                    return true;
                }
                if(tryrecursively(charactersArray, passwordString, index + 1))
                {
                    opened = true;
                    return true;
                }

            }

        }
        return false;
    }

    public void breakPassword(int sizeOfPassword) {
        char[] password = new char[sizeOfPassword];
        char[] characters = getCharacters();
        if (tryOpen(password)) {
            opened = true;
            return;
        }
        tryrecursively(characters, password, 0);
    }
}
