/*
 * Copyright (C) 2014 UndeadScythes <udscythes@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.undeadscythes.pgpips;

import com.undeadscythes.jogger.Jog;
import com.undeadscythes.jogger.Output;
import com.undeadscythes.superscribe.Scribe;
import java.io.File;
import java.io.IOException;


public class Pips {
    private static final String CIPHERTEXT = "ciphertext.asc";
    private static final String PLAINTEXT = "plaintext.asc";

    private static final File CIPHERTEXT_FILE = new File(CIPHERTEXT);
    private static final File PLAINTEXT_FILE = new File(PLAINTEXT);
    
    private static String passphrase = "passphrase";
    
    public static void loadSettings(File settingsFile) {
        passphrase = "something"; // TODO: Implement this load settings do-hickey.
    }
    
    public static Result decrypt(String ciphertext) throws IOException {
        Scribe.write(CIPHERTEXT_FILE, ciphertext);
        Output output = Jog.execute("gpg --batch --passphrase " + passphrase + " --output " + PLAINTEXT + " --decrypt " + CIPHERTEXT);
        CIPHERTEXT_FILE.delete();
        return new Result(Scribe.read(PLAINTEXT_FILE), output.getError());
    }

    private Pips() {}
}
