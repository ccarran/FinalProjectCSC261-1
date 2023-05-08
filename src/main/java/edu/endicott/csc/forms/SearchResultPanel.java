/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.endicott.csc.forms;

import edu.endicott.csc.finalproject.Controller;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author cesar
 */
public class SearchResultPanel extends javax.swing.JPanel {

    /**
     * Creates new form SearchResultPanel
     */
    public SearchResultPanel() {
        initComponents();
    }

    /**
     *
     * @param title
     */
    public void setResultTitle(String title) {
        lblResultTitle.setText(title);
    }

    /**
     *
     * @param url
     */
    public void setResultImage(URL url) {
//        if (url == null) {
//            lblResultTitle.setIcon(null);
//        }
//        
//        else {
//            try {
//                BufferedImage buffImage = ImageIO.read(url);
//                ImageIcon icon = new ImageIcon(buffImage);
//                Image image = icon.getImage();
//                Image resizedImage = image.getScaledInstance(
//                        186,106, java.awt.Image.SCALE_SMOOTH);
//
//                lblResultTitle.setIcon(new ImageIcon(resizedImage));
//
//            } catch (MalformedURLException ex) {
//                Logger.getLogger(EntryPanel.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(EntryPanel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblResultTitle = new javax.swing.JLabel();
        lblResultImage = new javax.swing.JLabel();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        lblResultTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblResultTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblResultTitleMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblResultTitleMouseExited(evt);
            }
        });

        lblResultImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResultImage.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblResultImage, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblResultTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 1038, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblResultImage, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(lblResultTitle)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblResultTitleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblResultTitleMouseEntered
        lblResultTitle.setForeground(new Color(10, 10, 255));
    }//GEN-LAST:event_lblResultTitleMouseEntered

    private void lblResultTitleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblResultTitleMouseExited
        lblResultTitle.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblResultTitleMouseExited

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblResultImage;
    private javax.swing.JLabel lblResultTitle;
    // End of variables declaration//GEN-END:variables
}