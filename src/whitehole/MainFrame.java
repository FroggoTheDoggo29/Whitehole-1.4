package whitehole;

import java.awt.*;
import java.util.prefs.Preferences;
import javax.swing.*;
import java.io.*;
import whitehole.fileio.*;

public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btnOpenGame = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnAbout = new javax.swing.JButton();
        btnBcsvEditor = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        GalaxyList = new javax.swing.JList();
        lbStatusBar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btnOpenGame.setText("Select game folder");
        btnOpenGame.setFocusable(false);
        btnOpenGame.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOpenGame.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnOpenGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenGameActionPerformed(evt);
            }
        });
        jToolBar1.add(btnOpenGame);
        jToolBar1.add(jSeparator1);

        btnAbout.setText("About...");
        btnAbout.setFocusable(false);
        btnAbout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAbout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAboutActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAbout);

        btnBcsvEditor.setText("BCSV editor");
        btnBcsvEditor.setFocusable(false);
        btnBcsvEditor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBcsvEditor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBcsvEditor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBcsvEditorActionPerformed(evt);
            }
        });
        jToolBar1.add(btnBcsvEditor);

        jScrollPane1.setViewportView(GalaxyList);

        lbStatusBar.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
            .addComponent(lbStatusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbStatusBar, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenGameActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setDialogTitle("Open a game archive");
        String lastdir = Preferences.userRoot().get("lastGameDir", null);
        if (lastdir != null) fc.setSelectedFile(new File(lastdir));
        fc.showOpenDialog(this);
        
        String seldir = fc.getSelectedFile().getPath();
        Preferences.userRoot().put("lastGameDir", seldir);
        
        try
        {
            FilesystemBase testfs = new ExternalFilesystem(seldir);
            /*FilesystemBase rarctest = new RarcFilesystem(testfs.OpenFile("/ObjectData/HeavenlyBeachPlanet.arc"));
            
            String[] files = rarctest.GetFiles("/HeavenlyBeachPlanet");
            System.out.println(files.length);
            for (String file : files)
                JOptionPane.showMessageDialog(null, file);*/
            //Bcsv loltest = new Bcsv(testfs.OpenFile("/test.bcsv"));
            //loltest.Entries.get(0).put("ScenarioNo", 0x1337);
            //loltest.Save();
        }
        catch (IOException ex)
        {
            setTitle("test failed");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        lbStatusBar.setText("Game directory successfully opened");
    }//GEN-LAST:event_btnOpenGameActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowOpened
    {//GEN-HEADEREND:event_formWindowOpened
        this.setTitle(Whitehole.FullName);
        this.setIconImage(Toolkit.getDefaultToolkit().createImage(Whitehole.class.getResource("/Resources/icon.png")));
        lbStatusBar.setText("Ready");
        
        // TODO make this optional
        lbStatusBar.setText("Checking for object database updates...");
        ObjectDBUpdater updater = new ObjectDBUpdater(lbStatusBar);
        updater.start();
    }//GEN-LAST:event_formWindowOpened

    private void btnAboutActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAboutActionPerformed
    {//GEN-HEADEREND:event_btnAboutActionPerformed
        String msg =
            Whitehole.Name + " " + Whitehole.Version + "\n" +
            "\n" +
            "A level editor for Super Mario Galaxy 1 and 2\n" +
            "\n" +
            Whitehole.Name + " is free software, and shouldn't be provided as\n" +
            "a part of a paid software package\n" + 
            "\n" +
            "Main coding: Mega-Mario\n" +
            "Credits: Phantom Wings, Treeki, yaz0r, thakis, groepaz/hitmen\n" + 
            "\n" + 
            "See " + Whitehole.WebsiteURL + " for more details.\n";

        if (Whitehole.Version.toLowerCase().contains("private"))
            msg += "\nThis is a private beta version. Leak it out and this'll be the last one you get.\n";
        else if (Whitehole.Version.toLowerCase().contains("beta"))
            msg += "\nThis is a beta version so don't expect full stability.\n";
        
        JOptionPane.showMessageDialog(this, msg, "About " + Whitehole.Name + "...", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnAboutActionPerformed

    private void btnBcsvEditorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnBcsvEditorActionPerformed
    {//GEN-HEADEREND:event_btnBcsvEditorActionPerformed
        new BcsvEditorForm().setVisible(true);
    }//GEN-LAST:event_btnBcsvEditorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList GalaxyList;
    private javax.swing.JButton btnAbout;
    private javax.swing.JButton btnBcsvEditor;
    private javax.swing.JButton btnOpenGame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbStatusBar;
    // End of variables declaration//GEN-END:variables
}
