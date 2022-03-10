/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author DEBOBRATO_BISWAS
 */
public class MyFileFilter extends FileFilter {

    public MyFileFilter(String java, String java_Source_Filesjava) {
    }

    @Override
    public boolean accept(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescription() {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
